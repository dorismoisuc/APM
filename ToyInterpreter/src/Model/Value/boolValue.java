package Model.Value;

import Model.Type.Type;
import Model.Type.boolType;

import java.util.Objects;

public class boolValue implements Value{

    private final boolean value;

    public boolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue(){return this.value;}

    @Override
    public Type getType() {
        return new boolType();
    }

    @Override
    public String toString(){return String.valueOf(value);}

    @Override
    public boolean equals(Object other){
        if (other instanceof  boolValue)
            return Objects.equals(((boolValue)other).getValue(),value);
        return false;
    }

}
