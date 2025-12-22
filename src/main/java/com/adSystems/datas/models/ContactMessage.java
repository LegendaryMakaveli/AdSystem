package com.adSystems.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "Contacts")
public class ContactMessage {
    @Id
    private String id;
    private String listingId;
    private String senderEmail;
    private String message;
    private LocalDateTime sendAt;
}
