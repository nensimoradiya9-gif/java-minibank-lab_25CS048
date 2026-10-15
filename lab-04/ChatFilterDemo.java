import java.util.*;
class ChatFilter{
    public static String[] splitLine(String line){
            String[] parts=line.split(" ", 3);
            if(parts.length<=2)
                return null;
            return parts;
    }
    public static boolean hasKeyword(String message,String keyword){
        return message.toLowerCase().contains(keyword.toLowerCase());
    }
}
public class ChatFilterDemo {
    public static void main(String[] args) {
        String[] a={
            "3:35 Dhruvi Hello everyone",
            "3:36 Diya Hello Guys",
            "3:37 Mungra Meow Meow",
            "3:38 Niti"
        };
        System.out.println("Enter Keyword:");
        Scanner sc= new Scanner(System.in);
        String key=sc.nextLine();
        int match=0;
        StringBuilder report= new StringBuilder();
       for (String line : a) {
        // System.out.println(line);
        String[] parts=ChatFilter.splitLine(line);
        if(parts==null)
            continue;
        if(ChatFilter.hasKeyword(parts[2], key))
            {
                match++;
                report.append(parts[0]).append(" ").append(parts[1]).append(" : ").append(parts[2]).append("\n");
                    }       
        //  System.out.println(parts[2]);
        
       }
        System.out.println("Match Found "+match+" "+report);
    }
}
