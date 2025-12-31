package com.adSystems.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private Role role;
    private UserStatus status = UserStatus.ACTIVE;
    private SubscriptionPlan subscriptionPlan;
    private LocalDateTime subscriptionExpiresAt;
    private int listingCount;
    private LocalDateTime createdAt;
}
