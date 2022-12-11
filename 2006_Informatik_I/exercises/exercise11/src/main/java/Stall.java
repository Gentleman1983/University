public class Stall extends Gebaeude //Unterklasse Stall von Gebäude 
    {
        protected String Tierart1 = "";
        protected String Tierart2 = "";
        protected String Tierart3 = "";

        public Stall(int hausnummer, int jahr) 
        {
            super(hausnummer, jahr);
        }

        public String getTierart() 
        {
            String Ausgabe_gT = Tierart1 + " " + Tierart2 + " " + Tierart3;
            return Ausgabe_gT;
        }

        public void setTierart(String tier) 
        {
            Tierart1 = tier;
        }

        public void setTierart(String tier1, String tier2) 
        {
            Tierart1 = tier1;
            Tierart2 = tier2;
        }

        public void setTierart(String tier1, String tier2, String tier3) 
        {
            Tierart1 = tier1;
            Tierart2 = tier2;
            Tierart3 = tier3;
        }

        public String toString() 
        {
            return("Stall");
        }
    }