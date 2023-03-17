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
        if (userExist(createUserRequest.getName())) {throw new IllegalArgumentException(createUserRequest.getUserName() + " already exists");}
        isValidEmail(createUserRequest);
        isValidPassword(createUserRequest);
        isValidUsername(createUserRequest);
        isValidName(createUserRequest);
        return userRepo.save(Mapper.toModel(createUserRequest));
    }
    private boolean userExist(String name) {
        Optional<User> foundUser = userRepo.findById(name);
        return foundUser.isPresent();
    }
    @Override
    public void login(String username) {
        try {
            userRepo.findById(username);
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
    public FindUserResponse findUserByEmailAddress(String email) {
        Optional<User> foundUser = Optional.ofNullable(userRepo.findUserByEmailAddress(email));
        if(foundUser.isEmpty()) throw new NullPointerException("No user found with email : " + email);
        FindUserResponse response = new FindUserResponse();
        Mapper.toDTO(foundUser.get(), response);
        return response;
    }

    @Override
    public FindUserResponse findUserByName(String name) {
        Optional<User> foundUser = Optional.ofNullable(userRepo.findUserByName(name));
        if(foundUser.isEmpty()) throw new NullPointerException("No user found with name : " + name);
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

    public static void isValidEmail(CreateUserRequest createUserRequest) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!createUserRequest.getEmailAddress().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email address: " + createUserRequest.getEmailAddress());
        }
    }

    public void isValidPassword(CreateUserRequest createUserRequest) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$";
        if (!createUserRequest.getPassword().matches(regex)){
            throw new IllegalArgumentException("Invalid password : " + createUserRequest.getPassword() + """
                    Password must have:
                    At least one uppercase letter
                    At least one lowercase letter
                    At least one digit
                    At least one special character
                    Must be between 8 and 20 characters)""");
        }
    }

    public void isValidUsername(CreateUserRequest createUserRequest) {
        String regex = "^[a-zA-Z0-9_]{3,16}$";
        if (!createUserRequest.getUserName().matches(regex)) {
            if (!createUserRequest.getPassword().matches(regex)) {
                throw new IllegalArgumentException("Invalid username: " + createUserRequest.getUserName() + """
                        Username must usernames consisting of
                        letters, numbers,
                        and underscores with a length between 3 and 16 characters
                        """);
            }
        }
    }

    public void isValidName(CreateUserRequest createUserRequest) {
        String regex = "^(?i)[a-z]+( [a-z]+)+$";
        if (!createUserRequest.getName().matches(regex)){
            throw new IllegalArgumentException("Invalid name: " + createUserRequest.getUserName() + """
                    Name must contain only letters for firstname and lastname separated by a space
                    """);
        }
    }
}