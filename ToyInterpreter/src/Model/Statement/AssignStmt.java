package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements Stmt{


    private final String id;
    private final Expression expr;

    public AssignStmt(String id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }


    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        DictionaryInterface<String, Value> symbolTable = programState.getSymbolTable();
        HeapInterface heap = programState.getHeap();
        if (!symbolTable.isDefined(id))
            throw new StmtExcept("Variable id is not declared ±\n");
        Value value=expr.evaluate(symbolTable,heap);
        if (!value.getType().equals(symbolTable.lookUp(id).getType()))
            throw new StmtExcept("Type of expression and type of variable do not match ±\n");
        symbolTable.update(id,value);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type typeVar = (Type) typeEnv.lookUp(id);
        Type typeExp = expr.typeCheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new StmtExcept("RHS and LFS have different types");
    }

    @Override
    public String toString(){
        return id + "= " +expr;
    }
}
