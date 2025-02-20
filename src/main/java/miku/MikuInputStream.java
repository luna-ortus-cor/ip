package miku;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class to redirect System.in to user input in the GUI.
 */
public class MikuInputStream extends InputStream {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public MikuInputStream() {
        
    }

    @Override
    public int read() throws IOException {
        try {
            int c = queue.take().intValue();
            return c;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1; // End of stream
        }
    }

    /**
     * Adds a string to the input queue.
     *
     * @param text string to be added
     */
    public void add(String text) {
        for (char c:text.toCharArray()) {
            queue.add((int) c);
        }
        queue.add((int) '\n');

        synchronized (this) {
            this.notify(); //Wake up any waiting thread
        }
    }
}
