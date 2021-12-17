package com.example.userservice;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserMessage implements Serializable {
    private String name;
    private Object massage;
}
