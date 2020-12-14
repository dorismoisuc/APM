package Model.ADT;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MyList<V> implements ListInterface<V> {

    private final List<V> elems;

    public MyList(ArrayList<V> elems) {
        this.elems = Collections.synchronizedList(new ArrayList<>());
    }

    public MyList(){
        this.elems = Collections.synchronizedList(new ArrayList<>());
    }
    @Override
    public void append(V newValue) {
        elems.add(newValue);
    }

    @Override
    public Iterator<V> iterator() {
        return elems.iterator();
    }

    @Override
    public String toString(){
        String result="[ ";
        int position=0;
        for(V value:elems)
        {
            if (position==0) result+="" +value;
            else result+=" , "+value;
            position++;
        }
        result+=" ]";
        return result;
    }
}
