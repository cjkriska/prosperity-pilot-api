package com.charliekriska.prosperitypilotapi.service;

import com.charliekriska.prosperitypilotapi.model.PayoffStage;

import java.util.List;

public interface PayoffPlanService {

    public List<PayoffStage> calculatePayoffStages(double additionalPayment);

}
