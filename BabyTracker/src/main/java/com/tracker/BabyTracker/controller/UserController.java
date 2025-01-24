package com.tracker.BabyTracker.controller;

import com.tracker.BabyTracker.Exception.ExistRecord;
import com.tracker.BabyTracker.entity.User;
import com.tracker.BabyTracker.model.ResponseUser;
import com.tracker.BabyTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/newUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) == null) {
            return new ResponseEntity<>(userService.createUser(user.getName(), user.getEmail(), user.getPassword()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ExistRecord("Already Exist").getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        return new ResponseEntity<>(userService.allUsers(),HttpStatus.OK);
    }
}
