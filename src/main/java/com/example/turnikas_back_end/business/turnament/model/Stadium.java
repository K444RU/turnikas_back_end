package com.example.turnikas_back_end.business.turnament.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stadium {
    private int id;
    private int cityId;
    private String name;
}
