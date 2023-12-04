package com.example.turnikas_back_end.business.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int userId;
    private int roleCode;
    private String email;
    private String password;

    public User(int userId, int roleCode, String email, String password) {
        this.userId = userId;
        this.roleCode = roleCode;
        this.email = email;
        this.password = password;
    }


}
