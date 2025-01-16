package com.tracker.BabyTracker.controller;

import com.tracker.BabyTracker.Exception.ExistRecord;
import com.tracker.BabyTracker.model.User;
import com.tracker.BabyTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(path = "/newUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        if(userService.findByEmail(user.getEmail()) == null){
        return new ResponseEntity<>(userService.createUser(user.getName(),user.getEmail()), HttpStatus.CREATED);}
        else {
            return new ResponseEntity<>(new ExistRecord("Already Exist").getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
