package miku;

import java.util.Scanner;

/**
 * The miku class handles the initialization and running of the miku bot.
 */
public class Miku {
    private Scanner sc;
    private Ui ui;
    private Parser p;
    private MikuInputStream mikuInputStream;
    private MikuOutputStream mikuOutputStream;

    /**
     * Creates a new Miku instance with a new Ui to interact with the user, and
     * a new Parser to parse messages/instructions from the user
     */
    public Miku() {
        this.ui = new Ui();
        this.p = new Parser(this.ui);
        this.sc = new Scanner(System.in);
        //this.p.start();
    }

    /**
     * Creates a new Miku instance with specified input and output streams for interaction with the user,
     * with a new Ui to display interaction with the user, and a new Parser to parse messages/instructions
     * from the user
     */
    public Miku(MikuInputStream mikuInputStream, MikuOutputStream mikuOutputStream) {
        this.ui = new Ui();
        this.p = new Parser(this.ui);
        this.mikuInputStream = mikuInputStream;
        this.mikuOutputStream = mikuOutputStream;
        System.setIn(mikuInputStream);
        this.sc = new Scanner(System.in);
    }

    /**
     * Runs the Miku bot
     */
    public int run() {
        this.p.start();
        String in;
        int response = 1;
        while (response == 1) {
            ui.printNextInstrMsg();
            synchronized (System.in) {
                System.in.notify(); //Force wake-up
            }
            in = Constants.buildInputString();
            //in = sc.nextLine();
            response = p.parse(in);
        }
        return response;
    }

    public static void main(String[] args) {
        int response = new Miku().run();
    }
}
