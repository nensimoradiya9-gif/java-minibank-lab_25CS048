

import java.lang.annotation.*;
import java.lang.reflect.Field;

// Define Column annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

// Class containing column fields
class Student {

    @Column(name = "student_name")
    String name;

    @Column(name = "student_email")
    String email;

    @Column(name = "student_age")
    int age;

    public String toString() {
        return "Name: " + name
                + ", Email: " + email
                + ", Age: " + age;
    }
}

// Column mapper
public class ColumnMapper {

    public static void populate(
            Object obj, String[] headers, String[] data)
            throws Exception {

        if (headers.length != data.length) {
            throw new IllegalArgumentException(
                    "Header and data lengths do not match");
        }

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();

                int index = -1;

                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equals(columnName)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println(
                            "Missing column: " + columnName);
                    continue;
                }

                field.setAccessible(true);

                if (field.getType() == String.class) {
                    field.set(obj, data[index]);
                } else if (field.getType() == int.class) {
                    field.setInt(obj, Integer.parseInt(data[index]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String[] headers = {
                "student_email",
                "student_name",
                "student_age"
        };

        String[] data = {
                "riya@gmail.com",
                "Riya",
                "20"
        };

        Student student = new Student();

        populate(student, headers, data);

        System.out.println(student);
    }
}
