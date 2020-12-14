package Model.ADT;

import CustomExceptions.ADTexcept;

import java.awt.*;
import java.util.List;

public interface StackInterface<T> {
    T pop() throws ADTexcept;
    void push(T value);
    boolean isEmpty();

    List<T> getValues();
}
