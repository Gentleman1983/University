public class IteEuklid 
{
    private int x = 0;
    private int y = 0;
    private boolean ggt = false;
    private int ggtwert = 0;
    
    public IteEuklid()
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
        while(b != 0)
        {
            if (a > b)
            {
                a = a - b;
            }
            else
            {
                b = b - a;
            }
        }
        ggt = true;
        ggtwert = a;
    }
    
    public static void main(String[] args) 
    {
        IteEuklid Eu = new IteEuklid();
        Eu.SetEuklid(4, 4);
        System.out.println("Der größte gemeinsame Teiler ist: " + 
                Eu.GetEuklid());
    }
}