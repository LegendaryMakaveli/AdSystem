package com.adSystems.services;

import com.adSystems.datas.models.User;
import com.adSystems.datas.repositories.UserRepository;
import com.adSystems.dtos.reponses.LoginUserResponse;
import com.adSystems.dtos.reponses.RegisterUserResponse;
import com.adSystems.dtos.requests.LoginUserRequest;
import com.adSystems.dtos.requests.RegisterUserRequest;
import com.adSystems.exception.UserALreadyExistException;
import com.adSystems.exception.ValidationException;
import com.adSystems.security.JwtService;
import com.adSystems.util.PasswordHash;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.adSystems.util.Mapper.*;


@AllArgsConstructor
@Service
public class AuthServiceImplemetation implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;


    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new UserALreadyExistException("User already exixt!");});
        User user = mapToRegisterUserRequest(request);
        user.setPassword(PasswordHash.hash(request.getPassword()));

        User savedUser = userRepository.save(user);
        return mapToRegisterUserResponse(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ValidationException("Invalid Credentials"));
        if(!PasswordHash.checkPassword(request.getPassword(), user.getPassword())) throw new ValidationException("Invalid Credentials");
        String token = jwtService.generateLoginToken(user);

        LoginUserResponse response = mapToLoginUserResponse(token);

        return response;
    }
}
