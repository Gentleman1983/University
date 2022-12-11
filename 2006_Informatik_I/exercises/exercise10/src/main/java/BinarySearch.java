public class BinarySearch 
{
    public static int linearSearch(int[] feld, int wert) 
    {
        int position = 0;

        for (int i = 0; i < feld.length; ++i)
            {
                if (feld[i] == wert)       // Abbruch bei Fund
                {
                    position = (i + 1) * (-1);
                    break;
                }
                if (wert < feld[i])      // Abbruch bei vorz. Unm.
                {
                    position = i + 1;
                    break;
                }
                if (i == feld.length - 1) // Anpassung bei Ende des Array's
                {
                    position = i + 1;
                } 
            }
        return position;
    }
    
    public static void main(String[] args) 
    {
        int[] werte = {1, 3, 4, 7, 8, 10, 12, 15, 16, 19, 
            23, 33, 35, 55};
        
        System.out.println("Wert:\t| Position:\t\t\t| Anzahl Iterationen:");
        System.out.println("==================================" +
                    "============================");
        for(int zaehler = 0; zaehler <= 55; zaehler++)
        {
            int rueckgabe = linearSearch(werte, zaehler);
            System.out.print(" " + zaehler + "\t| ");
            if(rueckgabe >= 0)
            {
                System.out.print("Nicht gefunden...\t\t| ");
            }
            else
            {
                rueckgabe = rueckgabe * (-1);
                System.out.print("Wert gefunden an Position " + (rueckgabe - 1));
                System.out.print( "\t| ");
            }
            System.out.print(rueckgabe + "\n");
        }
    }
}
