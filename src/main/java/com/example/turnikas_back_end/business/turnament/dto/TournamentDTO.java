package com.example.turnikas_back_end.business.turnament.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TournamentDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int ageCategoryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int playerAmountCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int cityId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int stadiumId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate startDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int participationPrise;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String prize;
    @Nullable
    private String additionalInfo;

    public TournamentDTO(int id){
        this.id = id;
    }
}
