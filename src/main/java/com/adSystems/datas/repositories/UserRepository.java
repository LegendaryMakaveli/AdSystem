package com.adSystems.datas.repositories;

import com.adSystems.datas.models.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<@NonNull User, @NonNull String> {
    Optional<User> findByEmail(String email);
}
