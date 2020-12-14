package Model.Value;

import Model.Type.Type;
import Model.Type.stringType;

import java.util.Objects;

public class stringValue implements Value{

    private final String value;

    public stringValue(String value) {
        this.value = value;
    }
    public String getValue(){return value;}

    @Override
    public Type getType() {
        return new stringType();
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof  stringValue)
            return Objects.equals(((stringValue)other).getValue(),value);
        return false;
    }
}

