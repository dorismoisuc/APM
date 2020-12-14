package CustomExceptions;

public class ControllerExcept extends Exception {
    public ControllerExcept(String message){super(message);}
    @Override
    public String getMessage() {
        return "\n± CONTROLLER ERROR: " + super.getMessage();
}
}
