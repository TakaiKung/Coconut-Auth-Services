package com.example.springmessageservice.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("message")
public class Message {

    @Id
    private String _id;
    private String title;
    private String message;
    private String image;
}
