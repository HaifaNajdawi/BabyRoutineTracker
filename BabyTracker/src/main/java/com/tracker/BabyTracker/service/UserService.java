package com.tracker.BabyTracker.service;

import com.tracker.BabyTracker.Exception.ExistRecord;
import com.tracker.BabyTracker.entity.User;
import com.tracker.BabyTracker.model.ResponseUser;
import com.tracker.BabyTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseUser findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        ResponseUser responseUser = new ResponseUser();

        if (user != null) {
            responseUser.setName(user.getName());
            responseUser.setEmail(user.getEmail());
        }
        return user != null ? responseUser : null;

    }

    public ResponseEntity<?> createUser(String name, String email, String password) {
        User user = new User();
        String passwordFormat = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (findByEmail(email) == null) {
            if (password.matches(passwordFormat)) {
                user.setName(name);
                user.setEmail(email);
                user.setCreatedAt(LocalDateTime.now());
                String encodedPassword = encodedPassword(password);
                user.setPassword(encodedPassword);
                userRepository.save(user);
                ResponseUser responseUser = new ResponseUser();
                responseUser.setName(user.getName());

                return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ExistRecord("The password missed one or more condition").getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ExistRecord("This record already Exist").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<ResponseUser> allUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            ResponseUser responseUser = new ResponseUser();
            responseUser.setName(user.getName());
            responseUser.setEmail(user.getEmail());
            return responseUser;
        }).collect(Collectors.toList());
    }

    public String encodedPassword(String password) {
        return passwordEncoder.encode(password);

    }

    public boolean decodedPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

}
