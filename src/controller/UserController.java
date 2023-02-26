package controller;

import dtos.UserDTO;
import services.UserService;
import services.UserServiceImpl;

import java.util.NoSuchElementException;

public class UserController {

    private final UserService userService = new UserServiceImpl();

    public Object createUser(UserDTO userDTO) {
        try {
            return userService.createAccount(userDTO);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }
    }

    public Object updateUser(int id, UserDTO userDTO){
        return userService.updateAccount(id, userDTO);
    }

    public Object findUserById(int id) {
        return userService.findUserById(id);
    }

    public void login(String username) {
        boolean loginSuccessful = false;
        try {
            userService.findByUserName(username);
            // Perform login logic if the user is found
            loginSuccessful = true;
        } catch (NoSuchElementException e) {
            // Handle the case when no user is found with the specified username
            System.err.println("No user found with username: " + username);
            // Or throw a custom exception or return a default value
        } catch (Exception e) {
            // Handle any other exception that might be thrown by the service
            System.err.println("An error occurred while logging in: " + e.getMessage());
            // Or throw a custom exception or return a default value
        }

        if (!loginSuccessful) {
            // Handle the case when the login was not successful
            throw new RuntimeException("Login failed");
            // Or throw a custom exception or return a default value
        }

    }


    public void deleteAccount(int id){
        userService.deleteAccount(id);
    }

}
