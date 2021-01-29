package com.gluonapplication.model.choice;

import java.util.Random;

public class Choice {

    private final String description;
    private Double budgetEffect; // multiplicative
    private final Double repEffect;  // additive
    private final Double IQEffect; // additive
    private final Double IQLimit; // FIND online grænser for dumme dyr og smarte mennesker
    private final String summary;
    private String methodToBeApplied;
    private Double layoutXForImage;
    private Double layoutYForImage;
    private Double prefWidthOfImage;
    private Double prefHeightOfImage;
    private String imageName;

    private Integer unlocksScenarioID;
    private String nodeID;

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect,
                  Double IQLimit, String summary, String imageName, String nodeID) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.nodeID = nodeID;
    }

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect,
                  Double IQLimit,String summary, Integer unlocksScenarioID, String imageName, String nodeID) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.nodeID = nodeID;
        this.unlocksScenarioID = unlocksScenarioID;
    }

    public Choice(String description, Double budgetEffect, Double repEffect,
                  Double IQEffect, Double IQLimit, String summary) {
        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;

    } public Choice(String description, String methodToBeApplied, Double repEffect,
                  Double IQEffect, Double IQLimit, String summary) {
        this.description = description;
        this.methodToBeApplied = methodToBeApplied;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
    }

    public Choice(String description, Double budgetEffect, Double repEffect, Double IQEffect,
                  Double IQLimit, String summary, Integer unlocksScenarioID,
                  String imageName, String nodeID, double layoutXForImage, double layoutYForImage,
                  double prefWidthOfImage, double prefHeightOfImage) {

        this.description = description;
        this.budgetEffect = budgetEffect;
        this.repEffect = repEffect;
        this.IQEffect = IQEffect;
        this.IQLimit = IQLimit;
        this.summary = summary;
        this.unlocksScenarioID = unlocksScenarioID;
        this.layoutXForImage = layoutXForImage;
        this.layoutYForImage = layoutYForImage;
        this.prefHeightOfImage = prefHeightOfImage;
        this.prefWidthOfImage = prefWidthOfImage;
        this.imageName = imageName;
        this.nodeID = nodeID;
    }

    public Choice(String description, String methodToBeApplied, double repEffect,
                  double iqEffect, double iqLimit, String summary, String imageName, String nodeID) {
        this.description = description;
        this.methodToBeApplied = methodToBeApplied;
        this.repEffect = repEffect;
        this.IQEffect = iqEffect;
        this.IQLimit = iqLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.nodeID = nodeID;

    }

    public Choice(String description, String methodToBeApplied, double repEffect,
                  double iqEffect, double iqLimit, String summary, String imageName, String nodeID, double layoutXForImage, double layoutYForImage,
                  double prefWidthOfImage, double prefHeightOfImage) {

        this.description = description;
        this.methodToBeApplied = methodToBeApplied;
        this.repEffect = repEffect;
        this.IQEffect = iqEffect;
        this.IQLimit = iqLimit;
        this.summary = summary;
        this.imageName = imageName;
        this.prefWidthOfImage = prefWidthOfImage;
        this.prefHeightOfImage = prefHeightOfImage;
        this.layoutXForImage = layoutXForImage;
        this.layoutYForImage = layoutYForImage;
        this.nodeID = nodeID;
    }

    public Double getLayoutX() {
        return layoutXForImage;
    }

    public Double getLayoutY() {
        return layoutYForImage;
    }

    public String getImageName() {
        return imageName;
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
        return prefWidthOfImage;
    }

    public Double getPrefHeight() {
        return prefHeightOfImage;
    }

    public String getMethodToBeApplied() {
        return methodToBeApplied;
    }

    public void calculateReward(Double investedAmount) {

        Random random = new Random();
        Double multiplier = random.nextDouble() * 3;
        var result = investedAmount * multiplier;
        System.out.println(multiplier);
        this.budgetEffect = (result - investedAmount);
    }

    public String getNodeID() {
        return this.nodeID;
    }
}


