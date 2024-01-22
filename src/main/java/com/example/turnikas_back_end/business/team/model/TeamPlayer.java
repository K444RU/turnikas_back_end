package com.example.turnikas_back_end.business.team.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamPlayer {
    private int id;
    private int teamId;
    private String firstName;
    private String lastName;
}
