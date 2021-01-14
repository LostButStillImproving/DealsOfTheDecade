package com.gluonapplication.model.scenario;

import com.gluonapplication.model.choice.Choice;

import java.util.ArrayList;

public class Scenario {

    private int id;
    private String scenarioText;
    private ArrayList<Choice> choices = new ArrayList<>();

    public Scenario(String scenarioText, int id) {
        this.scenarioText = scenarioText;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }
}
