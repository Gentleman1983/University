public class SumIte 
{
    public static void SumIte(int n) 
    {
        System.out.println("Iterativer Lösungsansatz");
        System.out.println();
        
        int Sum = 0;
        int i = n;
        
        while(i > 0)
        {
            Sum += i;
            i--;
        }
        
        System.out.println("SumIte(" + n + ") = " + Sum);
    }
    
    public static void main(String[] args) 
    {
        SumIte(101);
    }
}
