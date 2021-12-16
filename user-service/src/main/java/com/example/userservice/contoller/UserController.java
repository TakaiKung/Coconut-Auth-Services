package com.example.userservice.contoller;

import com.example.userservice.pojo.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser () {
        try {
            List<User> lst = userService.getAllUser();
            return ResponseEntity.ok(lst);
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
    }


    @RequestMapping(value = "/getUser/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser (@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.getUser(name));
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser (@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
    }

}