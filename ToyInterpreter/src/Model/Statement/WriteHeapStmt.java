package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.HeapExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.Heap;
import Model.ADT.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.refType;
import Model.Value.Value;
import Model.Value.refValue;

import java.io.FileNotFoundException;

public class WriteHeapStmt implements Stmt {
    private final String varName;
    private final Expression expr;

    public WriteHeapStmt(String varName, Expression expr) {
        this.varName = varName;
        this.expr = expr;
    }


    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept, FileNotFoundException, HeapExcept {
        DictionaryInterface<String, Value> symbolTable=programState.getSymbolTable();
        HeapInterface heapInterface = programState.getHeap();

        if (!symbolTable.isDefined(varName))
        {
            throw new HeapExcept("The var is not defined in sym table");
        }
        Value val=symbolTable.lookUp(varName);
        if (!(val instanceof refValue))
        {
            throw new HeapExcept("Var is not of ref value");
        }
        refValue ref= (refValue)val;
        if (!heapInterface.exists(ref.getAddress())){
            throw new HeapExcept("It's not defined");
        }
        Value val2=expr.evaluate(symbolTable,heapInterface);
        if (!val2.getType().equals(ref.getLocationType())){
            throw new HeapExcept("The expr is not a reference");
        }
        heapInterface.setHeap(ref.getAddress(),val2);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type typeVar= typeEnv.lookUp(varName);
        Type typeExpr = expr.typeCheck(typeEnv);

        if (!(typeVar.equals(new refType(typeExpr)))){
            throw new StmtExcept("Var type should be a ref");
        }
        return typeEnv;
    }

    @Override
    public String toString(){
        return " WRITE HEAP (" + varName + " , "+expr.toString()+") ";
    }
}
