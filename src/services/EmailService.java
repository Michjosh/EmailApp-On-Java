package services;

import data.model.Email;
import dtos.EmailDTO;

public interface EmailService {

    Email sendEmail(EmailDTO email);

    int countSentEmails();

    Email getSentEmailsById(int id);

    Email getSentEmailByName(String name);

    Object readInbox();

    void countInbox();

    void deleteInbox();

    void deleteSentEmail(String name);

    void deleteAllSentEmail();
}
