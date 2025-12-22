package com.adSystems.util;

import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.ListingStatus;
import com.adSystems.dtos.reponses.ListingResponse;
import com.adSystems.dtos.reponses.UpdateListingResponse;
import com.adSystems.dtos.requests.ListingRequests;

import java.time.LocalDateTime;

public class Mapper {

    public static Listing mapToCreateAListing(ListingRequests request){
        Listing listing = new Listing();
        listing.setTitle(request.getTitle());
        listing.setDescription(request.getDescription());
        listing.setPrice(request.getPrice());
        listing.setEmail(request.getEmail());
        listing.setCityId(request.getCityId());
        listing.setCategoryId(request.getCategoryId());
        listing.setStatus(ListingStatus.ACTIVE);
        listing.setEditToken(Token.generate());
        listing.setCreatedAt(LocalDateTime.now());
        listing.setExpiresAt(LocalDateTime.now().plusDays(5));

        return listing;
    }

    public static ListingResponse mapToCreateListingResponse(){
        ListingResponse response = new ListingResponse();
        response.setMesage("Listing created successfully");

        return response;
    }

    public static UpdateListingResponse mapToUpdateListingResponse(){
        UpdateListingResponse response = new UpdateListingResponse();
        response.setMessage("Listing updated successfully");

        return response;
    }

    public static ListingResponse mapToDeleteListingResponse(){
        ListingResponse response = new ListingResponse();
        response.setMesage("Listing deleted successfully");

        return response;
    }

}
