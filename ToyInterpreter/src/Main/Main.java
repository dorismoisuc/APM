package Main;
import View.View;
import JUnitTests.TestingExpressions;

public class Main {

    public static void main(String[]arg)
    {

        TestingExpressions tests=new TestingExpressions();
        tests.testAll();
        View UI=new View();
        UI.run();
    }



}
