package lib;

public class MainClass {
    private int classNumber = 20;
    private String classString = "Hello, world";

    public int getLocalNumber(){
        return 14;
    }
    public int getLocalNumber(int number){
        if (number != 14){
            System.out.println("Wrong number: "+number);
            return number;
        }
        return number;
    }
    public int getClassNumber(){
        return classNumber;
    }
    public String getClassString(){
        return classString;
    }
}
