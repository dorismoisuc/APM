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

import java.io.*;

public class OpenRFile implements Stmt {

    private final Expression expr;

    public OpenRFile(Expression expr) {
        this.expr = expr;
    }


    @Override
    public PrgState execute(PrgState programState) throws StmtExcept, ExpExcept, FileExcept, FileNotFoundException {
        DictionaryInterface<String, Value> symbolTable = programState.getSymbolTable();
        HeapInterface heap = programState.getHeap();
        Value value = expr.evaluate(symbolTable,heap);
        if (!value.getType().equals(new stringType()))
            throw new FileExcept("Expected a string for the file path");
        stringValue fileName = (stringValue) value;
        FileTableInterface FileTable = programState.getFileTable();
        if (FileTable.isOpened(fileName))
            throw new FileExcept("File" + fileName +"already opened");
        try {
            BufferedReader fileDescr = new BufferedReader(new FileReader(fileName.getValue()));
            FileTable.add_file(fileName, fileDescr);
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
        return typeEnv;
    }


    @Override
    public String toString() {
        return " OPEN FILE(" + expr.toString() + ") ";
    }

}