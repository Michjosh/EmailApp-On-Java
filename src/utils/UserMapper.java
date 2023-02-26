package utils;

import data.model.User;
import dtos.UserDTO;

public class UserMapper {
    public static UserDTO toDTO(UserDTO userDTO){
    User user1 = new User();
    user1.setName(userDTO.getName());
    user1.setUserName(userDTO.getUserName());
    user1.setEmail(userDTO.getEmail());
    user1.setPassword(userDTO.getPassword());
    user1.setEmail(userDTO.getEmail());
    return userDTO;
    }

    public static UserDTO toModel(UserDTO user){
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName(user.getName());
        userDTO1.setUserName(user.getUserName());
        userDTO1.setEmail(user.getEmail());
        userDTO1.setPassword(user.getPassword());
        userDTO1.setEmail(user.getEmail());
        return user;
    }
}
