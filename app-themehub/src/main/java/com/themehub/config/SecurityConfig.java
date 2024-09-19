package com.themehub.config;

import com.themehub.constant.ThemehubConstant;
import com.themehub.util.JwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationConverter jwtAuthConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF protection is disabled
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/check/simple").permitAll()  // Public endpoint
                        .requestMatchers(ThemehubConstant.RESOURCE_GENERIC + "/**").permitAll()
                        .anyRequest().authenticated()  // All other requests require authentication
                );
        http.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter))
                );
        http.sessionManagement(sess -> sess
                        .sessionCreationPolicy(STATELESS)
                );

        return http.build();
    }
}
