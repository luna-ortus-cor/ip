import java.util.ArrayList;
import java.util.Arrays;

public class Constants{
    public static final String INDENT = "    ";
    public static final String MIKU_LOGO =  " __  __ _ _\n|  \\/  (_) | ___   _\n| |\\/| | | |/ / | | |\n| |  | | |   <| |_| |\n|_|  |_|_|_|\\_\\\\__,_|";
    public static final String EXIT_MSG = "byee~ hope to see you again soon!";
    public static final String INSTR_INVALID = ":3 oops! i don't understand that!";
    public static final String ADD_TASK = "got it! I've added this task desu:";
    public static final String TASK_LIST_MSG = "here are the tasks in your list desu:";
    public static final String TODO_EMPTY_WS = ":3 oops! name field of todo cannot be empty!";
    public static final String DEADLINE_EMPTY_WS = ":3 oops! name and/or by field of deadline cannot be empty!";
    public static final String EVENT_EMPTY_WS = ":3 oops! name and/or from and/or to field of event cannot be empty!";
    public static final String LIST_IDX_INVALID = ":3 oops! no such task exists or task number is empty!";
    public static final String GAMES_MSG = "yay~ here are the list of games available!";
    public static final ArrayList<String> GAMES_LIST = new ArrayList<String>(Arrays.asList("Mental Math","Wordle","back")); 
}
