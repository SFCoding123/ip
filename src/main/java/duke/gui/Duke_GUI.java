package duke.gui;

import duke.command.menu;
import duke.utilities.Parser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Duke_GUI extends Application {
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Handsome.jpg")));
    private final Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.jpg")));
    public String input;
    public String output;
    VBox dialogContrainer = new VBox();
    TextField userInput = new TextField();

    @Override
    public void start(Stage stage)  {
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(dialogContrainer);

        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        //settings
        stage.setTitle("Alpha Beast");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContrainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContrainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //actions
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });


        userInput.setOnAction((event) -> {
            handleUserInput();
        });


    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @FXML
    private void handleUserInput(){
        input = userInput.getText();
        output = getResponse(input);

        dialogContrainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(output, duke)
        );
        userInput.clear();
    }


    String getResponse(String input) {
        return menu.In_Out(input);
        //+ input;
    }

}