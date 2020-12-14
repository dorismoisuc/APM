package CustomExceptions;

public class ADTexcept extends Exception{
    public ADTexcept(String message){super(message);}
    @Override
    public String getMessage(){
        return "\n±ADT ERROR: " + super.getMessage();
    }
}
