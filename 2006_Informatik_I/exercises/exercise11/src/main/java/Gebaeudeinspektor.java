class Gebaeudeinspektor 
{
    public static void main(String[] args) 
        {
            Haus haus = new Haus(5, 2002);
            Stall stall = new Stall (12, 1983);
            Reihenhaus reihenhaus = new Reihenhaus(42, 1901);
            MFHaus mfhaus = new MFHaus (19, 1901);
            reihenhaus.setAnzahlKinderzimmer(3);
            mfhaus.setAnzahlWohnungen("fuenf");
            mfhaus.setAnzahlKinderzimmer(10);
            haus.setAnzahlKinderzimmer(4);
            System.out.println("Anzahl Kinderzimmer " + reihenhaus.toString() + ": " + reihenhaus.getAnzahlKinderzimmer());
            System.out.println("Anzahl Kinderzimmer " + haus.toString() + ": " + haus.getAnzahlKinderzimmer());
            System.out.println("Anzahl Kinderzimmer " + mfhaus.toString() + ": " + mfhaus.getAnzahlKinderzimmer());
            stall.setTierart("Ochse", "Ziege");
            stall.getTierart();
            System.out.println("Tierarten im " + stall.toString() + ": " + stall.getTierart());
            Gebaeude[] strasse = new Gebaeude[4];
            strasse[0] = haus;
            strasse[1] = stall;
            strasse[2] = reihenhaus;
            strasse[3] = mfhaus;
            System.out.println("Strasse: " + strasse[0].toString() + " "
                    + strasse[1].toString() + " " + strasse[2].toString() + " " + strasse[3].toString() );
        }
    
       
}
