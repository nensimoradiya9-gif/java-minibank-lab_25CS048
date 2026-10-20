
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Annotation for non-empty fields
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

// Annotation for maximum length
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Signup form class
class SignupForm {
    @NotBlank
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(10)
    String mobile;

    SignupForm(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}

// Validator class
public class FormValidator {

    public static List<String> validate(Object obj)
            throws IllegalAccessException {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(obj);
            String text = (value == null) ? "" : value.toString();

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (text.trim().isEmpty()) {
                    errors.add(field.getName() + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength annotation =
                        field.getAnnotation(MaxLength.class);

                if (text.length() > annotation.value()) {
                    errors.add(field.getName()
                            + " must not exceed "
                            + annotation.value() + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args)
            throws IllegalAccessException {

        SignupForm form = new SignupForm(
                "",
                "verylongemailaddress123456789@example.com",
                "123456789012"
        );

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}

    

