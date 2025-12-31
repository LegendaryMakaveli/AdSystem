package com.adSystems.dtos.reponses;


import lombok.Data;

@Data
public class ListingResponse {
    private  String message;
    private String createdAt;
    private String listingId;
    private String userId;
    private String editToken;
}
