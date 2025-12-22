package com.adSystems.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Listings")

@CompoundIndexes({
        @CompoundIndex(
                name = "city_status_createdAt_idx",
                def = "{'cityId': 1, 'status': 1, 'createdAt': -1}"
        ),
        @CompoundIndex(
                name = "city_category_status_createdAt_idx",
                def = "{'cityId': 1, 'categoryId': 1, 'status': 1, 'createdAt': -1}"
        )
})
public class Listing {
    @Id
    private String id;
    private String title;
    private String description;
    private double price;
    private String email;
    private String cityId;
    private String categoryId;
    private List<String> images = new ArrayList<>();
    private ListingStatus status;
    @Indexed
    private String editToken;
    private LocalDateTime createdAt;
    @Indexed
    private LocalDateTime expiresAt;
}
