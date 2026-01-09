package com.adSystems.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Listings")

public class Listing {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private String phone;
    private String category;
    private String location;
    private double price;
    private String email;
    private List<String> images = new ArrayList<>();
    private ListingStatus status;
    @Indexed
    private String editToken;
    private String createdAt;
    @Indexed
    private LocalDateTime expiresAt;
}
