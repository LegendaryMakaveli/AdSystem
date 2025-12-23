package com.adSystems.dtos.requests;


import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String Address;
    private String email;
    private String password;
}
