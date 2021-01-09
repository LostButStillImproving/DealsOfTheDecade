package com.gluonapplication.model.choice;

public class Choice {

    private String description;
    private Double budgetEffect; // multiplicative
    private Double repEffect;  // additive
    private Double IQEffect; // additive
    private Double IQLimit; // FIND online grænser for dumme dyr og smarte mennesker

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
    }
}


