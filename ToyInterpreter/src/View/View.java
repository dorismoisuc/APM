package View;
import Model.ADT.*;
import Model.Expression.ArithExp;
import Model.Expression.ValExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.stringType;
import Model.Value.Value;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.stringValue;
import Repository.InterfaceRepository;
import Repository.Repository;
import Controller.Controller;

import java.util.Scanner;

public class View {

    public void run(){
        menu();
        Scanner input=new Scanner(System.in);
        while (true){
            int choice=input.nextInt();
            Stmt programState=new NOPStmt();
            switch(choice)
            {
                case 1:
                    programState=runExample1();
                    break;
                case 2:
                    programState=runExample2();
                    break;
                case 3:
                    programState=runExample3();
                    break;
                case 4:
                    programState=runException();
                    break;
                case 5:
                    programState=runSecondException();
                    break;
                case 6:
                    programState=runExample1();
                    break;
                case 7:
                    programState=runExample2();
                    break;
                case 8:
                    programState=runExample3();
                    break;
                case 9:
                    programState=fileExample();
                    break;
                case 0:
                    System.out.println("...................See ya later!:)\n");
                    return;
                default:
                    System.out.println("Invalid option, retry\n");
                    break;
            }
        if (choice<0 | choice>8) {
            System.out.println("Choose from 0->5\n");
            menu();}
       /* else if (choice>5 & choice <=8)
        {
            System.out.println("ONE STEP EXECUTION\n");
            System.out.println("Currently running example " +  "\n");
            System.out.println("| " + programState + " |");
            System.out.print("\n");
            runProgram2(programState);
            menu();
        }*/
        else if (choice>=1 & choice<=5 ){
            System.out.println("ALL STEPS EXECUTION\n");
            System.out.println("Currently running example " + choice + "\n");
            System.out.println("| " + programState + " |");
            System.out.print("\n");
            //runProgram(programState);
            //System.out.print("\n");
            //runProgram2(programState);
            menu();
        }
        }
    }

    public void menu()
    {
        System.out.println("\n-------------------------------MENU-----------------------------\n");
        System.out.println("1. All steps First example");
        System.out.println("2. All steps Second example");
        System.out.println("3. All steps Third example");
        System.out.println("4. Run an exception with division by 0");
        System.out.println("5. Run an exception with assignment different from declaration");
        System.out.println("6. One step First example");
        System.out.println("7. One step Second example");
        System.out.println("8. One step Third example");
        System.out.println("9. File execution example");
        System.out.println("0. Exit\n");
        System.out.println("\n----------------------------------------------------------------\n");
        System.out.println(">");
    }
/*
    public void runProgram(Stmt prg)
    {
        StackInterface<Stmt> exeStack = new MyStack<>();
        DictionaryInterface<String, Value> symbolTable=new Dictionary<>();
        ListInterface<Value> out=new MyList<>();
        FileTableInterface fileTable=new FileTable();
        HeapInterface heap = new Heap();
        PrgState programState=new PrgState(exeStack,symbolTable,out,fileTable,heap,prg);
        InterfaceRepository repo=new Repository(programState,"log.txt");
        Controller contr=new Controller(repo);

        try {
            contr.allSteps();
            contr.printOut();
        }catch(Exception except)
        {
            System.out.println(except.getMessage());
        }

    }
/*
    public void runProgram2(Stmt prg)
    {
        StackInterface<Stmt> exeStack = new MyStack<>();
        DictionaryInterface<String, Value> symbolTable=new Dictionary<>();
        ListInterface<Value> out=new MyList<>();
        PrgState programState=new PrgState(exeStack,symbolTable,out,prg);
        InterfaceRepository repo=new Repository(programState);
        Controller contr=new Controller(repo);

        try {
            contr.allSteps2();
            contr.printOut();
        }catch(Exception except)
        {
            System.out.println(except.getMessage());
        }

    }

*/
    public Stmt runExample1()
    {
        return new CompStmt(new VarDeclStmt("v",new intType()),
                new CompStmt(new AssignStmt("v",new ValExp(new intValue(2))),
                       new PrintStmt(new VarExp("v"))));
    }

    public Stmt runExample2(){

        return new CompStmt( new VarDeclStmt("a",new intType()),
                new CompStmt(new VarDeclStmt("b",new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValExp(new intValue(2)),
                                new ArithExp('*',new ValExp(new intValue(3)), new ValExp(new intValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                        new ValExp(new intValue(1)))), new PrintStmt(new VarExp("b"))))));
    }
    public Stmt runExample3(){
        return new CompStmt(new VarDeclStmt( "a",new boolType()), new CompStmt(new VarDeclStmt("v",new intType()),
                new CompStmt(new AssignStmt("a", new ValExp(new boolValue(true))),
                        (new CompStmt(new IfStmt(new VarExp("a"),
                                new AssignStmt("v",new ValExp(new intValue(2))),
                                new AssignStmt("v", new ValExp(new intValue(3)))),
                                new PrintStmt(new VarExp("v")))))));
    }

    public Stmt runException(){
        return new CompStmt( new VarDeclStmt("a",new intType()),
                new CompStmt(new VarDeclStmt("b",new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValExp(new intValue(2)),
                                new ArithExp('/',new ValExp(new intValue(5)), new ValExp(new intValue(0))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                        new ValExp(new intValue(1)))), new PrintStmt(new VarExp("b"))))));
    }

    public Stmt runSecondException(){
        return new CompStmt(new VarDeclStmt("a",new intType()),new AssignStmt("a",new ValExp(new boolValue(true))));
    }

    public Stmt fileExample(){
        return new CompStmt(new VarDeclStmt("varf",new stringType()),
                new CompStmt(new AssignStmt("varf",new ValExp(new stringValue("file.in"))),
                        new CompStmt(new VarDeclStmt("varc",new intType()),
                                new CompStmt(new OpenRFile(new VarExp("varf")),
                                        new CompStmt(new ReadFile(new VarExp("varf"),"varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"),"varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
    }
}
