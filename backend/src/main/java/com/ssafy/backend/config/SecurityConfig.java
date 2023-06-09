package com.ssafy.backend.config;

import com.google.firebase.auth.FirebaseAuth;
import com.ssafy.backend.jwt.*;
import com.ssafy.backend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final FirebaseAuth firebaseAuth;

    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler,
            CustomUserDetailsService customUserDetailsService, FirebaseAuth firebaseAuth) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.firebaseAuth = firebaseAuth;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 인증, 인가 서비스가 필요하지 않은 endpoint에 사용
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/favicon.ico"
//                        "/api/v1/login",
//                        "/api/v1/user/**",
//                        "/api/v1/re-issue",
//                        "/api/v1/user-info/**",
//                        "/api/v1/social/googleSignup",
//                        "/api/v1/social/googleLogin",
//                        "/api/v1/mbti/**",
//                        "/api/v1/like/**",
//                        "/api/v1/password",
//                        "/api/v1/user-singo/**",
//                        "/api/v1/check/**",
//                        "/api/vi/matching"
                );
    }

    // http 요청으로 들어온 정보 security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 사이트간 위조 방지

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .authorizeRequests()
                .antMatchers(
                        "/api/v1/login",
                        "/api/v1/user/**",
                        "/api/v1/re-issue",
                        "/api/v1/email/**",
                        "/ws/**",
                        "/api/v1/user-info/**",
                        "/api/v1/social/googleSignup",
                        "/api/v1/social/googleLogin",
//                        "/api/v1/mbti/**",
//                        "/api/v1/like/**",
                        "/api/v1/password",
//                        "/api/v1/user-singo/**",
                        "/api/v1/check/**"

                ).permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider, firebaseAuth, customUserDetailsService));
    }
}
