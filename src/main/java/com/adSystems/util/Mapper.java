package com.adSystems.util;

import com.adSystems.datas.models.ContactMessage;
import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.ListingStatus;
import com.adSystems.dtos.reponses.ListingResponse;
import com.adSystems.dtos.reponses.UpdateListingResponse;
import com.adSystems.dtos.requests.ContactMessageRequest;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.reponses.ContactMessageResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static ContactMessage mapToContactSeller(ContactMessageRequest request){
        ContactMessage message = new ContactMessage();
        message.setSenderEmail(request.getEmail());
        message.setMessage(request.getMessage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);
        message.setSendAt(LocalDateTime.parse(formattedDate));

        return message;

    }

    public static ContactMessageResponse mapToSendMessageResponse(){
        ContactMessageResponse response = new ContactMessageResponse();
        response.setMessage("Message sent successfully");
        response.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        return response;
    }

}
