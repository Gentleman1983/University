class Gruss
    {
        // Deklaration von Attributen
        String text;
        
        // Konstruktor
        public Gruss()
            {
                text = "Hallo Welt!!!";
            }
    
        // Deklaration von Methoden
        public void print()
            {
                System.out.print(text);
            }
        // Main-Methode
        public static void main(String args[])
            {
                Gruss g = new Gruss();
                g.print();
            }
    }