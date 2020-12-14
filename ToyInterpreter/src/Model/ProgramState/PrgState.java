package Model.ProgramState;
import Model.ADT.*;
import Model.Statement.Stmt;
import Model.Value.Value;
import Model.Value.stringValue;

import java.io.BufferedReader;


public class PrgState {

    private StackInterface<Stmt> exeStack;
    private DictionaryInterface<String,Value> symbolTable;
    private ListInterface<Value> out;
    private FileTableInterface fileTable;
    private HeapInterface heap;
    private static int previousID=1;
    private int id;

    public synchronized  void setId(){
        previousID++;
        id=previousID;
    }

    public PrgState(StackInterface<Stmt> exeStack, DictionaryInterface<String,Value> symbolTable, ListInterface<Value> out, FileTableInterface fileTab,HeapInterface heap)
    {
        this.exeStack=exeStack;
        this.symbolTable=symbolTable;
        this.out=out;
        this.fileTable=fileTab;
        this.heap=heap;
    }

    public PrgState(StackInterface<Stmt> exeStack, DictionaryInterface<String,Value> symbolTable, ListInterface<Value> out, FileTableInterface fileTab,HeapInterface heap, Stmt programState)
    {
        this.exeStack=exeStack;
        this.symbolTable=symbolTable;
        this.out=out;
        this.fileTable=fileTab;
        this.id=1;
        this.heap=heap;
        if (programState!=null)
            exeStack.push(programState);
    }

    public HeapInterface getHeap(){return heap;}

    public StackInterface<Stmt> getExeStack(){return exeStack;}

    public DictionaryInterface<String,Value> getSymbolTable(){return symbolTable;}

    public ListInterface<Value> getOutput(){return out;}

    public FileTableInterface getFileTable(){return fileTable;}

    public void setExeStack(StackInterface<Stmt> newExeStack){exeStack=newExeStack;}

    public void setSymbolTable(DictionaryInterface<String,Value> newSymbolTable){symbolTable=newSymbolTable;}

    public void setOutput(ListInterface<Value> newOutput){out=newOutput;}

    public void setFileTable(FileTableInterface newFileTable){fileTable=newFileTable;}

    public void setHeap(HeapInterface newHeap){heap=newHeap;}

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep()throws Exception {
        if (exeStack.isEmpty())
            throw new Exception("EXE STACK-> EMPTY!!±\n");
        Stmt currentStatement=exeStack.pop();
        return currentStatement.execute(this);
    }


    @Override
    public String toString(){
        String display="";
        display+="\n";
        display+="PROGRAM ID= " + id +"\n";
        display+="EXE STACK= " +exeStack + "\n";
        display+="SYM TABLE= " +symbolTable + "\n";
        display+="OUT= " + out+"\n";
        display+="FILE TABLE= " +fileTable+"\n";
        display+="HEAP= " + heap + "\n";
        display+="\n________________________________________________________________\n";
        return display;
    }

}
