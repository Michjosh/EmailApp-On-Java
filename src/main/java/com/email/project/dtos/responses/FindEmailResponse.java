package com.email.project.dtos.responses;

import lombok.Data;

@Data
public class FindEmailResponse {
    private String emailId;
    private String userId;
    private String subject;
    private String body;
    private String recipientName;
    private String recipientEmail;
    private String sentAt;
    private String recipientId;
}
