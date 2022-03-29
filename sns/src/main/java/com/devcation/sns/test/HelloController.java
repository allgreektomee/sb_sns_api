package com.devcation.sns.test;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//RESTful API Postman Test
@RestController
@RequestMapping("test")
public class
HelloController {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    public String helloPath(){

        return "hello GetMapping /hello ";
    }

    @GetMapping("/{id}")
    public String helloWithPathVariable(@PathVariable(required = false) int id){

        return "hello GetMapping /id " + id;
    }
}
