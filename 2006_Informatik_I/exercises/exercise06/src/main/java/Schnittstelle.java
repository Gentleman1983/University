public class Schnittstelle
{
    public void uebertragen(int wert)
    {
        System.out.print("Zu uebertragen: ");
        printAsBinary(wert);
        int toTransmit;
        printAsBinary(0); // �bertrage die Ruhelage
        toTransmit = (wert & 240) | 3;
        
        /* 240 = 1111 0000
         *   3 = 0000 0011
         * Dies f�hrt dazu, dass durch die logische Verkn�pfung von der Variablen
         * wert und 240 die ersten 4 Bit der Zahl wert ausgelesen werden. Anschlie�end
         * wird an diese 4 Bit der Bin�rwert f�r 3 (0011) angeh�ngt (bzw. die letzten
         * 4 Stellen 0000 durch 0011 �berschrieben).
         */
        
        printAsBinary(toTransmit); // �bertrage die erste H�lfte von wert
        printAsBinary(0); // �bertrage die Ruhelage
        toTransmit = (wert & 15) << 4 | 3;
        
        /*  15 = 0000 1111
         *   3 = 0000 0011
         * Dies f�hrt dazu, dass durch die logische Verkn�pfung von der Variablen
         * wert und 15 die letzten 4 Bit der Zahl wert ausgelesen werden. Anschlie�end
         * werden diese um 4 Stellen nach vorne geschoben und an diese 4 Bit wird der 
         * Bin�rwert f�r 3 (0011) angeh�ngt.
         */
        
        printAsBinary(toTransmit); // �bertrage die letzten 4 Bit der Varable wert
    }
    
    public static void printAsBinary(int x)
    {
        for(int i = 7; i >= 0; i--)
        {
            System.out.print(x/(int)(Math.pow(2,i)));
            x = (int)(x%Math.pow(2,i));
        }
        System.out.println();
    }
}