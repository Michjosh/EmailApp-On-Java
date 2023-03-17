package com.email.project.data.repo;

import com.email.project.data.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SentBoxRepo extends MongoRepository<Email, String> {
}
