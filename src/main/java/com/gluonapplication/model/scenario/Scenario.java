package com.gluonapplication.model.scenario;

import com.gluonapplication.model.choice.Choice;

import java.util.ArrayList;

public class Scenario {

    private int id;
    private final String scenarioText;
    private final ArrayList<Choice> choices = new ArrayList<>();

    public Scenario(String scenarioTextDescription, int id) {
        scenarioText = scenarioTextDescription;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    public String getScenarioText() {
        return scenarioText;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }
}
