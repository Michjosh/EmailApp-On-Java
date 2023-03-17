package com.email.project.controller;

import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emailApp/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) {
        try {
            return new ResponseEntity<>(emailService.sendEmail(sendEmailRequest), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/emailApp/readSentEmail/{id}")
    public ResponseEntity <?> getSentEmailById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(emailService.getSentEmailsById(id), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/emailApp/readInbox/{id}")
    public ResponseEntity <?> readInbox(@PathVariable String id){
        try {
            return new ResponseEntity<>(emailService.readInbox(id), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
