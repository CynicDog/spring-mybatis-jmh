package com.example.web.controller;

import com.example.web.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HomeRestController {

    @GetMapping() public String home() { return "Welcome to Spring Web Application"; }

    @GetMapping("/hello") public String hello() { return "hello :)"; }

    @GetMapping("/bye") public String bye() { return "bye :)"; }

    @GetMapping("/user")
    public User user(){
        return new User("hong", "john@test.com", "john1234", "John Doe");
    }
}