package com.adSystems.services;

import com.adSystems.dtos.requests.ContactMessageRequest;

public interface ContactMessageService {
    void saveContactMessage(String listingId, ContactMessageRequest request);
}
