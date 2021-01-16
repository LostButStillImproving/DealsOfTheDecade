package com.gluonapplication;

import com.gluonapplication.model.Game;

import static java.lang.Thread.sleep;

public class GameController {

    public Game game;

    public Game getGame() {
        try {
            System.out.println(game.getCompany().getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return game;
    }

    public GameController(String companyType) {
        game = new Game(companyType);
        t1.start();
        //t2.start();
    }

    public Thread t1 = new Thread(() -> game.run());
   /* public Thread t2 = new Thread(() -> {
        while (true) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });*/
}
