package com.email.project.services;

import com.email.project.data.model.User;
import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.dtos.responses.FindUserResponse;

public interface UserService {
    User createAccount(CreateUserRequest createUserRequest);
    void login(String id);
    void deleteAccount(User user);
    FindUserResponse findUserById(String id);
    void findByUserName(String username);
}

