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
            if(in.trim().equals("list")){
                printList(taskList);       
            }else if(in.matches("mark \\d+")){
                handleMark(in,1);
            }else if(in.matches("unmark \\d+")){
                handleMark(in,0);
            }else if(in.split(" ")[0].equals("todo")){
                handleTodo(in);
            }else if(in.split(" ")[0].equals("deadline")){
                handleDeadline(in);
            }else if(in.split(" ")[0].equals("event")){
                handleEvent(in);
            }else{
                handleError(1);
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
    
    //mark done if mark=1, else mark not done if mark=0
    private static void handleMark(String in, int mark){
        try{
            int idx = Integer.valueOf(in.split(" ")[1])-1;
            if(mark==1){
                taskList.get(idx).markDone();
            }else{
                taskList.get(idx).markNotDone();
            }
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    private static void handleTodo(String in){
        try{
            String name = in.split("todo ")[1];
            if(name.trim().isEmpty()){
                handleError(2);
            }else{
                Todo t = new Todo(name);
                taskList.add(t);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+t.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            handleError(2);
        }
    }

    private static void handleDeadline(String in){
        try{
            String name = in.split(" /by ")[0].split("deadline ")[1];
            String by = in.split(" /by ")[1];
            if(name.trim().isEmpty() || by.trim().isEmpty()){
                handleError(3);
            }else{
                Deadline d = new Deadline(name,by);
                taskList.add(d);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+d.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            handleError(3);
        }
    }

    private static void handleEvent(String in){
        try{
            String name = in.split(" /from ")[0].split("event ")[1];
            String from = in.split(" /from ")[1].split(" /to ")[0];
            String to = in.split(" /from ")[1].split(" /to ")[1];
            if(name.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()){
                handleError(4);
            }else{
                Event e = new Event(name,from,to);
                taskList.add(e);
                System.out.println(Constants.INDENT+Constants.ADD_TASK);
                System.out.println(Constants.INDENT+Constants.INDENT+e.toString());
                System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            handleError(4);
        }
    }
                
    //error codes
    //1:invalid instruction
    //2:name field of todo is empty or whitespace
    //3:name and/or by field of deadline is empty or whitespace
    //4:name and/or from and/or to field of event is empty or whitespace
    //5:no such task exists or task number is empty or whitespace
    private static void handleError(int error){
        switch(error){
            case 1: System.out.println(Constants.INDENT+Constants.INSTR_INVALID);
                    break;
            case 2: System.out.println(Constants.INDENT+Constants.TODO_EMPTY_WS);
                    break;
            case 3: System.out.println(Constants.INDENT+Constants.DEADLINE_EMPTY_WS);
                    break;
            case 4: System.out.println(Constants.INDENT+Constants.EVENT_EMPTY_WS);
                    break;
            case 5: System.out.println(Constants.INDENT+Constants.LIST_IDX_INVALID);
                    break;
        }
    }
}
