package com.example.turnikas_back_end.business.user.service;

import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.repository.ContactRepository;
import com.example.turnikas_back_end.business.user.repository.UserRepository;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    ContactRepository contactRepository;

    @Autowired
    public UserService(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public UserDTO registerUser(UserRegistration userRegistration) {
        Integer userId = userRepository.add(userRegistration);
        userRegistration.setId(userId);
        contactRepository.add(userRegistration);
        return new UserDTO(userId);
    }

    public UserDTO loginUser(UserDTO user) {
        return userRepository.findUserByLoginInformation(user);
    }

    @SuppressWarnings("unchecked")
    public List<UserDTO> getAllUsers() {
        return (List<UserDTO>) userRepository.findAll();
    }
}
