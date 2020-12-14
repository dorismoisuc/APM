package View;

public abstract class Command {
    private String key,description;
    public Command(String keyy,String desc)
    {
        key=keyy;
        description=desc;
    }

    public abstract void execute();

    public String getKey(){return key;}
    public String getDescription(){return description;}


}
