import java.util.*;
public class Driver {
    public static void main(String[] args) {
        System.out.println("Enter Password:");
        Scanner sc = new Scanner(System.in);
        String pw=sc.next();
        System.out.println(PasswordChecker.getStrenth(pw));
    }
}
