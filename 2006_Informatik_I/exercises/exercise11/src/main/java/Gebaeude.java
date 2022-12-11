abstract class Gebaeude //abstrakte Oberklasse Gebäude mit
    {                       //gew. Variablen und Methoden
        protected int Hausnummer;
        protected int Jahr_Der_Erbauung;

        public Gebaeude(int hausnummer, int jahr) 
        {
            Hausnummer = hausnummer;
            Jahr_Der_Erbauung = jahr;
        }

        public int getHausnummer() 
        {
            return Hausnummer;
        }

        public int getJahrDerErbauung() 
        {
            return Jahr_Der_Erbauung;
        }

        public void setHausnummer(int hausnummer) 
        {
            Hausnummer = hausnummer;
        }

        public void setJahrDerErbauung(int jahr) 
        {
            Jahr_Der_Erbauung = jahr;
        }

        public int compareTo(Object other) 
        {
            if (other instanceof Gebaeude) 
            {
                Gebaeude o = (Gebaeude)other;
                if (Hausnummer < o.Hausnummer) 
                {
                    return -1;
                }
                if (Hausnummer > o.Hausnummer) 
                {
                    return 1;
                }
                return 0;
            }
            return 0;
        }
    }