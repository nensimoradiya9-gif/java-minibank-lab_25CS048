public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked=0;
CinemaShow(String title,int capacity){
    this.title=title;
    this.capacity=capacity;
    seatsAvailable = capacity;
}
CinemaShow(String title){
    this(title, 100);
}
//book(int n): if n ≤ seatsAvailable, reduce seatsAvailable by n, add n to totalBooked, return true; else return false unchanged.
public boolean book(int n)
{
    if(n<=seatsAvailable){
        seatsAvailable-=n;
        totalBooked+=n;
    return true;
    }
    return false;
}

 //cancel(int n): increase seatsAvailable by n but never above capacity.
public void cancel(int n)
{
    if((seatsAvailable+n)>capacity){
        System.out.println("Cannot cancel");}
        else{
        seatsAvailable+=n;
        System.out.println(n+": Seats Cancled");
        System.out.println("Seats Available: "+getSeatsAvailable());
        }
        

}

public int getSeatsAvailable() {
     return seatsAvailable;
}

public static int getTotalBooked() {
     return totalBooked;
}

//(f) In main: run a sequence of book and cancel calls,
//  printing the result and seats after each, then print getTotalBooked().
    public static void main(String[] args) {
       
        CinemaShow c1=new CinemaShow("abc",100);
        c1.book(3);
        int a=c1.getSeatsAvailable();
        System.out.println("Seats Available after Book");
        System.err.println(a);
        c1.cancel(102);
         int b=c1.getSeatsAvailable();
         System.out.println("Seats Available after cancel");
        System.err.println(b);
        int c=CinemaShow.getTotalBooked();
        System.out.println("Total Booked Seats");
        System.err.println(c);
    }
}
