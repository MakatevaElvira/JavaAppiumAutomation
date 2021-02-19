package tests;

import helpers.MathHelper;
import lib.CoreTestCase;
import org.junit.Test;

public class MainTest extends CoreTestCase {
    MathHelper mathHelper = new MathHelper();
    int a;

    @Test()
    public void myFirstTest(){
        System.out.println("Hello QA!!!");
    }
    @Test
    public void myMultiplyTest(){
        int a = multiply(5);
        System.out.println(a);
        int b = multiply(10,15);
        System.out.println(b);
    }
    @Test
    public void myFirstStaticTest(){
        System.out.println("First: Before changing StaticInt= "+ mathHelper.staticInt);
        mathHelper.staticInt =8;
        MathHelper math = new MathHelper();
        System.out.println("First: Before changing SimpleInt= "+ math.simpleInt);
        math.simpleInt = 8;
    }
    @Test
    public void mySecondStaticTest(){
        startTestMessage();
        System.out.println("Second: Before changing StaticInt= "+ mathHelper.staticInt);
        mathHelper.staticInt =8;
        MathHelper math = new MathHelper();
        System.out.println("Second: Before changing SimpleInt= "+ math.simpleInt);
        math.simpleInt = 8;
    }
    public int multiply(int number){
        return number * 2;
    }
    public int multiply(int number,int multiply){
        return number * multiply;
    }

    public void startTestMessage(){
        System.out.println("Start test!");
    }
}
