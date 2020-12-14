package Model.ADT;

import java.util.HashMap;

public interface DictionaryInterface<Key,Value> {
    void add(Key key, Value value);
    void update(Key key, Value newValue);
    Value lookUp(Key key);
    boolean isDefined(Key key);
    public void set(Key key, Value val);
    public int size();
    public HashMap<Key,Value> getContent();
    public void setContent(HashMap<Key,Value> newHm);

    public DictionaryInterface<Key,Value> createCopy();
}
