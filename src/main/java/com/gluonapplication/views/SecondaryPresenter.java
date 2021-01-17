package com.gluonapplication.views;

import com.gluonapplication.GameController;
import com.gluonapplication.model.Game;
import com.gluonapplication.model.company.GameObserver;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class SecondaryPresenter extends GameObserver {

    @FXML
    private View secondary;

    @FXML
    private Label reputationLabel;

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

    private Game game;

    public void initialize() {
        gameController = new GameController("SMALL");
        this.game = gameController.getGame();
        game.attach(this);


        //Thanks Anders Hjordrup for ";"
        reputationLabel.setStyle("-fx-font-family: '8-bit Operator+ 8'; -fx-font-weight: bold");

        update();
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
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    sleep(1000);
                    update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

    private String getScenarioText() {
        return gameController.getGame().getCurrentScenario().getScenarioText();
    }
    private String getChoiceDescription(int i) {
        return gameController.getGame().getCurrentScenario().getChoices().get(i).getDescription();
    }
    @FXML
    public void makeBusinessDecision(Event event) {

        final Node source = (Node) event.getSource();
        var id = source.getId();
        var game = gameController.getGame();
        var company = game.getCompany();

        company.makeBusinessDecision(id,game);
    }

    public void makeDefaultBusinessDecision() {
        var game = gameController.getGame();
        var company = game.getCompany();
        company.makeBusinessDecision("choiceFour",game);
    }

    public void clickSummaryOK() {
        gameController.getGame().flipIsDecisionRound();
    }

    private void spawnTimer() {
        AtomicReference<Double> progress = new AtomicReference<>((double) 0);
        Thread t= new Thread(() -> {
            while (true) {

                try {
                    sleep(50);
                    progress.updateAndGet(v -> v + 0.005);
                    progressBar.setProgress(progress.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (progress.get() == 1) {
                    break;
                }
            }

            if (!choiceMade) {
                makeDefaultBusinessDecision();
            }

        });
        t.start();
    }

    @Override
    public void update() {

        Platform.runLater(() -> {
            updateBudgetField();
            updateRepIcon();
            updateDateField();
            updateScenarioDescription();
            updateChoiceButtons();
        });
    }

    private void updateDateField() {
        dateField.setText(gameController.getGame().getDate().toString());
    }

    private void updateRepIcon() {
        double reputation = gameController.getGame().getCompany().getReputation();
        if (reputation <= 1.0) {
            File file = new File("src/main/resources/graphics/reputation/veryangry.png");
            Image image = new Image(file.toURI().toString());
            reputationImage.setImage(image);
        }
        else if (reputation <= 2.0) {
            File file = new File("src/main/resources/graphics/reputation/angry.png");
            Image image = new Image(file.toURI().toString());
            reputationImage.setImage(image);
        }
        else if (reputation <= 3.0) {
            File file = new File("src/main/resources/graphics/reputation/neutral.png");
            Image image = new Image(file.toURI().toString());
            reputationImage.setImage(image);
        }
        else if (reputation <= 4.0) {
            File file = new File("src/main/resources/graphics/reputation/happy.png");
            Image image = new Image(file.toURI().toString());
            reputationImage.setImage(image);
        }
        else if (reputation <= 5.0) {
            File file = new File("src/main/resources/graphics/reputation/loved.png");
            Image image = new Image(file.toURI().toString());
            reputationImage.setImage(image);
        }
    }

    private void updateBudgetField() {
        budgetField.setText(String.valueOf(game.getCompany().getBudget().get()));
    }

    private void updateScenarioDescription() {
        scenarioDescription.setText(getScenarioText());
    }
    private void updateChoiceButtons() {

        choiceOne.setText(getChoiceDescription(0));
        choiceTwo.setText(getChoiceDescription(1));
        choiceThree.setText(getChoiceDescription(2));
        choiceFour.setText(getChoiceDescription(3));
    }
}
