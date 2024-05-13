package com.charliekriska.prosperitypilotapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Debt {

    public Debt(String debtName, String debtType, double balance, double apr) {
        this.debtName = debtName;
        this.debtType = debtType;
        this.balance = balance;
        this.apr = apr;
    }

    private int debtId;
    private String debtName;
    private String debtType;
    private double balance;
    private double apr;

}
