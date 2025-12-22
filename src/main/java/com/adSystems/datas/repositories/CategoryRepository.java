package com.adSystems.datas.repositories;

import com.adSystems.datas.models.Category;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<@NonNull Category, @NonNull String> {
}
