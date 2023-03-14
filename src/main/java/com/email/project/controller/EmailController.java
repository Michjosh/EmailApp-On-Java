package com.email.project.controller;

import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.services.EmailServiceImpl;
import com.email.project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
//
//    @PostMapping("/emailApp/sendEmail")
//    public Object sendEmail(@RequestBody SendEmailRequest sendEmailRequest) {
//        try {
//            return emailService.sendEmail(sendEmailRequest);
//        } catch (IllegalArgumentException ex) {
//            return ex.getMessage();
//        }
//    }

    @PostMapping("/emailApp/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) {
        try {
            return new ResponseEntity<>(emailService.sendEmail(sendEmailRequest), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/emailApp/readSentEmail/{id}")
    public Object getSentEmailById(@PathVariable String id) {
        try {
            return emailService.getSentEmailsById(id);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public void readInbox(){
        emailService.readInbox();
    }

    public void countSentEmails(){
        emailService.countSentEmails();
    }

    public void deleteSentEmail(String name){
        emailService.deleteSentEmail(name);
    }

    public void deleteALlSentEmail(){
        emailService.deleteAllSentEmail();
    }
}
