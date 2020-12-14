package Model.Expression;

import CustomExceptions.ExpExcept;
import CustomExceptions.HeapExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Type.intType;
import Model.Value.Value;
import Model.Value.intValue;

public class ArithExp implements Expression{

    private final Expression exp1;
    private final Expression exp2;
    private final char operator;

    public ArithExp(char operator,Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heap) throws ExpExcept {
        Value val1=null, val2=null;
        try{
            val1=exp1.evaluate(symbolTable,heap);
        } catch (ExpExcept expExcept) {
            expExcept.printStackTrace();
        }

        if(!val1.getType().equals(new intType()))
            throw new ExpExcept("1st operand is not of type int\n");

        val2=exp2.evaluate(symbolTable,heap);


        if(!val2.getType().equals(new intType()))
            throw new ExpExcept("2nd operand is not of type int\n");
        intValue result=new intValue(0);
        switch(operator)
        {
            case '+'->result=new intValue(((intValue)val1).getValue()+((intValue)val2).getValue());
            case '-'->result=new intValue(((intValue)val1).getValue()-((intValue)val2).getValue());
            case '*'->result=new intValue(((intValue)val1).getValue()*((intValue)val2).getValue());
            case '/'->{
                if (((intValue)val2).getValue()==0)
                    throw new ExpExcept("DIVISION BY 0±\n");
                result=new intValue(((intValue)val1).getValue()/((intValue)val2).getValue());
            }
        }
        return result;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type t1, t2;
        t1 = exp1.typeCheck(typeEnv);
        t2 = exp2.typeCheck(typeEnv);

        if (!t1.equals(new intType()))
            throw new ExpExcept("First operand is not of type int");
        if (!t2.equals(new intType()))
            throw new ExpExcept("Second operand is not of type int");
        return new intType();

    }

    @Override
    public String toString()
    {
        return " (" + exp1 + operator + exp2 + ") ";
    }
}
