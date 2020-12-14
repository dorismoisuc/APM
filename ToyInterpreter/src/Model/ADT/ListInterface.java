package Model.ADT;
import java.util.Iterator;

public interface ListInterface<V> {
    void append(V newValue);
    Iterator<V> iterator();
}
