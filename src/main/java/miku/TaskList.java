package miku;

import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String FILE_PATH = Constants.FILEPATH_TASKLIST;
    private Ui ui;
    
    /**
     * Instantiates a new TaskList instance taking in a Ui ui.
     *
     * @param ui a Ui instance
     */
    public TaskList(Ui ui){
        this.ui=ui;
    }

    /**
     * Returns an arraylist of tasks in the TaskList.
     *
     * @return an arraylist of tasks in the TaskList
     */
    public ArrayList<Task> getList(){
        return this.taskList;
    }

    /**
     * Load tasks from a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void loadTasks(Storage s){
        this.taskList=s.readTasks(FILE_PATH);
    }

    /**
     * Save tasks to a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void saveTasks(Storage s){
        s.writeTasks(this.taskList,FILE_PATH);
    }

    /**
     * Mark the task at a specified index as done.
     *
     * @param idx the index of the task in the list
     */
    public void markDone(int idx){
        try{
            int response = taskList.get(idx).markDone();
            ui.printMarkDoneMsg(taskList.get(idx),response);
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    /**
     * Mark the task at a specified index as not done.
     *
     * @param idx the index of the task in the list
     */
    public void markNotDone(int idx){
        try{
            int response = taskList.get(idx).markNotDone();
            ui.printMarkNotDoneMsg(taskList.get(idx),response);
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    /**
     * Delete the task at a specified index.
     *
     * @param idx the index of the task in the list
     */
    public void delete(int idx){
        try{
            Task t = taskList.get(idx);
            taskList.remove(idx);
            ui.printDeleteMsg(t,taskList.size());
        }catch(IndexOutOfBoundsException e){
            handleError(5);
        }
    }

    /**
     * Delete all tasks in the list.
     */
    public void deleteAll(){
        taskList.clear();
        ui.printDeleteAllMsg();
    }

    /**
     * Add a new Todo Task to the list.
     *
     * @param name description of the Todo
     */
    public void addTodo(String name){
        Todo t = new Todo(name);
        taskList.add(t);
        ui.printAddMsg(t,taskList.size());
    }

    /**
     * Add a new Deadline Task to the list.
     *
     * @param name description of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     */
    public void addDeadline(String name, String by){
        Deadline d = new Deadline(name, by);
        taskList.add(d);
        ui.printAddMsg(d,taskList.size());
    }

    /**
     * Add a new Event Task to the list.
     *
     * @param name description of the Event
     * @param from a String representing either a colloquial time or valid date time formmated time
     * @param to a String representing either a colloquial time or valid date time formatted time
     */
    public void addEvent(String name, String from, String to){
        Event e = new Event(name, from, to);
        taskList.add(e);
        ui.printAddMsg(e,taskList.size());
    }

    private void handleError(int code){
        ui.printErrorMsg(code);
    }
}
