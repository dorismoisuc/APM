package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Type.intType;
import Model.Value.Value;
import Model.Value.boolValue;
import Model.Type.boolType;

public class LogicExp implements Expression {
    private final Expression exp1;
    private final Expression exp2;
    private final String operator;

    public LogicExp(Expression exp1, Expression exp2, String operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heap) throws ExpExcept {
       Value value1=exp1.evaluate(symbolTable,heap);
       if (!value1.getType().equals(new boolType()))
           throw new ExpExcept("1st operand is not of type bool\n");
       Value value2=exp2.evaluate(symbolTable,heap);
       if (!value2.getType().equals(new boolType()))
           throw new ExpExcept("2nd operand is not of type bool\n");

       new boolValue(true);
       Value result=switch(operator){
           case "and"->new boolValue(((boolValue)value1).getValue()&&((boolValue)value2).getValue());
           case "or"->new boolValue(((boolValue)value1).getValue()||((boolValue)value2).getValue());
           default->new boolValue(true);
       };
       return result;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type t1, t2;
        t1 = exp1.typeCheck(typeEnv);
        t2 = exp2.typeCheck(typeEnv);

        if (!t1.equals(new boolType()))
            throw new ExpExcept("First operand is not of type bool");
        if (!t2.equals(new boolType()))
            throw new ExpExcept("Second operand is not of type bool");
        return new boolType();

    }

    @Override
    public String toString(){
        return " (" +exp1+ " " + operator + " " + exp2 + ") ";
    }
}
