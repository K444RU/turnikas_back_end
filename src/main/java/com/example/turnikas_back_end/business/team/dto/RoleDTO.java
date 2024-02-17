package com.example.turnikas_back_end.business.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int roleCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;
}
