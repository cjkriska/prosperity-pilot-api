package com.charliekriska.prosperitypilotapi.controller;

import com.charliekriska.prosperitypilotapi.dao.DebtsDao;
import com.charliekriska.prosperitypilotapi.model.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DebtPayoffController {

    @Autowired
    DebtsDao debtsDao;

    @GetMapping("/debts")
    public List<Debt> getAllDebts() {
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/debt")
    public Debt addDebt(@RequestBody Debt debt) {
        return debtsDao.addDebt(debt);
    }

}
