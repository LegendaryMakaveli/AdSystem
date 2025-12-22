package com.adSystems.datas.repositories;

import com.adSystems.datas.models.City;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<@NonNull City, @NonNull String> {
}
