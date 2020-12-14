package Model.ADT;

import Model.Value.Value;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Heap implements HeapInterface {

    private Map<Integer,Value> heap;
    private Integer freeAddr;

    public Heap(){

        heap= Collections.synchronizedMap(new HashMap<>());
        freeAddr = 1;
    }

    @Override
    public Value getValue(int addr) {
        return heap.get(addr);
    }

    @Override
    public void setHeap(int addr, Value val) {
        heap.put(addr,val);
    }

    @Override
    public int add(Value val) {
        int addr=freeAddr;
        setHeap(freeAddr,val);
        freeAddr++;
        return addr;
    }

    @Override
    public boolean exists(int addr) {
        return heap.containsKey(addr);
    }

    @Override
    public Map<Integer, Value> getHeap() {
        return heap;
    }


    @Override
    public void setHeap(HashMap<Integer, Value> newHeap) {
        heap=newHeap;
    }

    @Override
    public String toString(){
        String result="[ ";
        int position=0;
        for(Integer address : heap.keySet())
        {
            if (position==0) result +=""+address+"← "+heap.get(address);
            else result+=" , "+address+"←  "+heap.get(address);
            position++;
        }
        result+=" ]";
        return result;
    }
}
