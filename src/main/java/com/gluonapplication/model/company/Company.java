package com.gluonapplication.model.company;

import com.gluonapplication.model.Game;

import java.util.concurrent.atomic.AtomicInteger;

public interface Company {


    void updateBudgetConstant();
    void updateBudget();
    AtomicInteger getBudget();

    void makeBusinessDecision(String id, Game game);

    double getReputation();
}
