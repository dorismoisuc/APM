package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class CompStmt implements Stmt{

    private final Stmt stmt1;
    private final Stmt stmt2;

    public CompStmt(Stmt stmt1, Stmt stmt2) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }


    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        StackInterface<Stmt> executionStack = programState.getExeStack();
        executionStack.push(stmt2);
        executionStack.push(stmt1);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        return stmt2.typeCheck(stmt1.typeCheck(typeEnv));
    }

    @Override
    public String toString(){
        return stmt1+" | "+stmt2;
    }
}
