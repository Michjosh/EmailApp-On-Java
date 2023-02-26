package services;

import data.model.Email;
import data.repo.EmailRepo;
import data.repo.EmailRepoImpl;
import dtos.EmailDTO;
import utils.EmailMapper;

import java.util.NoSuchElementException;

public class EmailServiceImpl implements EmailService{
    private EmailRepo emailRepo = new EmailRepoImpl();

    int sentEmailCount = 0;
    int inboxBoxCount = 0;

    @Override
    public Email sendEmail(EmailDTO email) {
        sentEmailCount++;
        return emailRepo.sendEmail(EmailMapper.toModel(email));
    }

    @Override
    public int countSentEmails() {
        if (sentEmailCount == 0) System.out.println("Email sent is empty");
        else System.out.println(sentEmailCount);
        return sentEmailCount;
    }

    @Override
    public Email getSentEmailsById(int id) {
        return emailRepo.getSentEmailById(id);
    }

    @Override
    public Email getSentEmailByName(String name) {
        return emailRepo.getSentEmailByRecipientName(name);
    }

    @Override
    public Object readInbox() {
        if (emailRepo.countInbox() == 0) throw new NoSuchElementException("No Inbox to read");
        else return null;
    }

    @Override
    public void countInbox() {
        if (inboxBoxCount == 0) throw new NoSuchElementException("Inbox is empty");
    }

    @Override
    public void deleteInbox() {
        if (emailRepo.countInbox() == 0) throw new NoSuchElementException("Inbox is empty");
    }

    @Override
    public void deleteSentEmail(String name) {
        if (sentEmailCount == 0) throw new NoSuchElementException("Nothing to delete");
        emailRepo.deleteSentEmail(name);
        sentEmailCount--;

    }

    @Override
    public void deleteAllSentEmail() {
        if (sentEmailCount == 0) throw new NoSuchElementException("Nothing to delete");
        emailRepo.deleteAllSentEmail();
        sentEmailCount = 0;

    }
}
