package com.gluonapplication.views;

import com.gluonapplication.GameController;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

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

    private Boolean choiceMade = false;

    private GameController gameController;

    public void initialize() {
        gameController = new GameController("SMALL");

        //Hardcoded filler stuff
        updateScenarioDescription();
        updateChoiceButtons();
        spawnTimer();

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

    private String getScenarioText() {
        return gameController.getGame().getCurrentScenario().getScenarioText();
    }
    private String getChoiceDescription(int i) {
        return gameController.getGame().getCurrentScenario().getChoices().get(i).getDescription();
    }
    private void updateScenarioDescription() {
        Platform.runLater(() -> {
            scenarioDescription.setText(getScenarioText());
        });

    }
    private void updateChoiceButtons() {

        Platform.runLater(() -> {
            choiceOne.setText(getChoiceDescription(0));
            choiceTwo.setText(getChoiceDescription(1));
            choiceThree.setText(getChoiceDescription(2));
            choiceFour.setText(getChoiceDescription(3));
        });
    }
    @FXML
    public void makeBusinessDecision(Event event) {

        final Node source = (Node) event.getSource();
        var id = source.getId();
        var game = gameController.getGame();
        var company = game.getCompany();

        company.makeBusinessDecision(id,game);
        this.choiceMade = true;

        updateScenarioDescription();
        updateChoiceButtons();
    }

    public void makeDefaultBusinessDecision() {
        var game = gameController.getGame();
        var company = game.getCompany();
        company.makeBusinessDecision("choiceFour",game);

    }

    private void spawnTimer() {

        Thread t= new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!choiceMade) {
                makeDefaultBusinessDecision();
                updateScenarioDescription();
                updateChoiceButtons();
            }

        });
        t.start();
    }
}
