package com.gluonapplication.model.company;

import com.gluonapplication.model.Game;

public interface Company {


    void updateBudgetConstant();
    void updateBudget();

    void makeBusinessDecision(String id, Game game);
}
