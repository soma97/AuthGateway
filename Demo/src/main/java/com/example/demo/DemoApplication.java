package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.unibl.etf.master.security.anno.gateway.aop.EnableGatewaySecurity;

@SpringBootApplication
@EnableGatewaySecurity
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
