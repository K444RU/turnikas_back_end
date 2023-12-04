package com.example.turnikas_back_end.business.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role {
    private int roleCode;
    private String roleName;

    public Role(int roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }
}
