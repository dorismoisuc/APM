package Main;
import Controller.Controller;
import Repository.Repository;
import View.View;

public class Main {
    public static void main(String[] args){
        Repository repository=new Repository(20);
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.start();
    }


}
