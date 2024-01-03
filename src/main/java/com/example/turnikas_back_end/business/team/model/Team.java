package com.example.turnikas_back_end.business.team.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Team {
    private int id;
    private int userId;
    private int categoryCode;
    private int statsId;
    private String teamName;
    private byte[] teamLogo;
    private String teamCoachName;
}
