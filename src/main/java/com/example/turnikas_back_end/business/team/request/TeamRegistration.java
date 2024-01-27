package com.example.turnikas_back_end.business.team.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamRegistration {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int statsId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int categoryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teamName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teamCoachName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teamLogo;
}
