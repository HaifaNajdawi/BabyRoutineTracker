package com.tracker.BabyTracker.service;

import com.tracker.BabyTracker.Exception.ExistRecord;
import com.tracker.BabyTracker.model.User;
import com.tracker.BabyTracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public ResponseEntity<?> createUser(String name, String email){
        User user = new User();
        if(findByEmail(email)== null){

        user.setName(name);
        user.setEmail(email);
        user.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);}
        else {
            return new ResponseEntity<>( new ExistRecord("This record already Exist"),HttpStatus.BAD_REQUEST);
        }
    }

}
