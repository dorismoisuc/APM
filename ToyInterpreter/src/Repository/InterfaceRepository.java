package Repository;
import Model.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;

public interface InterfaceRepository {


    void logPrgStateExec(PrgState prgState) throws Exception;

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgs);

    PrgState getMainProgram();
    void addPrg(PrgState prg);

}
