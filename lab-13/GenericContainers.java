
public class GenericContainers {

    // Generic Box class
    static class Box<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

    // Generic Pair class
    static class Pair<A, B> {
        private A first;
        private B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

    // Generic method returning first array element
    public static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be empty or null."
            );
        }

        return array[0];
    }

    public static void main(String[] args) {

        // Box with String
        Box<String> nameBox = new Box<>();
        nameBox.set("Riya");

        System.out.println("Name Box: " + nameBox.get());

        // Box with Integer
        Box<Integer> numberBox = new Box<>();
        numberBox.set(100);

        System.out.println("Number Box: " + numberBox.get());

        // Pair with different types
        Pair<String, Integer> student =
                new Pair<>("Amit", 21);

        System.out.println("\nPair Example:");
        System.out.println("Name: " + student.getFirst());
        System.out.println("Age: " + student.getSecond());

        // Generic method with String array
        String[] names = {"Riya", "Amit", "Neha"};

        System.out.println(
                "\nFirst name: " + firstElement(names)
        );

        // Generic method with Integer array
        Integer[] numbers = {10, 20, 30};

        System.out.println(
                "First number: " + firstElement(numbers)
        );
    }
}
