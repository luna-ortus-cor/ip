public class Todo extends Task{
    public Todo(String name){
        super(name);
    }

    public Todo(String name, boolean done){
        super(name,done);
    }

    @Override
    public String toString(){
        return "[T] "+super.toString();
    }

    public String toSaveFormat(){
        return "T | "+super.toSaveFormat();
    }
}
