import java.util.Scanner;
import java.util.ArrayList;

public class Miku{
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String args[]){
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT+"hello! i'm Miku desuyo!");
        System.out.println(Constants.INDENT+"what can i do for you?");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")){
            if(in.equals("list")){
                printList(taskList);       
            }else if(in.matches("mark \\d+")){
                taskList.get(Integer.valueOf(in.split(" ")[1])-1).markDone();
            }else if(in.matches("unmark \\d+")){
                taskList.get(Integer.valueOf(in.split(" ")[1])-1).markNotDone();
            }else if(in.split(" ")[0].equals("todo")){
                String name = in.split("todo ")[1];
                Todo t = new Todo(name);
                taskList.add(t);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+t.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }else if(in.split(" ")[0].equals("deadline")){
                String name = in.split(" /by ")[0].split("deadline ")[1];
                String by = in.split(" /by ")[1];
                Deadline d = new Deadline(name,by);
                taskList.add(d);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+d.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }else if(in.split(" ")[0].equals("event")){
                String name = in.split(" /from ")[0].split("event ")[1];
                String from = in.split(" /from ")[1].split(" /to ")[0];
                String to = in.split(" /from ")[1].split(" /to ")[1];
                Event e = new Event(name,from,to);
                taskList.add(e);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+e.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }else{
                System.out.println(Constants.INDENT+Constants.INVALID_INSTR);
            }
            in=sc.nextLine();
        }
        System.out.println(Constants.INDENT+Constants.EXIT_MSG);
    }

    private static <T> void printList(ArrayList<T> list){
        int idx = 1;
        System.out.println(Constants.INDENT+Constants.TASK_LIST_MSG);
        for(T t:list){
            System.out.println(Constants.INDENT+Constants.INDENT+idx+". "+t.toString());
            idx++;
        }
    }
}
