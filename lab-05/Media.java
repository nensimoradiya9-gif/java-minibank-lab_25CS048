abstract class MediaDemo
{
    abstract public int lateFee();
}

class Book extends MediaDemo
{
    String title;
    int days;

    Book(String title, int days)
    {
        this.title = title;
        this.days = days;
    }

    public int lateFee()
    {
        if(days <= 5)
        {
            System.out.println(title + " : No extra charges!!");
            return 0;
        }
        else
        {
            int extraDays = days - 5;
            return extraDays * 10;
        }
    }
}

class Cd extends MediaDemo
{
    String title;
    int days;
    int grace;

    Cd(String title, int days, int grace)
    {
        this.grace = grace;
        this.title = title;
        this.days = days;
    }

    public int lateFee()
    {
        if(days <= grace)
        {
            System.out.println(title + " : No extra charges!!");
            return 0;
        }
        else
        {
            int extraDays = days - grace;
            return extraDays * 30;
        }
    }
}

public class Media
{
    public static void main(String[] a)
    {
        MediaDemo[] m = new MediaDemo[4];

        m[0] = new Book("Java Book", 7);
        m[1] = new Book("C Book", 3);
        m[2] = new Cd("Music CD", 6, 3);
        m[3] = new Cd("Movie CD", 2, 3);

        int total = 0;

        for(int i = 0; i < m.length; i++)
        {
            int fee = m[i].lateFee();

            System.out.println("Late Fee: " + fee);

            total = total + fee;
        }

        System.out.println("Total Late Fee: " + total);
    }
}