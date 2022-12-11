public class Haus extends Gebaeude   // Unterklasse Haus v. Geb. 
    {
        protected int AnzahlKinderzimmer;
        
        public Haus(int hausnummer, int jahr) 
        {
            super(hausnummer, jahr);
        }

        public int getAnzahlKinderzimmer() 
        {
            return AnzahlKinderzimmer;
        }

        public void setAnzahlKinderzimmer(int kz) 
        {
            AnzahlKinderzimmer = kz;
        }

        public String toString() 
        {
            return("Haus");
        }
    }
