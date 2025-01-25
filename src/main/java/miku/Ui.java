package miku;

public class Ui{
    public Ui(){

    }

    public void printStartMsg(){
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT+Constants.START_MSG_1);
        System.out.println(Constants.INDENT+Constants.START_MSG_2);
    }

    public void printNextInstrMsg(){
        System.out.println(Constants.AWAIT_INSTR);
    }

    public void printExitMsg(){
        System.out.println(Constants.INDENT+Constants.EXIT_MSG);
    }

    public void printListItem(int idx, Object item){
        System.out.println(Constants.INDENT+Constants.INDENT+idx+". "+item.toString());
    }
    
    public void printSearchMsg(){
        System.out.println(Constants.INDENT+Constants.SEARCH_MSG);
    }

    public <T extends Task> void printMarkDoneMsg(T task, int response){
        if(response==1){
            System.out.println(Constants.INDENT+Constants.TASK_MARK_DONE_SUCCESS);
            System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        }else{
            System.out.println(Constants.INDENT+Constants.TASK_MARK_DONE_FAILURE);
        }
    }

    public <T extends Task> void printMarkNotDoneMsg(T task, int response){
        if(response==1){
            System.out.println(Constants.INDENT+Constants.TASK_MARK_NOT_DONE_SUCCESS);
            System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        }else{
            System.out.println(Constants.INDENT+Constants.TASK_MARK_NOT_DONE_FAILURE);
        }
    }

    public <T extends Task> void printAddMsg(T task, int num){
        System.out.println(Constants.INDENT + Constants.ADD_TASK);
        System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        System.out.println(Constants.INDENT + "now you have " + num+ " tasks in the list.");
    }

    public <T extends Task> void printDeleteMsg(T task, int num){
        System.out.println(Constants.INDENT+"UwU i've removed this task:");
        System.out.println(Constants.INDENT+Constants.INDENT+task.toString());
        System.out.println(Constants.INDENT + "now you have " + num+ " tasks in the list.");
    }

    public void printDeleteAllMsg(){
        System.out.println(Constants.INDENT+"UwU i've removed all tasks");
    }

    public void printTaskListMsg(){
        System.out.println(Constants.INDENT+Constants.TASK_LIST_MSG);
    }

    public void printGamesMsg(){
        System.out.println(Constants.INDENT+Constants.GAMES_MSG);
    }

    public void printTrackMsg(){
        System.out.println(Constants.INDENT+Constants.TRACK_MSG);
    }

    public void printStatsMsg(){
        System.out.println(Constants.INDENT+Constants.STATS_MSG);
    }

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
