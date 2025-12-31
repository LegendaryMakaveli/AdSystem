package com.adSystems.dtos.requests;

import com.adSystems.datas.models.ListingStatus;
import lombok.Data;


@Data
public class ListingRequests {
    private String id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String location;
    private String contactEmail;
    private String contactPhone;
    private ListingStatus status;
}
