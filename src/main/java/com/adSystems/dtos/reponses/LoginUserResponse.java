package com.adSystems.dtos.reponses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponse {
    private String userId;
    private String email;
    private String token;
    private String message;
    private String role;
}
