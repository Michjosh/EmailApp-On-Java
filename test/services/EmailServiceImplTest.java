package services;

import data.model.Email;
import dtos.EmailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceImplTest {

    EmailService emailService;

    EmailDTO emailDTO;

    @BeforeEach
    void setUp() {
        emailService = new EmailServiceImpl();
        emailDTO = new EmailDTO();
        emailDTO.setRecipientEmail("jean@gmailcom");
        emailDTO.setRecipientName("Jean");
        emailDTO.setSubject("The Election Day");
        emailDTO.setBody("""
                Hello there,
                Its All about the election day,
                My prayer is that the Obidient would win
                """);
        emailDTO.setSenderEmail("michael@gmail.com");
        emailDTO.setSenderName("Michael");
    }

    @Test
    void sendEmailTest() {
        Email email = emailService.sendEmail(emailDTO);
        assertEquals(1, emailService.countSentEmails());
    }

    @Test
    void countSentEmailsTest() {
        emailService.sendEmail(emailDTO);
        assertEquals(1, emailService.countSentEmails());
    }

    @Test
    void getSentEmailsByName() {
        emailService.sendEmail(emailDTO);
        emailService.getSentEmailByName("Jean");
        assertEquals(1, emailService.countSentEmails());

    }

    @Test
    void deleteSentEmail() {
        emailService.sendEmail(emailDTO);
        emailService.deleteSentEmail("Jean");
        assertEquals(0, emailService.countSentEmails());
    }

    @Test
    void deleteAllSentEmail() {
        emailService.sendEmail(emailDTO);
        emailService.deleteAllSentEmail();
        assertEquals(0, emailService.countSentEmails());
    }
}