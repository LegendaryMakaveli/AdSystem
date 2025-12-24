package com.adSystems.dtos.requests;

import com.adSystems.datas.models.ListingStatus;
import lombok.Data;


@Data
public class ListingRequests {
    private String title;
    private String description;
    private double price;
    private String email;
    private ListingStatus status;
}
