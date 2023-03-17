package com.email.project.data.repo;

import com.email.project.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUserName(String name);
    User findUserByEmailAddress(String email);
    User findUserByName(String name);
}
