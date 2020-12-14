package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.stringType;
import Model.Value.Value;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.stringValue;

import java.io.File;

public class VarDeclStmt implements Stmt{
    private final Type type;
    private final String id;

    public VarDeclStmt(String id, Type type) {
        this.type = type;
        this.id = id;
    }


    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        DictionaryInterface<String, Value> symbolTable=programState.getSymbolTable();
        if (symbolTable.isDefined(id))
            throw new StmtExcept("\n");
        /*if(type.equals(new intType()))
            symbolTable.add(id,new intValue(0));
        if(type.equals(new boolType()))
            symbolTable.add(id,new boolValue(false));
        if (type.equals(new stringType()))
            symbolTable.add(id,new stringValue(""));
        */

        symbolTable.add(this.id,this.type.defaultValue());
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        typeEnv.add(id, type);
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return id+"←"+type;
    }
}
