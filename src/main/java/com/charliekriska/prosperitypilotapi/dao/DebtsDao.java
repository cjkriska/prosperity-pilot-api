package com.charliekriska.prosperitypilotapi.dao;

import com.charliekriska.prosperitypilotapi.model.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DebtsDao {

    @Autowired
    JdbcTemplate jdbc;

    public List<Debt> getAllDebts() {
        final String SELECT_ALL_DEBTS = "SELECT * FROM debtsdata;";
        List<Debt> debts = jdbc.query(SELECT_ALL_DEBTS, new DebtMapper());
        return debts;
    }

    @Transactional
    public Debt addDebt(Debt debt) {
        final String INSERT_DEBT = "INSERT INTO debtsdata(debtName, debtType, balance, apr, minPayment) " +
                "VALUES(?,?,?,?,?)";

        jdbc.update(INSERT_DEBT,
                debt.getDebtName(),
                debt.getDebtType(),
                debt.getBalance(),
                debt.getApr(),
                debt.getMinPayment()
                );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        debt.setDebtId(newId);

        return debt;

    }

    public static final class DebtMapper implements RowMapper<Debt> {

        @Override
        public Debt mapRow(ResultSet rs, int rowNum) throws SQLException {

            Debt debt = new Debt();
            debt.setDebtId(rs.getInt("debtId"));
            debt.setDebtName(rs.getString("debtName"));
            debt.setDebtType(rs.getString("debtType"));
            debt.setBalance(rs.getDouble("balance"));
            debt.setApr(rs.getDouble("apr"));
            debt.setMinPayment(rs.getDouble("minPayment"));
            return debt;

        }

    }

}
