package com.adSystems.controllers;


import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/allCities")
    public ResponseEntity<?> getAllCities(){
        try {
            return new ResponseEntity<>(new APiResponse(true, cityService.getAllCities()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
