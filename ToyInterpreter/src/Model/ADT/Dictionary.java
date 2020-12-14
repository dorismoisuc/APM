package Model.ADT;

import java.util.HashMap;

public class Dictionary<Key,Value> implements DictionaryInterface<Key,Value> {

    private final HashMap<Key,Value> elems;

    public Dictionary(HashMap<Key, Value> elems) {
        this.elems = new HashMap<>();
    }

    public Dictionary(){
        this.elems=new HashMap<>();
    }
    @Override
    public void add(Key key, Value value) {
        elems.put(key,value);
    }

    @Override
    public void update(Key key, Value newValue) {
        elems.replace(key,newValue);
    }

    @Override
    public Value lookUp(Key key) {
        return elems.get(key);
    }

    @Override
    public boolean isDefined(Key key) {
        return elems.containsKey(key);
    }

    @Override
    public String toString(){
        String result=" [";
        int position=0;
        for (Key key:elems.keySet()){
            if (position==0) result +=""+key+" → "+elems.get(key);
            else result+=" , "+key+" → "+elems.get(key);
            position++;
        }
        result+=" ]";
        return result;

    }

    @Override
    public int size(){
        return elems.size();
    }

    @Override
    public void set(Key key, Value val)
    {
        elems.put(key,val);
    }

    @Override
    public HashMap<Key,Value> getContent(){
        return elems;
    }

    @Override
    public void setContent(HashMap<Key,Value> content)
    {
        elems.clear();
        elems.putAll(content);
    }

    @Override
    public DictionaryInterface<Key, Value> createCopy() {
        Dictionary<Key,Value> newDict=new Dictionary<>();
        for (var key: elems.keySet())
        {
            newDict.elems.put(key,elems.get(key));
        }
        return newDict;
    }

}
