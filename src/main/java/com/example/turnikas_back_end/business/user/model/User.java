package com.example.turnikas_back_end.business.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private int userId;
    private int roleCode;
    private String email;
    private String password;
}
