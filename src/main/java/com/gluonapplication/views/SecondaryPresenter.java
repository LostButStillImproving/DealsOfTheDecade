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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class SecondaryPresenter extends GameObserver {

    @FXML
    private View secondary;
    @FXML
    private AnchorPane anchorPane;
    //GRAPHICS
    @FXML
    private ImageView cityGraphics;
    @FXML
    private ImageView reputationImage;
    //Texts

    AtomicReference<Double> progress = new AtomicReference<>((double) 0);

    private final Label businessDecision = new Label();
    @FXML
    private final Label scenarioDescription = new Label();

    private final Label summary = new Label();
    @FXML
    private Label budgetField;
    @FXML
    private Label dateField;
    //PROGRESS BAR
    @FXML
    private final ProgressBar progressBar = new ProgressBar();
    //CHOICES BUTTONS
    @FXML
    private final Button choiceOne = new Button();
    @FXML
    private final Button choiceTwo = new Button();
    @FXML
    private final Button choiceThree = new Button();
    @FXML
    private final Button choiceFour = new Button();

    private final Button summaryContinue = new Button();

    private AtomicBoolean choiceMade = new AtomicBoolean(false);

    private GameController gameController;

    private Game game;

    private final ArrayList<Control> decisionNodes = new ArrayList<>();

    public void initialize() {

        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Secondary");
            }
        });

        gameController = new GameController("SMALL");
        this.game = gameController.getGame();
        game.attach(this);

        decisionNodes.add(businessDecision);
        decisionNodes.add(scenarioDescription);
        decisionNodes.add(choiceOne);
        decisionNodes.add(choiceTwo);
        decisionNodes.add(choiceThree);
        decisionNodes.add(choiceFour);

        constructButtonsAndLabels();
        updateScenarioDescription();
        updateChoiceButtons();

        update();
        spawnTimer();


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

    private void constructButtonsAndLabels() {

        //Summary page nodes
        summary.setLayoutX(29.0);
        summary.setLayoutY(268.0);
        summary.setPrefHeight(91.0);
        summary.setPrefWidth(300.0);

        summaryContinue.setOnAction((e) -> clickSummaryContinue());
        summaryContinue.setText("Continue");
        summaryContinue.setLayoutX(26.0);
        summaryContinue.setLayoutY(385.0);
        summaryContinue.setPrefHeight(70.0);
        summaryContinue.setPrefWidth(139.0);

        //Decision page nodes
        businessDecision.setLayoutX(26.0);
        businessDecision.setLayoutY(366.0);
        businessDecision.setPrefHeight(18.0);
        businessDecision.setPrefWidth(126.0);

        scenarioDescription.setLayoutX(29.0);
        scenarioDescription.setLayoutY(268.0);
        scenarioDescription.setPrefHeight(91.0);
        scenarioDescription.setPrefWidth(300.0);

        choiceOne.setId("choiceOne");
        choiceOne.setOnAction(this::makeBusinessDecision);
        choiceOne.setLayoutX(26.0);
        choiceOne.setLayoutY(385.0);
        choiceOne.setPrefHeight(70.0);
        choiceOne.setPrefWidth(139.0);

        choiceTwo.setId("choiceTwo");
        choiceTwo.setOnAction(this::makeBusinessDecision);
        choiceTwo.setLayoutX(188.0);
        choiceTwo.setLayoutY(388.0);
        choiceTwo.setPrefHeight(70.0);
        choiceTwo.setPrefWidth(139.0);

        choiceThree.setId("choiceThree");
        choiceThree.setOnAction(this::makeBusinessDecision);
        choiceThree.setLayoutX(26.0);
        choiceThree.setLayoutY(469.0);
        choiceThree.setPrefHeight(70.0);
        choiceThree.setPrefWidth(139.0);

        choiceFour.setId("choiceFour");
        choiceFour.setOnAction(this::makeBusinessDecision);
        choiceFour.setLayoutX(188.0);
        choiceFour.setLayoutY(469.0);
        choiceFour.setPrefHeight(70.0);
        choiceFour.setPrefWidth(139.0);
    }

    @FXML
    public void makeBusinessDecision(Event event) {

        final Node source = (Node) event.getSource();
        var id = source.getId();
        var game = gameController.getGame();
        var company = game.getCompany();

        game.flipIsDecisionRound();
        flipChoiceMade();
        company.makeBusinessDecision(id,game);
        removeDecisionPage();
        showSummaryPage(id);
    }

    private void flipChoiceMade() {
        choiceMade.set(!choiceMade.get());
    }


    public void makeDefaultBusinessDecision() {
        var game = gameController.getGame();
        var company = game.getCompany();

        game.flipIsDecisionRound();
        flipChoiceMade();
        company.makeBusinessDecision("choiceFour",game);
        removeDecisionPage();
        showSummaryPage("choiceFour");
    }
    private void showSummaryPage(String id) {
        Platform.runLater(() -> {
            anchorPane.getChildren().add(summary);
            anchorPane.getChildren().add(summaryContinue);
            if (id.equals("choiceOne")) {
                summary.setText(getSummary(0));
            }
            if (id.equals("choiceTwo")) {
                summary.setText(getSummary(1));

            }
            if (id.equals("choiceThree")) {
                summary.setText(getSummary(2));
            }
            if (id.equals("choiceFour")) {
                summary.setText(getSummary(3));
            }
        });


    }
    private void removeDecisionPage() {
        Platform.runLater(() -> {
            anchorPane.getChildren().remove(businessDecision);
            anchorPane.getChildren().remove(scenarioDescription);
            anchorPane.getChildren().remove(choiceOne);
            anchorPane.getChildren().remove(choiceTwo);
            anchorPane.getChildren().remove(choiceThree);
            anchorPane.getChildren().remove(choiceFour);
            anchorPane.getChildren().remove(progressBar);
        });
    }
    public void clickSummaryContinue() {
        gameController.getGame().flipIsDecisionRound();
        removeSummaryPage();

        for (Control node:this.decisionNodes) {
            anchorPane.getChildren().add(node);
        }
        flipChoiceMade();
        spawnTimer();
    }

    private void removeSummaryPage() {
        System.out.println("remove");
        anchorPane.getChildren().remove(summary);
        anchorPane.getChildren().remove(summaryContinue);
    }

    private synchronized void spawnTimer() {
        progressBar.setLayoutX(25.0);
        progressBar.setLayoutY(557.0);
        progressBar.setPrefHeight(20.0);
        progressBar.setPrefWidth(303.0);
        progressBar.setProgress(0);
        progress.set(0.);
        Thread t= new Thread(() -> {

            Platform.runLater(() ->
                    anchorPane.getChildren().add(progressBar));

            while (true) {
                if (choiceMade.get()) {
                    anchorPane.getChildren().remove(progressBar);
                    break;
                }
                try {
                    sleep(50);
                    progress.updateAndGet(v -> v + 0.005);
                    progressBar.setProgress(progress.get());

                    if (progress.get() >= 1) {
                        Platform.runLater(() ->
                                anchorPane.getChildren().remove(progressBar));
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(choiceMade.get());
            if (!choiceMade.get()) {
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

        anchorPane.getChildren().add(scenarioDescription);
        scenarioDescription.setText(getScenarioText());
    }
    private void updateChoiceButtons() {

        anchorPane.getChildren().add(choiceOne);
        anchorPane.getChildren().add(choiceTwo);
        anchorPane.getChildren().add(choiceThree);
        anchorPane.getChildren().add(choiceFour);

        choiceOne.setText(getChoiceDescription(0));
        choiceTwo.setText(getChoiceDescription(1));
        choiceThree.setText(getChoiceDescription(2));
        choiceFour.setText(getChoiceDescription(3));
    }

    private String getScenarioText() {
        return gameController.getGame().getCurrentScenario().getScenarioText();
    }
    private String getChoiceDescription(int i) {
        return gameController.getGame().getCurrentScenario().getChoices().get(i).getDescription();
    }
    private String getSummary(int i) {
        return gameController.getGame().getCurrentScenario().getChoices().get(i).getSummary();
    }
}
