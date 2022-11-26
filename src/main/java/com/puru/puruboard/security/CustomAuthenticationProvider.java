package com.puru.puruboard.security;

import com.puru.puruboard.security.service.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    private UserDetailsService userDetailsService;
    
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(email);
        
        if (!passwordEncoder.matches(password, userContext.getPassword())) { // 이러면 안되나?
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            userContext.getUser(), null, userContext.getAuthorities());
        
        return token;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
