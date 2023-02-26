package data.repo;

import data.model.Email;

public interface EmailRepo {
    Email sendEmail(Email email);

    int countSentEmails();

    Email getSentEmailById(int id);

    String readInbox(int id);

    int countInbox();

    void deleteInbox(int id);

    Email getSentEmailByRecipientName(String name);

    void deleteSentEmail(String name);

    void deleteAllSentEmail();

}
