package com.tracker.BabyTracker.service;

import com.tracker.BabyTracker.Exception.ExistRecord;
import com.tracker.BabyTracker.model.User;
import com.tracker.BabyTracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<?> createUser(String name, String email, String password) {
        User user = new User();
        String passwordFormat = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (findByEmail(email) == null) {
            if (password.matches(passwordFormat)) {
                user.setName(name);
                user.setEmail(email);
                user.setCreatedAt(LocalDateTime.now());
                user.setPassword(password);
                return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ExistRecord("The password missed one or more condition").getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ExistRecord("This record already Exist"), HttpStatus.BAD_REQUEST);
        }
    }
    public List<User> allUsers(){
        return userRepository.findAll();
    }

}
