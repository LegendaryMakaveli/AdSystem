package com.adSystems.controllers;

import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/users/{id}/downgrade")
    public ResponseEntity<?> downgradeUser(@PathVariable("id") String userId){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.downgradeUser(userId)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUsers(){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.getAllUsers()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String userId){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.getUserById(userId)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String userId){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.deleteUser(userId)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllListing")
    public ResponseEntity<?> getAllListings(){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.getAllListings()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteListing/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable("id") String listingId){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.deleteListing(listingId)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getListingByUserId/{id}")
    public ResponseEntity<?> getListingByUserId(@PathVariable("id") String userId){
        try{
            return new ResponseEntity<>(new APiResponse(true, adminService.getListingsByUserId(userId)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
