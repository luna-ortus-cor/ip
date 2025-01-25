package miku;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Parser{
    private Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private Scanner sc = new Scanner(System.in);

    public Parser(Ui ui){
        this.ui=ui;
        this.taskList=new TaskList(ui);
        this.storage=new Storage(ui);
    }

    public void start(){
        loadTaskList();
        ui.printStartMsg();
    }

    public int parse(String in){
        in=in.trim();
        Pattern listPattern = Pattern.compile("^list$");
        Pattern markPattern = Pattern.compile("^mark (\\d+)$");
        Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Pattern deleteAllPattern = Pattern.compile("^delete /all$");
        Pattern todoPattern = Pattern.compile("^todo (.+)$");
        Pattern deadlinePattern = Pattern.compile("^deadline (.+)$");
        Pattern eventPattern = Pattern.compile("^event (.+)$");
        Pattern simpleCommandsPattern = Pattern.compile("^(games|track|stats|chat|bye)$");
        Pattern searchNamePattern = Pattern.compile("^find (.+)$");
        Matcher matcher;

        if (listPattern.matcher(in).matches()) {
            printList(taskList.getList(), 0);
        } else if ((matcher = markPattern.matcher(in)).matches()) {
            handleMark(matcher.group(1), 1);
        } else if ((matcher = unmarkPattern.matcher(in)).matches()) {
            handleMark(matcher.group(1), 0);
        } else if ((matcher = deletePattern.matcher(in)).matches()) {
            handleDelete(matcher.group(1));
        } else if (deleteAllPattern.matcher(in).matches()) {
            handleDeleteAll();
        } else if ((matcher = todoPattern.matcher(in)).matches()) {
            handleTodo(matcher.group(1));
        } else if ((matcher = deadlinePattern.matcher(in)).matches()) {
            handleDeadline(matcher.group(1));
        } else if ((matcher = eventPattern.matcher(in)).matches()) {
            handleEvent(matcher.group(1));
        } else if ((matcher = searchNamePattern.matcher(in)).matches()) {
            handleSearchName(matcher.group(1));
        } else if ((matcher = simpleCommandsPattern.matcher(in)).matches()) {
            String command = matcher.group(1);
            switch (command) {
                case "games":
                    handleGame();
                    break;
                case "track":
                    handleTrack();
                    break;
                case "stats":
                    handleStats();
                    break;
                case "chat":
                    handleChat();
                    break;
                case "bye":
                    handleExit();
                    return 0;
            }
        } else {
            handleError(1);
        }
        return 1;
    }

    private void handleExit(){
        saveTaskList();
        ui.printExitMsg();
    }

    private void loadTaskList(){
        taskList.loadTasks(storage);
    }
    
    private void saveTaskList(){
        taskList.saveTasks(storage);
    }

    //type=0 is task, type=1 is game, type=2 is track, type=3 is stats, type=4 is search
    private <T> void printList(ArrayList<T> list,int type){
        int idx = 1;
        if(type==0){
            ui.printTaskListMsg();
        }else if(type==1){
            ui.printGamesMsg();
        }else if(type==2){
            ui.printTrackMsg();
        }else if(type==3){
            ui.printStatsMsg();
        }else{
            ui.printSearchMsg();
        }
        for(T t:list){
            ui.printListItem(idx,t);
            idx++;
        }
    }
    
    //mark done if mark=1, else mark not done if mark=0
    private void handleMark(String idx, int mark){
        if(mark==1){
            taskList.markDone(Integer.valueOf(idx.trim())-1);
        }else{
            taskList.markNotDone(Integer.valueOf(idx.trim())-1);
        }
    }

    private void handleDelete(String idx){
        taskList.delete(Integer.valueOf(idx.trim())-1);
    }

    private void handleDeleteAll(){
        taskList.deleteAll();
    }

    private void handleTodo(String in){
        Pattern todoPattern = Pattern.compile("^(.*)$");
        Matcher matcher = todoPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            if (name.isEmpty()) {
                handleError(2); // Empty task description
            } else {
                taskList.addTodo(name);
            }
        } else {
            handleError(2); // Invalid input
        }
    }

    private void handleDeadline(String in){
        Pattern deadlinePattern = Pattern.compile("^(.*?)\\s+/by\\s+(.*)$");
        Matcher matcher = deadlinePattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String by = matcher.group(2).trim();

            if (name.isEmpty() || by.isEmpty()) {
                handleError(3); // Empty task description or deadline
            } else {
                taskList.addDeadline(name, by);
            }
        } else {
            handleError(3); // Invalid input
        }
    }

    private void handleEvent(String in){
        Pattern eventPattern = Pattern.compile("^(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)$");
        Matcher matcher = eventPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();

            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                handleError(4); // Empty task description, from, or to fields
            } else {
                taskList.addEvent(name, from, to);
            }
        } else {
            handleError(4); // Invalid input
        }
    }

    private void handleGame(){
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

    private void handleTrack(){
        printList(Constants.TRACK_LIST,2);
        int choice = sc.nextInt();sc.nextLine();
        if(choice==1){
            System.out.print("Enter START DATE (YYYY-MM-DD): ");
            String startDate = sc.nextLine();
            System.out.print("Enter START TIME (HH:mm): ");
            String startTime = sc.nextLine();
            System.out.print("Enter END DATE (YYYY-MM-DD): ");
            String endDate = sc.nextLine();
            System.out.print("Enter END TIME (HH:mm): ");
            String endTime = sc.nextLine();
            System.out.print("Enter ACTIVITY: ");
            String name = sc.nextLine();
            Activity a = new Activity(startDate, startTime, endDate, endTime, name);
            TimeTracker.saveActivityToFile(a);
        }
    }

    private void handleStats(){
        printList(Constants.TRACK_LIST,3);
        int choice = sc.nextInt();sc.nextLine();
        if(choice==1){
            TimeTracker.displayStatistics();
        }
    }

    private void handleChat(){
        int choice = sc.nextInt();sc.nextLine(); //choose language
    }

    private void handleSearchName(String in){
        ArrayList<Task> searchList = taskList.searchName(in);
        printList(searchList,4);
    }
                
    //error codes
    //1:invalid instruction
    //2:name field of todo is empty or whitespace
    //3:name and/or by field of deadline is empty or whitespace
    //4:name and/or from and/or to field of event is empty or whitespace
    //5:no such task exists or task number is empty or whitespace
    //6:error reading task list from file
    //7:error writing task list to file
    private void handleError(int code){
        ui.printErrorMsg(code);
    }
}
