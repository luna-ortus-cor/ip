public class Deadline extends Task{
    String by;

    public Deadline(String name, String by){
        super(name);
        this.by=by;
    }

    public Deadline(String name, boolean done, String by){
        super(name,done);
        this.by=by;
    }

    @Override
    public String toString(){
        return "[D] "+super.toString()+" (by: "+this.by+")";
    }
}
