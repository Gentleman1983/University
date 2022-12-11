public class FakultaetRek
{
    public static long berechnen(int n)
    {
        if(n == 0)
        {
            return 1;
        }
        else
        {
            return berechnen(n-1) * n;
        }
    }
    public static void main(String[] args)
    {
        int n=42;
        
        System.out.println(n + "! = " + berechnen(n) + " (rekursiv)");
    }
}