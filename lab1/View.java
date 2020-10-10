package View;
import Controller.Controller;
import Model.*;
import Exceptions.*;
import java.util.Scanner;

public class View {
    Controller controller;
    public View(Controller control)
    {
        controller=control;
    }

    public void menu()
    {
        System.out.println("--------AQUARIUM--------\n");
        System.out.println("You have the following options:\n" +
                "1. Add a new animal\n" +
                "2. Delete an animal\n" +
                "3. See all animals older than 1\n" +
                "4. See all animals\n"+
                "5. Populate the aquarium\n"+
                "0. Exit the app\n"
        );
    }

    public void populateAquarium(){
        Aquarium turtle1=new Turtle(1.2,"Donatello");
        Aquarium turtle2=new Turtle(0.8,"Michelangelo");
        Aquarium turtle3=new Turtle(0.7,"Leonardo");
        Aquarium turtle4=new Turtle(1.4,"Raphael");
        Aquarium fish1=new Fish(0.5,"Dory");
        Aquarium fish2=new Fish(0.2,"Nemo");
        Aquarium fish3=new Fish(0.7,"Marlin");
        try{
            controller.addAnimal(turtle1);
            controller.addAnimal(turtle2);
            controller.addAnimal(turtle3);
            controller.addAnimal(turtle4);
            controller.addAnimal(fish1);
            controller.addAnimal(fish2);
            controller.addAnimal(fish3);
        }catch(Excep ignored){}
    }

    public void add() throws Excep{
        Scanner input=new Scanner(System.in);
        System.out.println("Turtle/Fish: ");
        String or=input.next();
        System.out.println("Age: ");
        double age=input.nextDouble();
        System.out.println("Name: ");
        String name=input.next();
        Aquarium animals;
        switch(or)
        {
            case "turtle"->animals=new Turtle(age,name);
            case "fish"->animals=new Fish(age,name);
            default->{
                System.out.println("Not a turtle nor a fish, so not added\n");
                return;
            }
        }
        try{
            controller.addAnimal(animals);
        }
        catch(Excep except){
            throw new Excep(except.getMessage());
        }
    }

    public void remove() throws Excep{
        Scanner input=new Scanner(System.in);
        try{
            System.out.println("Name of the animal you want to remove: ");
            String name=input.next();
            controller.deleteAnimal(name);
        }
        catch(Excep except){
            throw new Excep(except.getMessage());
        }
    }

    public void listAll() throws Excep{
        try{
            printAnimals(controller.getAnimals());
        }
        catch(Excep except){
            throw new Excep(except.getMessage());
        }
    }
    public void printAnimals(Aquarium[] animals) throws Excep {

            for (Aquarium animal:animals)
                if(animal!=null)
                    System.out.println(animal.toString());

    }
    public void printOlderThan1() throws Excep{
        try{
            printAnimals(controller.getOlder());
        }
        catch(Excep except){
            throw new Excep(except.getMessage());
        }
    }
    public void start(){
        Scanner input=new Scanner(System.in);
        menu();
        System.out.println(">>>");
        String option=input.next();
        while(true)
        {
            try{
                switch(option){
                    case "0":
                        return;
                    case "1":
                        add();
                        break;
                    case "2":
                        remove();
                        break;
                    case"3":
                        printOlderThan1();
                        break;
                    case"4":
                        listAll();
                        break;
                    case"5":
                        populateAquarium();
                        break;
                    default:
                        System.out.println("Invalid option,retry\n");
                        break;
                }
            }
            catch(Excep except){
                System.out.println(except.getMessage());
            }
            System.out.println(">>>");
            option=input.next();
        }
    }

}
