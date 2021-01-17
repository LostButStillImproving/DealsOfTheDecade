package com.gluonapplication.model;

import com.gluonapplication.model.choice.Choice;
import com.gluonapplication.model.company.Company;
import com.gluonapplication.model.company.CompanyFactory;
import com.gluonapplication.model.company.GameObserver;
import com.gluonapplication.model.scenario.Scenario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Thread.sleep;
public class Game implements Runnable {

    public Company company;
    private final List<GameObserver> observers = new ArrayList<>();
    public void attach(GameObserver observer){
        observers.add(observer);
    }
    public void notifyAllObservers(){
        for (GameObserver observer : observers) {
            observer.update();
        }
    }
    public CompanyFactory companyFactory = new CompanyFactory();
    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }
    public ArrayList<Scenario> scenarios = new ArrayList<>();
    private final Queue<Scenario> scenarioQueue = new ArrayDeque<>();
    private LocalDate date = LocalDate.of(2020,1,1);
    private Scenario currentScenario;

    private boolean decisionRound = true;
    public void setCurrentScenario() {
        if (!scenarioQueue.isEmpty()) {
            this.currentScenario = scenarioQueue.poll();
            notifyAllObservers();
        }
    }
    public Scenario getCurrentScenario() {
        return currentScenario;
    }
    public Game(String companyType) {

        if (companyType.equals("SMALL")) {
            company = companyFactory.getSmallCompany();
        }
        if (companyType.equals("MEDIUM")) {
            company = companyFactory.getMediumCompany();
        }
        if (companyType.equals("HUGE")) {
            company = companyFactory.getHugeCompany();
        }

        buildScenarios();
        buildScenarioQueue();
        setCurrentScenario();
    }

    public Boolean gameDone() {
        return getCompany().getBudget().get() < 0;
    }

    public Company getCompany() {
        return this.company;
    }

    public LocalDate getDate() {
        return date;
    }
    private void buildScenarioQueue() {
        scenarioQueue.addAll(getScenarios());
    }

    private void buildScenarios() {

        Scenario coronaScenario = new Scenario("Scenario: \n" +
                "10 of your employees has caught the\n" +
                "china virus, what do you do?", 1);
        coronaScenario.addChoice(new Choice("It's their own fault!\nFire them!", 1000., -0.5, -5.0, 0.0));
        coronaScenario.addChoice(new Choice("Send them home\nwith pay", -200.0, 0.5, 5.0, 90.));
        coronaScenario.addChoice(new Choice("Blame China!", -5000., -2., -10., 0.));
        coronaScenario.addChoice(new Choice("Do nothing!", -5000., 0., 0., 0.));
        this.scenarios.add(coronaScenario);

        Scenario testScenario = new Scenario("Scenario: \n" +
                "Your reputation within the CCP\n" +
                "has been destroyed, what do you do?", 1);
        testScenario.addChoice(new Choice("Make China\nGreat Again!", 10000., 1., -5.0, 0.0));
        testScenario.addChoice(new Choice("Blame China Again", -200.0, 0.5, 5.0, 90.));
        testScenario.addChoice(new Choice("Beg for forgiveness ", 200., -2., -10., 0.));
        testScenario.addChoice(new Choice("Do nothing!", -2000., 0.1, -5., 0.));
        this.scenarios.add(testScenario);
    }

    private void increaseDate() {
        date = date.plusDays(1);
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

        do {
            sleep(1000);
            if (decisionRound) {
                getCompany().updateBudget();
                getCompany().updateBudgetConstant();
                increaseDate();
                notifyAllObservers();
            }
            System.out.println(company);
        } while (!gameDone());
    }

    public void flipIsDecisionRound() {
        decisionRound = !decisionRound;
    }
}
