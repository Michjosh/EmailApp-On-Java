package com.email.project.dtos.requests;

import lombok.Data;
@Data
public class SendEmailRequest {
    private String subject;
    private String body;
    private String recipientName;
    private String recipientEmail;
    private String senderEmail;

}
