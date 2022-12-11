public class Euklid 
{
    private int x = 0;
    private int y = 0;
    private boolean ggt = false;
    private int ggtwert = 0;
    
    public Euklid()
    {
        System.out.println("Sie haben soeben ein Objekt vom Typ" +
                " Euklid erzeugt!");
    }
    
    public void SetEuklid(int a, int b)
    {
        x = a;
        y = b;
    }
    
    public int GetEuklid()
    {
        EuklidGGT(x, y);
        return ggtwert;
    }
    
    private void EuklidGGT(int a, int b) 
    {
        if (b == 0)
        {
            ggt = true;
            ggtwert = a;
            System.out.println("\nGrößter gemeinsamer Teiler " +
                    "wurde berechnet!\n");
        }
        else if (a > b)
        {
            EuklidGGT(a - b, b);
        }
        else
        {
            EuklidGGT(a, b - a);
        }
    }
    
    public static void main(String[] args) 
    {
        Euklid Eu = new Euklid();
        Eu.SetEuklid(15, 50);
        System.out.println("Der größte gemeinsame Teiler ist: " + 
                Eu.GetEuklid());
    }
    
}
