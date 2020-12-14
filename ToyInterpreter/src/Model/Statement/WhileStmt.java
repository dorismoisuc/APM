package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.HeapExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expression.Expression;
import Model.Expression.RelationalExp;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Value.Value;
import Model.Value.boolValue;

import java.io.FileNotFoundException;
import java.util.Stack;

public class WhileStmt implements Stmt{

    private final Expression expr;
    private final Stmt whileStmt;

    public WhileStmt(Expression expr, Stmt whileStmt) {
        this.expr = expr;
        this.whileStmt = whileStmt;
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept, FileNotFoundException, HeapExcept {
        StackInterface<Stmt> exeStack = programState.getExeStack();

        Value cond=null;
        try {
            cond = expr.evaluate(programState.getSymbolTable(), programState.getHeap());
        }
        catch (ExpExcept e)
        {
            e.printStackTrace();
        }

        if (!cond.getType().equals(new boolType()))
            throw new StmtExcept("Bool type required\n");

        boolean flag = ((boolValue)cond).getValue();

        if (flag)
        {
            exeStack.push(this);
            exeStack.push(whileStmt);
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        whileStmt.typeCheck(typeEnv);
        Type typeExp=expr.typeCheck(typeEnv);
        System.out.println(expr instanceof RelationalExp);
        //System.out.println(typeExp.toString());
        if(!(typeExp.equals(new boolType())))
        {
            throw new StmtExcept("Expression should be a bool");
        }
        return typeEnv;
    }

    @Override
    public String toString(){
        return " WHILE ( "+expr+" )"  + " {"+ whileStmt  +"}";
    }
}
