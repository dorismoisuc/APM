package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.Dictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public interface Expression {
    Value evaluate(DictionaryInterface<String,Value> symbolTable, HeapInterface heap) throws ExpExcept;
    Type typeCheck(DictionaryInterface<String,Type> typeEnv) throws Exception;
}
