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

    public void markDone(){
        if(!this.done){
            this.done=!this.done;
            System.out.println(Constants.INDENT+"YATTA! I've marked this task as done:");
            System.out.println(Constants.INDENT+Constants.INDENT+this.toString());
        }else{
            System.out.println(Constants.INDENT+"Task is already done");
        }
    }

    public void markNotDone(){
        if(this.done){
            this.done=!this.done;
            System.out.println(Constants.INDENT+"T_T I've marked this task as not done:");
            System.out.println(Constants.INDENT+Constants.INDENT+this.toString());
        }else{
            System.out.println("Task is already not done");
        }
    }

    public void delete(){
        System.out.println(Constants.INDENT+"UwU i've removed this task:");
        System.out.println(Constants.INDENT+Constants.INDENT+this.toString());
    }
    
    @Override
    public String toString(){
        return "["+(this.done?"X":" ")+"] "+this.name;
    }
}
