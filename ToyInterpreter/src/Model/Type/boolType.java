package Model.Type;

import Model.Value.Value;
import Model.Value.boolValue;

public class boolType implements Type{
    @Override
    public boolean equals(Object other){
        return other instanceof boolType;
    }

    @Override
    public String toString(){return " bool_type";}

    @Override
    public Value defaultValue() {
        return new boolValue(false);
    }
}
