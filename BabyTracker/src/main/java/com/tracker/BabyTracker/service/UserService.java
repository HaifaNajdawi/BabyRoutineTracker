package com.tracker.BabyTracker.service;

import com.tracker.BabyTracker.model.User;
import com.tracker.BabyTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User createUser(String name, String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }
}
