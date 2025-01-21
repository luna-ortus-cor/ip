import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Miku{
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT+"hello! i'm Miku desuyo!");
        System.out.println(Constants.INDENT+"what can i do for you?");
        
        String in = sc.nextLine();
        while(!in.equals("bye")){
            if(in.trim().equals("list")){
                printList(taskList,0);       
            }else if(in.matches("mark \\d+")){
                handleMark(in,1);
            }else if(in.matches("unmark \\d+")){
                handleMark(in,0);
            }else if(in.matches("delete \\d+")){
                handleDelete(in);
            }else if(in.split(" ")[0].equals("todo")){
                handleTodo(in);
            }else if(in.split(" ")[0].equals("deadline")){
                handleDeadline(in);
            }else if(in.split(" ")[0].equals("event")){
                handleEvent(in);
            }else if(in.split(" ")[0].equals("games")){
                handleGame();
            }else{
                handleError(1);
            }
            in=sc.nextLine();
        }
        System.out.println(Constants.INDENT+Constants.EXIT_MSG);
    }
    
    //type=0 is task, type=1 is game
    private static <T> void printList(ArrayList<T> list,int type){
        int idx = 1;
        if(type==0){
            System.out.println(Constants.INDENT+Constants.TASK_LIST_MSG);
        }else{
            System.out.println(Constants.INDENT+Constants.GAMES_MSG);
        }
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

    private static void handleDelete(String in){
        try{
            int idx = Integer.valueOf(in.split(" ")[1])-1;
            taskList.get(idx).delete();
            taskList.remove(idx);
            System.out.println(Constants.INDENT+"now you have "+taskList.size()+" tasks in the list.");
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

    private static void handleGame(){
        printList(Constants.GAMES_LIST,1);
        int choice = sc.nextInt();sc.nextLine();
        if(choice==1){
            System.out.println("Welcome to Mental Math Game!");
            System.out.print("Select difficulty (1: Easy, 2: Normal, 3: Hard, 4: Insane): ");
            int difficulty = sc.nextInt();sc.nextLine();
            System.out.print("Select length (1: Short, 2: Normal, 3: Long): ");
            int length = sc.nextInt();sc.nextLine();
            MentalMathGame game = new MentalMathGame(difficulty, length);
            game.startGame();
        }else if(choice==2){
            try{
                System.out.println("Welcome to Wordle Game!");
                System.out.print("Select difficulty (1: Easy, 2: Normal, 3: Hard): ");
                int difficulty = sc.nextInt();sc.nextLine();
                WordleGame game = new WordleGame(difficulty);
                game.startGame();
            }catch(IOException e) {
                System.out.println("Error loading word list: " + e.getMessage());   
            }
        }else{
            //go back
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
