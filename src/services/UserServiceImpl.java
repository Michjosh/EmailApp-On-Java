package services;

import data.model.User;
import data.repo.UserRepo;
import data.repo.UserRepoImpl;
import dtos.UserDTO;
import utils.UserMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepo userRepo = new UserRepoImpl();
    int count = 0;

    @Override
    public User createAccount(UserDTO userDTO) {
        if (userExist(userDTO.getUserName())) {
            throw new IllegalArgumentException(userDTO.getUserName() + " already exists");
        }
        count++;
        return userRepo.createNewUser(UserMapper.toModel(userDTO));

    }

    @Override
    public User updateAccount(int id, UserDTO userDTO) {
        return userRepo.updateUserDetails(id, userDTO);
    }

    private int generateId() {
        return count + 1;
    }

    private boolean userExist(String username) {
        User foundUser = userRepo.findByUserName(username);
        if (foundUser != null) return true;
        return false;
    }

    @Override
    public void login(int id) {
        try {
            userRepo.findById(id);
        } catch (NoSuchElementException e) {
            System.err.println("User with username does not exist.");
        }
    }

    @Override
    public void deleteAccount(int id) {
        userRepo.deleteUserById(1);
        count--;
    }

    @Override
    public User findUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public Object findByUserName(String username) {
        Object result = userRepo.findByUserName(username);
        return Optional.ofNullable(result).orElseThrow(() ->
                new NoSuchElementException("No user found with username: " + username));
    }




    @Override
    public int countUsers() {
        return count;
    }
}
