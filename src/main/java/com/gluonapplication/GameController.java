package com.gluonapplication;

import com.gluonapplication.model.Game;


public class GameController {

    public Game game;

    public Game getGame() {

        return game;
    }

    public GameController(String companyType) {
        game = new Game(companyType);
        t1.start();
    }

    public Thread t1 = new Thread(() -> game.run());

}
