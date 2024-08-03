package com.example.turnikas_back_end.user;

import com.example.turnikas_back_end.business.team.repository.TeamRepository;
import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.repository.ContactRepository;
import com.example.turnikas_back_end.business.user.repository.UserRepository;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import com.example.turnikas_back_end.business.user.service.UserService;
import com.example.turnikas_back_end.exception.EmailAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = EmailAlreadyExistsException.class)
    public void testRegisterUser_EmailAlreadyExists() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail("treicarol@gmail.com");

        when(userRepository.emailExists(anyString())).thenReturn(true);

        userService.registerUser(userRegistration);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail("test@example.com");

        when(userRepository.emailExists(anyString())).thenReturn(false);
        when(userRepository.add(any(UserRegistration.class))).thenReturn(1);
        when(teamRepository.createDefaultTeam(anyInt(), any(LocalDate.class))).thenReturn(1);

        UserDTO result = userService.registerUser(userRegistration);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Integer.valueOf(1), result.getDefaultTeam());

        verify(contactRepository, times(1)).add(any(UserRegistration.class));
    }
}
