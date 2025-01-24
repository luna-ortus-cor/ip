package miku;

public class Task{
    String name;
    boolean done;

    public Task(String name){
        this.name=name;
        this.done=false;
    }

    public Task(String name, boolean done){
        this.name=name;
        this.done=done;
    }

    public int markDone(){
        if(!this.done){
            this.done=!this.done;
            return 1;
        }else{
            return 0;
        }
    }

    public int markNotDone(){
        if(this.done){
            this.done=!this.done;
            return 1;
        }else{
            return 0;
        }
    }
    
    @Override
    public String toString(){
        return "["+(this.done?"X":" ")+"] "+this.name;
    }

    public String toSaveFormat(){
        return (this.done?"1":"0")+ " | "+this.name;
    }
}
