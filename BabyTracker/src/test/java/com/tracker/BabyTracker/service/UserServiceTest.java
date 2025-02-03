package com.tracker.BabyTracker.service;

import com.tracker.BabyTracker.entity.User;
import com.tracker.BabyTracker.model.ResponseUser;
import com.tracker.BabyTracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserService userService;
    User user = new User();
    ResponseUser responseUser = new ResponseUser();
    String name = "Yazan";
    String validPassword = "Valid*123";
    String invalidPassword = "weakpass";

    String email = "test@test.com";
    String email2 = "test2@test.com";


    @BeforeEach
    public void setup() {
        user.setName("Test");
        user.setEmail(email);
        user.setPassword("23416Lj*");
        user.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void findByEmail_whenUserExist() {
        when(userRepository.findByEmail(email)).thenReturn(user);
        responseUser.setEmail(email);
        responseUser.setName("Test");
        ResponseUser result = userService.findByEmail(email);
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void findByEmail_whenUserNotExist() {
        when(userRepository.findByEmail(email2)).thenReturn(null);
        ResponseUser result = userService.findByEmail(email2);
        assertNull(result);
    }

    @Test
    void createUser_addUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userService.encodedPassword(anyString())).thenReturn("encodedPassword123");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ResponseEntity<?> response = userService.createUser(name, email, validPassword);
        assertEquals(CREATED, response.getStatusCode());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void createUser_mismatchPassword(){
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        ResponseEntity<?> response = userService.createUser(name, email, invalidPassword);
        assertEquals(BAD_REQUEST,response.getStatusCode());
        assertEquals("The password missed one or more condition",response.getBody());
    }
    @Test
    void createUser_emailExist(){
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        ResponseEntity<?> response = userService.createUser(name, email, validPassword);
        assertEquals("This record already Exist",response.getBody());

    }
}