package com.adSystems.dtos.requests;


import lombok.Data;

@Data
public class ContactMessageRequest {
    private String listingId;
    private String email;
    private String message;
}
