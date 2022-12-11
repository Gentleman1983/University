public class SumRek 
{
    public static void SumRek(int n)      // Funktion zur Ausgabe-
    {                                     // steuerung
        System.out.println("Rekursiver Lösungsansatz");
        System.out.println();
        
        int Sum = CalcSumRek(n);
        
        System.out.println("SumRek(" + n + ") = " + Sum);
    }
    
    private static int CalcSumRek(int n)  // Methode zur Rekursiven 
    {                                     // Rechnung
        if(n == 1)
        {
            return 1;
        }
        else
        {
            return n + CalcSumRek(n -1);
        }
    }
    
    public static void main(String[] args) 
    {
        SumRek(101);
    }
}