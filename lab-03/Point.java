import java.util.*;
public class Point {
    private int x;
    private int y;
    Point(){
        x=0;
        y=0;
    }
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "("+ x +" ,"+ y + ")" ;
    } 
    public boolean equals(Object obj){
       Point p=(Point) obj;
       if(x==p.x && y==p.y){
        return true;
       }
       else{
        return false;
       }
    }
}