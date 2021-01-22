package com.gluonapplication.model.company;

import com.gluonapplication.model.Game;
import com.gluonapplication.model.choice.Choice;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SmallCompany implements Company {

    private Double budgetConstant = 1.1;
    private int IQ = 110;
    private Double reputation = 2.5;
    private final AtomicInteger budget = new AtomicInteger(5000);

    private Boolean hasBecomeRich = false;

    public AtomicInteger getBudget() {
        return budget;
    }

    public void setHasBecomeRich() {
        hasBecomeRich = true;
    }

    @Override
    public void updateBudgetConstant() {

        int budget = this.budget.get();

        if (budget <= 10000) {
            setBudgetConstant(1.1);
        } else if (budget < 50000) {
            setBudgetConstant(1.2);
        } else if (budget < 100000) {
            setBudgetConstant(1.3);
        } else if (budget < 500000) {
            setBudgetConstant(1.4);
        } else setBudgetConstant(1.5);

        if (budget > 10000) setHasBecomeRich();
    }

    private void setBudgetConstant(double budgetConstant) {

        this.budgetConstant = budgetConstant;

    }

    @Override
    public synchronized void updateBudget() {
        int budgetIncrease = (int) (this.budgetConstant * 100);
        budget.addAndGet(budgetIncrease);
    }
    @Override
    public synchronized void makeBusinessDecision(String id, Game game) {
        if (id.equals("choiceOne")) {
            applyChoiceImpact(game, 1);
        }
        if (id.equals("choiceTwo")) {
            applyChoiceImpact(game, 2);
        }
        if (id.equals("choiceThree")) {
            applyChoiceImpact(game, 3);
        }
        if (id.equals("choiceFour")) {
            applyChoiceImpact(game, 4);

        }

        game.setCurrentScenario();
    }

    @Override
    public double getReputation() {
        return this.reputation;
    }

    @Override
    public Double getBudgetConstant() {
        return this.budgetConstant;
    }

    private void applyChoiceImpact(Game game, int choiceNumber) {
        Choice choice = game.getCurrentScenario().getChoices().get(choiceNumber - 1);
        this.budget.addAndGet( choice.getBudgetEffect().intValue());
        this.IQ += choice.getIQEffect();

        if (!(this.reputation < 0) && !(this.reputation > 5)) {
            this.reputation += choice.getRepEffect();
            if (reputation < 0) reputation = 0.;
            if (reputation > 5) reputation = 5.;
        }
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName()+"{" +
                "budgetConstant=" + budgetConstant +
                ", budget=" + budget +
                ", IQ=" + IQ +
                ", reputation=" + reputation +
                '}';
    }
}
