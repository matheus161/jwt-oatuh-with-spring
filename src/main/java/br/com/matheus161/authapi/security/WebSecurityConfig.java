package br.com.matheus161.authapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/open").permitAll()
                        .requestMatchers("/users", "POST").permitAll()
                        .requestMatchers("/login", "POST").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
