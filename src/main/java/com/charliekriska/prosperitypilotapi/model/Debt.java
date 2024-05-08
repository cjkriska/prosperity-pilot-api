package com.charliekriska.prosperitypilotapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Debt {

    public Debt() {}

    public Debt(String debtName, String debtType, double balance, double apr) {
        this.debtName = debtName;
        this.debtType = debtType;
        this.balance = balance;
        this.apr = apr;
    }

    public Debt(int debtId, String debtName, String debtType, double balance, double apr) {
        this.debtId = debtId;
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
