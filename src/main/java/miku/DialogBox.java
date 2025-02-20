package miku;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Settings settings = new Settings();

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates Dialogbox from specified xml
     *
     * @param s message to be displayed on dialogbox
     * @param i image to be displayed on dialogbox
     */
    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);

        applyFontSize(dialog);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        dialog.setStyle("-fx-background-color: #3498DB; -fx-background-radius: 15px; "
            + "-fx-padding: 10px; -fx-text-fill: white;");
        applyFontSize(dialog);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Applies current font size from settings to dialogbox.
     */
    private void applyFontSize(Label dialog) {
        int fontSize = settings.getFontSize();
        dialog.setFont(new Font("Arial", fontSize));
    }

    /**
     * Gets user dialogbox.
     *
     * @param s message to be displayed on dialogbox
     * @param i image to be displayed on dialogbox
     * @return dialogbox for the user
     */
    public static DialogBox getUserDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.getStyleClass().add("user-dialog");
        return db;
    }

    /**
     * Gets miku dialogbox.
     *
     * @param s message to be displayed on dialogbox
     * @param i image to be displayed on dialogbox
     * @return dialogbox for miku
     */
    public static DialogBox getMikuDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        db.getStyleClass().add("miku-dialog");
        return db;
    }
}
