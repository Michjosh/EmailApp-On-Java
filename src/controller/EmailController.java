package controller;

import dtos.EmailDTO;
import services.EmailService;
import services.EmailServiceImpl;

public class EmailController {

    private EmailService emailService = new EmailServiceImpl();

    public void sendEmail(EmailDTO emailDTO) {
        try {
            emailService.sendEmail(emailDTO);
        } catch (IllegalArgumentException ex) {
            ex.getMessage();
        }
    }

    public Object getSentEmailByName(String name){
        return emailService.getSentEmailByName(name);
    }

    public void readInbox(){
        emailService.readInbox();
    }

    public void countInbox(){
        emailService.countInbox();
    }

    public void countSentEmails(){
        emailService.countSentEmails();
    }

    public void deleteInboxMessage(){
        emailService.deleteInbox();
    }

    public void deleteSentEmail(String name){
        emailService.deleteSentEmail(name);
    }

    public void deleteALlSentEmail(){
        emailService.deleteAllSentEmail();
    }
}
