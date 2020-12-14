package Main;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.refType;
import Model.Type.stringType;
import Model.Value.Value;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.stringValue;
import Repository.InterfaceRepository;
import Repository.Repository;
import View.ExitCmd;
import View.RunExampleCmd;
import View.TextMenuCls;

import javax.management.ValueExp;

public class Interpreter {
    public static void main(String[] args) {
        Stmt example1=runExample1();
        PrgState progState1=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example1);
        InterfaceRepository repo1=new Repository("file1.txt");
        repo1.addPrg(progState1);
        Controller ctr1=new Controller(repo1);

        Stmt example2=runExample2();
        PrgState progState2=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example2);
        InterfaceRepository repo2=new Repository("file2.txt");
        repo2.addPrg(progState2);
        Controller ctr2=new Controller(repo2);

        Stmt example3=runExample3();
        PrgState progState3=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example3);
        InterfaceRepository repo3=new Repository("file3.txt");
        repo3.addPrg(progState3);
        Controller ctr3=new Controller(repo3);

        Stmt example4=runExample4();
        PrgState progState4=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example4);
        InterfaceRepository repo4=new Repository("file4.txt");
        repo4.addPrg(progState4);
        Controller ctr4=new Controller(repo4);

        Stmt example5=runExample5();
        PrgState progState5=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example5);
        InterfaceRepository repo5=new Repository("file5.txt");
        repo5.addPrg(progState5);
        Controller ctr5=new Controller(repo5);

        Stmt example6=runExample6();
        PrgState progState6=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example6);
        InterfaceRepository repo6=new Repository("file6.txt");
        repo6.addPrg(progState6);
        Controller ctr6=new Controller(repo6);

        Stmt example7=runExample7();
        PrgState progState7=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example7);
        InterfaceRepository repo7=new Repository("file7.txt");
        repo7.addPrg(progState7);
        Controller ctr7=new Controller(repo7);

        Stmt example8=runFileExample();
        PrgState progState8=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example8);
        InterfaceRepository repo8=new Repository("file8.txt");
        repo8.addPrg(progState8);
        Controller ctr8=new Controller(repo8);

        Stmt example9=runException();
        PrgState progState9=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example9);
        InterfaceRepository repo9=new Repository("file9.txt");
        repo9.addPrg(progState9);
        Controller ctr9=new Controller(repo9);

        Stmt example10=exampleGarbage();
        PrgState progState10=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example10);
        InterfaceRepository repo10=new Repository("file10.txt");
        repo10.addPrg(progState10);
        Controller ctr10=new Controller(repo10);

        Stmt example11=forkExample();
        PrgState progState11=new PrgState(new MyStack<>(),new Dictionary<>(), new MyList<>(), new FileTable(), new Heap(),example11);
        InterfaceRepository repo11=new Repository("file11.txt");
        repo11.addPrg(progState11);
        Controller ctr11=new Controller(repo11);

        TextMenuCls menuCls = new TextMenuCls();
        menuCls.addCommand(new ExitCmd("exit","exit"));
        menuCls.addCommand(new RunExampleCmd("1",example1.toString(),ctr1));
        menuCls.addCommand(new RunExampleCmd("2",example2.toString(),ctr2));
        menuCls.addCommand(new RunExampleCmd("3",example3.toString(),ctr3));
        menuCls.addCommand(new RunExampleCmd("4",example4.toString(),ctr4));
        menuCls.addCommand(new RunExampleCmd("5",example5.toString(),ctr5));
        menuCls.addCommand(new RunExampleCmd("6",example6.toString(),ctr6));
        menuCls.addCommand(new RunExampleCmd("7",example7.toString(),ctr7));
        menuCls.addCommand(new RunExampleCmd("8",example8.toString(),ctr8));
        menuCls.addCommand(new RunExampleCmd("9",example9.toString(),ctr9));
        menuCls.addCommand(new RunExampleCmd("10",example10.toString(),ctr10));
        menuCls.addCommand(new RunExampleCmd("11",example11.toString(),ctr11));



        menuCls.show();
    }
    public static Stmt runExample1(){
        return new CompStmt(new VarDeclStmt("v",new intType()),
                new CompStmt(new AssignStmt("v", new ValExp(new intValue(2))), new PrintStmt(new
                        VarExp("v"))));
    }

    public static Stmt runExample2(){

        return new CompStmt( new VarDeclStmt("a",new intType()),
                new CompStmt(new VarDeclStmt("b",new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValExp(new intValue(2)),
                                new ArithExp('*',new ValExp(new intValue(3)), new ValExp(new intValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                        new ValExp(new intValue(1)))), new PrintStmt(new VarExp("b"))))));
    }
    public static Stmt runExample3(){
        return new CompStmt(new VarDeclStmt( "a",new boolType()), new CompStmt(new VarDeclStmt("v",new intType()),
                new CompStmt(new AssignStmt("a", new ValExp(new boolValue(true))),
                        (new CompStmt(new IfStmt(new VarExp("a"),
                                new AssignStmt("v",new ValExp(new intValue(2))),
                                new AssignStmt("v", new ValExp(new intValue(3)))),
                                new PrintStmt(new VarExp("v")))))));
    }

    public static Stmt runExample4(){
        return new CompStmt(new VarDeclStmt("x",new intType() ),
                new CompStmt(new AssignStmt("x", new ValExp(new intValue(2))), new PrintStmt(
                        new RelationalExp(new VarExp("x"),
                        new ValExp(new intValue(2)), "=="))));
    }

    public static Stmt runExample5(){
        return new CompStmt(new VarDeclStmt("x",new intType()),
                new CompStmt(new AssignStmt("x", new ValExp(new intValue(10))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("x"), new ValExp(new intValue(5)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("x")), new AssignStmt("x", new ArithExp('-',
                                        new VarExp("x"), new ValExp(new intValue(1)))))),
                                new PrintStmt(new VarExp("x")))));
    }

    public static Stmt exampleGarbage()
    {
        return new CompStmt(new VarDeclStmt( "v",new refType(new intType())),
                new CompStmt(new NewStmt("v",new ValExp(new intValue(20))),
                        new CompStmt(new VarDeclStmt( "a",new refType(new refType(new intType()))),
                                new CompStmt(new NewStmt("a",new VarExp("v")),
                                        new CompStmt(new NewStmt("v",new ValExp(new intValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
    }

    // write heap
    public static Stmt runExample6(){
        return new CompStmt(new VarDeclStmt("x",new refType(new intType())),
                new CompStmt(new NewStmt("x", new ValExp(new intValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("x"))),
                                new CompStmt(new WriteHeapStmt("x", new ValExp(new intValue(30))),
                                        new PrintStmt(new ArithExp('+',new ReadHeapExp(new VarExp("x")),new ValExp(new intValue(5))))))));
    }

    // read heap
    public static Stmt runExample7(){
       return new CompStmt(new VarDeclStmt("x",new refType(new intType())),
               new CompStmt(new NewStmt("x",new ValExp(new intValue(20))),
                       new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("x"))),
                       new CompStmt(new WriteHeapStmt("x", new ValExp(new intValue(20))),
                               new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("x")), new ValExp(new intValue(5))))))));
    }
    public static Stmt runException(){
        return new CompStmt( new VarDeclStmt("a",new intType()),
                new CompStmt(new VarDeclStmt("b",new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValExp(new intValue(2)),
                                new ArithExp('/',new ValExp(new intValue(5)), new ValExp(new intValue(0))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                        new ValExp(new intValue(1)))), new PrintStmt(new VarExp("b"))))));
    }

    public static Stmt runSecondException(){
        return new CompStmt(new VarDeclStmt("a",new intType()),new AssignStmt("a",new ValExp(new boolValue(true))));
    }

    public static Stmt runFileExample(){
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


    public static Stmt forkExample(){
        /* int v; Ref int a; v=10;new(a,22);
         fork(wH(a,30);v=32;print(v);print(rH(a)));
         print(v);print(rH(a))
        At the end:
        Id=1
        SymTable_1={v->10,a->(1,int)}
        Id=10
        SymTable_10={v->32,a->(1,int)}
        Heap={1->30}
        Out={10,30,32,30}
        */
        return new CompStmt(new VarDeclStmt("v",new intType()),
                new CompStmt(new VarDeclStmt("a",new refType(new intType())),
                        new CompStmt(new AssignStmt("v",new ValExp(new intValue(10))),
                                new CompStmt(new NewStmt("a",new ValExp(new intValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(
                                                new WriteHeapStmt("a",new ValExp(new intValue(30))),
                                                new CompStmt(new AssignStmt("v",new ValExp(new intValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
    }
}
