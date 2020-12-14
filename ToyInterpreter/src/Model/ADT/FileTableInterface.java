package Model.ADT;

import Model.Value.stringValue;

import java.io.BufferedReader;

public interface FileTableInterface {
    void add_file(stringValue fileName, BufferedReader fileDescriptor);

    void remove_file(stringValue fileName);

    boolean isOpened(stringValue fileName);

    BufferedReader getFileDescriptor(stringValue filename);

    String toString();


}
