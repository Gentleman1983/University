public class Schnittstelle
{
    public void uebertragen(int wert)
    {
        System.out.print("Zu uebertragen: ");
        printAsBinary(wert);
        int toTransmit;
        printAsBinary(0); // Übertrage die Ruhelage
        toTransmit = (wert & 240) | 3;
        
        /* 240 = 1111 0000
         *   3 = 0000 0011
         * Dies führt dazu, dass durch die logische Verknüpfung von der Variablen
         * wert und 240 die ersten 4 Bit der Zahl wert ausgelesen werden. Anschließend
         * wird an diese 4 Bit der Binärwert für 3 (0011) angehängt (bzw. die letzten
         * 4 Stellen 0000 durch 0011 überschrieben).
         */
        
        printAsBinary(toTransmit); // Übertrage die erste Hälfte von wert
        printAsBinary(0); // Übertrage die Ruhelage
        toTransmit = (wert & 15) << 4 | 3;
        
        /*  15 = 0000 1111
         *   3 = 0000 0011
         * Dies führt dazu, dass durch die logische Verknüpfung von der Variablen
         * wert und 15 die letzten 4 Bit der Zahl wert ausgelesen werden. Anschließend
         * werden diese um 4 Stellen nach vorne geschoben und an diese 4 Bit wird der 
         * Binärwert für 3 (0011) angehängt.
         */
        
        printAsBinary(toTransmit); // Übertrage die letzten 4 Bit der Varable wert
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