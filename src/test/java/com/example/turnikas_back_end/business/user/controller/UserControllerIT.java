package com.example.turnikas_back_end.business.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser() throws Exception {
        String uniqueEmail = "integration.test" + Math.random() + "@test.com";

        String userRegistrationJson = """
            {
                "email": "%s",
                "password": "password",
                "passwordRepeat": "password",
                "firstName": "John",
                "lastName": "Deer",
                "dateOfBirth": "1990-01-01"
            }
            """.formatted(uniqueEmail);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegistrationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.defaultTeam").isNumber());
    }

    @Test
    void loginUser() throws Exception {
        String uniqueEmail = "login.test" + Math.random() + "@test.com";

        String userRegistrationJson = """
            {
                "email": "%s",
                "password": "password",
                "passwordRepeat": "password",
                "firstName": "John",
                "lastName": "Deer",
                "dateOfBirth": "1990-01-01"
            }
            """.formatted(uniqueEmail);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegistrationJson))
                .andExpect(status().isOk());

        String userLoginJson = "{\"email\":\"%s\",\"password\":\"password\"}".formatted(uniqueEmail);

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userLoginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void loginUserInvalidEmail() throws Exception {
        String userJson = "{\"email\":\"invalid@email.com\",\"password\":\"123\"}";

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }
}
