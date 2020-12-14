package Model.Statement;

import CustomExceptions.ExpExcept;
import CustomExceptions.FileExcept;
import CustomExceptions.StmtExcept;
import Model.ADT.DictionaryInterface;
import Model.ADT.FileTableInterface;
import Model.ADT.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.stringType;
import Model.Value.Value;
import Model.Value.stringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CloseRFile implements Stmt{
    private final Expression expr;

    public CloseRFile(Expression expr) {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept {
        DictionaryInterface<String, Value> symbolTable = programState.getSymbolTable();
        HeapInterface heap = programState.getHeap();
        Value value = expr.evaluate(symbolTable,heap);
        if (!value.getType().equals(new stringType()))
            throw new FileExcept("Expected a string for the file path");
        stringValue fileName = (stringValue) value;
        FileTableInterface FileTable = programState.getFileTable();
        if (!FileTable.isOpened(fileName))
            throw new FileExcept("File is not opened");
        BufferedReader fileDescriptor = FileTable.getFileDescriptor(fileName);


        try {
            fileDescriptor.close();

        } catch (IOException ioerror) {
            throw new FileExcept("Unavailable file");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeEnv) throws Exception {
        Type typeExp = expr.typeCheck(typeEnv);
        if (!(typeExp.equals(new stringType())))
            throw new StmtExcept("Expression should be a string");
        else
            return typeEnv;
    }

    @Override
    public String toString() {
        return " CLOSE FILE(" + expr.toString() + ") ";
    }

}
