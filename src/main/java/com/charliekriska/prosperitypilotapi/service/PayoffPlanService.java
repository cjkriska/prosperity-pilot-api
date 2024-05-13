package com.charliekriska.prosperitypilotapi.service;

import com.charliekriska.prosperitypilotapi.model.PayoffStage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayoffPlanService {

    public List<PayoffStage> calculatePayoffStages();

}
