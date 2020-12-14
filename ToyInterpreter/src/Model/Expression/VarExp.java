package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Expression{

    private final String variable;

    public VarExp(String variable) {
        this.variable = variable;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heapInterface) throws ExpExcept {
        return symbolTable.lookUp(variable);
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
       return typeEnv.lookUp(variable);
    }

    @Override
    public String toString(){
        return variable;
    }
}
