package Model.Expression;

import CustomExceptions.ExpExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Value.Value;
import Model.Value.boolValue;
import Model.Value.intValue;

public class RelationalExp implements Expression{
    private Expression expr1;
    private Expression expr2;
    private String op;

    public RelationalExp(Expression e1,Expression e2,String op)
    {
        this.expr1=e1;
        this.expr2=e2;
        this.op=op;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface heap) throws ExpExcept {
        Value value1=null;
        Value value2=null;
        try{ value1=expr1.evaluate(symbolTable,heap);}
        catch (ExpExcept except){except.printStackTrace();}
        if (!value1.getType().equals(new intType()))
            throw new ExpExcept("The first value given is not an int\n");
        try{ value2=expr2.evaluate(symbolTable,heap);}
        catch (ExpExcept except) { except.printStackTrace();}
        if(!value2.getType().equals(new intType()))
            throw new ExpExcept("The second value given is not an int\n");

        int nr1=((intValue)value1).getValue();
        int nr2=((intValue)value2).getValue();

        return switch(op){
            case "<"-> new boolValue(nr1<nr2);
            case "<="->new boolValue(nr1<=nr2);
            case "==" -> new boolValue(nr1==nr2);
            case ">"->new boolValue (nr1>nr2);
            case ">="-> new boolValue(nr1>=nr2);
            default ->throw new ExpExcept("Invalid operand"+op);
        };

    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type t1, t2;
        t1 = expr1.typeCheck(typeEnv);
        t2 = expr2.typeCheck(typeEnv);

        if (!t1.equals(new intType()))
            throw new ExpExcept("First operand is not of type int");
        if (!t2.equals(new intType()))
            throw new ExpExcept("Second operand is not of type int");
        return new boolType();

    }

    @Override
    public String toString(){
        return " (" + this.expr1.toString() + this.op+this.expr2.toString() + ") ";
    }
}
