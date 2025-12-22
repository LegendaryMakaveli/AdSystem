package com.adSystems.validation;

import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.exception.ValidateListingException;

public class Validations {


    public static void validateCreateListing(ListingRequests request) {
        if(request.getTitle() == null || request.getTitle().trim().isEmpty())throw new ValidateListingException("Listing title cannot be empty");
        if(request.getDescription() == null || request.getDescription().trim().isEmpty())throw new ValidateListingException("Listing description cannot be empty");
        if(request.getPrice() <= 0)throw new ValidateListingException("Listing price cannot be less than or equal to zero");
        if(request.getCityId() == null || request.getCityId().trim().isEmpty())throw new ValidateListingException("Listing city cannot be empty");
        if(request.getCategoryId() == null || request.getCategoryId().trim().isEmpty())throw new ValidateListingException("Listing category cannot be empty");
        if(request.getEmail() == null || request.getEmail().trim().isEmpty())throw new ValidateListingException("Listing email cannot be empty");
        if(request.getEditToken() == null || request.getEditToken().trim().isEmpty())throw new ValidateListingException("Listing edit token cannot be empty");
        String emailPatterns = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$\n";
        if(!request.getEmail().matches(emailPatterns))throw new ValidateListingException("Invalid email format");
    }
}
