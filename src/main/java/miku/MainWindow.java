package miku;

import java.io.PrintStream;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;
    @FXML
    private Button settingsButton;

    private Miku miku;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/emu1.jpg"));
    private Image mikuImage = new Image(this.getClass().getResourceAsStream("/images/miku4.png"));
    private MikuOutputStream mikuOutputStream;
    private MikuInputStream mikuInputStream;

    /**
     * Initializes the javafx application.
     */
    @FXML
    public void initialize() {
        mikuOutputStream = new MikuOutputStream(dialogContainer);
        mikuInputStream = new MikuInputStream();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        System.setOut(new PrintStream(mikuOutputStream));
        System.setIn(mikuInputStream);
        //dialogContainer.getChildren().addAll(DialogBox.getMikuDialog("uwu", mikuImage));
        this.miku = new Miku(mikuInputStream, mikuOutputStream); //Initialize Miku
        Platform.runLater(() -> {
            new Thread(() -> {
                int response = this.miku.run();
                if (response == 0) {
                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
                    delay.setOnFinished(event -> Platform.exit()); //Close the JavaFX app
                    //System.exit(0); //Exit the app entirely
                    delay.play();
                }
            }).start(); //Start chatbot loop after UI is ready
        });
    }

    /** Injects the Miku instance */
    public void setMiku(Miku m) {
        miku = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Miku's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, userImage));
        //int mikuResponse = miku.getResponse(userText);
        mikuInputStream.add(userText);
        userInput.clear();
    }

    @FXML
    private void handleSettings() {
        
    }
}

