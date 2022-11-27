package com.puru.puruboard.service;

import com.puru.puruboard.dto.CreateUserDto;
import com.puru.puruboard.dto.UserInfo;

public interface UserService {
    
    UserInfo createUser(CreateUserDto createUserDto) throws Exception;
    
    UserInfo getUserInfo(String email);
}