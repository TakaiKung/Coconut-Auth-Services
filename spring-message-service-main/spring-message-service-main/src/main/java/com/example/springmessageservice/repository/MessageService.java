package com.example.springmessageservice.repository;


import com.example.springmessageservice.pojo.GifData;
import com.example.springmessageservice.pojo.Gifs;
import com.example.springmessageservice.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public boolean createMsg(Message msg){
        try {
            messageRepository.insert(msg);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public ArrayList<String> getgif(String search){
        Gifs gifs = WebClient.create().get().uri("https://api.giphy.com/v1/gifs/search?api_key=ps6eW0EdM79pYdF40WxaSOPMH4rLOMEZ&q=+"+ search +"&limit=5&offset=0&rating=g&lang=en")
                .retrieve().bodyToMono(Gifs.class).block();

        ArrayList<String> listUrl = new ArrayList<>();

        for (GifData var : gifs.getData())
        {
            listUrl.add(var.getImages().getOriginal().getUrl());
        }


        return listUrl;
    }

}
