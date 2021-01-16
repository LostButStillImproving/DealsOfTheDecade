package com.gluonapplication.model;

import com.gluonapplication.model.choice.Choice;
import com.gluonapplication.model.company.Company;
import com.gluonapplication.model.company.CompanyFactory;
import com.gluonapplication.model.scenario.Scenario;

import java.util.*;

import static java.lang.Thread.sleep;
public class Game implements Runnable {

    public CompanyFactory companyFactory = new CompanyFactory();

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public ArrayList<Scenario> scenarios = new ArrayList<>();
    private Queue<Scenario> scenarioQueue = new ArrayDeque<>();

    private Scenario currentScenario;

    public Company company;

    public void setCurrentScenario() {
        if (!scenarioQueue.isEmpty()) {
            this.currentScenario = scenarioQueue.poll();
        }
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }

    public Game() {
        buildScenarios();
        buildScenarioQueue();
        setCurrentScenario();
    }

    private void buildScenarios() {

        Scenario coronaScenario = new Scenario("Scenario: \n" +
                "10 of your employees has caught the\n" +
                "china virus, what do you do?", 1);
        coronaScenario.addChoice(new Choice("It's their own fault!\nFire them!", 1000., -0.5, -5.0, 0.0));
        coronaScenario.addChoice(new Choice("Send them home\nwith pay", -200.0, 0.5, 5.0, 90.));
        coronaScenario.addChoice(new Choice("Blame China!", -5000., -2., -10., 0.));
        coronaScenario.addChoice(new Choice("Do nothing", 0., 0., 0., 0.));
        this.scenarios.add(coronaScenario);

        Scenario testScenario = new Scenario("Scenario: \n" +
                "10 of your employees has caught the\n" +
                "china virus, what do you do?", 1);
        testScenario.addChoice(new Choice("It's their own fault!\nFire them!", 1000., -0.5, -5.0, 0.0));
        testScenario.addChoice(new Choice("Send them home\nwith pay", -200.0, 0.5, 5.0, 90.));
        testScenario.addChoice(new Choice("Blame China!", -5000., -2., -10., 0.));
        testScenario.addChoice(new Choice("Do nothing1111", 0., 0., 0., 0.));
        this.scenarios.add(testScenario);
    }
    private void buildScenarioQueue() {
        scenarioQueue.addAll(getScenarios());
    }

    @Override
    public void run() {
        try {
            timer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void timer() throws InterruptedException {

        Company mediumCompany = companyFactory.getCompany("MEDIUM");
        company = mediumCompany;

        while (true){
            sleep(1000);
            mediumCompany.updateBudget();
            System.out.println(mediumCompany);
        }
    }
}
