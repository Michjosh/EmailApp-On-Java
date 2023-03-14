package services;

import com.email.project.data.model.User;
import com.email.project.dtos.requests.CreateUserRequest;
import com.email.project.services.UserService;
import com.email.project.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;

    private CreateUserRequest createUserRequest;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        createUserRequest = new CreateUserRequest();
        createUserRequest.setName("Michael");
        createUserRequest.setUserName("mc");
        createUserRequest.setPassword("1234");
        createUserRequest.setEmailAddress("michael@gmail.com");
    }

    @Test
    void createAccountTest() {
        userService = new UserServiceImpl();
        createUserRequest = new CreateUserRequest();
        createUserRequest.setName("Michael");
        createUserRequest.setUserName("mc");
        createUserRequest.setPassword("1234");
        createUserRequest.setEmailAddress("michael@gmail.com");
        User savedUser = userService.createAccount(createUserRequest);
       assertNotNull(savedUser);
    }

    @Test
    void loginTest() {
        User savedUser = userService.createAccount(createUserRequest);
        userService.login("1");
        assertEquals(1, savedUser.getUserId());
    }

    @Test
    void deleteOneUserAccountAfterCreatingTwoTest() {
        User savedUser = userService.createAccount(createUserRequest);
//        assertTrue(savedUser.getUserId() !=0);
        CreateUserRequest createUserRequest1;
        createUserRequest1 = new CreateUserRequest();
        createUserRequest1.setName("Michael");
        createUserRequest1.setUserName("mi");
        createUserRequest1.setPassword("1234");
        createUserRequest1.setEmailAddress("michael@gmail.com");
        userService.createAccount(createUserRequest1);
        userService.deleteAccount(savedUser);
        assertNull(savedUser);
    }

    @Test
    void findUserById() {
        userService.createAccount(createUserRequest);
        userService.findUserById("1");
        System.out.println(userService.findUserById("1"));
    }

    @Test
    void duplicateUserNameThrowsExceptionTest() {
        createUserRequest.setName("Michael");
        createUserRequest.setUserName("mc");
        createUserRequest.setPassword("1234");
        createUserRequest.setEmailAddress("michael@gmail.com");
        userService.createAccount(createUserRequest);
        assertThrows(IllegalArgumentException.class, () -> userService.createAccount(createUserRequest));
    }
}