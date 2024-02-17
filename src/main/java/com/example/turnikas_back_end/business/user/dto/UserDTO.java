package com.example.turnikas_back_end.business.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String passwordRepeat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer defaultTeam;

    public UserDTO(int id) {
        this.id = id;
    }

    public UserDTO(int id, Integer defaultTeam) {
        this.id = id;
        this.defaultTeam = defaultTeam;
    }
}
