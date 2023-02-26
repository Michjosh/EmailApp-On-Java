package services;

import data.model.User;
import dtos.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        userDTO = new UserDTO();
        userDTO.setName("Michael");
        userDTO.setUserName("mc");
        userDTO.setPassword("1234");
        userDTO.setEmail("michael@gmail.com");
    }

    @Test
    void createAccountTest() {
        User savedUser = userService.createAccount(userDTO);
        assertTrue(savedUser.getId() !=0);
    }

    @Test
    void loginTest() {
        User savedUser = userService.createAccount(userDTO);
        userService.login(1);
        assertEquals(1, savedUser.getId());
    }

    @Test
    void deleteOneUserAccountAfterCreatingTwoTest() {
        User savedUser = userService.createAccount(userDTO);
        assertTrue(savedUser.getId() !=0);
        UserDTO userDTO1;
        userDTO1 = new UserDTO();
        userDTO1.setName("Michael");
        userDTO1.setUserName("mi");
        userDTO1.setPassword("1234");
        userDTO1.setEmail("michael@gmail.com");
        userService.createAccount(userDTO1);
        userService.countUsers();
        assertEquals(2, userService.countUsers());
        userService.deleteAccount(1);
        assertEquals(1, userService.countUsers());
    }

    @Test
    void findUserById() {
        userService.createAccount(userDTO);
        userService.findUserById(1);
        System.out.println(userService.findUserById(1));
        assertEquals(1,userService.countUsers());
    }

    @Test
    void countUsers() {
        userService.createAccount(userDTO);
        userService.countUsers();
        assertEquals(1, userService.countUsers());
    }
    @Test
    void duplicateUserNameThrowsExceptionTest() {
        userDTO.setName("Michael");
        userDTO.setUserName("mc");
        userDTO.setPassword("1234");
        userDTO.setEmail("michael@gmail.com");
        userService.createAccount(userDTO);
        assertThrows(IllegalArgumentException.class, () -> userService.createAccount(userDTO));
    }
}