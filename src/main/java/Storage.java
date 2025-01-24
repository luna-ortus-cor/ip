import java.util.ArrayList;
import java.io.*;

public class Storage{
    private Ui ui;

    public Storage(Ui ui){
        this.ui=ui;
    }

    //we should also include generic read and write methods for arbitrary files

    public ArrayList<Task> readTasks(String fp){
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(fp);
        if(f.isFile()){
            try(BufferedReader br = new BufferedReader(new FileReader(fp))){
                String line;
                while((line=br.readLine())!=null){
                    String type=line.split("] ")[0];
                    String done=line.split("] ")[1];
                    String name=line.split("] ")[2].split(" ")[0];
                    if(type.equals("[T")){
                        taskList.add(new Todo(name,done.equals("[X")?true:false));
                    }else if(type.equals("[D")){
                        int start=line.indexOf("(");
                        int end=line.indexOf(")");
                        String by=line.substring(start+1,end).split("by: ")[1];
                        taskList.add(new Deadline(name,done.equals("[X")?true:false,by));
                    }else if(type.equals("[E")){
                        int start=line.indexOf("(");
                        int end=line.indexOf(")");
                        String from=line.substring(start+1,end).split(" to: ")[0].split("from: ")[1];
                        String to=line.substring(start+1,end).split(" to: ")[1];
                        taskList.add(new Event(name,done.equals("[X")?true:false,from,to));
                    }else{
                        //do nth
                    }
                }
            }catch(IOException e){
                handleError(6);
            }
        }
        return taskList;
    }
    //consider changing save format for easier read/write
    public void writeTasks(ArrayList<Task> taskList, String fp){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fp,false))){
            for(Task t:taskList){
                bw.write(t.toString());
                bw.newLine();
            }
        }catch(IOException e){
            handleError(7);
        }
    }

    private void handleError(int code){
        ui.printErrorMsg(code);
    }
}
