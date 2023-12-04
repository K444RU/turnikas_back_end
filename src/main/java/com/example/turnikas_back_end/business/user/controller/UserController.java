package com.example.turnikas_back_end.business.user.controller;

import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.model.Contact;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import com.example.turnikas_back_end.business.user.service.ContactService;
import com.example.turnikas_back_end.business.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ContactService contactService;

    @Autowired
    public UserController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @PostMapping("/register")
    @Operation(summary = "Post user information (new user register)")
    public UserDTO registerUser(@RequestBody UserRegistration userRegistration) {
        return userService.registerUser(userRegistration);
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody UserDTO userLogin) {
        return userService.loginUser(userLogin);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/contact/{userId}")
    public List<Contact> getContactByUserId(@PathVariable int userId){
        return contactService.getContactsByUserId(userId);
    }
}

