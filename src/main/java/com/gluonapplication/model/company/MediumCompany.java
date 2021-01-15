package com.gluonapplication.model.company;
import com.gluonapplication.model.Game;
import com.gluonapplication.model.choice.Choice;

import java.util.concurrent.atomic.AtomicInteger;

public class MediumCompany implements Company {

    private Double budgetConstant = 0.1;
    private int IQ = 110;
    private Double reputation = 2.5;
    private AtomicInteger budget = new AtomicInteger(10000);


    @Override
    public void updateBudgetConstant() {
    }
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

    private void applyChoiceImpact(Game game, int choiceNumber) {
        Choice choice = game.getCurrentScenario().getChoices().get(choiceNumber - 1);
        this.budget.addAndGet( choice.getBudgetEffect().intValue());
        this.IQ += choice.getIQEffect();
        this.reputation += choice.getRepEffect();
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
