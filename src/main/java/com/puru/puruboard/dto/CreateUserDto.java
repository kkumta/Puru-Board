package com.puru.puruboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {
    
    @NotNull
    private String email;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    private String password;
    
    @NotNull
    private String nickname;
}