package com.gluonapplication.views;

import com.gluonapplication.GameController;
import com.gluonapplication.model.scenario.Scenario;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private GameController gameController;

    public void initialize() {
        gameController = new GameController();

        //Hardcoded filler stuff
        updateScenarioDescription();
        updateChoiceButtons();

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

    private void updateScenarioDescription() {
        scenarioDescription.setText(getScenarioText());
    }

    private String getScenarioText() {
        return gameController.getGame().getCurrentScenario().getScenarioText();
    }

    private String getChoiceDescription(int i) {
        return gameController.getGame().getCurrentScenario().getChoices().get(i).getDescription();
    }
    private void updateChoiceButtons() {
        choiceOne.setText(getChoiceDescription(0));
        choiceTwo.setText(getChoiceDescription(1));
        choiceThree.setText(getChoiceDescription(2));
        choiceFour.setText(getChoiceDescription(3));
    }
    @FXML
    public void makeBusinessDecision(Event event) {
        final Node source = (Node) event.getSource();
        String id = source.getId();
        gameController.game.company.makeBusinessDecision(id,gameController.getGame());
        updateScenarioDescription();
        updateChoiceButtons();
    }
}
