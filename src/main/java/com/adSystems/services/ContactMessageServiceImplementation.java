package com.adSystems.services;

import com.adSystems.datas.models.ContactMessage;
import com.adSystems.datas.models.Listing;
import com.adSystems.datas.repositories.ContactMessageRepository;
import com.adSystems.datas.repositories.ListingRepository;
import com.adSystems.dtos.reponses.ContactMessageResponse;
import com.adSystems.dtos.requests.ContactMessageRequest;
import com.adSystems.exception.ListingNotFoundException;
import com.adSystems.mail.MailService;
import com.adSystems.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ContactMessageServiceImplementation implements ContactMessageService{
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    @Autowired
    private MailService mailService;

    @Override
    public ContactMessageResponse sendContactMessage(String listingId, ContactMessageRequest request) {
        Listing listing = listingRepository.findById(listingId).orElseThrow(() -> new ListingNotFoundException("Listing not found!"));

        ContactMessage message = Mapper.mapToContactSeller(request);
        message.setListingId(listingId);
        contactMessageRepository.save(message);

        String subject = "New Message About Your Listing: " + listing.getTitle();
        String body = "You have received a new message from " + request.getEmail() + "\n\n" + request.getMessage();

        mailService.sendMail(listing.getEmail(), subject, body);

        return Mapper.mapToSendMessageResponse();
    }

}
