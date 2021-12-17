package com.example.userservice.contoller;

import com.example.userservice.MQConfig;
import com.example.userservice.pojo.Message;
import com.example.userservice.pojo.RabbitInventory;
import com.example.userservice.pojo.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/add/inventory", method = RequestMethod.POST)
    public ResponseEntity<?> addInventory(@RequestBody Message message, @RequestHeader(value = "name") String name){
        RabbitInventory rabbitInventory = new RabbitInventory("name",message);

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, rabbitInventory);
        return ResponseEntity.ok(true);
    }


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
