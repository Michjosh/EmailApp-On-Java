package com.email.project.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String userId;
    private String name;
    private String emailAddress;
    private String userName;
    private String password;
}
