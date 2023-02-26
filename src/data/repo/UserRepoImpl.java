package data.repo;

import data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo{
    private final List<User> users = new ArrayList<>();
    private int count = 0;

    @Override
    public User createNewUser(User user) {
        boolean userHasNotBeenSaved = user.getId() == 0;
        if(userHasNotBeenSaved){
            user.setId(generateId());
            this.users.add(user);
            count++;
        }
        return user;
    }

    private int generateId() {
        return count+1;
    }

    @Override
    public User findById(int id) {
        return users.get(id-1);
    }

    @Override
    public User findByUserName(String username) {
        for (User user : this.users){
            if (user.getUserName().equalsIgnoreCase(username)) return user;
        }
        return null;
}

    @Override
    public int countUsers() {
        return users.size();
    }

    @Override
    public void findAllUsers() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Override
    public User updateUserDetails(int id, User user) {
            User existingUser = findById(id);
            if (existingUser != null) {
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                return existingUser;
            } else {
                return null;
            }
    }

    @Override
    public void deleteUserById(int Id) {
        users.removeIf(user -> user.getId() == Id);
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }
}
