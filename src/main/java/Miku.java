import java.util.Scanner;

public class Miku{
    private Ui ui;
    private Parser p;
    private static Scanner sc = new Scanner(System.in);

    public Miku(){
        this.ui = new Ui();
        this.p = new Parser(this.ui);
    }

    public void run(){
        this.p.start();
        String in;
        int response = 1;
        while(response==1){
            in=sc.nextLine();
            response=p.parse(in);
        }
    }

    public static void main(String args[]){
        new Miku().run();
    }
}
