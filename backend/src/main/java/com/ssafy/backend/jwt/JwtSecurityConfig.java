package com.ssafy.backend.jwt;

import com.google.firebase.auth.FirebaseAuth;
import com.ssafy.backend.service.CustomUserDetailsService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;
    private final FirebaseAuth firebaseAuth;
    private final CustomUserDetailsService customUserDetailsService;
    public JwtSecurityConfig(TokenProvider tokenProvider, FirebaseAuth firebaseAuth, CustomUserDetailsService customUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.firebaseAuth = firebaseAuth;
        this.customUserDetailsService = customUserDetailsService;
    }

    // JwtFilter를 Security 로직에 필터로 등록
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(tokenProvider, firebaseAuth, customUserDetailsService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
