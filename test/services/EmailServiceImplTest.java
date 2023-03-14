package services;

import com.email.project.data.model.Email;
import com.email.project.dtos.requests.SendEmailRequest;
import com.email.project.services.EmailService;
import com.email.project.services.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceImplTest {

    EmailService emailService;

    SendEmailRequest sendEmailRequest;

    @BeforeEach
    void setUp() {
        emailService = new EmailServiceImpl();
        sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setRecipientEmail("jean@gmailcom");
        sendEmailRequest.setRecipientName("Jean");
        sendEmailRequest.setSubject("The Election Day");
        sendEmailRequest.setBody("""
                Hello there,
                Its All about the election day,
                My prayer is that the Obidient would win
                """);
    }

    @Test
    void sendEmailTest() {
        Email email = emailService.sendEmail(sendEmailRequest);
        assertEquals(1, emailService.countSentEmails());
    }

    @Test
    void countSentEmailsTest() {
        emailService.sendEmail(sendEmailRequest);
        assertEquals(1, emailService.countSentEmails());
    }

    @Test
    void getSentEmailsByName() {
        emailService.sendEmail(sendEmailRequest);
        assertEquals(1, emailService.countSentEmails());

    }

    @Test
    void deleteSentEmail() {
        emailService.sendEmail(sendEmailRequest);
        emailService.deleteSentEmail("Jean");
        assertEquals(0, emailService.countSentEmails());
    }

    @Test
    void deleteAllSentEmail() {
        emailService.sendEmail(sendEmailRequest);
        emailService.deleteAllSentEmail();
        assertEquals(0, emailService.countSentEmails());
    }
}