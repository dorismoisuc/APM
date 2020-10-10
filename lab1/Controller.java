package Controller;
import Exceptions.Excep;
import Model.Aquarium;
import Repository.*;



public class Controller {
    InterfaceRepository repository;
    public Controller(InterfaceRepository repo)
    {
        repository=repo;
    }

    public Aquarium[] getAnimals() throws Excep {
        try{
            return repository.getAll();
        }
        catch (Excep except){
            throw new Excep(except.getMessage());
        }
    }

    public void addAnimal(Aquarium animal) throws Excep{
        try{
            repository.addAnimal(animal);
        }
        catch (Excep except){
            throw new Excep(except.getMessage());
        }
    }

    public void deleteAnimal(String name) throws Excep{
        boolean checkExistence=repository.checkExistence(name);
        if(!checkExistence)
            throw new Excep("No animal with this name\n");
        repository.deleteAnimal(name);
    }

    public Aquarium[] filterByAge(float age) throws Excep{
        int counter = 0;
        Aquarium[] olderThan1=new Aquarium[repository.getSize()];
        Aquarium[] allAnimals;
        try
        {
            allAnimals=repository.getAll();
        }
        catch(Excep except)
        {
            throw new Excep(except.getMessage());
        }
        for(Aquarium animal:allAnimals)
        {
            if(animal!=null&&animal.getAge()>1)
            {
                olderThan1[counter]=animal;
                counter++;
            }
        }
        return olderThan1;
    }

    public Aquarium[] getOlder() throws Excep{
        try{
            return filterByAge(1);
        }
        catch (Excep except){
            throw new Excep(except.getMessage());
        }
    }
}
