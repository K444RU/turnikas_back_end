package com.example.turnikas_back_end.business.turnament.dto;

import com.example.turnikas_back_end.business.team.dto.TeamDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TournamentGroupDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TeamDTO> teams;

    public TournamentGroupDTO(String name, List<TeamDTO> teams) {
        this.name = name;
        this.teams = teams;
    }
}
