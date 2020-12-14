package Model.Type;

import Model.Value.Value;
import Model.Value.stringValue;

public class stringType implements Type{
    @Override
    public Value defaultValue() {
        return new stringValue("");
    }

    @Override
    public String toString(){
        return " string_type";
    }

    @Override
    public boolean equals(Object other){
        return other instanceof stringType;
    }
}
