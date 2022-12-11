public class quine 
{
    public static void main(String[] EventArgs) 
    {
        System.out.println("Willkommen bei Quine McCluskey V. 1.0");
        System.out.println("=====================================");
        System.out.println("(C) 2007 by Christian Otto for Informatik II\n");
        System.out.println("Ich wuensche Ihnen viel Spass bei der Nutzung dieser Testversion...");
        
        if(EventArgs.length > 0)
        {
            int varszahl = java.lang.Integer.decode(EventArgs[0]);
            
            if(varcntchk(varszahl, EventArgs.length - 1))
            {
                if(varchk(EventArgs))
                {
                    int arrlaenge = getarrlength(varszahl);
                    int[] ergebnistab = new int[arrlaenge];
                    
                    initergtab(ergebnistab, arrlaenge);
                    fuelleergtab(EventArgs, ergebnistab, arrlaenge);
                    ergebnistabanz(arrlaenge, varszahl, ergebnistab);
                    
                    int mtcnt = wertanzahl(ergebnistab);
                    int[] termgroup = new int[mtcnt * (varszahl + 2)]; // Eine Term-ID + Werte
                    int anz = ergebnisauswahl(arrlaenge, varszahl, ergebnistab, termgroup);
                    if(anz == 0)
                    {
                        System.out.print("Keine Minterme auffindbar. Das erleichtert uns doch die Arbeit erheblich!!!\nf(xn, ... , x1) = (0)\n");
                    }
                    else
                    {
                        grouptab(termgroup, varszahl, mtcnt);
                    
                        System.out.println("Vereinfache Minterme...\n");
                    
                        int maxlvl = termgroup[(mtcnt - 1) * (varszahl + 2) + 1] + 2; // Waehle hoechsten Level aus...
                        int maxmin = mtcnt * mtcnt * mtcnt * mtcnt; // hatte grad auf Anhieb keine obere Schranke gefunden, die passte... ;) Daher auf Nummer sicher gehen... ;)
                    
                        long[][][] mtliste = new long[maxlvl][maxmin][varszahl + 3];
                    
                        for(int i = 0; i < maxlvl; i++) // Initialisierung des Arrays
                        {
                            for(int j = 0; j < maxmin; j++)
                            {
                                for(int k = 0; k < varszahl + 3; k++)
                                {
                                    mtliste[i][j][k] = -42;
                                }
                            }
                        }
                        for(int m = 0; m < mtcnt; m++) // Einpflegen der alten Werte
                        {
                            for(int i = 0; i < varszahl + 2; i++)
                            {
                                mtliste[maxlvl - 1][m][i] = termgroup[m * (varszahl + 2) + i];
                            }
                        }
                    
                        int number_term = mintermopt(mtliste, varszahl + 3, maxmin, maxlvl, 1, false, 0); // Termoptimierung starten
                    
                        int primcnt = 0; // Zaehler fuer Menge der Primterme
                        System.out.println("Zusammengefasste Minterme:"); // Ausgabe der Minterme
                        for (int i = 0; (mtliste[0][i][0] != -42) && (i < number_term); i++)
                        {
                            System.out.print("M" + mtliste[0][i][0] + "\t||");
                            for(int j = 1; j < varszahl + 2; j++)
                            {
                                if(mtliste[0][i][j] == -1)
                                {
                                    System.out.print(" - |");
                                }
                                else
                                {
                                    System.out.print(" " + mtliste[0][i][j] + " |");
                                }
                            }
                            System.out.print("\n");
                            primcnt++;
                        }
                        System.out.print("\n");
                    
                        char[][] prim = new char[primcnt][arrlaenge + 1]; // Erstellen der Primtermtabelle
                        initprimarr(mtliste, prim, varszahl, primcnt, arrlaenge);
                    
                        System.out.println("Optimiere Primterme...");
                        primopt(prim, primcnt, arrlaenge);
                    
                        System.out.println("Berechne Boolsche Funktion...");
                        getboolfunc(prim, arrlaenge, primcnt, mtliste, varszahl, number_term);
                    
                        System.out.println("Vielen Dank, dass Sie Quine McCluskey V. 1.0 getestet haben... Wir wuenschen Ihnen noch einen wunderschoenen Tag!");
                    }
                }
            }
        }
        else
        {
            System.out.println("Bitte Parameter angeben. Programmabbruch...\nFunktionsaufruf:\n\njava quine <Variablenanzahl n> <2^n Variablenkombinationsergebnisse>");
        }
    }
    
    // Hilfsfunktionen (alphabetisch sortiert)
    
    static int ergebnisauswahl(int arrlaenge, int varszahl, int[] ergebnistab, int[] minterm) // Auswahl der Minterme
    {
        int anz = 0;
        
        System.out.println("Ergebnisauswahl:");
        for(int i = 0, m = 0; i < arrlaenge; i++)
        {
            int[] tmparr = new int[varszahl];
            for(int varcnt = 0, tmp = i; varcnt < varszahl; varcnt++, tmp /= 2)
            {
                tmparr[varszahl - varcnt - 1] = tmp % 2;
            }
            for(int j = 0; j < varszahl; j++)
            {
                System.out.print(" " + tmparr[j] + " |");
            }
            System.out.print("| " + ergebnistab[i] + " ");
            if(ergebnistab[i] == 1)
            {
                System.out.println("X");
                anz++;
                minterm[m * (varszahl + 2)] = i; //Indextitel vergeben
                int onecnt = 0;
                for(int j = 1; j <= varszahl; j++)
                {
                    minterm[m * (varszahl + 2) + j + 1] = tmparr[j - 1];
                    if(tmparr[j - 1] == 1)
                    {
                        onecnt++;
                    }
                }
                minterm[m * (varszahl + 2) + 1] = onecnt;
                m++;
            }
            else
            {
                System.out.println("-");
            }
        }
        System.out.print("\n");
        return anz;
    }
    
    static void ergebnistabanz(int arrlaenge, int varszahl, int[] ergebnistab) // Ausgabe der Ergebnistabelle
    {
        System.out.println("Ergebnistabelle:");
        for(int i = 0; i < arrlaenge; i++)
        {
            int[] tmparr = new int[varszahl];
            for(int varcnt = 0, tmp = i; varcnt < varszahl; varcnt++, tmp /= 2)
            {
                tmparr[varszahl - varcnt - 1] = tmp % 2;
            }
            for(int j = 0; j < varszahl; j++)
            {
                System.out.print(" " + tmparr[j] + " |");
            }
            System.out.println("| " + ergebnistab[i]);
        }
        System.out.print("\n");
    }
    
    static void fuelleergtab(String[] EventArgs, int[] ergebnistab, int laenge)
    {
        for(int i = 1; i <= laenge; i++)
        {
            ergebnistab[i-1] = java.lang.Integer.decode(EventArgs[i]);
        }
    }
    
    static int getarrlength(int vars)  // Berechnung der noetigen Arraylaenge
    {
        int arrlength; 
        
        for(arrlength = 1; vars != 0; vars--)
        {
            arrlength *= 2;
        }
        
        return arrlength;
    }
    
    static void getboolfunc(char[][] prim, int arrlng, int primcnt, long[][][] mt, int varszahl, int nt) // Zusammenbauen des logischen Ausdrucks... ;)
    {
        boolean started = false, conn = false, neverstarted = true;
        
        System.out.print("f(xn, ..., x1) = ");
        
        for (int p = 0; p < primcnt; p++)
        {
            if(prim[p][arrlng] == 'J')
            {
                if(conn == true)
                {
                    System.out.print(" v ");
                    conn = false;
                }
                
                System.out.print("(");
                
                for(int j = 2; j < varszahl + 2; j++)
                {
                    if(mt[0][p][j] == 1)
                    {
                        if (started == true)
                        {
                            System.out.print(" ^ ");
                        }
                        System.out.print("x" + (varszahl + 2 - j));
                        started = true;
                        neverstarted = false;
                    }
                    else if (mt[0][p][j] == 0)
                    {
                        if (started == true)
                        {
                            System.out.print(" ^ ");
                        }
                        System.out.print("!x" + (varszahl + 2 - j));
                        started = true;
                        neverstarted = false;
                    }
                }
                if(started == true)
                {
                    System.out.print(")");
                    conn = true;
                    started = false;
                }
            }
        }
        if(neverstarted == true)
        {
            System.out.print("1)");
        }
        System.out.print("\n\n");
    }
    
    static void grouptab(int[] mt, int varszahl, int mtcnt) // Anzeigen der gruppierten Tabelle
    {
        System.out.println("Gruppierungen:");
        for(int m = 0; m < mtcnt; m++)
        {
            System.out.print(" " + mt[m * (varszahl + 2) + 1] + " ||");
            System.out.print(" M" + mt[m * (varszahl + 2)] + " |");
            for(int i = 0; i < varszahl; i++)
            {
                System.out.print("| " + mt[m * (varszahl + 2) + i + 2] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
     
    static void initergtab(int[] ergebnistab, int laenge) // Ergebnistabelle vorinitialisieren
    {
        for(int i = 0; i < laenge; i++)
        {
            ergebnistab[i]=0;
        }
    }
    
    static void initprimarr(long[][][] mt, char[][] prim, int varszahl, int primcnt, int arrlng) // Primfaktortabelle anlegen
    {
        for(int i = 0; i < primcnt; i++) // Vorinitialisierung des Prim-Arrays
        {
            for(int j = 0; j <= arrlng; j++)
            {
                prim[i][j] = ' ';
            }
        }
        
        for(int i = 0; i < primcnt; i++) // Zuordnung der Minterme zu den Primtermen
        {
            long tmp = mt[0][i][0];
            while(tmp != 0)
            {
                prim[i][(int)tmp % 10] = 'X';
                tmp /= 10;
            }
        }
        primausg(prim, primcnt, arrlng);
    }
    
    static int mintermopt(long[][][] mt, int varszahl, int maxmin, int maxlvl, int optlvl, boolean optimized, int cnt) // Minterme vereinfachen und minimieren
    {
        if(optimized == true || optlvl > maxlvl) // Rekursionsabbruch
        {
            return cnt;
        }
        
        int lvl = maxlvl - optlvl;
        
        optimized = true;
        int lvlcnt = 0;
        for(int i = 0; (i + 1 < maxmin) && (mt[lvl][i][0] != -42); i++)
        {
            for(int j = i + 1; (j < maxmin) && (mt[lvl][j][0] != -42); j++)
            {
                if(mt[lvl][i][1] + 1 != mt[lvl][j][1])
                {
                    continue;
                }
                
                int chk = 0;
                
                for(int k = 2; k + 1 < varszahl; k++)
                {
                    if(mt[lvl][i][k] != mt[lvl][j][k])
                    {
                        chk++;
                    }
                }
                if(chk == 1)
                {
                    optimized = false;
                    mt[lvl - 1][lvlcnt][0] = mt[lvl][j][0]*((int) Math.pow(10, optlvl)) + mt[lvl][i][0];
                    int onecnt = 0;
                    for(int k = 2; k + 1 < varszahl; k++)
                    {
                        if(mt[lvl][i][k] == mt[lvl][j][k])
                        {
                            mt[lvl - 1][lvlcnt][k] = mt[lvl][j][k]; // korrekten Eintrag übernehmen
                            if(mt[lvl][i][k] == 1) // Einsenzaehler inkrementieren
                            {
                                onecnt++;
                            }
                        }
                        else
                        {
                            mt[lvl - 1][lvlcnt][k] = -1; // ungleichen Eintrag durch -1 ersetzen
                        }
                    }
                    mt[lvl - 1][lvlcnt][1] = onecnt;
                    mt[lvl][i][varszahl - 1] = 1;
                    mt[lvl][j][varszahl - 1] = 0;
                    lvlcnt++;
                }   
            }
        }
        for(int i = 0; (mt[lvl][i][0] != -42) && (i <= maxmin); i++)
        {
            if(mt[lvl][i][varszahl - 1] == -42)
            {
                for(int j = 0; j < varszahl; j++)
                {
                    mt[0][cnt][j] = mt[lvl][i][j];
                }
                cnt++;
            }
        }        
        return mintermopt(mt, varszahl, maxmin, maxlvl, optlvl + 1, optimized, cnt);
    }
    
    static void primausg(char[][] prim, int primcnt, int arrlng) // Ausgabe der Primtermtabelle
    {
        System.out.println("Primterme:"); 
        System.out.print(" \t|");
        for(int j = 0; j < arrlng; j++)
        {
            System.out.print("| M" + j + "\t");
        }
        System.out.print("\n");
        for(int i = 0; i < primcnt; i++)
        {
            System.out.print("P" + i + "\t|");
            for(int j = 0; j < arrlng; j++)
            {
                System.out.print("| " + prim[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    static void primkill(char[][] prim, int primcnt, int arrlng) // Ausstreichen ueberfluessiger Terme
    {
        for(int i = 0; i < primcnt; i++)
        {
            if(prim[i][arrlng] == 'J')
            {
                if(i > 0)
                {
                    for(int k = i - 1; k >= 0; k--)
                    {
                        for(int j = 0; j < arrlng; j++)
                        {
                            if((prim[i][j] == 'X') || (prim [i][j] == '*'))
                            {
                                prim[k][j] = ' ';
                            }
                        }
                    }
                }
                if(i < primcnt)
                {
                    for(int k = i + 1; k < primcnt; k++)
                    {
                        for(int j = 0; j < arrlng; j++)
                        {
                            if((prim[i][j] == 'X') || (prim [i][j] == '*'))
                            {
                                prim[k][j] = ' ';
                            }
                        }
                    }
                }
            }
        }
        
        for(int j = 0; j < arrlng; j++)
        {
            int termcnt = 0;
            
            for(int i = 0; i < primcnt; i++)
            {
                if(prim[i][j] == 'X')
                {
                    termcnt++;
                }
            }
            
            if(termcnt == 1)
            {
                for(int k = 0; k < primcnt; k++)
                {
                    if(prim[k][j] == 'X')
                    {
                        prim[k][j] = '*';
                        prim[k][arrlng] = 'J';
                    }
                }
            }
        }
    }
    
    static void primopt(char[][] prim, int primcnt, int arrlng) // Optimierung der Primtabelle
    {
        for(int j = 0; j < arrlng; j++)
        {
            int termcnt = 0;
            
            for(int i = 0; i < primcnt; i++)
            {
                if(prim[i][j] == 'X')
                {
                    termcnt++;
                }
            }
            
            if(termcnt == 1)
            {
                for(int k = 0; k < primcnt; k++)
                {
                    if(prim[k][j] == 'X')
                    {
                        prim[k][j] = '*';
                        prim[k][arrlng] = 'J';
                    }
                }
            }
            else if(termcnt == 0)
            {
                for(int k = 0; k < primcnt; k++)
                {
                    prim[k][j] = '-';
                }
            }
        }
        primausg(prim, primcnt, arrlng);
        
        primkill(prim, primcnt, arrlng);
        
        System.out.println("Waehle Primterme...");
        
        int[] xcnt = new int[arrlng];
        boolean fertig = false;
        
        while(fertig == false) // Sauber machen... ;)
        {
            for(int j = 0; j < arrlng; j++)
            {
                for(int i = 0; i < primcnt; i++)
                {
                    xcnt[i] = 0;
                }
                for(int i = 0; i < primcnt; i++)
                {
                    if(prim[i][j] == 'X')
                    {
                        xcnt[i]++;
                    }
                }
                int[] xmax = {0, 0};
                
                for(int i = 0; i < primcnt; i++)
                {
                    if(xcnt[i] > xmax[1])
                    {
                        xmax[0] = i;
                        xmax[1] = xcnt[i];
                    }
                }
                prim[xmax[0]][arrlng] = 'J';
                primkill(prim, primcnt, arrlng);
                
                if(xmax[1] == 0)
                {
                    fertig = true;
                }
            }
            
        }
        
        primausg(prim, primcnt, arrlng);
    }
    
    static boolean varchk(String[] EventArgs) // Wertebereichspruefung
    {
        boolean chk = true; // Hilfsvariable
        int varcnt = java.lang.Integer.decode(EventArgs[0]);
        
        for(int i = 1; chk == true && i <= varcnt; i++)
        {
            if(java.lang.Integer.decode(EventArgs[i]) == 1 || java.lang.Integer.decode(EventArgs[i]) == 0)
            {
                chk = true;
            }
            else
            {
                System.out.println("Ungueltiger Variablenwert!");
                chk = false;
            }
        }
        
        return chk;
    }
    
    static boolean varcntchk(int vars, int varcnt)  // Ueberpruefung der Arraylaenge
    {
        int chk; // Hilfsvariable fuer Zaehlung der Terme
        
        if(vars < 1) // Es sollte ja schon mindestens eine Variable geben!
        {
            System.out.println("Bitte eine gueltige Variablenanzahl angeben (>=1)");
            return false;
        }
        
        for(chk = 1; vars != 0; vars--) // Berechnung der nötigen Parameterzahl
        {
            chk *= 2;
        }
        
        if(chk <= varcnt) // Es sollten genug Parameter angegeben sein. Zuviele sind mir an dieser Stelle egal!
        {
            return true;
        }
        else
        {
            System.out.println("Zu wenige Variablenauswertungen angegeben. Programmabbruch...");
            return false;
        }
    }
    
    static int wertanzahl(int[] erg) // Zaehlen der Menge der Minterme (1-er Terme)
    {
        int cnt=0;
        
        for(int i = 0; i < erg.length; i++)
        {
            cnt += erg[i]; // Da nur 1-en und 0-en enthalten sind
        }
        
        System.out.println("Es wurden " + cnt + " Minterme gefunden...\n");
        
        return cnt;
    }
}
