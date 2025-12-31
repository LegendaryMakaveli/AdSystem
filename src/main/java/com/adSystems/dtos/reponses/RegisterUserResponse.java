package com.adSystems.dtos.reponses;


import lombok.Data;

@Data
public class RegisterUserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String Address;
    private String email;
    private String message;
    private String dateOfRegistration;
}
