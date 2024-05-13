package com.charliekriska.prosperitypilotapi.service;

import com.charliekriska.prosperitypilotapi.dao.DebtsDao;
import com.charliekriska.prosperitypilotapi.model.Debt;
import com.charliekriska.prosperitypilotapi.model.PayoffStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PayoffPlanServiceImpl implements PayoffPlanService {

    @Autowired
    DebtsDao debtsDao;

    @Override
    public List<PayoffStage> calculatePayoffStages(double additionalPayment) {

        List<Debt> debts = debtsDao.getAllDebts();
        Collections.sort(debts, new DebtComparator());

        double totalBalance = debts.stream().mapToDouble(debt -> debt.getBalance()).sum();

        int months = 0;
        double carryover = 0.0;

        for(Debt debt : debts) {
            double currentDebtBalance = debt.getBalance() - carryover;
            System.out.println(currentDebtBalance);
            while(currentDebtBalance >= 0.0) {
                // Since you are making payments on all debts concurrently, you need to apply this code simultaneously on the other debts as well
                // while only having the additionalPayment affect the first debt in the list
                currentDebtBalance *= (1 + debt.getApr()/12/100);
                currentDebtBalance -= debt.getMinPayment();
                currentDebtBalance -= additionalPayment;
                months++;
                System.out.println(currentDebtBalance);
            }
            carryover = currentDebtBalance;
        }

        System.out.println(months);

        return null;
    }

    public static boolean checkForIncreasingBalance() {
        return true;
    }

    public static final class DebtComparator implements Comparator<Debt> {

        @Override
        public int compare(Debt o1, Debt o2) {
            return Double.compare(o2.getApr(), o1.getApr());
        }
    }
}
