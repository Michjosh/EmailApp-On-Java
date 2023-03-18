package com.email.project.services;
import com.email.project.data.model.Email;
import com.email.project.data.repo.InboxRepo;
import com.email.project.data.repo.TrashRepo;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.dtos.responses.FindEmailResponse;
import com.email.project.data.repo.SentBoxRepo;
import com.email.project.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    SentBoxRepo sentBoxRepo;
    @Autowired
    InboxRepo inboxRepo;
    @Autowired
    TrashRepo trashRepo;
    @Autowired
    private UserService userService;

    @Override
    public Email sendEmail(SendEmailRequest sendEmailRequest) {
        if (validateSenderEmail(sendEmailRequest)) throw new NullPointerException("No User found with email : " + sendEmailRequest.getSenderEmail());
        if (validateRecipientEmail(sendEmailRequest)) throw new NullPointerException("No User found with email : " + sendEmailRequest.getRecipientEmail());
        if (validateRecipientName(sendEmailRequest)) throw new NullPointerException("No User found with name : " + sendEmailRequest.getRecipientName());
        Email email = new Email();
        Mapper.toModel(sendEmailRequest, email);
        inboxRepo.save(email);
        return sentBoxRepo.save(email);
    }
    @Override
    public long countSentEmails() {
        return sentBoxRepo.count();
    }
    @Override
    public FindEmailResponse getSentEmailsById(String id) {
        Optional <Email> foundEmail = sentBoxRepo.findById(id);
        if(foundEmail.isEmpty()) throw new NullPointerException("No email found with ID : " + id);
        FindEmailResponse response = new FindEmailResponse();
        Mapper.toDTO(foundEmail.get(), response);
        return response;
    }
    @Override
    public FindEmailResponse readInbox(String id) {
        Optional <Email> foundEmail = inboxRepo.findById(id);
        if(foundEmail.isEmpty()) throw new NullPointerException("No email found with ID : " + id);
        FindEmailResponse response = new FindEmailResponse();
        Mapper.toDTO(foundEmail.get(), response);
        return response;

    }
    @Override
    public FindEmailResponse readTrashBox(String id) {
        Optional <Email> foundEmail = trashRepo.findById(id);
        if(foundEmail.isEmpty()) throw new NullPointerException("No email found with ID : " + id);
        FindEmailResponse response = new FindEmailResponse();
        Mapper.toDTO(foundEmail.get(), response);
        return response;
    }
    @Override
    public void deleteSentEmail(String id) {
        if (sentBoxRepo.count() == 0) throw new NoSuchElementException("Nothing to delete");
        sentBoxRepo.findById(id);
    }
    @Override
    public void deleteAllSentEmail() {
        if (sentBoxRepo.count() == 0) throw new NoSuchElementException("Nothing to delete");
        sentBoxRepo.deleteAll();
    }
    private boolean validateSenderEmail(SendEmailRequest sendEmailRequest){
        userService.findUserByEmailAddress(sendEmailRequest.getRecipientEmail());
        return false;
    }

    private boolean validateRecipientEmail(SendEmailRequest sendEmailRequest){
        userService.findUserByEmailAddress(sendEmailRequest.getRecipientEmail());
        return false;
    }
    private boolean validateRecipientName(SendEmailRequest sendEmailRequest){
        userService.findUserByName(sendEmailRequest.getRecipientName());
        return false;
    }
}
