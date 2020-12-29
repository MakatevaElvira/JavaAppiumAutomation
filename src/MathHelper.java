public class MathHelper {

    public int simpleInt = 7;
    public static int staticInt = 7;

    public void changeSimpleInt(){
        this.simpleInt = 8;
    }
    public static void changeStaticInt(){
        staticInt = 8;
    }

    public int calculator(int a,int b,char action){
        if (action =='+'){
            return this.plus(a,b);
        } else if (action == '-'){
            return this.minus(a,b);
        } else if (action == '*'){
            return this.multiply(a,b);
        } else if (action == '/'){
            return this.divide(a,b);
        } else{
            return this.typeAnErrorAndReturnDefaultValue("Wrong action: " +action+ " !");
        }

    }
    private int plus(int a,int b){
        return a + b;
    }
    private int minus(int a,int b){
        return a - b;
    }
    private int multiply(int a,int b){
        return a * b;
    }
    private int divide(int number,int divider){
        if (divider == 0){
           return this.typeAnErrorAndReturnDefaultValue("Cannot divide by zero!");
        }
        return number / divider;
    }
    public int typeAnErrorAndReturnDefaultValue(String errorMassage){
        System.out.println(errorMassage);
        return 0;
    }
}
