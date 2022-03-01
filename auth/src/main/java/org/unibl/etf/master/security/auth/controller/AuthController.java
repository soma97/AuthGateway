package org.unibl.etf.master.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.master.security.auth.exception.UnauthorizedException;
import org.unibl.etf.master.security.auth.model.response.BaseResponse;
import org.unibl.etf.master.security.auth.service.AuthenticationService;

@RestController
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> user() throws UnauthorizedException {
        return ResponseEntity.ok(authenticationService
                .getUserDetails(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()));
    }
}