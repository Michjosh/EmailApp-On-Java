package utils;

import data.model.Email;
import dtos.EmailDTO;

public class EmailMapper {

    public static Email toModel(EmailDTO emailDTO){
        Email email = new Email();
        email.setSenderEmail(emailDTO.getSenderEmail());
        email.setSenderName(emailDTO.getSenderName());
        email.setSubject(emailDTO.getSubject());
        email.setBody(emailDTO.getBody());
        email.setRecipientEmail(emailDTO.getRecipientEmail());
        email.setRecipientName(emailDTO.getRecipientName());
        return email;
    }

    private static EmailDTO toDTO(Email email){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSenderEmail(email.getSenderEmail());
        emailDTO.setSenderName(email.getSenderName());
        emailDTO.setRecipientName(email.getRecipientName());
        emailDTO.setSubject(email.getSubject());
        emailDTO.setBody(email.getBody());
        return emailDTO;
    }
}
