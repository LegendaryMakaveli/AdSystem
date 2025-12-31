package com.adSystems.datas.repositories;

import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.ListingStatus;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ListingRepository extends MongoRepository<@NonNull Listing, @NonNull String> {
    List<Listing> findByCityIdAndStatusOrderByCreatedAtDesc(String cityId, ListingStatus status);
    List<Listing> findByCityIdAndCategoryIdAndStatusOrderByCreatedAtDesc(String cityId, String categoryId, ListingStatus status);
    Optional<Listing> findByIdAndEditToken(String id, String editToken);
    List<Listing> findByExpiresAtBeforeAndStatus(LocalDateTime time, ListingStatus status);
    List<Listing> findByUserId(String userId);
}
