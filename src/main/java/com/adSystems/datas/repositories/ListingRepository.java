package com.adSystems.datas.repositories;

import com.adSystems.datas.models.Listing;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListingRepository extends MongoRepository<@NonNull Listing, @NonNull String> {
}
