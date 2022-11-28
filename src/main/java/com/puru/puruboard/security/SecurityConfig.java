package com.puru.puruboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

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
    
    // CustomAuthenticationProvider를 만들지 않고 Security에 기본으로 존재하는 AuthenticationProvider를 사용하면 Rememberme 인증에는 문제 X.
    // 단 sec:authentication="principal.nickname" 사용 불가
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
            .loginPage("/user/login")
            .usernameParameter("email")
            .loginProcessingUrl("/user/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
            
            .and()
            .logout()
            .logoutUrl("/user/logout")
            .logoutSuccessUrl("/")
            .deleteCookies("remember-me")
            
            // remember-me 쿠키에 대해서는 현재 CustomUserDetailsService의 loadUserByUsername 메서드의 매개변수인
            // 이메일 값이 com.puru.puruboard.domain.User@6f16fa98 등의 Object의 toString 값으로 반환되는 이슈가 있으므로
            // 추후 해결 가능할 때 재설정하도록 한다.
            .and()
            .rememberMe()
            .userDetailsService(userDetailsService)
            
            .and()
            .build();
    }
}