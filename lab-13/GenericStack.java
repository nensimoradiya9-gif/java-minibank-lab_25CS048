
public class GenericStack<T> {

    private Object[] items;
    private int top;

    public GenericStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than zero."
            );
        }

        items = new Object[capacity];
        top = -1;
    }

    // Add an element
    public void push(T value) {
        if (top == items.length - 1) {
            System.out.println("Stack is full!");
            return;
        }

        top++;
        items[top] = value;
    }

    // Remove and return the top element
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }

        T value = (T) items[top];
        items[top] = null;
        top--;

        return value;
    }

    // View the top element
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }

        return (T) items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {

        // Stack of Strings
        GenericStack<String> names = new GenericStack<>(3);

        names.push("Riya");
        names.push("Amit");

        System.out.println("Name stack top: " + names.peek());
        System.out.println("Removed name: " + names.pop());

        // Stack of Integers
        GenericStack<Integer> numbers = new GenericStack<>(3);

        numbers.push(10);
        numbers.push(20);

        System.out.println("Number stack top: " + numbers.peek());
        System.out.println("Removed number: " + numbers.pop());

        // Test empty case
        System.out.println("Removed again: " + numbers.pop());
        System.out.println("Is stack empty? " + numbers.isEmpty());
    }
}
