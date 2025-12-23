package com.adSystems.controllers;

import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/users/{id}/upgrade")
    public ResponseEntity<?> upgradeUser(@PathVariable("id") String userId){
        try {
            return new ResponseEntity<>(new APiResponse(true, adminService.upgradeUserToPremium(userId)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
