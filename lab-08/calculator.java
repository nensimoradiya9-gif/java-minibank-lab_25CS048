import java.util.*;


public class calculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int attempt=0;
        boolean success=false;
        double num1=0;
        double num2=0;
        String op="";
        double result=0;
    
        while(!success){
            attempt++;

        try{
            num1=Double.parseDouble(sc.nextLine());
             op=sc.nextLine().trim();
             num2=Double.parseDouble(sc.nextLine());

             if(num2==0 && op.equals("/")){
                throw new DivideByZero("DivideByZeroException");
             }

             switch (op) {
                case "+":
                    result=num1+num2;
                    break;
                    case "-":
                    result=num1-num2;
                    break;
                    case "*":
                    result=num1*num2;
                    break;
                    case "/":
                    result=num1/num2;
                    break;
                    
                default: System.out.println("Invalid Operation");
                    continue;
             }
             success=true;
            }
        catch(NumberFormatException e){
            System.out.println(e);
        }
        catch(DivideByZero d){
            System.out.println(d);
        }
        finally{
            System.out.println("The Result is " +result);
            System.out.println("Total Number of Attempts " +attempt);
        }
    }
}
}
class DivideByZero extends Exception{
    DivideByZero(String msg){
    super(msg);
    }
}