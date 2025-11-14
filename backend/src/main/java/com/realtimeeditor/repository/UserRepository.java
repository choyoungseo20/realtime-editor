package com.realtimeeditor.repository;

import com.realtimeeditor.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByNickname(String nickname);
}
