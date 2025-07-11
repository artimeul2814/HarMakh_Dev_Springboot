package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

public class JWTSecurity {
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/files/**").authenticated()
                            .anyRequest().permitAll()
                    )
                    .oauth2ResourceServer(oauth2 ->
                            oauth2.jwt(jwt -> jwt.decoder(jwtDecoder))
                    );

            return http.build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
            return JwtDecoders.fromIssuerLocation("http://keycloak:8080/realms/HarMakh-Dev");
        }
    }
}
