package com.adSystems.controllers;


import com.adSystems.dtos.reponses.APiResponse;
import com.adSystems.exception.ClassifiedAdSystemException;
import com.adSystems.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryControllers {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/root")
    public ResponseEntity<?> getRootCategories(){
        try{
            return new ResponseEntity<>(new APiResponse(true, categoryService.getRootCategories()), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sub/{parentId}")
    public ResponseEntity<?> getSubCategories(@PathVariable("parentId") String parentId){
        try{
            return new ResponseEntity<>(new APiResponse(true, categoryService.getSubCategories(parentId)), HttpStatus.OK);
        }catch (ClassifiedAdSystemException error){
            return new ResponseEntity<>(new APiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
