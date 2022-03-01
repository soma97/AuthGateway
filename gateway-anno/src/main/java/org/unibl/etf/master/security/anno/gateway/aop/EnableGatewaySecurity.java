package org.unibl.etf.master.security.anno.gateway.aop;

import org.springframework.context.annotation.Import;
import org.unibl.etf.master.security.anno.gateway.aop.configuration.GatewaySecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GatewaySecurityConfiguration.class)
public @interface EnableGatewaySecurity {
}