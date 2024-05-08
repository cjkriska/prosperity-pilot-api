package com.charliekriska.prosperitypilotapi.dao;

import com.charliekriska.prosperitypilotapi.model.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DebtsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Transactional
    public Debt addDebt(Debt debt) {
        final String INSERT_DEBT = "INSERT INTO debtsdata(debtName, debtType, balance, apr) " +
                "VALUES(?,?,?,?)";

        jdbc.update(INSERT_DEBT,
                debt.getDebtName(),
                debt.getDebtType(),
                debt.getBalance(),
                debt.getApr()
                );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        debt.setDebtId(newId);

        return debt;

    }

}
