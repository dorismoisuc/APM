package Model;

public class Turtle implements Aquarium {
    String name;
    double age;
    public Turtle(double turtleAge,String turtleName){
        this.age=turtleAge;
        this.name = turtleName;
    }
    public String getName(){
        return name;
    }

    public double getAge(){
        return age;
    }
    public String toString(){
        return "Aquarium: Turtle, age: " +age +" Turtle name: "+name;
    }

}
