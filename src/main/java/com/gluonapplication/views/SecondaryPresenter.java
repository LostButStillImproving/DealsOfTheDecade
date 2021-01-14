package com.gluonapplication.views;

import com.gluonapplication.GameController;
import com.gluonapplication.model.Game;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import static java.lang.Thread.sleep;

public class SecondaryPresenter {


    @FXML
    private View secondary;

    //GRAPHICS
    @FXML
    private ImageView cityGraphics;

    @FXML
    private ImageView reputationImage;

    //Texts
    @FXML
    private Label scenarioDescription;
    @FXML
    private Label budgetField;
    @FXML
    private Label dateField;

    //PROGRESS BAR
    @FXML
    private ProgressBar progressBar;

    //CHOICES BUTTONS
    @FXML
    private Button choiceOne;
    @FXML
    private Button choiceTwo;
    @FXML
    private Button choiceThree;
    @FXML
    private Button choiceFour;

    private GameController gameController = new GameController();

    public void initialize() {
        gameController.t1.start();


        //Hardcoded filler stuff
        scenarioDescription.setText("Scenario: \n" +
                "10 of your employees has caught the\n" +
                "china virus, what do you do?");

        choiceOne.setText("It's their own fault!\nFire them!");
        choiceTwo.setText("Send them home\nwith pay");
        choiceThree.setText("Blame China!");
        choiceFour.setText("Do nothing");

        secondary.setShowTransitionFactory(BounceInRightTransition::new);

        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Secondary");
            }
        });
    }
}
