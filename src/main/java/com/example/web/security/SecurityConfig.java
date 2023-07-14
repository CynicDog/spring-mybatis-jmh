package com.example.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/").permitAll()
//                    .antMatchers("/emp/login", "/emp/logout", "/emp/add").permitAll()
//                    .anyRequest().authenticated()
//                .and()
                .formLogin()
                    .loginPage("/emp/login")            // GET method
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/emp/login")   // POST method
                    .defaultSuccessUrl("/")
                    .failureUrl("/emp/login?error=fail")
                .and()
                .logout()
                    .logoutUrl("/emp/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
//                .exceptionHandling().authenticationEntryPoint((request, response, authentication) -> {
//                    response.sendRedirect("/emp/login?error=required");
//                })
//                .and()
//                .exceptionHandling().accessDeniedHandler((request, response, exception) -> {
//                    response.sendRedirect("/emp/login?error=denied");
//                })
//                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
