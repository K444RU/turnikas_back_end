package com.example.turnikas_back_end.business.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int categoryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int statsId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teamName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private byte[] teamLogo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teamCoachName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int roleCode;

    public TeamDTO(int id){
        this.id = id;
    }

    public TeamDTO(int id, int userId, int categoryCode, int statsId, String teamName, byte[] teamLogo, String teamCoachName, int roleCode) {
        this.id = id;
        this.userId = userId;
        this.categoryCode = categoryCode;
        this.statsId = statsId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teamCoachName = teamCoachName;
        this.roleCode = roleCode;
    }

}
