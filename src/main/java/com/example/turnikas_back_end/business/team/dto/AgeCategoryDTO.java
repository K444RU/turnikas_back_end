package com.example.turnikas_back_end.business.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgeCategoryDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int categoryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryName;
}
