package com.adSystems.services;

import com.adSystems.datas.models.*;
import com.adSystems.datas.repositories.ListingRepository;
import com.adSystems.datas.repositories.UserRepository;
import com.adSystems.exception.ListingNotFoundException;
import com.adSystems.exception.UserNotFoundException;
import com.adSystems.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListingRepository listingRepository;


    @Override
    public String upgradeUserToPremium(String userId) {
        if(userId == null || userId.trim().isEmpty()) throw new ValidationException("User ID cannot be empty");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setSubscriptionPlan(SubscriptionPlan.PREMIUM);
        userRepository.save(user);

        return "User upgraded successfully";
    }

    @Override
    public String downgradeUser(String userId) {
        if(userId == null || userId.trim().isEmpty()) throw new ValidationException("User ID cannot be empty");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setSubscriptionPlan(SubscriptionPlan.FREE);
        userRepository.save(user);

        return "User downgraded successfully";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        if(userId == null || userId.trim().isEmpty())throw new ValidationException("User ID cannot be empty");
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userRepository.findById(userId);
    }

    @Override
    public String deleteUser(String userId) {
        if(userId == null || userId.trim().isEmpty()) throw new ValidationException("User ID cannot be empty");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);

        return "User deleted successfully";
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public String deleteListing(String listingId) {
        if(listingId == null || listingId.trim().isEmpty()) throw new ValidationException("Listing ID cannot be empty");
        Listing listing = listingRepository.findById(listingId).orElseThrow(() -> new ListingNotFoundException("Listing not found"));
        listing.setStatus(ListingStatus.DELETED);
        listingRepository.save(listing);

        return "Listing deleted successfully";
    }

    @Override
    public List<Listing> getListingsByUserId(String userId) {
        if(userId == null || userId.trim().isEmpty()) throw new ValidationException("User ID cannot be empty");
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        return listingRepository.findByUserId(userId);
    }
}