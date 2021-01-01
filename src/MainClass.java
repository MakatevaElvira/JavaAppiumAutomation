public class MainClass {
    private int classNumber = 20;

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
}
