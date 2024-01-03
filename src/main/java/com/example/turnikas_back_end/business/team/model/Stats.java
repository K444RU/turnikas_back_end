package com.example.turnikas_back_end.business.team.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stats {
    private int id;
    private int played;
    private int won;
    private int lost;
    private int draw;
    private int goalFor;
    private int goalAgainst;
}
