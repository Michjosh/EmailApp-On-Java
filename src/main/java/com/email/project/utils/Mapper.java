package com.email.project.utils;

import com.email.project.data.model.Email;
import com.email.project.data.model.User;
import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.dtos.responses.FindEmailResponse;
import com.email.project.dtos.responses.FindUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static void toDTO(User user, FindUserResponse response){
    response.setName(user.getName());
    response.setUserName(user.getUserName());
    response.setEmail(user.getEmailAddress());
    response.setPassword(user.getPassword());
    }

    public static User toModel(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setUserName(createUserRequest.getUserName());
        user.setEmailAddress(createUserRequest.getEmailAddress());
        user.setPassword(createUserRequest.getPassword());
        return user;
    }

    public static void toModel(SendEmailRequest sendEmailRequest, Email email){
        email.setSubject(sendEmailRequest.getSubject());
        email.setBody(sendEmailRequest.getBody());
        email.setRecipientName(sendEmailRequest.getRecipientName());
        email.setRecipientEmail(sendEmailRequest.getRecipientEmail());
        email.setUserId(sendEmailRequest.getUserId());
        email.setRecipientId(sendEmailRequest.getRecipientId());
    }

    public static void toDTO(Email email, FindEmailResponse response){
        response.setRecipientName(email.getRecipientName());
        response.setSubject(email.getSubject());
        response.setBody(email.getBody());
        response.setUserId(email.getUserId());
        response.setRecipientEmail(email.getRecipientEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy, hh:mm a");
        response.setSentAt(formatter.format(email.getSentAt()));
        response.setRecipientId(email.getRecipientId());
    }
}
