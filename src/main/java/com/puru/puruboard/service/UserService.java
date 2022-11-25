package com.puru.puruboard.service;

import com.puru.puruboard.dto.CreateUserDto;
import com.puru.puruboard.dto.CreateUserResult;
import com.puru.puruboard.dto.UserInfo;

public interface UserService {
    
    public CreateUserResult createUser(CreateUserDto createUserDto) throws Exception;
    
    public UserInfo getUserInfo(String email);
}