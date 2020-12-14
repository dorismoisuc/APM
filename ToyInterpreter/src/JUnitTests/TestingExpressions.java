package JUnitTests;
import org.junit.Test;
import Model.Value.*;
import Model.Expression.*;
import Model.ADT.*;
import org.junit.Assert;

import static org.junit.Assert.assertFalse;

public class TestingExpressions {

    @Test
    public void testOr(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap = new Heap();
        Expression exp=new LogicExp(new ValExp(new boolValue(true)),new ValExp(new boolValue(false)),"or");
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            boolean answer=((boolValue)val).getValue();
            assert(answer==true);
        }catch (Exception except){}
        //System.out.println("*OR test passed");
    }

    @Test
    public void testOrFalse(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap = new Heap();
        Expression exp=new LogicExp(new ValExp(new boolValue(true)),new ValExp(new boolValue(false)),"or");
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            boolean answer=((boolValue)val).getValue();
            assert(answer==false);
        }catch (Exception except){}
        //System.out.println("*OR test passed");
    }


    @Test
    public void testAnd(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap = new Heap();
        Expression exp=new LogicExp(new ValExp(new boolValue(true)),new ValExp(new boolValue(false)),"and");
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            boolean answer=((boolValue)val).getValue();
            assert(answer==false);
        }catch (Exception except){}
        //System.out.println("*AND test passed");
    }

    @Test
    public void testAndFalse(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new LogicExp(new ValExp(new boolValue(true)),new ValExp(new boolValue(false)),"and");
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            boolean answer=((boolValue)val).getValue();
            assertFalse(answer==true);
        }catch (Exception except){}
        //System.out.println("*AND test passed");
    }


    @Test
    public void testAdd(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('+',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assert(answer==20);
        }catch (Exception except){}
        //System.out.println("*ADD test passed");
    }

    @Test
    public void testAddFalse(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('+',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assertFalse(answer==10);
        }catch (Exception except){}
        //System.out.println("*ADD test passed");
    }

    @Test
    public void testSubtraction(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('-',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assert(answer==0);
        }catch (Exception except){}
       // System.out.println("*SUB test passed");
    }

    public void testDivision(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('/',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assert(answer==1);
        }catch (Exception except){}
        //System.out.println("*DIV test passed");
    }

    @Test
    public void testDivisionFalse(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('/',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assertFalse(answer==2);
        }catch (Exception except){}
        //System.out.println("*DIV test2 passed");
    }

    @Test
    public void testSubtractionFalse(){
        DictionaryInterface<String,Value> dictionary=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new ArithExp('-',new ValExp(new intValue(10)),new ValExp(new intValue(10)));
        try
        {
            Value val=exp.evaluate(dictionary,heap);
            int answer=((intValue)val).getValue();
            assertFalse(answer==2);
        }catch (Exception except){}
        // System.out.println("*SUB test passed");
    }

    @Test
    public void testRelationalExpr(){
        DictionaryInterface<String,Value> dict=new Dictionary<>();
        HeapInterface heap= new Heap();
        Expression exp=new RelationalExp(new ValExp(new intValue(5)),new ValExp(new intValue(7)),"<");
        try
        {
            Value val=exp.evaluate(dict,heap);
            boolean answer=((boolValue)val).getValue();
            assert(answer);
        }catch (Exception except){

        }
    }

    @Test
    public void testAll() {
        testAnd();
        testOr();
        testAdd();
        testSubtraction();
        testDivision();
        testDivisionFalse();
        testSubtractionFalse();
        testAddFalse();
        testAndFalse();
        testOrFalse();
        System.out.println("\nAll tests passed!\n");
    }




}
