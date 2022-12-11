public class FakultaetIt
{
    public static long berechnen(int n)
    {
        int i = 1;
        long result = 1;
        
        while(i < n)
        {
            i++;
            result *= i;
        }
        
        return result;
    }
    
    public static void main(String[] args)
    {
        int n=42;
        
        System.out.println(n + "! = " + berechnen(n) + " (iterativ)");
    }
}