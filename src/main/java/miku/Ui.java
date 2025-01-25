package miku;

public class Ui{
    public Ui(){

    }

    /**
     * Prints start message.
     */
    public void printStartMsg(){
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT+Constants.START_MSG_1);
        System.out.println(Constants.INDENT+Constants.START_MSG_2);
    }

    /**
     * Prints a message prompting user for next instruction.
     */
    public void printNextInstrMsg(){
        System.out.println(Constants.AWAIT_INSTR);
    }

    /**
     * Prints exit message.
     */
    public void printExitMsg(){
        System.out.println(Constants.INDENT+Constants.EXIT_MSG);
    }

    /**
     * Prints a list item.
     *
     * @param idx the index of the item in the list
     * @param item the item to be printed
     */
    public void printListItem(int idx, Object item){
        System.out.println(Constants.INDENT+Constants.INDENT+idx+". "+item.toString());
    }

    /**
     * Prints the message when the user marks a task as done.
     *
     * @param task the task to be marked done
     * @param response an int denoting if the task is already done or not
     */
    public <T extends Task> void printMarkDoneMsg(T task, int response){
        if(response==1){
            System.out.println(Constants.INDENT+Constants.TASK_MARK_DONE_SUCCESS);
            System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        }else{
            System.out.println(Constants.INDENT+Constants.TASK_MARK_DONE_FAILURE);
        }
    }

    /**
     * Prints the message when the user marks a task as not done.
     *
     * @param task the task to be marked not done
     * @param response an int denoting if the task is already not done or not
     */
    public <T extends Task> void printMarkNotDoneMsg(T task, int response){
        if(response==1){
            System.out.println(Constants.INDENT+Constants.TASK_MARK_NOT_DONE_SUCCESS);
            System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        }else{
            System.out.println(Constants.INDENT+Constants.TASK_MARK_NOT_DONE_FAILURE);
        }
    }

    /**
     * Prints the message when the user adds a task.
     *
     * @param task the task to be added
     * @param num the number of tasks in the task list after the addition
     */
    public <T extends Task> void printAddMsg(T task, int num){
        System.out.println(Constants.INDENT + Constants.ADD_TASK);
        System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        System.out.println(Constants.INDENT + "now you have " + num+ " tasks in the list.");
    }

    /**
     * Prints the message when the user deletes a task.
     *
     * @param task the task to be deleted
     * @param num the number of tasks in the task list after the deletion
     */
    public <T extends Task> void printDeleteMsg(T task, int num){
        System.out.println(Constants.INDENT+"UwU i've removed this task:");
        System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        System.out.println(Constants.INDENT + "now you have " + num+ " tasks in the list.");
    }

    /**
     * Prints the message when the user deletes all tasks.
     */
    public void printDeleteAllMsg(){
        System.out.println(Constants.INDENT+"UwU i've removed all tasks");
    }

    /**
     * Prints the message when the user asks to list all tasks.
     */
    public void printTaskListMsg(){
        System.out.println(Constants.INDENT+Constants.TASK_LIST_MSG);
    }

    /**
     * Prints the message when the user asks to play a game.
     */
    public void printGamesMsg(){
        System.out.println(Constants.INDENT+Constants.GAMES_MSG);
    }

    /**
     * Prints the message when the user asks to track a statistic.
     */
    public void printTrackMsg(){
        System.out.println(Constants.INDENT+Constants.TRACK_MSG);
    }

    /**
     * Prints the message when the user asks to view statistics.
     */
    public void printStatsMsg(){
        System.out.println(Constants.INDENT+Constants.STATS_MSG);
    }

    /**
     * Prints the relevant error message given an error code.
     *
     * @param code an int denoting the specific type of error
     */
    public void printErrorMsg(int code){
        switch(code){
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
            case 6: System.out.println(Constants.INDENT+Constants.READ_LIST_FILE_ERROR);
                    break;
            case 7: System.out.println(Constants.INDENT+Constants.WRITE_LIST_FILE_ERROR);
                    break;
        }
    }
}
