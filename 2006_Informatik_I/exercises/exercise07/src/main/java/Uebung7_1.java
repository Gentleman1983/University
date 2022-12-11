public class Uebung7_1 
{    
    public static void main(String[] args) 
    {
        // Aufgabenteil 1
        int a = 1; // Der INT-Variablen a wird der Wert 1 zugeordnet.
        a ^= ++a > 1 ? a : 0;
        /*
         * Zuerst wird eine bitweise XOR-Verknüpfung zwischen a und  
         * (a+1) bzw. (++a) durchgeführt. Hierbei interessieren uns 
         * nur die letzen vier Bits:
         * a:     ... 0001 (1)
         * (a+1): ... 0010 (2)
         * XOR:   ========
         *        ... 0011 (3)
         * Damit hat das Ergebnis von (a ^= ++a) den Wert (3)Dez, 
         * welcher wieder der Variablen a zugeordnet wird.
         * Nun wird der Wert von Variablen a mit 1 verglichen. Ist 
         * a <= 1, so wird a = 0 gesetzt. Ist a > 1, so wird der 
         * Wert von a = a gesetzt, also nicht verändert.
         * Da (3)Dez größer als (1)Dez ist, wird der Wert von a nicht 
         * verändert und das Ergebnis der Rechnung ist a = 3!
         */
        
        // Aufgabenteil 2
        float a2 = 1; // Der FLOAT-Variablen a2 wird der Wert 1.0 
                      // zugeordnet.
        a2 = a2*a2++ == 2 ? 2F : 1;
        /*
         * Als erstes wird die Rechnung a2*a2++ durchgeführt. Da 
         * a2++ bedeutet, dass a2 erst nach Ausführung der Zeile 
         * inkrementiert wird, ist das Ergebnis dieser Rechnung 1.0. 
         * Dieser Wert wird mit dem Wert 2 auf Gleichheit überprüft. 
         * Da diese Aussage den Wahrheitswert FALSE liefert, wird 
         * nicht der Variablen a2 2F zugewiesen, sondern der Wert 1!
         */
    }
    
}
