package com.adSystems.controllers;

import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.dtos.requests.LoginUserRequest;
import com.adSystems.dtos.requests.RegisterUserRequest;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllers {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterUserRequest request){
        try {
            return new ResponseEntity<>(new APiResponse(true, authService.register(request)), HttpStatus.CREATED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request){
        try {
            return new ResponseEntity<>(new APiResponse(true, authService.login(request)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
