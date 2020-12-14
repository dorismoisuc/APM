package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Value.Value;
import Model.Value.boolValue;

public class IfStmt implements Stmt{
    private final Expression expr;
    private final Stmt ifStmt;
    private final Stmt elseStmt;

    public IfStmt(Expression expr, Stmt ifStmt, Stmt elseStmt) {
        this.expr = expr;
        this.ifStmt = ifStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        StackInterface<Stmt>  executionStack = programState.getExeStack();
        HeapInterface heap = programState.getHeap();
        Value condition=expr.evaluate(programState.getSymbolTable(),heap);
        if (!condition.getType().equals(new boolType()))
            throw new StmtExcept("conditional expr is not a boolean ±\n");
        if (((boolValue)condition).getValue())
            executionStack.push(ifStmt);
        else
            executionStack.push(elseStmt);
        return null;

    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type typeExp = expr.typeCheck(typeEnv);
        if (typeExp.equals(new boolType())){
            ifStmt.typeCheck(typeEnv.createCopy());
            elseStmt.typeCheck(typeEnv.createCopy());
            return typeEnv;
        }
        else {
            throw new StmtExcept("The condition of IF has not type BOOL");
        }
    }

    public String toString(){
        String display="";
        display+="IF (" + expr + ") THEN{ " + ifStmt+" ELSE { " + elseStmt+"} ";
        return display;
    }
}
