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
            loginSuccessful = true;
        } catch (NoSuchElementException e) {
            System.err.println("No user found with username: " + username);
        } catch (Exception e) {
            System.err.println("An error occurred while logging in: " + e.getMessage());
        }

        if (!loginSuccessful) {
            throw new RuntimeException("Login failed");
        }

    }


    public void deleteAccount(int id){
        userService.deleteAccount(id);
    }

}
