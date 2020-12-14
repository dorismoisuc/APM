package Repository;

import Model.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Repository implements InterfaceRepository{

    // private final ArrayList<PrgState> programs;

    private List<PrgState> programStates;

    private String filePath="";

    private boolean firstFile = true;


    public Repository(String fileP) {
        this.programStates = new ArrayList<>(); filePath=fileP;
    }

    public void setFilePath(String path){this.filePath=path;}


    @Override
    public void logPrgStateExec(PrgState prgState) throws Exception {
        try{
            if (this.firstFile)
            {
                PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
                logFile.write(prgState.toString());
                logFile.close();
                this.firstFile = false;
                return;
            }
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            logFile.write(prgState.toString());
            logFile.close();
        }
        catch(IOException e){
            System.out.println(" \n± FILE ERROR: File path was not found\n");
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return programStates;
    }

    @Override
    public void setPrgList(List<PrgState> prgs) {
        this.programStates=prgs;
    }

    @Override
    public PrgState getMainProgram() {
        return programStates.get(0);
    }

    @Override
    public void addPrg(PrgState prg) {
        programStates.add(prg);
    }

}
