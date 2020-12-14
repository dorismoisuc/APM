package Controller;
import CustomExceptions.ControllerExcept;
import Model.ADT.ListInterface;
import Model.ADT.StackInterface;
import Model.ProgramState.PrgState;
import Model.Statement.Stmt;
import Model.Value.Value;
import Repository.InterfaceRepository;
import Controller.GarbageCollector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {
    private final InterfaceRepository repository;
    private boolean stepsFlag;
    private ExecutorService executor;

    public InterfaceRepository getRepository() {return this.repository;}
    public Controller(InterfaceRepository repository) {
        this.repository = repository;
    }

    /*public PrgState oneStep(PrgState programState)throws Exception {
        StackInterface<Stmt> exeStack=programState.getExeStack();
        if (exeStack.isEmpty())
            throw new Exception("EXE STACK-> EMPTY!!±\n");
        Stmt currentStatement=exeStack.pop();
        return currentStatement.execute(programState);
    }*/

    public void setStepsFlag(boolean flagValue){stepsFlag=flagValue;}

    public void oneStepForAllPrg(List<PrgState> prgStateList) throws InterruptedException {
        prgStateList.forEach(prg-> {
            try {
                repository.logPrgStateExec(prg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        List<Callable<PrgState>> callList=prgStateList.stream(
                ).map((PrgState p)->(Callable<PrgState>)(p::oneStep)).collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future-> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgStateList.addAll(newPrgList);
        prgStateList.forEach(
                p->{
                        try {
                            repository.logPrgStateExec(p);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                }
        );
        repository.setPrgList(prgStateList);

    }
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void allSteps() throws InterruptedException {
        executor= Executors.newFixedThreadPool(2);

        // removing the completed programs
        List<PrgState> programStates=removeCompletedPrg(repository.getPrgList());
        while (programStates.size()>0)
        {
            GarbageCollector.conservativeGarbageCollector(programStates);
            oneStepForAllPrg(programStates);
            programStates=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        repository.setPrgList(programStates);
    }

  /*
    public void allSteps() throws Exception{
        PrgState program=repository.getCurrentProgram();
        repository.logPrgStateExec();
        if (stepsFlag)
            System.out.println("INITIAL STATE:");
        System.out.println(program);
        while (!program.getExeStack().isEmpty())
        {
            oneStep(program);

            program.getHeap().setHeap((HashMap<Integer,Value>) GarbageCollector.unsafeGarbageCollector(
                    GarbageCollector.getAllAddr(program.getSymbolTable().getContent().values(), program.getSymbolTable(),program.getHeap()),
                    program.getHeap().getHeap()));

            repository.logPrgStateExec();

            if (stepsFlag)
                System.out.println(program);
        }
    }

    public void allStepsOld() throws Exception{
        PrgState program=repository.getCurrentProgram();
        System.out.println("INITIAL STATE:");
        System.out.println(program);
        while (!program.getExeStack().isEmpty())
        {
            oneStep(program);
            System.out.println(program);
        }
    }

    public void allSteps2() throws Exception{
        PrgState program=repository.getCurrentProgram();
        System.out.println("INITIAL STATE:");
        System.out.println(program);
        while (!program.getExeStack().isEmpty())
        {
            oneStep(program);
            //System.out.println(program);
        }
    }

    public void printOut(){
        System.out.println("_______________________________OUT______________________________\n");
        PrgState program=repository.getCurrentProgram();
        ListInterface<Value>output=program.getOutput();

        Iterator<Value> iterator = output.iterator();
        while(iterator.hasNext()){
            Value value=iterator.next();
            System.out.println(value);
        }
        System.out.println("________________________________________________________________\n");
    }*/


}
