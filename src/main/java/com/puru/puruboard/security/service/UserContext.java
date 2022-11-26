package com.puru.puruboard.security.service;

import com.puru.puruboard.domain.User;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class UserContext extends org.springframework.security.core.userdetails.User {
    
    private final User user;
    
    public UserContext(com.puru.puruboard.domain.User user,
                       Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}