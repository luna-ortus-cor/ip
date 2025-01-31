package miku;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import java.io.PrintStream;


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

    private Miku miku;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/emu1.jpg"));
    private Image mikuImage = new Image(this.getClass().getResourceAsStream("/images/miku4.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        System.setOut(new PrintStream(new MikuOutputStream(dialogContainer)));
        //dialogContainer.getChildren().addAll(DialogBox.getMikuDialog("uwu", mikuImage));
    }

    /** Injects the Miku instance */
    public void setMiku(Miku m) {
        miku = m;
        miku.awaitResponse();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Miku's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, userImage));
        int mikuResponse = miku.getResponse(userText);
        userInput.clear();
        if (mikuResponse == 1) {
            miku.awaitResponse();
        } else {
            Platform.exit();
            System.exit(0);
        }
    }
}

