package com.gluonapplication;

import com.gluonapplication.model.Game;

public class GameController {

        public Game game = new Game();
        public Thread t1 = new Thread(() -> {
            game.run();
    });
}
