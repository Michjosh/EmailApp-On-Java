package com.email.project.services;
import com.email.project.data.model.Email;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.dtos.responses.FindEmailResponse;

import java.util.Optional;

public interface EmailService {
    Email sendEmail(SendEmailRequest email);
    long countSentEmails();
    FindEmailResponse getSentEmailsById(String id);
    void readInbox();
    void deleteSentEmail(String name);
    void deleteAllSentEmail();
}
