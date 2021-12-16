package com.example.authenticationservice.account.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(value = "Account")
public class Account {
    @Id
    private String _id;
    private String name;
    private String password;
    private ArrayList<Object> items;
}
