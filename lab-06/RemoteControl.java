interface Switchable
{
    void on();
    void off();
    boolean isOn();
    default void toggle()
    {
        if(isOn())
            off();
        else
            on();
    }
}
interface SwitchOnRule
{
    boolean maySwitchOn(Switchable device,int hour);
}
// class DayTimeRule implements SwitchOnRule
// {
//     public boolean maySwitchOn(Switchable device,int hour)
//     {
//         return (hour>=6&&hour<=22);
//     }
// }
class Light implements Switchable
{
    boolean state = false;
    public void on()
    {
        state = true;
        System.out.println("light on");
    }
    public void off()
    {
        state = false;
        System.out.println("light off"); 
    }
    public boolean isOn()
    {
        return state;
    }
}
class Fan implements Switchable
{
    boolean state = false;
    public void on()
    {
        state = true;
        System.out.println("fan on");
    }
    public void off()
    {
        state = false;
        System.out.println("fan off"); 
    }
    public boolean isOn()
    {
        return state;
    }
}
class RemoteControl
{
 public static void main(String[] args)
{
    Switchable[] devices = {
        new Light(),
        new Fan()
    };
    for(Switchable d: devices)
    {
        d.toggle();
    }
   // DayTimeRule rule = new DayTimeRule();
    SwitchOnRule rule = new SwitchOnRule()
    {
        public boolean maySwitchOn(Switchable device,int hour)
        {
            return (hour>=6&&hour<=22);
        }
    };
    if(rule.maySwitchOn(devices[0], 20))
    {
        devices[0].on();
    }
    else
        System.out.println("can not on the light at this time");
}
}