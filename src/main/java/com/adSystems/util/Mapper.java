package com.adSystems.util;

import com.adSystems.datas.models.*;
import com.adSystems.dtos.reponses.*;
import com.adSystems.dtos.requests.ContactMessageRequest;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.RegisterUserRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {

    public static Listing mapToCreateAListing(ListingRequests request){
        Listing listing = new Listing();
        listing.setTitle(request.getTitle());
        listing.setDescription(request.getDescription());
        listing.setPrice(request.getPrice());
        listing.setCategory(request.getCategory());
        listing.setLocation(request.getLocation());
        listing.setEmail(request.getContactEmail());
        listing.setPhone(request.getContactPhone());
        listing.setStatus(ListingStatus.ACTIVE);
        listing.setEditToken(Token.generate());
        listing.setCreatedAt(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm ss").format(LocalDateTime.now()));
        listing.setExpiresAt(LocalDateTime.now().plusDays(5));

        return listing;
    }

    public static ListingResponse mapToCreateListingResponse(User user, Listing listing){
        ListingResponse response = new ListingResponse();
        response.setListingId(listing.getId());
        response.setMessage("Listing created successfully");
        response.setEditToken(listing.getEditToken());
        response.setUserId(user.getId());
        response.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ss").format(LocalDateTime.now()));

        return response;
    }

    public static UpdateListingResponse mapToUpdateListingResponse(){
        UpdateListingResponse response = new UpdateListingResponse();
        response.setMessage("Listing updated successfully");

        return response;
    }

    public static ListingResponse mapToDeleteListingResponse(){
        ListingResponse response = new ListingResponse();
        response.setMessage("Listing deleted successfully");

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

    public static User mapToRegisterUserRequest(RegisterUserRequest request){
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setAddress(request.getAddress());
        newUser.setEmail(request.getEmail());
        newUser.setRole(Role.USER);
        newUser.setSubscriptionPlan(SubscriptionPlan.FREE);

        return newUser;
    }

    public static RegisterUserResponse mapToRegisterUserResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setUserId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setAddress(user.getAddress());
        response.setEmail(user.getEmail());
        response.setMessage("Account created successfully");
        response.setDateOfRegistration(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        return response;
    }


}
