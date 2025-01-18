import java.util.Scanner;
import java.util.ArrayList;

public class Miku{
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String args[]){
        System.out.println("Hello! I'm Miku!\nWhat can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")){
            if(in.equals("list")){
                printList(taskList);       
            }else{
                Task t = new Task(in);
                taskList.add(t);
                System.out.println("added: "+t.toString());
            }
            in=sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static <T> void printList(ArrayList<T> list){
        int idx = 1;
        for(T t:list){
            System.out.println(idx+". "+t.toString());
            idx++;
        }
    }
}
