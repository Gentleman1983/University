public class MFHaus extends Haus //Unterklasse MFHaus von Haus 
    {
        private int AnzahlWohnungen = 1;

        public MFHaus(int hausnummer, int jahr) 
        {
            super(hausnummer, jahr);
        }

        public int getAnzahlKinderzimmer() 
        {
            return (super.AnzahlKinderzimmer / AnzahlWohnungen);
        }

        public int getAnzahlWohnungen() 
        {
            return AnzahlWohnungen;
        }

        public void setAnzahlWohnungen(int w) 
        {
            AnzahlWohnungen = w;
        }

        public void setAnzahlWohnungen(String anz) 
        {
            if (anz == "zwei") //da Datentyp String, leider kein
            {                  //Switch-Case möglich, daher nur
                AnzahlWohnungen = 2;//unschöne if-Lösung
            }
            if (anz == "drei") 
            {
                AnzahlWohnungen = 3;
            }
            if (anz == "vier") 
            {
                AnzahlWohnungen = 4;
            }
            if (anz == "fuenf") 
            {
                AnzahlWohnungen = 5;
            }
            if (anz == "sechs") 
            {
                AnzahlWohnungen = 6;
            }
            if (anz == "sieben") 
            {
                AnzahlWohnungen = 7;
            }
            if (anz == "acht") 
            {
                AnzahlWohnungen = 8;
            }
            if (anz == "neun") 
            {
                AnzahlWohnungen = 9;
            }
            if (anz == "zehn") 
            {
                AnzahlWohnungen = 10;
            }
        }

        public String toString() 
        {
            return("Mehr-Familien-Haus");
        }
    }