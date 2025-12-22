package com.adSystems.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Listings")
public class Listing {
    @Id
    private String id;
    private String title;
    private String description;
    private double price;
    private String email;
    private String cityId;
    private String categoryId;
    private ListingStatus status;
    private String editToken;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
