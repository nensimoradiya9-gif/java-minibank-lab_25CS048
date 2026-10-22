
public class BoundedStats {

    // Generic method restricted to Number types
    public static <N extends Number> double average(N[] values) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be empty or null."
            );
        }

        double sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum += values[i].doubleValue();
        }

        return sum / values.length;
    }

    public static void main(String[] args) {

        Integer[] integers = {10, 20, 30, 40};
        Double[] doubles = {2.5, 3.5, 4.5};
        Long[] longs = {100L, 200L, 300L};

        System.out.println(
                "Integer average: " + average(integers)
        );

        System.out.println(
                "Double average: " + average(doubles)
        );

        System.out.println(
                "Long average: " + average(longs)
        );
    }
}
