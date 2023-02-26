package data.repo;

import data.model.User;

public interface UserRepo {

    User createNewUser(User user);

    User findById(int id);

    User findByUserName(String name);

    int countUsers();

    void findAllUsers();

    User updateUserDetails(int id, User user);

    void deleteUserById(int Id);

    void deleteAllUsers();
}
