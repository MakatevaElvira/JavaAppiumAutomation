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
}
