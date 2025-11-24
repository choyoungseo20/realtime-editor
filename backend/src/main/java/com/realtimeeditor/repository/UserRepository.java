package com.realtimeeditor.repository;

import com.realtimeeditor.domain.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByNickname(String nickname);
    Optional<User> findByNickname(String nickname);
}
