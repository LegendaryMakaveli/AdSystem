package com.adSystems.datas.repositories;

import com.adSystems.datas.models.City;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends MongoRepository<@NonNull City, @NonNull String> {
    Optional<City> findByName(String name);
}
