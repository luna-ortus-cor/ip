package miku;

import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Class to redirect System.out.println calls to the GUI
 */
public class MikuOutputStream extends OutputStream {
    private Image mikuImage = new Image(this.getClass().getResourceAsStream("/images/miku4.png"));
    private StringBuilder buffer = new StringBuilder();
    private final VBox dialogContainer;

    public MikuOutputStream(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    @Override
    public void write(int b) {
        // Ensure thread safety for JavaFX UI updates
        Platform.runLater(() ->
            dialogContainer.getChildren().add(DialogBox.getMikuDialog(String.valueOf((char) b), mikuImage)));
    }

    @Override
    public void write(byte[] b, int off, int len) {
        String text = new String(b, off, len);
        buffer.append(text);
        if (buffer.toString().contains("\n\n")) {
            flushBuffer();
        }
    }

    /**
     * Flush the buffer and write the contents of buffer to GUI.
     */
    private void flushBuffer() {
        String msg = buffer.toString();
        buffer.setLength(0);
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getMikuDialog(msg, mikuImage)));
    }
}

