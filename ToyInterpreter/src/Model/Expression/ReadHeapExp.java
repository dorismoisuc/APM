package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Type.refType;
import Model.Value.Value;
import Model.Value.refValue;

public class ReadHeapExp  implements Expression {
    private final Expression expr;

    public ReadHeapExp(Expression expr) {
        this.expr = expr;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heap) throws ExpExcept {
        Value val=expr.evaluate(symbolTable,heap);
        if (!(val instanceof refValue))
            throw new ExpExcept("Not of reference value\n");
        int addr=((refValue)val).getAddress();
       // System.out.println(val);
        if (!heap.exists(addr))
            throw new ExpExcept("Ref addr was not found");
        return heap.getValue(addr);
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type type = expr.typeCheck(typeEnv);
        if (type instanceof refType)
        {
            refType ref = (refType) type;
            return ref.getInner();
        }
        else {
            throw new ExpExcept("The ReadHeapExp is not a ref type");
        }

    }

    @Override
    public String toString(){
        return " read heap (" + this.expr.toString() + ") ";
    }

}
