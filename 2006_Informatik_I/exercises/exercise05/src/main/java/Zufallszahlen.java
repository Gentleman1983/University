import java.util.Random;

public class Zufallszahlen
{
    public static int[] ziehen(int k)
    {
        int[] die_zahlen = new int[k];
        Random rnd = new Random(); //Zufallszahlenzieher initialisieren
        
        for(int i = 0; i < k; i++)
        {
            die_zahlen[i] = Math.abs(rnd.nextInt()); //eine Zahl ziehen
        }
        
        return die_zahlen;
    }
    
    public static int[] ziehen(int k, int max)
    {
        int[] die_zahlen = ziehen(k);
        
        for(int i = 0; i < k; i++)
        {
            die_zahlen[i] = die_zahlen[i] % max + 1; // Modulo-Div
        }
        
        return die_zahlen; //nun werden alle Zahlen zwischen 1 und max (einschl.)
    }
}