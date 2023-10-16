package com.megazone.act.cms.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
            .build();
    }

    @Bean
    public UserDetailsService memberFixtures() {
        UserDetails user = createMember("user", "user", "USER");
        UserDetails admin = createMember("admin", "admin", "ADMIN");
        return new InMemoryUserDetailsManager(user, admin);
    }

    private UserDetails createMember(String username, String password, String... roles) {
        return User.builder()
            .username(username)
            .password("{noop}" + password)
            .roles(roles)
            .build();
    }
}
