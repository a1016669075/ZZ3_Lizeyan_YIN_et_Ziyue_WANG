package com.example.demo.service.gson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

public class MyGson {
    @SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
    public class GsonSpringBootApplication {

        public static void main(String[] args) {
            SpringApplication.run(GsonSpringBootApplication.class, args);
        }
    }
}
