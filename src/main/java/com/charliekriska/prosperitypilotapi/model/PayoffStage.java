package com.charliekriska.prosperitypilotapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayoffStage {

    private int payoffStageId;
    private int months;
    private String mainPaymentName;
    private double mainPaymentAmount;
    private String secondaryPaymentName;
    private String secondaryPaymentAmount;
    private List<Debt> debts;

}
