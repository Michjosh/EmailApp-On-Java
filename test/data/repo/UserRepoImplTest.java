package data.repo;

import data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoImplTest {
    private UserRepo userRepo;
    private User user;

    @BeforeEach
    void setUp() {
        userRepo = new UserRepoImpl();
        user = new User();

        user.setName("Mike");
        user.setEmail("michaeljoshua@gmail.com");
        user.setPassword("1234");

    }

    @Test
    void createNewUserTest() {
        userRepo.createNewUser(user);
        assertEquals(1, userRepo.countUsers());

    }

    @Test
    void findByIdTest() {
       User savedUser = userRepo.createNewUser(user);
       userRepo.findById(1);
       assertEquals(1, savedUser.getId());
    }

    @Test
    void countUsersAfterCreatingTwoTest() {
        userRepo.createNewUser(user);
        assertEquals(1, userRepo.countUsers());
        User savedUser2 = new User();
        savedUser2.setName("Josh");
        savedUser2.setPassword("1234");
        savedUser2.setEmail("mymy@gmail.com");
        userRepo.createNewUser(savedUser2);
        assertEquals(2, userRepo.countUsers());
    }

    @Test
    void updateUserDetailsTest(){
        userRepo.createNewUser(user);
        userRepo.updateUserDetails(1, user);
        user.setName("Josh");
        user.setEmail("mymy@gmail.com");
        assertEquals("mymy@gmail.com", user.getEmail());
    }


    @Test
    void deleteUserByIdTest() {
        userRepo.createNewUser(user);
        userRepo.deleteUserById(1);
        assertEquals(0, userRepo.countUsers());
    }

    @Test
    void deleteAllUsersTest() {
        userRepo.createNewUser(user);
        User savedUser2 = new User();
        savedUser2.setName("Josh");
        savedUser2.setPassword("1234");
        savedUser2.setEmail("mymy@gmail.com");
        userRepo.createNewUser(savedUser2);
        userRepo.deleteAllUsers();
        assertEquals(0, userRepo.countUsers());
    }
}