package Model.Type;

import Model.Value.Value;
import Model.Value.intValue;

public class intType implements Type{

    @Override
    public boolean equals(Object other){
        return other instanceof intType;
    }

    @Override
    public String toString(){return " int_type";}

    @Override
    public Value defaultValue() {
        return new intValue(0);
    }
}
