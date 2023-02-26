package services;

import data.model.User;
import dtos.UserDTO;

public interface UserService {

    User createAccount(UserDTO userDTO);

    User updateAccount(int id, UserDTO userDTO);

    void login(int id);

    void deleteAccount(int id);

    User findUserById(int id);

    Object findByUserName(String username);

    int countUsers();
}
