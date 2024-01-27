package com.example.turnikas_back_end.business.turnament.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Tournament {
    private int id;
    private int ageCategoryCode;
    private int playerAmountCode;
    private int cityId;
    private int stadiumId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int participationPrise;
    private String prize;
    private String additionalInfo;
}
