package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.HeapExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.Dictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.MyStack;
import Model.ADT.StackInterface;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Map;
import java.util.Stack;

public class ForkStmt implements Stmt {

    Stmt forkStmt;

    public ForkStmt(Stmt forkStmtvar)
    {
        this.forkStmt=forkStmtvar;
    }

    public Stmt createCopy(){
        return new ForkStmt(forkStmt);
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept, FileNotFoundException, HeapExcept {

        DictionaryInterface<String, Value> symTableCopy = programState.getSymbolTable().createCopy();
        StackInterface<Stmt> newExeStack = new MyStack<>();
        newExeStack.push(this.forkStmt);
        PrgState newPrgState = new PrgState(newExeStack,symTableCopy,programState.getOutput(), programState.getFileTable(),programState.getHeap());
        System.out.println(newPrgState.toString());
        newPrgState.setId();
        return newPrgState;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        forkStmt.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return " FORK STMT(" + forkStmt.toString() +") ";
    }
}
