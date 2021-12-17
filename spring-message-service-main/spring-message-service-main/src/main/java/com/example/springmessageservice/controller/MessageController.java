package com.example.springmessageservice.controller;


import com.example.springmessageservice.pojo.Message;
import com.example.springmessageservice.repository.MessageService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/get/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getMessage(@RequestHeader(value = "name") String token)  {
        List<Message> messageList = messageService.findAll();
            return ResponseEntity.ok(messageList);
    }

    @RequestMapping(value = "/post/createMsg", method = RequestMethod.POST)
    public ResponseEntity<?> createMessage(@RequestBody Message msg){
        boolean isComplete = messageService.createMsg(msg);
        return ResponseEntity.ok(isComplete);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(){
        return "You look to good";
    }

    @RequestMapping(value = "/gif/{search}", method = RequestMethod.GET)
    public ResponseEntity<?> getGif(@PathVariable("search") String search){
        ArrayList<String> listUrl = messageService.getgif(search);
        return ResponseEntity.ok(listUrl);
    }

}
