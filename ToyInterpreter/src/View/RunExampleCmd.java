package View;

import Controller.Controller;
import Model.ADT.Dictionary;

public class RunExampleCmd extends Command{
    private Controller ctr;
    public RunExampleCmd(String keyy, String desc,Controller c) {
        super(keyy, desc);
        ctr=c;

    }

    @Override
    public void execute(){
        try{
            this.ctr.getRepository().getPrgList().get(0).getExeStack().getValues().get(0).typeCheck(new Dictionary<>());
            this.ctr.allSteps();
        }
        catch (Exception ctrexcept)
        {
            System.out.println(ctrexcept.getMessage());
        }

    }
}
