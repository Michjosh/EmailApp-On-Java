package data.model;

import java.util.Date;
import java.util.List;

public class Email {
    private int id;
    private int userId;
    private String subject;
    private String body;
    private String senderEmail;
    private String senderName;
    private String recipientEmail;
    private String recipientName;
    private Date sentAt;

    private String inbox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String name) {
        this.senderName = name;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientEmail(String emailAddress) {
        this.recipientEmail = emailAddress;

    }

    public String getRecipientEmail() {
        return recipientEmail;
    }



    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", userId=" + userId +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", senderName='" + senderName + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }

    public String getInbox() {
        return inbox;
    }

    public void setInbox(String inbox) {
        this.inbox = inbox;
    }

    public int getUserId() {
        return userId;


    }
}
