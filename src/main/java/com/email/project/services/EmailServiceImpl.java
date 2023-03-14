package com.email.project.services;
import com.email.project.data.model.Email;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.dtos.responses.FindEmailResponse;
import com.email.project.data.repo.EmailRepo;
import com.email.project.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    EmailRepo emailRepo;
    @Autowired
    private UserService userService;
    @Override
    public Email sendEmail(SendEmailRequest sendEmailRequest) {
        if (checkUserExist(sendEmailRequest))throw new NullPointerException("No User found with ID : " + sendEmailRequest.getUserId());
        Email email = new Email();
        Mapper.toModel(sendEmailRequest, email);
        return emailRepo.save(email);
    }

    private boolean checkUserExist(SendEmailRequest request) {
        userService.findUserById(request.getUserId());
        return false;
    }

    @Override
    public long countSentEmails() {
        return emailRepo.count();
    }

    @Override
    public FindEmailResponse getSentEmailsById(String id) {
        Optional <Email> foundEmail = emailRepo.findById(id);
        if(foundEmail.isEmpty()) throw new NullPointerException("No email found with ID : " + id);
        FindEmailResponse response = new FindEmailResponse();
        Mapper.toDTO(foundEmail.get(), response);
        return response;
    }

    @Override
    public void readInbox() {
        if (emailRepo.count() == 0) throw new NoSuchElementException("No Inbox to read");
    }

    @Override
    public void deleteSentEmail(String id) {
        if (emailRepo.count() == 0) throw new NoSuchElementException("Nothing to delete");
        emailRepo.findById(id);
    }
    @Override
    public void deleteAllSentEmail() {
        if (emailRepo.count() == 0) throw new NoSuchElementException("Nothing to delete");
        emailRepo.deleteAll();
    }
}
