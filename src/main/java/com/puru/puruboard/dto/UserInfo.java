package com.puru.puruboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfo {
    
    private String email;
    private String nickname;
    private String role;
    
    @Builder
    public UserInfo(String email, String nickname, String role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }
}