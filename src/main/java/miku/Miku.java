package miku;

import java.util.Scanner;

/**
 * The miku class handles the initialization and running of the miku bot.
 */
public class Miku {
    private static Scanner sc = new Scanner(System.in);
    private Ui ui;
    private Parser p;

    /**
     * Creates a new Miku instance with a new Ui to interact with the user, and
     * a new Parser to parse messages/instructions from the user
     */
    public Miku() {
        this.ui = new Ui();
        this.p = new Parser(this.ui);
        //this.p.start();
    }

    /**
     * Runs the Miku bot (for text UI)
     */
    public void run() {
        this.p.start();
        String in;
        int response = 1;
        while (response == 1) {
            ui.printNextInstrMsg();
            in = sc.nextLine();
            response = p.parse(in);
        }
    }

    /**
     * Starts the parser (for GUI)
     */
    public void init() {
        this.p.start();
    }

    /**
     * Gets the response from a specified user input string (for GUI)
     * 
     * @param in user input string
     */
    public int getResponse(String in) {
        //return "Miku heard: " + in;
        int response = this.p.parse(in);
        return response;
    }

    /**
     * Prints the message prompting for user input (for GUI)
     */
    public void awaitResponse() {
        ui.printNextInstrMsg();
    }

    public static void main(String[] args) {
        new Miku().run();
    }
}
