public class RechnenMitBytes
{
    public static void main(String args[])
    {
        short a = 83;                       // A erhält den Short-Wert 83 -> 0000 0000 0101 0011
        short b = 119;                      // B erhält den Short-Wert 119-> 0000 0000 0111 0111
        int c = a + b;                      // C wird die Summe von A und B zugewiesen...
        
        /*
         *   0000 0000 0101 0011  (83)
         *   0000 0000 0111 0111 (119)
         *   =========================
         *   0000 0000 1100 1010 (202)
         */
        
        byte d = (byte) c;        // C wird auf BYTE gecastet und der BYTE-Variablen D zugewiesen
        
        /*
         * Die Short-Zahl C wird dabei auf 8 Bit gekürzt, wobei es sich um ein 8 Bit-Komplement handelt,
         * der Wertebereich also nur 7 Bit umfasst und ein Bit für den SIGN-Wert "verbraucht" wird. Hieraus
         * folgt:
         * C = 0000 0000 1100 1010 ===(BYTE-Cast)===> 1100 1010 = D
         *
         * Da die BYTE-Struktur folgendermaßen aussieht:
         * SWWW WWW (S: SIGN-Bit, W: Wert-Bits zur Angabe des Zahlenwerts)
         * wird der Zahlenwert 1100 1010 fälschlicherweise als Zweierkomplement erkannt.
         * 1100 1010 ist somit ein negativer Zahlenwert. Nur welcher?
         * 
         *                   1100 1010
         * Invertieren:      0011 0101
         * Inkrementieren:   0011 0110 (54)
         *                   =========
         * Damit sieht für den Computer der BYTE-Wert in D aus wie eine -54 anstatt der 202.
         *
         */
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(d);
    }
}