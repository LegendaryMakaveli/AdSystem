package com.adSystems.controllers;


import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.dtos.requests.ContactMessageRequest;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactMessageService contactMessageService;


    @PostMapping("/contactSeller/{listingId}")
    public ResponseEntity<?> contacrSeller(@PathVariable String listingId, @RequestBody ContactMessageRequest request) {
        try{
            return new ResponseEntity<>(new APiResponse(true, contactMessageService.sendContactMessage(listingId, request)), HttpStatus.CREATED);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
