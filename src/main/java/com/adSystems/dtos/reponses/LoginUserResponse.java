package com.adSystems.dtos.reponses;


import lombok.Data;

@Data
public class LoginUserResponse {
    private String token;
    private String message;
}
