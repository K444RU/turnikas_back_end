package com.example.turnikas_back_end.business.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Contact {
    private int userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
