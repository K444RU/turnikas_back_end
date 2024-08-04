package com.example.turnikas_back_end.business.user.controller;

import com.example.turnikas_back_end.business.team.repository.TeamRepository;
import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.repository.ContactRepository;
import com.example.turnikas_back_end.business.user.repository.UserRepository;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import com.example.turnikas_back_end.business.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail("test@example.com");
        userRegistration.setPassword("password");
        userRegistration.setPasswordRepeat("password");
        userRegistration.setFirstName("John");
        userRegistration.setLastName("Doe");
        userRegistration.setDateOfBirth(LocalDate.of(1990, 1, 1));

        when(userRepository.emailExists(anyString())).thenReturn(false);
        when(userRepository.add(any(UserRegistration.class))).thenReturn(1);
        when(teamRepository.createDefaultTeam(anyInt(), any(LocalDate.class))).thenReturn(1);

        UserDTO result = userService.registerUser(userRegistration);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Integer.valueOf(1), result.getDefaultTeam());

        verify(contactRepository, times(1)).add(any(UserRegistration.class));
    }

    @Test
    void loginUser() {
        UserDTO mockUser = new UserDTO();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");

        UserDTO foundUser = new UserDTO();
        foundUser.setId(1);
        foundUser.setEmail("test@example.com");
        foundUser.setPassword("password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(foundUser);
        when(userRepository.findUserByLoginInformation(any(UserDTO.class))).thenReturn(foundUser);

        UserDTO result = userService.loginUser(mockUser);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void loginUserWithInvalidEmail() {
        UserDTO user = new UserDTO();
        user.setEmail("invalid@example.com");
        user.setPassword("wrongpassword");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.loginUser(user);
        });

        String expectedMessage = "Invalid email or password.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}