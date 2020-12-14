package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.ListInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements Stmt{

    private final Expression expr;

    public PrintStmt(Expression expr) {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        ListInterface<Value> output=programState.getOutput();
        HeapInterface heap = programState.getHeap();
        DictionaryInterface<String,Value> symbolTable=programState.getSymbolTable();
        output.append(expr.evaluate(symbolTable,heap));
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        expr.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return " OUT(" + expr + ") ";
    }
}
