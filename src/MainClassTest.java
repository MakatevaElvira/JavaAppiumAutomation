import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass main = new MainClass();
    @Test
    public void testGetLocalNumber(){
        int number = 13;
        Assert.assertEquals(main.getLocalNumber(),14);
        Assert.assertEquals(main.getLocalNumber(number),14);
    }
    @Test
    public void testGetClassNumber(){
        if (main.getClassNumber()<=45){
            System.out.println("Bad classNumber: "+main.getClassNumber());
        }
        Assert.assertTrue(main.getClassNumber()>=45);
    }
    @Test
    public void testGetClassString(){
       String phrase = main.getClassString();
       if (phrase.contains("Hello")){
           return;
       } else if (phrase.contains("hello")){
           return;
       }
       System.out.println("Phrase not contain Hello");
       Assert.fail();
       return;
    }
}
