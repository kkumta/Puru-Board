package com.puru.puruboard.security.service;

import com.puru.puruboard.domain.User;
import com.puru.puruboard.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
        System.out.println("email: "+email);
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("해당 email로 등록된 회원이 없습니다.");
        }
        
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.get().getRole()));
        
        UserContext userContext = new UserContext(user.get(), roles);
        
        return userContext;
    }
}