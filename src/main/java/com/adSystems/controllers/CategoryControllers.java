package com.adSystems.controllers;


import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryControllers {
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = "https://ad-system-front-end.vercel.app")
    @GetMapping("/root")
    public ResponseEntity<?> getRootCategories(){
        try{
            return new ResponseEntity<>(new APiResponse(true, categoryService.getRootCategories()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "https://ad-system-front-end.vercel.app")
    @GetMapping("/sub/{parentId}")
    public ResponseEntity<?> getSubCategories(@PathVariable("parentId") String parentId){
        try{
            return new ResponseEntity<>(new APiResponse(true, categoryService.getSubCategories(parentId)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
