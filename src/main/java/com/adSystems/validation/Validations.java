package com.adSystems.validation;

import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.PasswordResetRequest;
import com.adSystems.dtos.requests.RegisterUserRequest;
import com.adSystems.exception.ValidateListingException;
import com.adSystems.exception.ValidationException;

public class Validations {


    public static void validateCreateListing(ListingRequests request) {
        if(request.getTitle() == null || request.getTitle().trim().isEmpty())throw new ValidateListingException("Listing title cannot be empty");
        if(request.getDescription() == null || request.getDescription().trim().isEmpty())throw new ValidateListingException("Listing description cannot be empty");
        if(request.getPrice() <= 0)throw new ValidateListingException("Listing price cannot be less than or equal to zero");
        if(request.getContactEmail() == null || request.getContactEmail().trim().isEmpty())throw new ValidateListingException("Listing email cannot be empty");
        String emailPatterns = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        if(!request.getContactEmail().matches(emailPatterns))throw new ValidateListingException("Invalid email format");
    }

    public static void signUpValidation(RegisterUserRequest request) {
        if(request.getFirstName() == null || request.getFirstName().trim().isEmpty())throw new ValidationException("First name cannot be empty");
        if(request.getLastName() == null || request.getLastName().trim().isEmpty())throw new ValidationException("Last name cannot be empty");
        if(request.getEmail() == null || request.getEmail().trim().isEmpty())throw new ValidationException("Email cannot be empty");
        if(request.getAddress() == null || request.getAddress().trim().isEmpty())throw new ValidationException("Address cannot be empty");
        String emailPatterns = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        if(!request.getEmail().matches(emailPatterns))throw new ValidateListingException("Invalid email format");
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$";
        if(!request.getPassword().matches(passwordPattern))throw new ValidationException("Incorrect password!");
        if(request.getPassword() == null || request.getPassword().trim().isEmpty())throw new ValidationException("Password cannot be empty");
    }

    public static void validateResetPassword(PasswordResetRequest request) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$";
        if(!request.getNewPassword().matches(passwordPattern))throw new ValidationException("Invalid password format");
        if(request.getEmail() == null || request.getEmail().trim().isEmpty())throw new ValidationException("Email cannot be empty");
        if(request.getNewPassword() == null || request.getNewPassword().trim().isEmpty())throw new ValidationException("New password cannot be empty");
    }
}
