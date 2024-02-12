package com.example.turnikas_back_end.business.turnament.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerAmountDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int amountCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int amountName;
}
