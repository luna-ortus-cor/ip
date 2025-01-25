package miku;

public class Task{
    String name;
    boolean isDone;
    
    /**
     * Creates a new Task instance
     *
     * @param name description of Task
     */
    public Task(String name){
        this.name=name;
        this.isDone=false;
    }

    /**
     * Creates a new Task instance specifying the doneness of the Task
     *
     * @param name description of Task
     * @param isDone boolean denoting if task is done
     */
    public Task(String name, boolean isDone){
        this.name=name;
        this.isDone=isDone;
    }

    /**
     * Marks the task as done if not done.
     *
     * @return an int denoting a successful change in doneness
     */
    public int markDone(){
        if(!this.isDone){
            this.isDone=!this.isDone;
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Marks the task as not done if done.
     *
     * @return an int denoting a successful change in doneness
     */
    public int markNotDone(){
        if(this.isDone){
            this.isDone=!this.isDone;
            return 1;
        }else{
            return 0;
        }
    }
    
    /**
     * Returns a string representation of the task for the UI.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString(){
        return "["+(this.isDone?"X":" ")+"] "+this.name;
    }

    /**
     * Returns a string representation of the task for the save file.
     *
     * @return a string representation of the task
     */
    public String toSaveFormat(){
        return (this.isDone?"1":"0")+ " | "+this.name;
    }
}
