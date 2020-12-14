package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public class ValExp implements Expression {
    private final Value value;

    public ValExp(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heapInterface) throws ExpExcept {
        return value;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        return value.getType();
    }

    @Override
    public String toString(){
        return value.toString();
    }

}
