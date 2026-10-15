abstract class Employee{
String name;
int id;
public Employee(String name,int id)
{
this.name=name;
this.id=id;
} 
abstract public int monthlySalary();
}
class FullTime extends Employee{
    private int fixed;
    public FullTime (String name,int id,int fixed){
        super(name, id);
        this.fixed=fixed;
    }
    public int monthlySalary(){
        return fixed;
    }
}
class PartTime extends Employee{
    private int hours;
    private int rate;
    public PartTime(String name,int id,int hours,int rate)
    {
        super(name, id);
        this.hours=hours;
        this.rate=rate;
    }
    public int monthlySalary(){
        return hours*rate;
    }
}
class Intern extends Employee{
    private int stipend;
    public Intern(String name,int id,int stipend){
        super(name, id);
        this.stipend=stipend;
    }
     public int monthlySalary(){
        return stipend;
    }
}
public class EmployeeDemo {
    public static void main(String[] args) {
        Employee[] employees ={
            new FullTime("Dhruvi", 1, 50000),
            new PartTime("Kalp",2,10,1000),
            new Intern("Shlok", 3, 5000)
        };
        int total=0;
        for (Employee e : employees) {
            // System.out.println(e.monthlySalary());
            if(e instanceof Intern){
                System.out.println("Interns don't have salary,they have stipend.");
                continue;
            }
                System.out.println(e.monthlySalary());
                total+=e.monthlySalary();
        }
        System.out.println("The total salary : "+total);
    }

}
