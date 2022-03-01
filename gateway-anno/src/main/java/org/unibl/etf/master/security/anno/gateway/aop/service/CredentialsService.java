package org.unibl.etf.master.security.anno.gateway.aop.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;
import org.unibl.etf.master.security.anno.gateway.aop.configuration.Constants;
import org.unibl.etf.master.security.anno.gateway.aop.model.UserDetailsResponse;
import javax.servlet.http.Cookie;

@Service
public class CredentialsService {
    @Value("${gateway.security.api}")
    private String gatewaySecurityAPI;

    @Getter
    @Value("${gateway.security.disable-ssl-checks}")
    private boolean sslChecksDisabled;

    private final RestTemplate restTemplate;

    public CredentialsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cacheable(value = "cookies", key = "#cookie.value", unless="#result == null")
    public UserDetailsResponse getUserDetails(Cookie cookie) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cookie", cookie.getName() + "=" + cookie.getValue());
            ResponseEntity<UserDetailsResponse> userDetails = restTemplate.exchange(gatewaySecurityAPI + Constants.USER_ENDPOINT, HttpMethod.GET,
                    new HttpEntity<>(headers), UserDetailsResponse.class);

            if(userDetails.getStatusCode() == HttpStatus.OK && userDetails.getBody() != null)
                return userDetails.getBody();
        } catch (RestClientException restClientException) {
            if(!(restClientException instanceof UnknownContentTypeException))
                restClientException.printStackTrace();
        }
        return null;
    }
}
