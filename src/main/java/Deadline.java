import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    String by;
    LocalDateTime byLDT;

    public Deadline(String name, String by){
        super(name);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.byLDT=LocalDateTime.parse(by, inputFormat);
            this.by=null;
        }catch(DateTimeParseException e){
            this.by=by;
            this.byLDT=null;
        }
    }

    public Deadline(String name, boolean done, String by){
        super(name,done);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.byLDT=LocalDateTime.parse(by, inputFormat);
            this.by=null;
        }catch(DateTimeParseException e){
            this.by=by;
            this.byLDT=null;
        }
    }

    public String getFormattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }

    public String getUnformattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HHmm");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }

    @Override
    public String toString(){
        return "[D] "+super.toString()+" (by: "+getFormattedDateTime(this.by,this.byLDT)+")";
    }

    public String toSaveFormat(){
        return "D | "+super.toSaveFormat()+" | "+getFormattedDateTime(this.by,this.byLDT);
    }
}
