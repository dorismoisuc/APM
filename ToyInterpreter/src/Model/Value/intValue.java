package Model.Value;

import Model.Type.Type;
import Model.Type.intType;

import java.util.Objects;

public class intValue implements Value {

    private final int value;

    public intValue(int value) {
        this.value = value;
    }

    public int getValue() { return value;}

    @Override
    public Type getType() {
        return new intType();
    }

    @Override
    public String toString() { return String.valueOf(value);}

    @Override
    public boolean equals(Object other){
        if (other instanceof  intValue)
            return Objects.equals(((intValue)other).getValue(),value);
        return false;
    }
}
