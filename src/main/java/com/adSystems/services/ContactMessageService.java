package com.adSystems.services;

import com.adSystems.dtos.reponses.ContactMessageResponse;
import com.adSystems.dtos.requests.ContactMessageRequest;

public interface ContactMessageService {
    ContactMessageResponse sendContactMessage(String listingId, ContactMessageRequest request);
}
