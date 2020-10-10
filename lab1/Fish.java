package Model;

public class Fish implements Aquarium {
    String name;
    double age;
    public Fish(double fishAge,String fishName){
        this.age=fishAge;
        this.name=fishName;
    }

    public String getName(){
        return name;
    }
    public double getAge(){
        return age;
    }
    public String toString(){
        return "Aquarium: Fish, age: " +age+" Fish name: "+name;
    }

}