package com.gluonapplication;

import com.gluonapplication.model.Game;

import static java.lang.Thread.sleep;

public class GameController {

    public Game game = new Game();

    public Game getGame() {
        return game;
    }

    public GameController() {
        t1.start();
        t2.start();
    }

    public Thread t1 = new Thread(() -> {
            game.run();
    });
    public Thread t2 = new Thread(() -> {
        while (true) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
