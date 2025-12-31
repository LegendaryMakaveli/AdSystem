package com.adSystems.services;


import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    String upgradeUserToPremium(String userId);
    String downgradeUser(String userId);
    List<User> getAllUsers();
    Optional<User> getUserById(String userId);
    String deleteUser(String userId);
    List<Listing> getAllListings();
    String deleteListing(String listingId);
    List<Listing> getListingsByUserId(String userId);
}