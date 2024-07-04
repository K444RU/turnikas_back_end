package com.example.turnikas_back_end.business.user.service;

import com.example.turnikas_back_end.business.team.repository.TeamRepository;
import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.repository.ContactRepository;
import com.example.turnikas_back_end.business.user.repository.UserRepository;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import com.example.turnikas_back_end.exception.EmailAlreadyExistsException;
import org.jooq.generated.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    ContactRepository contactRepository;
    TeamRepository teamRepository;

    @Autowired
    public UserService(UserRepository userRepository, ContactRepository contactRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.teamRepository = teamRepository;
    }

    public UserDTO registerUser(UserRegistration userRegistration) {
        if (userRepository.emailExists(userRegistration.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Integer userId = userRepository.add(userRegistration);
        userRegistration.setId(userId);
        contactRepository.add(userRegistration);
        LocalDate dateOfBirth = userRegistration.getDateOfBirth();
        Integer defaultTeam = teamRepository.createDefaultTeam(userId, dateOfBirth);
        return new UserDTO(userId, defaultTeam);
    }


    public UserDTO loginUser(UserDTO user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser == null) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return userRepository.findUserByLoginInformation(user);
    }

    @SuppressWarnings("unchecked")
    public List<UserDTO> getAllUsers() {
        return (List<UserDTO>) userRepository.findAll();
    }
}
