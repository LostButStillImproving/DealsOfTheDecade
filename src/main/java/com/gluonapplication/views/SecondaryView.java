package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class SecondaryView {
    
    public View getView() {

        try {
            return FXMLLoader.load(SecondaryView.class.getResource("secondary.fxml"));

        } catch (IOException e) {

            System.out.println("IOException: " + e.getMessage());
            return new View();
        }
    }
}
