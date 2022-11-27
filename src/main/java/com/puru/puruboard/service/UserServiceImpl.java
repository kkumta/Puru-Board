package com.puru.puruboard.service;

import com.puru.puruboard.domain.User;
import com.puru.puruboard.domain.UserRepository;
import com.puru.puruboard.dto.CreateUserDto;
import com.puru.puruboard.dto.UserInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    
    private final PasswordEncoder passwordEncoder;
    
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public UserInfo createUser(CreateUserDto signUpDto) throws Exception {
        
        User user = User.builder()
            .email(signUpDto.getEmail())
            .password(passwordEncoder.encode(signUpDto.getPassword()))
            .nickname(signUpDto.getNickname())
            .role("ROLE_USER")
            .build();
        
        if (!validateEmail(signUpDto.getEmail())) {
            throw new Exception("이메일 중복");
        }
    
        if (!validateNickName(signUpDto.getNickname())) {
            throw new Exception("닉네임 중복");
        }
        
        User result = userRepository.save(user);
        
        return UserInfo.builder()
            .email(result.getEmail())
            .nickname(result.getNickname())
            .role(result.getRole())
            .build();
    }
    
    private Boolean validateNickName(String nickname) {
        
        if (!userRepository.findByNickname(nickname).isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    private Boolean validateEmail(String email) {
        
        if (!userRepository.findByEmail(email).isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public UserInfo getUserInfo(String email) {
        
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        
        return UserInfo.builder()
            .email(user.get().getEmail())
            .nickname(user.get().getNickname())
            .role(user.get().getRole())
            .build();
    }
}