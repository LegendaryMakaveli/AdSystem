package com.adSystems.services;

import com.adSystems.dtos.reponses.LoginUserResponse;
import com.adSystems.dtos.reponses.RegisterUserResponse;
import com.adSystems.dtos.requests.LoginUserRequest;
import com.adSystems.dtos.requests.RegisterUserRequest;

public interface AuthService {
    RegisterUserResponse register(RegisterUserRequest request);
    LoginUserResponse login(LoginUserRequest request);
}
