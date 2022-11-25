package com.puru.puruboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final UserDetailsService userDetailsService;
    
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/user")
            .permitAll() // home,login 요청: 모두에게 허용
            .antMatchers("/user/info").hasRole("USER") // my-page에 관한 모든 요청: USER만 허용
            .antMatchers(HttpMethod.GET, "/posts/**").permitAll() // posts에 관한 get 요청: 모두에게 허용
            .antMatchers("/posts/**")
            .hasRole("USER") // posts에 관한 모든 요청: USER만 허용
            .anyRequest().authenticated()
            
            .and()
            .formLogin()
            .defaultSuccessUrl("/", true)
            .permitAll()
            
            .and()
            .logout()
            .deleteCookies("remember-me")
            
            .and()
            .rememberMe()
            .userDetailsService(userDetailsService)
            
            .and()
            .build();
    }
}