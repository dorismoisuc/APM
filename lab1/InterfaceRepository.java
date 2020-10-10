package Repository;
import Exceptions.Excep;
import Model.Aquarium;

public interface InterfaceRepository {
    Aquarium[] getAll() throws Excep;


    void addAnimal(Aquarium animal) throws Excep;

    void deleteAnimal(String name) throws Excep;
    int getSize();
    int getByName(String name);
    boolean checkExistence(String name);
}
