package in.bvm.college.icwstcsc;

public class MessageList{
    public int type;
    public String message;

    public MessageList(String message,int type){
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}