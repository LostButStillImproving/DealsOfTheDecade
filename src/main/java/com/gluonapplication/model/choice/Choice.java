package com.gluonapplication.model.choice;

import com.gluonapplication.views.GamePresenter;

import java.awt.*;
import java.io.File;

public class Choice {

    private final String description;
    private final Double budgetEffect; // multiplicative
    private final Double repEffect;  // additive
    private final Double IQEffect; // additive
    private final Double IQLimit; // FIND online grænser for dumme dyr og smarte mennesker
    private final String summary;
    private Double layoutX;
    private Double layoutY;
    private Double prefWidth;
    private Double prefHeight;
    private String imageName;

    private String fieldName;

    private Integer unlocksScenarioID;

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit, String summary, String imageName, String fieldName) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.fieldName = fieldName;
    }

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit,String summary,Integer unlocksScenarioID, String imageName, String fieldName) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.fieldName = fieldName;
        this.unlocksScenarioID = unlocksScenarioID;
    }



    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit, String summary) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect, Double IQLimit, String summary, Integer unlocksScenarioID) {

        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.unlocksScenarioID = unlocksScenarioID;
    }

    public Double getLayoutX() {
        return layoutX;
    }

    public Double getLayoutY() {
        return layoutY;
    }

    public String getImageName() {
        return imageName;
    }

    public Choice(String description,
                  Double budgetEffect,
                  Double repEffect,
                  Double IQEffect,
                  Double IQLimit,
                  String summary,
                  Integer unlocksScenarioID,
                  String imageName, double layoutX, double layoutY, double prefWidth, double prefHeight) {

        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.unlocksScenarioID = unlocksScenarioID;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.prefHeight = prefHeight;
        this.prefWidth = prefWidth;
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getUnlocksScenarioID() {
        return unlocksScenarioID;
    }

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

    public Double getPrefWidth() {
        return prefWidth;
    }

    public Double getPrefHeight() {
        return prefHeight;
    }
}


