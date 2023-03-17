package com.email.project.controller;
import com.email.project.data.model.User;
import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/emailApp/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            return new ResponseEntity<>(userService.createAccount(createUserRequest), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/emailApp/findUser/{id}")
    public Object findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    public void login(String username) {
        boolean loginSuccessful = false;
        try {
            userService.login(username);
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

    public void deleteAccount(User user){
        userService.deleteAccount(user);
    }

}
