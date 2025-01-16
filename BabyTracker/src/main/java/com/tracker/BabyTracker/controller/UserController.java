package com.tracker.BabyTracker.controller;

import com.tracker.BabyTracker.model.User;
import com.tracker.BabyTracker.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(path = "/newUser")
    public User addUser(@RequestBody String name, String email){
        return userService.createUser(name,email);

    }
}
