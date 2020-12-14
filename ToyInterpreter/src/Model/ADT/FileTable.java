package Model.ADT;

import Model.Value.stringValue;

import java.io.BufferedReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements FileTableInterface{

    private final Map<stringValue, BufferedReader> file;

   public FileTable(){file = Collections.synchronizedMap(new HashMap<>());}

    @Override
    public void add_file(stringValue fileName, BufferedReader fileDescriptor) {
        file.put(fileName,fileDescriptor);
    }

    @Override
    public void remove_file(stringValue fileName) {
        file.remove(fileName);
    }

    @Override
    public boolean isOpened(stringValue fileName) {
        return file.containsKey(fileName);
    }

    @Override
    public BufferedReader getFileDescriptor(stringValue filename) {
        return file.get(filename);
    }

    @Override
    public String toString(){
        String result="[ ";
        int position=0;
        for (stringValue key:file.keySet()){
            if (position==0) result +=""+key+" → "+file.get(key);
            else result+=" , "+key+" → "+file.get(key);
            position++;
        }
        result+=" ]";
        return result;
    }
}
