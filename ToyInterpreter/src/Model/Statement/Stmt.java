package Model.Statement;
import CustomExceptions.*;
import Model.ADT.Dictionary;
import Model.ADT.DictionaryInterface;
import Model.ProgramState.PrgState;
import CustomExceptions.ExpExcept;
import Model.Type.Type;
import Model.Value.Value;

import java.io.FileNotFoundException;

public interface Stmt {
    PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept, FileNotFoundException, HeapExcept;
    DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String,Type> typeEnv) throws Exception;
}
