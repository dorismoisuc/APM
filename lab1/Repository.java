package Repository;

import Exceptions.Excep;
import Model.Aquarium;

public class Repository implements InterfaceRepository {

    int repoSize;
    int repoCap;

    Aquarium[] animals=new Aquarium[20];
    public Repository(int capacity)
    {
        repoCap=capacity;
    }
    @Override
    public Aquarium[] getAll() throws Excep {
        if(repoSize==0)
            throw new Excep("The aquarium doesn't have any fish nor turtles\n");
        return animals;
    }



    @Override
    public void addAnimal(Aquarium animal) throws Excep {
        if (repoSize==repoCap)
            throw new Excep("Cannot add more animals, there are too many\n");
        int validity=getByName(animal.getName());
        if (validity>=0)
            throw new Excep("An animal with this name already exists\n");
        animals[repoSize]=animal;
        repoSize++;
    }

    @Override
    public void deleteAnimal(String name) throws Excep {
       int valid=getByName(name);
       if (valid==-1)
                throw new Excep("An animal with the name given doesn't exist\n");

       else
       {
           System.arraycopy(animals, valid + 1, animals, valid, animals.length - 1 - valid);
       }
    }

    @Override
    public int getSize() {
        return repoSize;
    }

    public int getByName(String name){
        for (int index=0;index<repoSize;index++)
        {
            if (animals[index].getName().equals(name))
                return index;
        }
        return -1;
    }

    @Override
    public boolean checkExistence(String name) {
        for (int index=0;index<repoSize;index++)
        {
            if (animals[index].getName().equals(name))
                return true;
        }
        return false;
    }
}
