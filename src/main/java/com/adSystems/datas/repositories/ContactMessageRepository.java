package com.adSystems.datas.repositories;

import com.adSystems.datas.models.ContactMessage;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactMessageRepository extends MongoRepository<@NonNull ContactMessage, @NonNull String> {
}
