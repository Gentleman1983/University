public class Temperature
{
    public static int fahrenheit2celsius(int fahrenheit)
    {
        return (int) ((5.0 / 9.0) * fahrenheit - 32);
    }
    
    public static void main(String args[])
    {
        System.out.println("fahrenheit=" + -50 + " celsius=" + fahrenheit2celsius(-50));
        System.out.println("fahrenheit=" + 0 + " celsius=" + fahrenheit2celsius(0));
        System.out.println("fahrenheit=" + 32 + " celsius=" + fahrenheit2celsius(32));
        System.out.println("fahrenheit=" + 213 + " celsius=" + fahrenheit2celsius(213));
        System.out.println("fahrenheit=" + 451 + " celsius=" + fahrenheit2celsius(351));
    }
}