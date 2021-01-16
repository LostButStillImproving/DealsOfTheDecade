package com.gluonapplication.model.choice;

public class Choice {

    private final String description;
    private final Double budgetEffect; // multiplicative
    private final Double repEffect;  // additive
    private final Double IQEffect; // additive
    private final Double IQLimit; // FIND online grænser for dumme dyr og smarte mennesker

    public Double getBudgetEffect() {
        return budgetEffect;
    }

    public Double getRepEffect() {
        return repEffect;
    }

    public Double getIQEffect() {
        return IQEffect;
    }

    public Double getIQLimit() {
        return IQLimit;
    }

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
    }

    public String getDescription() {
        return description;
    }
}


