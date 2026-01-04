package com.adSystems.dtos.requests;


import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String newPassword;
}
