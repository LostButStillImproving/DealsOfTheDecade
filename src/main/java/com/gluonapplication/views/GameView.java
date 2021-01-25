package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class GameView {
    
    public View getView() {

        try {
            return FXMLLoader.load(GameView.class.getResource("highscores.fxml"));

        } catch (IOException e) {

            System.out.println("IOException: " + e.getMessage());
            return new View();
        }
    }
}
