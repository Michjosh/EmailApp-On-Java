package data.repo;

import data.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailRepoImplTest {

    private EmailRepo emailRepo;

    private Email email;

    @BeforeEach
    void setUp() {
        emailRepo = new EmailRepoImpl();
        email = new Email();

        email.setRecipientEmail("mmm@gmail.com");
        email.setRecipientName("Jean");
        email.setSubject("The New Season");
        email.setBody("""
              
                Hi Jean,
                Good day.
                Hope this mail finds you well.
                Here is inform you about the new season approaching, which is at hand.
                I want you to be prepaid, so you can receive all the good that come with the new season
                Warm regards.
                """);
        email.setSenderEmail("michael@gmail.com");
        email.setSenderName("Michael");
    }

    @Test
    void sendEmailTest() {
        emailRepo.sendEmail(email);
        assertEquals(1, emailRepo.countSentEmails());
    }

    @Test
    void getSendEmailByRecipientNameTest() {
        emailRepo.sendEmail(email);
        emailRepo.getSentEmailByRecipientName("Jean");
        assertEquals(1, emailRepo.countSentEmails());
    }

    @Test
    void deleteSentEmailTest() {
        emailRepo.sendEmail(email);
        emailRepo.deleteSentEmail("Jean");
        assertEquals(0, emailRepo.countSentEmails());
    }

    @Test
    void deleteAllSentEmailTest(){
        emailRepo.sendEmail(email);
        emailRepo.deleteAllSentEmail();
        assertEquals(0, emailRepo.countSentEmails());

    }


}