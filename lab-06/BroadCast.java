interface urgent{}
interface Notifier
{
    void send(String msg);
}
class Sms implements Notifier,urgent
{
    public void send(String msg)
    {
        System.out.println("Sms "+msg);
    }
}
class Email implements Notifier
{
    public void send(String msg)
    {
        System.out.println("Email "+msg);
    }
}
class BroadCast
{
    public static void main(String[] args) 
    {
        Notifier[] notifiers = 
        {
            new Sms(),
            new Email()
        };
        for(Notifier n: notifiers)
        {
            n.send("server down");
            if(n instanceof urgent)
            {
                n.send("urgent server down");
            }
        }    
    }
}