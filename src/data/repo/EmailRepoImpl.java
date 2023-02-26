package data.repo;

import data.model.Email;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmailRepoImpl implements EmailRepo{
    private final List<Email> emails = new ArrayList<>();
    private int sentEmailCount = 0;


    @Override
    public Email sendEmail(Email email) {
        boolean emailHasNotBeenSent = email.getId() == 0;
        if(emailHasNotBeenSent){
            email.setId(generateId());
            email.setUserId(generateId()-1);
            email.setSentAt(new Date());
            this.emails.add(email);
            sentEmailCount++;
        }
        return email;
    }

    private int generateId() {
        return sentEmailCount++;
    }


    @Override
    public Email getSentEmailById(int id) {
        for (Email email : emails){
            if (email.getId() == id){
                display(email);
                return email;
            }
        }

        throw new IllegalArgumentException("Email with ID " + id + " not found.");
    }


    @Override
    public String readInbox(int id) {
        return null;
    }

    @Override
    public int countInbox() {
        return 0;
    }

    @Override
    public void deleteInbox(int id) {

    }

    @Override
    public Email getSentEmailByRecipientName(String name) {
        for (Email email : emails) {
            if (email.getRecipientName().equals(name)) {
                display(email);
                return email;
            }
        }
        throw new IllegalArgumentException("Email with ID " + name + " not found.");
    }

    public void deleteSentEmail(String name) {
        emails.removeIf(email -> email.getRecipientName().equals(name));
    }

    @Override
    public void deleteAllSentEmail() {
        emails.clear();
    }

    public int countSentEmails(){
        return emails.size();

    }
    private void display(Email email) {
            System.out.println("Recipient Name: " + email.getRecipientName());
            System.out.println("Recipient Email: " + email.getRecipientEmail());
            System.out.println("Email Subject: " + email.getSubject());
            System.out.println("Email Body: " + email.getBody());
            System.out.println("Sender Email: " + email.getSenderEmail());
            System.out.println("Sender Name: " + email.getSenderName());
            System.out.println("Time Stamp: " + email.getSentAt());
        }
}
