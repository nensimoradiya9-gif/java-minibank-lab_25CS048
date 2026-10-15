abstract class Shape {
    abstract double area();
}
class Circle extends Shape{
    private double radius;
    Circle(double r)
    {
        radius=r;
    }
    public double area()
    {
        return 3.14*radius*radius;
    }
}
class Rectangle extends Shape{
    private double length;
    private double width;
    Rectangle(double l,double w)
    {
        length=l;
        width=w;
    }
    public double area()
    {
        return length*width;
    }
}
class Triangle extends Shape{
    private double base;
    private double height;
    Triangle(double b,double h)
    {
        base=b;
        height=h;
    }
    public double area()
    {
        return 0.5*base*height;
    }
}
public class ShapeDemo{
    public static void main(String[] args) {
           Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(8, 3),
        };
        double total=0;
        double largest=-1;
        String label="";
        for (Shape s : shapes) {
            System.out.println(s.area());
            total+=s.area();
            if(largest<s.area()){
                largest=s.area();
                label=s.getClass().getSimpleName();
            }
        }
        System.out.println("The Total Area is : "+total);
        System.out.println("The largest Area is : "+largest +label);
    }
}