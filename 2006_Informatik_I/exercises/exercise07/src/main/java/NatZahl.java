public class NatZahl 
{
    public static void NatZahl(int zahl) 
    {
        int hunderter;
        int zehner;
        int einer;
        
        einer = zahl % 10;                            // Zerlegung in
        zehner = ((zahl - einer) % 100) / 10;         // Einer, Zehner
        hunderter = (zahl - zehner - einer) / 100;    // und Hunderter
        
        switch (hunderter)      // Ausgabe der Hunderterstelle
        {
            case 0:
                break;
            case 1:
                System.out.print("einhundert");
                break;
            case 2:
                System.out.print("zweihundert");
                break;
            case 3:
                System.out.print("dreihundert");
                break;
            case 4:
                System.out.print("vierhundert");
                break;
            case 5:
                System.out.print("fünfhundert");
                break;
            case 6:
                System.out.print("sechshundert");
                break;
            case 7:
                System.out.print("siebenhundert");
                break;
            case 8:
                System.out.print("achthundert");
                break;
            case 9:
                System.out.print("neunhundert");
                break;    
        }
        
        switch (zehner)    // Abarbeitung der Sonderfälle 11/12;
        {                  // in diesem Fall werden diese von der
            default:       // weiteren Bearbeitung ausgenommen
                break;
            case 1:
                switch (einer)
                {
                    default:
                        break;
                    case 1:
                        System.out.print("elf");
                        einer = 0;
                        zehner = 0;
                        break;
                    case 2:
                        System.out.print("zwölf");
                        einer = 0;
                        zehner = 0;
                        break;   
                }
                break;
        }
        
        switch (einer)       // Bearbeitung der regulären Einer-
        {                    // Stelle; bei 1, 6, 7, 8 Unterschei-
            default:         // dung zwischen der Sonderfallbehand-
                break;       // lung mit fehlenden Wortteilen
            case 1:
                System.out.print("ein");
                if (zehner == 0)
                {
                    System.out.print("s");
                }
                break;
            case 2:
                System.out.print("zwei");
                break;
            case 3:
                System.out.print("drei");
                break;
            case 4:
                System.out.print("vier");
                break;
            case 5:
                System.out.print("fünf");
                break;
            case 6:
                System.out.print("sech");
                if (zehner != 1)
                {
                    System.out.print("s");
                }
                break;
            case 7:
                System.out.print("sieb");
                if (zehner != 10)
                {
                    System.out.print("en");
                }
                break;
            case 8:
                System.out.print("ach");
                if (zehner != 1)
                {
                    System.out.print("t");
                }
                break;
            case 9:
                System.out.print("neun");
                break;
        }
        
        if (zehner != 0 && zehner != 1)    // Einfügen des obliga-
        {                                  // torischen "und" bei
            System.out.print("und");       // den "zig"-Werten
        }
        
        switch (zehner)       // Einfügen der passenden Zehnerstellen
        {
            default:
                break;
            case 1:
                System.out.print("zehn");
                break;
            case 2:
                System.out.print("zwanzig");
                break;
            case 3:
                System.out.print("dreißig");
                break;
            case 4:
                System.out.print("vierzig");
                break;
            case 5:
                System.out.print("fünfzig");
                break;
            case 6:
                System.out.print("sechzig");
                break;
            case 7:
                System.out.print("siebzig");
                break;
            case 8:
                System.out.print("achtzig");
                break;
            case 9:
                System.out.print("neunzig");
                break;    
        }
        System.out.print("\n");  // Einfügen eines Zeilenumbruchs zwi-
    }                            // schen zwei ausgegebenen Werten.
    public static void main(String[] args) 
    {
        NatZahl(256);
        NatZahl(1);
        NatZahl(999);
        NatZahl(666);
        NatZahl(555);
        NatZahl(512);
        NatZahl(11);
        NatZahl(73);
    }
}
