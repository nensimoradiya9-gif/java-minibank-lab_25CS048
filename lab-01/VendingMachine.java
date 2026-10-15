import java.util.Scanner;
class VendingMachine{
    enum Coin{ ONE, TWO, FIVE, TEN }
    public static void main(String []args)
    {
        Coin c=null;
        Scanner sc=new Scanner(System.in);
        // System.out.println(c);
        final int PRICE=15;
        int total=0;
        while(total<=PRICE){
            System.out.println("Enter coins:");
            String input=sc.next();
            try{
            c=Coin.valueOf(input.toUpperCase());
            }
            catch(Exception e){
                System.out.println("Invalid Coin..Try Again!!");
                continue;
            }
            int value=switch(c){
                case ONE->1;
                case TWO->2;
                case FIVE->5;
                case TEN->10;
            };
            // int value=0;
            // switch(c){
            //     case ONE:value=1; 
            //     break;
            //      case TWO:value=2;
            //     break;
            //      case FIVE:value=5;
            //     break;
            //      case TEN:value=10;
            //     break;
            // }
            total+=value;
            System.out.println("Paid:"+total);
        }
        System.out.println("Paid:"+total+" Change: "+(total-PRICE));
    }
}