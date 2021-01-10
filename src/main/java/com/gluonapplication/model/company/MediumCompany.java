package com.gluonapplication.model.company;
import java.util.concurrent.atomic.AtomicInteger;

public class MediumCompany implements Company {

    private Double budgetConstant = 0.1;
    private int IQ = 110;
    private Double reputation = 2.5;
    private final AtomicInteger budget = new AtomicInteger(0);

    @Override
    public void updateBudgetConstant() {
    }

    @Override
    public synchronized void updateBudget() {
        budget.incrementAndGet();
    }
    @Override
    public synchronized void makeBusinessDecision() {
        budget.addAndGet(100);
    }

    @Override
    public String toString() {
        return "MediumCompany{" +
                "budgetConstant=" + budgetConstant +
                ", budget=" + budget +
                ", IQ=" + IQ +
                ", reputation=" + reputation +
                ", budgett=" + budget +
                '}';
    }
}
