public class PasswordChecker {
 
    public static boolean hasLength(String pw){
            return pw.length()>=8;
    }

    public static boolean hasUpperCase(String pw){
        return pw.matches(".*[A-Z].*");
    }

    public static boolean hasDigit(String pw){
        return pw.matches(".*[0-9].*");
    }

    public static boolean hasSpecialChar(String pw){
        return pw.matches(".*[@$#&].*");
    }

    public static String getStrenth(String pw){
        int count=0;
        if(hasLength(pw))
            count++;
        if(hasDigit(pw))
            count++;
        if(hasSpecialChar(pw))
            count++;
        if(hasUpperCase(pw))
            count++;

        String st=switch(count)
        {
                case 0,1->"WEAK";
                case 2,3->"MEDIUM";
                case 4->"STRONG";
                default->"INVALID";
        };
        return st;
        }
    }
