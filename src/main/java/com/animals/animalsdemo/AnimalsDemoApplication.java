package com.animals.animalsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
public class AnimalsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalsDemoApplication.class, args);
    }

}
