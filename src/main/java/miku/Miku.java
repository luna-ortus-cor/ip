package miku;

import java.util.Scanner;

public class Miku{
    private Ui ui;
    private Parser p;
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Creates a new Miku instance with a new Ui to interact with the user, and
     * a new Parser to parse messages/instructions from the user
     */
    public Miku(){
        this.ui = new Ui();
        this.p = new Parser(this.ui);
    }
    
    /**
     * Runs the Miku bot
     */
    public void run(){
        this.p.start();
        String in;
        int response = 1;
        while(response==1){
            ui.printNextInstrMsg();
            in=sc.nextLine();
            response=p.parse(in);
        }
    }

    public static void main(String args[]){
        new Miku().run();
    }
}
