package com.example.userservice.repository;

import com.example.userservice.UserMessage;
import com.example.userservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private String name;

    public UserService (UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> getAllUser () {
        return userRepository.findAllUser();
    }

    public User registerUser (User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setName(user.getName());
        user.setItems(user.getItems());
        return userRepository.save(user);
    }

    public User getUser (String n) {
        return userRepository.findUsersByName(n);
    }

    public User getUserAuth (String n) {
        return userRepository.findUsersByNameAuth(n);
    }

}
