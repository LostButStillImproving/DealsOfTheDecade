package com.gluonapplication.model.company;

import com.gluonapplication.model.Game;
import com.gluonapplication.model.choice.Choice;

import java.util.concurrent.atomic.AtomicInteger;

public class SmallCompany implements Company {

    private final Double budgetConstant = 0.1;
    private int IQ = 110;
    private Double reputation = 2.5;
    private final AtomicInteger budget = new AtomicInteger(10000);

    public AtomicInteger getBudget() {
        return budget;
    }

    @Override
    public void updateBudgetConstant() { }
    @Override
    public synchronized void updateBudget() {
        budget.incrementAndGet();
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
