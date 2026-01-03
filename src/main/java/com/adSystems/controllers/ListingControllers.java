package com.adSystems.controllers;

import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.UpdateListingRequest;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.ListingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/listings")
public class ListingControllers {
    @Autowired
    private ListingServices listingServices;

    @PostMapping("/create")
    public ResponseEntity<?> createAnAd(@RequestBody ListingRequests request){
        try {
            return new ResponseEntity<>(new APiResponse(true, listingServices.createListing(request)),HttpStatus.CREATED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/addImages")
    public ResponseEntity<?> addImage(@PathVariable("id") String id, @RequestParam("token") String token, @RequestParam("file") MultipartFile file){
        try {
            return new ResponseEntity<>(new APiResponse(true, listingServices.addImage(id, token, file)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllListing(){
        try{
            return new ResponseEntity<>(new APiResponse(true, listingServices.getAllListings()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false,error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnAdById(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(new APiResponse(true, listingServices.getById(id)),HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateListing(@PathVariable("id")String id, @RequestParam("token")String token, @RequestBody UpdateListingRequest request ){
        try{
            return new ResponseEntity<>(new APiResponse(true, listingServices.updateListing(id,token,request)), HttpStatus.ACCEPTED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable("id")String id, @RequestParam("token")String token){
        try{
            return new ResponseEntity<>(new APiResponse(true, listingServices.deleteListing(id,token)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
