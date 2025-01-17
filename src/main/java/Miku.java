import java.util.Scanner;

public class Miku{
    public static void main(String args[]){
        System.out.println("Hello! I'm Miku!\nWhat can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")){
            System.out.println(in);
            in=sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
