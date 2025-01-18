import java.util.Scanner;
import java.util.ArrayList;

public class Miku{
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String args[]){
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT+"Hello! I'm Miku!");
        System.out.println(Constants.INDENT+"What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")){
            if(in.equals("list")){
                printList(taskList);       
            }else if(in.matches("mark \\d+")){
                taskList.get(Integer.valueOf(in.split(" ")[1])-1).markDone();
            }else if(in.matches("unmark \\d+")){
                taskList.get(Integer.valueOf(in.split(" ")[1])-1).markNotDone();
            }else{
                Task t = new Task(in);
                taskList.add(t);
                System.out.println(Constants.INDENT+"added: "+in);
            }
            in=sc.nextLine();
        }
        System.out.println(Constants.INDENT+"Byee~ Hope to see you again soon!");
    }

    private static <T> void printList(ArrayList<T> list){
        int idx = 1;
        for(T t:list){
            System.out.println(Constants.INDENT+idx+". "+t.toString());
            idx++;
        }
    }
}
