import java.util.*;
record Vehicle(String number, String type){}
class TollBooth2
{
   public static void main(String []a)
   {	
	int totalToll = 0;
	int bike = 0;
	int car = 0;
	int truck = 0;
    int i=0;
	Vehicle []v = new Vehicle[10];
	Scanner sc= new Scanner(System.in);
	while(true)
	{	
		System.out.println("Please Enter Valid Vehicle Type & Number:(or Done to Complete):");
		
		String type = sc.next();
	 	if(type.equalsIgnoreCase("done")) break;
	   
	  String num = sc.next();
	 v[i]=new Vehicle(num,type);
	  // Vehicle v = new Vehicle(num,type);
	   int toll=0;
	   switch(v[i].type())
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
	   System.out.println(v[i].type() + " passed with toll " + toll);
	   totalToll = totalToll + toll; 
	   i++;
	}
	 System.out.println("total bikes passed = " + bike);
	 System.out.println("total cars passed = " + car);
	 System.out.println("total trucks passed = " + truck);
	 System.out.println("total collected toll = " + totalToll);
	 for(Vehicle x:v)
	 {
		System.out.println(x);
	 }
   }
}