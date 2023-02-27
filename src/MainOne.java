import controller.EmailController;
import controller.UserController;
import data.model.User;
import data.repo.UserRepo;
import data.repo.UserRepoImpl;
import dtos.EmailDTO;
import dtos.UserDTO;

import javax.swing.*;

public class MainOne {
    private static final EmailController emailController = new EmailController();
    private static final UserController userController = new UserController();

    public static void main(String[] args) {
        try {
            startApp();
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            startApp();
        }

    }

    private static void startApp() {
        String message = """
            Welcome to To you Favorite Mobile Mail
            1 -> Login
            2 -> Register
            3 -> Exit
            """;
        try {
            String input = input(message);
            switch (input.charAt(0)) {
                case '1' -> loginUser();
                case '2' -> registerUser();
                case '3' -> exitApp();
                default -> {
                    displayMessage("Invalid input try again");
                    startApp();
                }
            }
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            startApp();
        }
    }

    private static void loginUser() {
        

        try {
            userController.login(input("Enter Your Username"));
            emailApp();
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            startApp();
        }
    }

    private static void emailApp() {
        try {
            String message = """
                Email Menu
                1 -> Send email
                2 -> Sent email box
                3 -> Inbox
                4 -> Account Settings
                5 -> Logout
                """;
            String input = input(message);
            switch (input.charAt(0)) {
                case '1' -> sendEmail();
                case '2' -> sentBox();
                case '3' -> inbox();
                case '4' -> accountSettings();
                case '5' -> startApp();
                default -> {
                    displayMessage("Enter the right key");
                    emailApp();
                }
            }
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            emailApp();
        }
    }


    private static void accountSettings() {
        try {
            String message = """
                Sent Email Box Menu
                1 -> Update Account Info
                2 -> View Profile
                3 -> Delete Account
                4 -> Back to Email Menu
                """;
            String input = input(message);
            switch (input.charAt(0)) {
                case '1' : updateUser();
                    displayMessage("Your Account Has Been Updated");
                    accountSettings();
                    break;
                case '2' : JOptionPane.showMessageDialog(null,userController.findUserById(Integer.parseInt(input("Enter your ID"))));
                    accountSettings();
                    break;
                case '3' : userController.deleteAccount(Integer.parseInt(input("Enter your ID")));
                    displayMessage("Your Account Has been deleted");
                    startApp();
                case '4' : emailApp();
                default : {
                    displayMessage("Enter the right key");
                    emailApp();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private static void inbox() {

        try {
            String message = """
                Sent Email Box Menu
                1 -> Read Inbox Messages
                2 -> Count Inbox Messages
                3 -> Delete Email inbox
                4 -> Back to Email Menu
                """;
            String input = input(message);
            switch (input.charAt(0)){
                case '1' -> emailController.readInbox();
                case '2' -> emailController.countInbox();
                case '3' -> emailController.deleteInboxMessage();
                case '4' -> emailApp();
                default -> {
                    displayMessage("Enter the right key");
                    emailApp();
                }
            }
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            inbox();
        }
    }

    private static void sentBox() {
        try {
            String message = """
                Sent Email Box Menu
                1 -> Read Sent Emails
                2 -> Count Sent Emails
                3 -> Delete Send Email
                4 -> Delete All Sent mails
                5 -> Back to Email Menu
                """;
            String input = input(message);
            switch (input.charAt(0)){
                case '1' : JOptionPane.showMessageDialog(null,emailController.getSentEmailByName(input("Enter Recipient Name")));
                    sentBox();
                    break;
                case '2': emailController.countSentEmails();
                    sentBox();
                case '3' : emailController.deleteSentEmail(input("Enter recipient's Name"));
                    sentBox();
                case '4' : emailController.deleteALlSentEmail();
                    emailApp();
                case '5' : emailApp();
                    break;
                default : {
                    displayMessage("Enter the right key");
                    startApp();
                }
            }
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            sentBox();
        }
    }


    private static void sendEmail(){
        try {
            EmailDTO emailDTO = new EmailDTO();
            UserDTO userDTO = new UserDTO();
            emailDTO.setRecipientEmail(input("Enter recipient email: "));
            emailDTO.setRecipientName(input("Enter recipient name: "));
            emailDTO.setSubject(input("Email Subject"));
            emailDTO.setBody(input("Email Body: "));
            emailController.sendEmail(emailDTO);
            displayMessage("Email Sent");
            emailApp();
        } catch (Exception e) {
            displayMessage("An error occurred while sending the email: " + e.getMessage());
            emailApp();
        }
    }

    private static void registerUser() {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(input("Enter your Username"));
            userDTO.setPassword(input("Enter your password"));
            userDTO.setName(input("Enter your First name and Lastname with a space in between"));
            userDTO.setEmail(input("Enter your Email Address"));
            var result = userController.createUser(userDTO);
            displayMessage("""
                Your Account Details Are:
                """ + result.toString());
            startApp();
        } catch (Exception e) {
            displayMessage("An error occurred: " + e.getMessage());
            startApp();
        }
    }

    private static void updateUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(input("Enter your Username"));
        userDTO.setPassword(input("Enter your password"));
        userDTO.setName(input("Enter your First name and Lastname with a space in between"));
        userDTO.setEmail(input("Enter your Email Address"));
        var result = userController.updateUser(Integer.parseInt(input("Enter Id")),userDTO);
        displayMessage("""
                Your Account Details Has Been Updated
                """ + result.toString());
    }

    private static void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private static void exitApp() {
        System.exit(1);
        displayMessage("Good bye");
    }

    private static String input(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }
}
