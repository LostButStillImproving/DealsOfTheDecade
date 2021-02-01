package com.gluonapplication;

import com.gluonapplication.model.Game;


public class GameController {
    public String companyName;

    public Game game;

    public Game getGame() {

        return game;
    }

    public GameController(String companyName, String companyType) {
        this.companyName = companyName;
        game = new Game(companyType);
        t1.start();
    }

    
    public Thread t1 = new Thread(() -> game.run());
}
