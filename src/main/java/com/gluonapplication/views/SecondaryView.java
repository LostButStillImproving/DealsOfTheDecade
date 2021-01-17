package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;

public class SecondaryView {
    
    public View getView() {
        Font.loadFont(getClass().getResourceAsStream("src/main/resources/font/8bitOperatorPlus8-Regular.ttf"), 14);

        try {
            return FXMLLoader.load(SecondaryView.class.getResource("secondary.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return new View();
        }
    }
}
