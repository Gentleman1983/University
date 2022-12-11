public class VektorSumme 
{
    public static String VektorSumme(int a[], int b[], int laenge) 
    {
        String rueckgabe ="( ";
        int zaehler = 0;
        while(zaehler < laenge)
        {
            rueckgabe += a[zaehler] + b[zaehler] + " ";
            zaehler++;
        }
        rueckgabe += ")";
        return rueckgabe;
    }
    public static void main(String[] args) 
    {
        int a[] = {0, 42, -91, 11, 4, 13, 6, 2, 4, 9};
        int b[] = {-9, 8, 7, 6, -19, 4, 3, -83, 1, 0};
        
        System.out.println(VektorSumme(a, b, 10));
    }
}
        