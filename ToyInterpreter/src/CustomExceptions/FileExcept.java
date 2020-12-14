package CustomExceptions;

public class FileExcept extends Exception{
    public FileExcept(String message){super(message);}
    @Override
    public String getMessage() {
        return "\n± FILE ERROR: " + super.getMessage();
    }
}
