package com.example.demo.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.master.security.anno.gateway.aop.GatewaySecurity;

@RestController
public class TestController {

    @GatewaySecurity(role = "admin")
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test-inv")
    public String testInv() throws InvalidArgumentException {
        if(true)
            throw new InvalidArgumentException(new String[] {"Invalid args"});
        return "test";
    }
}

