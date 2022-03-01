package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                //.antMatchers(HttpMethod.POST).hasRole(Role.ADMIN)
                //.antMatchers(HttpMethod.GET).hasRole(Role.REGULAR_USER)
                .antMatchers("/test").permitAll() // permit this endpoint (gateway security is responsible for this endpoint auth)
                .anyRequest().authenticated()
                .and().httpBasic();

        /* h2 console is opened so you could add manually:
            insert into demo_db.user(name,password,role) values('soma','$2a$10$MDK5DqgKo3zV5J4o8a9g0eR/EbcTxeQO.7dUVtxFkATV7Fuef42fG','REGULAR');
            insert into demo_db.user(name,password,role) values('somaa','$2a$10$MDK5DqgKo3zV5J4o8a9g0eR/EbcTxeQO.7dUVtxFkATV7Fuef42fG','ADMIN');

            Credentials are REGULAR USER - username:soma, password:soma
                            ADMIN - username: somaa, password:soma
        */

        // Disable csrf support and disable X-Frame-Options header in order to be able to show h2-console
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}

