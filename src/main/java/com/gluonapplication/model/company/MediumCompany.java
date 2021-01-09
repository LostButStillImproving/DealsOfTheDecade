package com.gluonapplication.model.company;

public class MediumCompany implements Company{

    private Double budgetConstant = 0.1;
    private Double budget = 1000.0;
    private int IQ = 110;
    private Double reputation = 2.5;

    @Override
    public void updateBudgetConstant() {

    }

    public void updateBudget() {
        this.budget = budget + 1;
    }

    @Override
    public String toString() {
        return "MediumCompany{" +
                "budgetConstant=" + budgetConstant +
                ", budget=" + budget +
                ", IQ=" + IQ +
                ", reputation=" + reputation +
                '}';
    }
}
