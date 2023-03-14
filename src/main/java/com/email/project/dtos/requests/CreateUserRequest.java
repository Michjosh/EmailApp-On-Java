package com.email.project.dtos.requests;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String emailAddress;
    private String password;
    private String userName;

}
