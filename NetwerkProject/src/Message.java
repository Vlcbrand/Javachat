import java.util.Date;

/**
 * Created by Vincent Brands on 5/30/2016.
 */
public class Message {

    protected String name;
    protected String message;
    private Date date;


    public Message(String name, String message){
        this.name = name;
        this.message = message;
        date = new Date();

    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getTime(){
        if(date.getMinutes() <10)
        return "" + date.getHours() + ":0" + date.getMinutes();
        else
            return "" + date.getHours() + ":" + date.getMinutes();
    }
}
