package Model.ADT;

import CustomExceptions.ADTexcept;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyStack<T> implements StackInterface<T> {

    private final ArrayList<T> elems;

    public MyStack(ArrayList<T> elems) {
        this.elems = new ArrayList<>();
    }

    public MyStack() { elems=new ArrayList<>();}

    @Override
    public T pop() throws ADTexcept {
        if (elems.isEmpty())
            throw new ADTexcept("± EXE STACK IS EMPTY\n!");
        T topOfTheStack = elems.get(elems.size()-1);
        elems.remove(elems.size()-1);
        return topOfTheStack;
    }

    @Override
    public void push(T value) {
        elems.add(value);
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }

    @Override
    public List getValues() {
        return elems.subList(0,elems.size());
    }

    @Override
    public String toString(){
        String result="[ ";
        for (int position=elems.size()-1;position>=0;position--)
        {
            if (position==elems.size()-1) result+="" + elems.get(position);
            else result+="|"+elems.get(position);
        }
        result+=" ]";
        return result;
    }
}
