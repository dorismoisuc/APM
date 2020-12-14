package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.FileTable;
import Model.ADT.FileTableInterface;
import Model.ADT.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.intType;
import Model.Type.stringType;
import Model.Value.Value;
import Model.Value.intValue;
import Model.Value.stringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements Stmt{
    private final Expression expr;
    private final String var;

    public ReadFile(Expression expr, String var) {
        this.expr = expr;
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        DictionaryInterface<String, Value> symbolTable=programState.getSymbolTable();
        HeapInterface heap = programState.getHeap();
        if (!symbolTable.isDefined(var))
        {
            throw new StmtExcept("Var"+var+"is not defined");
        }
        if (!symbolTable.lookUp(var).getType().equals(new intType()))
        { throw new StmtExcept("Var" + var + "should be int");}
        Value value=expr.evaluate(symbolTable,heap);
        if (!value.getType().equals(new stringType()))
            throw new FileExcept("Expected a string");
        FileTableInterface FileTable=programState.getFileTable();
        stringValue file_name=(stringValue)value;
        if (!FileTable.isOpened(file_name))
            throw new FileExcept("The file is not opened");
        BufferedReader fileDescriptor=FileTable.getFileDescriptor(file_name);

        int reader;
        try{
            String line=fileDescriptor.readLine();
            if (line==null)
                reader=0;
            else
                reader=Integer.parseInt(line);
        }
        catch (IOException ioerror)
        {
            throw new FileExcept("File is unavailable for reading");
        }
        symbolTable.update(var,new intValue(reader));
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type typeVar = (Type) typeEnv.lookUp(var);
        if (!(typeVar instanceof intType))
            throw new StmtExcept("Var type should be an int");
        Type typeExp = expr.typeCheck(typeEnv);
        if (!(typeExp instanceof  stringType))
            throw new StmtExcept("Expression should be a string");
        return typeEnv;
    }

    @Override
    public String toString() {
        return " READ FILE(" + var.toString() + ") ";
    }

}
