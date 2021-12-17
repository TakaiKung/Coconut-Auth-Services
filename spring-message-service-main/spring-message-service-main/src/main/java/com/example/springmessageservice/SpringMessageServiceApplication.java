package com.example.springmessageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMessageServiceApplication.class, args);
    }

}
