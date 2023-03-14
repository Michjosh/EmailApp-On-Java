package com.email.project.dtos.responses;
import lombok.Data;

@Data
public class FindUserResponse {
    private String name;
    private String email;
    private String password;
    private String userName;

}

