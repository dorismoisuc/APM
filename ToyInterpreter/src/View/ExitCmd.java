package View;

public class ExitCmd extends Command{
    public ExitCmd(String keyy, String desc) {
        super(keyy, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
