package com.adSystems.dtos.requests;

import com.adSystems.datas.models.ListingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListingRequests {
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
