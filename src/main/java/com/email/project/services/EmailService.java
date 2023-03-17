package com.email.project.services;
import com.email.project.data.model.Email;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.dtos.responses.FindEmailResponse;

public interface EmailService {
    Email sendEmail(SendEmailRequest email);
    long countSentEmails();
    FindEmailResponse getSentEmailsById(String id);
    FindEmailResponse readInbox(String id);
    FindEmailResponse readTrashBox(String id);
    void deleteSentEmail(String name);
    void deleteAllSentEmail();
}
