package com.email.project.services;

import com.email.project.data.repo.UserRepo;
import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.dtos.responses.FindUserResponse;
import com.email.project.utils.Mapper;
import com.email.project.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createAccount(CreateUserRequest createUserRequest) {
        if (userExist(createUserRequest.getName())) {
            throw new IllegalArgumentException(createUserRequest.getUserName() + " already exists");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String email = createUserRequest.getEmailAddress();
        if (email.matches(emailRegex)) {
            System.out.println("Valid email address");
        } else {
            System.out.println("Invalid email address");
        }
        return userRepo.save(Mapper.toModel(createUserRequest));
    }

    private boolean userExist(String name) {
        Optional<User> foundUser = userRepo.findById(name);
        return foundUser.isPresent();
    }

    @Override
    public void login(String id) {
        try {
            userRepo.findById(id);
        } catch (NoSuchElementException e) {
            System.err.println("User with username does not exist.");
        }
    }

    @Override
    public void deleteAccount(User user) {
    userRepo.delete(user);
    }
    @Override
    public FindUserResponse findUserById(String id) {
        Optional<User> foundUser = userRepo.findById(id);
        if(foundUser.isEmpty()) throw new NullPointerException("No user found with ID : " + id);
        FindUserResponse response = new FindUserResponse();
        Mapper.toDTO(foundUser.get(), response);
        return response;
    }
    @Override
    public void findByUserName(String username) {
        Optional <User> foundUser = Optional.ofNullable(userRepo.findByUserName(username));
        if (foundUser.isEmpty() ) throw new NullPointerException("User does not exist");
        FindUserResponse response = new FindUserResponse();
        Mapper.toDTO(foundUser.get(), response);
    }
}
