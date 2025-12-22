package com.adSystems.services;

import com.adSystems.datas.models.Listing;
import com.adSystems.dtos.reponses.ListingResponse;
import com.adSystems.dtos.reponses.UpdateListingResponse;
import com.adSystems.dtos.requests.GetByCityAndCategoryRequest;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.UpdateListingRequest;

import java.util.List;

public interface ListingServices {
    ListingResponse createListing(ListingRequests request);
    Listing getById(String id);
    List<Listing> getByCity(String cityId);
    List<Listing> getByCityAndCategory(String cityId, String categoryId);
    UpdateListingResponse updateListing(String id, String token, UpdateListingRequest request);
    ListingResponse deleteListing(String id, String token);
    void expireListing();
}
