import java.util.*;
record Vehicle(String number, String type){}
class TollBooth
{
   public static void main(String []a)
   {	
	int totalToll = 0;
	int bike = 0;
	int car = 0;
	int truck = 0;
	
	Scanner sc= new Scanner(System.in);
	while(true)
	{	
		System.out.println("Please Enter Valid Vehicle No:(or Done to Complete):");
		String num = sc.next();
	 	if(num.equalsIgnoreCase("done")) break;
	   	String type = sc.next();
	  
	 
	   Vehicle v = new Vehicle(num,type);
	   int toll=0;
	   switch(v.type())
	   {
		case "bike" :toll=20;
			bike++;
			break;
		case "car" :toll=50;
			car++;
			break;
		case "truck" :toll=150;
			truck++;
			break;
		default: toll=0;
	   }
	   System.out.println(v.type() + " passed with toll " + toll);
	   totalToll = totalToll + toll; 
	}
	 System.out.println("total bikes passed = " + bike);
	 System.out.println("total cars passed = " + car);
	 System.out.println("total trucks passed = " + truck);
	 System.out.println("total collected toll = " + totalToll);
   }
} 