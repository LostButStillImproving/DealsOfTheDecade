package com.gluonapplication.model;

import com.gluonapplication.GameController;
import com.gluonapplication.data.Connect;
import com.gluonapplication.model.company.Company;
import com.gluonapplication.model.company.CompanyFactory;
import com.gluonapplication.model.company.GameObserver;
import com.gluonapplication.model.scenario.Scenario;
import com.gluonapplication.model.scenario.ScenarioBuilder;
import com.gluonapplication.views.GamePresenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game implements Runnable {

    // OBSERVER implementation
    public Company company;

    public ScenarioBuilder scenarioBuilder = new ScenarioBuilder(this);

    private final List<GameObserver> observers = new ArrayList<>();

    public void attach(GameObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (GameObserver observer : observers) {
            observer.update();
        }
    }
    private LocalDate date = LocalDate.of(2020,1,1);
    public CompanyFactory companyFactory = new CompanyFactory();

    private Scenario currentScenario;
    private boolean decisionRound = true;

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

        setCurrentScenario();
    }

    public void rebuildScenarioQueue(Integer unlocksScenarioID) {
        scenarioBuilder.rebuildScenarioQueue(unlocksScenarioID);
    }

    public void setCurrentScenario() {
        var scenarioQueue = scenarioBuilder.getScenarioQueue();
        if (!scenarioQueue.isEmpty()) {
            this.currentScenario = scenarioQueue.poll();
        }
        notifyAllObservers();
    }
    public Scenario getCurrentScenario() {
        return currentScenario;
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

    private void increaseDate() {
        date = date.plusDays(1);
    }

    public void flipIsDecisionRound() {
        decisionRound = !decisionRound;
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
        } while (!gameDone());
    }
}
