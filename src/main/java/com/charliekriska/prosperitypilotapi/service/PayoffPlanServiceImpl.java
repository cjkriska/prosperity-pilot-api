package com.charliekriska.prosperitypilotapi.service;

import com.charliekriska.prosperitypilotapi.dao.DebtsDao;
import com.charliekriska.prosperitypilotapi.model.Debt;
import com.charliekriska.prosperitypilotapi.model.PayoffStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PayoffPlanServiceImpl implements PayoffPlanService {

    @Autowired
    DebtsDao debtsDao;

    @Override
    public List<PayoffStage> calculatePayoffStages(double additionalPayment) {

        List<PayoffStage> payoffStages = new ArrayList<>();

        List<Debt> debts = debtsDao.getAllDebts();
        Collections.sort(debts, new DebtComparator());

        double totalMonthlyMinPayment = debts.stream().mapToDouble(debt -> debt.getMinPayment()).sum();


        return payoffStages;
    }


    public List<PayoffStage> calculatePayoffStagesTST(double additionalPayment) {

        List<PayoffStage> payoffStages = new ArrayList<PayoffStage>();

        List<Debt> debts = debtsDao.getAllDebts();
        Collections.sort(debts, new DebtComparator());

        double totalBalance = debts.stream().mapToDouble(debt -> debt.getBalance()).sum();

        int months = 0;
        int prevMonths = 0;
        double carryover = 0.0;

        while(!debts.isEmpty()) {

            for(int i=0; i < debts.size(); i++) {

                if(debts.get(i).getBalance() >= 0.0) {
                    debts.get(i).setBalance(debts.get(i).getBalance() * (1 + debts.get(i).getApr()/12/100));
                    debts.get(i).setBalance(debts.get(i).getBalance() - debts.get(i).getMinPayment());
                    debts.get(i).setBalance(debts.get(i).getBalance() - carryover);
                    carryover = 0.0;
                    if(i == 0) {
                        debts.get(i).setBalance(debts.get(i).getBalance() - additionalPayment);
                    }
                } else {
                    carryover = Math.abs(debts.get(i).getBalance());
                    additionalPayment += debts.get(i).getMinPayment();
                    payoffStages.add(PayoffStage.builder()
                            .payoffStageId(payoffStages.size()+1)
                            .months(months-prevMonths)
                            .debts(List.of(debts.get(i)))
                            .build());
                    // TODO
                    payoffStages.add(PayoffStage.builder()
                            .payoffStageId(payoffStages.size()+1)
                            .months(1)
                            .debts(List.of(debts.get(i), debts.size()>1 ? debts.get(i+1) : debts.get(i)))
                            .build());
                    debts.remove(i);
                    prevMonths = months;
                    months--;
                }

            }

            months++;

        }

        System.out.println("TOTAL MONTHS: " + months);
        System.out.println("TOTAL YEARS: " + months/12 + " Years, " + months%12 + " months");

        return payoffStages;
    }

    // TODO
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
