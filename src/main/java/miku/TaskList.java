package miku;

import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String FILE_PATH = Constants.FILEPATH_TASKLIST;
    private Ui ui;

    public TaskList(Ui ui){
        this.ui=ui;
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public void loadTasks(Storage s){
        this.taskList=s.readTasks(FILE_PATH);
    }

    public void saveTasks(Storage s){
        s.writeTasks(this.taskList,FILE_PATH);
    }

    public void markDone(int idx){
        try{
            int response = taskList.get(idx).markDone();
            ui.printMarkDoneMsg(taskList.get(idx),response);
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    public void markNotDone(int idx){
        try{
            int response = taskList.get(idx).markNotDone();
            ui.printMarkNotDoneMsg(taskList.get(idx),response);
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    public void delete(int idx){
        try{
            Task t = taskList.get(idx);
            taskList.remove(idx);
            ui.printDeleteMsg(t,taskList.size());
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    public void deleteAll(){
        taskList.clear();
        ui.printDeleteAllMsg();
    }

    public void addTodo(String name){
        Todo t = new Todo(name);
        taskList.add(t);
        ui.printAddMsg(t,taskList.size());
    }

    public void addDeadline(String name, String by){
        Deadline d = new Deadline(name, by);
        taskList.add(d);
        ui.printAddMsg(d,taskList.size());
    }

    public void addEvent(String name, String from, String to){
        Event e = new Event(name, from, to);
        taskList.add(e);
        ui.printAddMsg(e,taskList.size());
    }

    private void handleError(int code){
        ui.printErrorMsg(code);
    }
}
