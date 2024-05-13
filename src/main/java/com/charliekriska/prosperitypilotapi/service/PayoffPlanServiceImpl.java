package com.charliekriska.prosperitypilotapi.service;

import com.charliekriska.prosperitypilotapi.dao.DebtsDao;
import com.charliekriska.prosperitypilotapi.model.PayoffStage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PayoffPlanServiceImpl implements PayoffPlanService {

    @Autowired
    DebtsDao debtsDao;

    @Override
    public List<PayoffStage> calculatePayoffStages() {
        return null;
    }
}
