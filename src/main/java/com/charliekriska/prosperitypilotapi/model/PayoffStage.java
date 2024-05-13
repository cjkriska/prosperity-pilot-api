package com.charliekriska.prosperitypilotapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayoffStage {

    private int payoffStageId;
    private int months;
    private String debtName;
    private double apr;

}
