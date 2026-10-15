import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Point[] p = new Point[5];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < p.length; i++) {
            System.out.print("Enter x and y: ");
            p[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
        int count = 0;

for (int i = 0; i < p.length; i++) {
    boolean found = false;

    for (int j = 0; j < i; j++) {
        if (p[i].equals(p[j])) {
            found = true;
            break;
        }
    }

    if (!found) {
        count++;
    }
}

System.out.println("Distinct: " + count);
    }
}