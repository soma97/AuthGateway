package org.unibl.etf.master.security.anno.gateway.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.unibl.etf.master.security.anno.gateway.aop.configuration.Constants;
import org.unibl.etf.master.security.anno.gateway.aop.configuration.SSLUtil;
import org.unibl.etf.master.security.anno.gateway.aop.exception.UnauthorizedException;
import org.unibl.etf.master.security.anno.gateway.aop.model.UserDetailsResponse;
import org.unibl.etf.master.security.anno.gateway.aop.service.CredentialsService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class GatewaySecurityAspect {
    private static final String AUTH_COOKIE_NAME = "JSESSIONID";
    private final CredentialsService credentialsService;

    public GatewaySecurityAspect(CredentialsService credentialsService) throws NoSuchAlgorithmException, KeyManagementException {
        this.credentialsService = credentialsService;
        if(credentialsService.isSslChecksDisabled())
            SSLUtil.turnOffSslChecking();
    }

    @Value("${gateway.security.api}")
    private String gatewaySecurityAPI;

    @Pointcut("within(@GatewaySecurity *)")
    public void beanAnnotatedWithGatewaySecurity() {}

    @Pointcut("execution(* *(..))")
    public void methodCall() {}

    @Around("(methodCall() && beanAnnotatedWithGatewaySecurity())")
    public Object annotatedBeanAction(ProceedingJoinPoint joinPoint) throws Throwable {
        return checkCookieMethod(joinPoint, null);
    }

    @Around("@annotation(gatewaySecurity)")
    public Object annotatedMethodAction(ProceedingJoinPoint joinPoint, GatewaySecurity gatewaySecurity) throws Throwable {
        return checkCookieMethod(joinPoint, gatewaySecurity);
    }

    public Object checkCookieMethod(ProceedingJoinPoint joinPoint, GatewaySecurity gatewaySecurity) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Optional<Cookie> cookie = request.getCookies() == null ? Optional.empty() : Arrays.stream(request.getCookies())
                .filter(ck -> ck.getName().equals(AUTH_COOKIE_NAME))
                .findFirst();

        UserDetailsResponse userDetailsResponse = cookie.map(credentialsService::getUserDetails).orElse(null);

        if (userDetailsResponse == null)
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getResponse().sendRedirect(gatewaySecurityAPI + Constants.LOGIN_ENDPOINT);
        else if (gatewaySecurity != null && !gatewaySecurity.role().isEmpty()
                && !gatewaySecurity.role().equals(userDetailsResponse.getRole())) {
            throw new UnauthorizedException();
        }
        return joinPoint.proceed();
    }
}