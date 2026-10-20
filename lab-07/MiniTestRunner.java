

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Define Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

// Methods for testing
class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Addition test executed");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Subtraction test executed");
    }

    public void normalMethod() {
        System.out.println("This method will not run");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Multiplication test executed");
    }
}

// Mini test runner
public class MiniTestRunner {

    public static void main(String[] args)
            throws Exception {

        MyTests obj = new MyTests();

        Method[] methods = obj.getClass().getDeclaredMethods();

        int count = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                method.invoke(obj);
                count++;
            }
        }

        System.out.println("Total tests executed: " + count);
    }
}

