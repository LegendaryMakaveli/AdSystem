package com.adSystems.dtos.requests;


import lombok.Data;

@Data
public class UpdateListingRequest {
    private String title;
    private String category;
    private String description;
    private double price;
}
