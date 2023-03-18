package com.email.project.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Email {
    @Id
    private String emailId;
    private String subject;
    private String body;
    private String recipientEmail;
    private String recipientName;
    private LocalDateTime sentAt = LocalDateTime.now();
    private String senderEmail;
}
