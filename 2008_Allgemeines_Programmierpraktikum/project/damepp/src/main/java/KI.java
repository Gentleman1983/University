/**
 * Allgemeines Programmierpraktikum Sommersemester 2008
 * Georg-August-Universität Göttingen (Dr. H. Brosenne)
 * Projektarbeit zum Thema DamePP
 * 
 * Gruppe: DamePP-2AD
 * Teamleitung: Christian Otto
 * Projektbetreuung: David-Alexandre Guiraud
 * 
 * Entwickler:
 * Kathrin Aßhauer, Christian Beulke, Franziska Klingner,
 * Stefanie Mühlhausen, Christian Otto, Martin Schewe, 
 * Sven Withus
 */

package damepp;

import java.util.*;

/**
 * Diese Klasse implementiert die Intelligenz des Computergegners.
 * @author Christian Otto
 */
public class KI
{
/*======Variablen============================================================*/
	
	/** Die bewerteten Züge. */
	private static LinkedList<KIZuege> bew_zuege = new LinkedList<KIZuege>();

	/** Spielfeld in geparster Form. */
	private int[][] feld;

	/** Statisches Feld. */
	private static int[][] statisches_feld;

	/** Spiele ich mit schwarz? */
	private boolean binIchSchwarz;

	/** Ist gerade der erste Zug? */
	private boolean ersterZug = true;

	/** Das aktuelle Zeitkonto */
	private static long Zeitkonto;

	/** Aktuelle Systemzeit kann hier abgelegt werden. */
	private static long akt_zeit;

	/** Die Anzahl Z&uuml;ge, die die KI vorberechnet um den Gegenzug zu berechnen. Werte >= 1 erlaubt. */
	private int Rechentiefe;

	/** Wie stark werden &uuml;bersprungene Steine zu verlorenen gewertet? */
	private double Offensivgrad;
	
/*======Methoden=============================================================*/
	
/*------Getter---------------------------------------------------------------*/
	/**
	 * Liefert die aktuelle Zeit vom Start der Zugberechnung zur&uuml;ck.
	 */
	protected static long AktuelleZeit()
	{
		return akt_zeit;
	}

	/**
	 * Gibt den Zeitkontostand zurück.
	 * @return Den Zeitkontostand.
	 */
	protected static long Zeitkonto()
	{
		return Zeitkonto;
	}

	/**
	 * Liest das lokale Spielfeld aus.
	 * @return Das lokalte Spielfeld.
	 */
	protected int[][] gibFeld()
	{
		return this.feld;
	}
	
	/**
	 * Liefert den Wert an einer Boardposition zurück.
	 * @param x Die X-Position auf dem Spielfeld.
	 * @param y Die Y-Position auf dem Spielfeld.
	 * @return Die Feldinformation.
	 */
	protected int gibFeldPosition(int x, int y)
	{
		return this.feld[x][y];
	}
	
	/**
	 * Liest das lokale Spielfeld aus.
	 * @return Das lokale Spielfeld.
	 */
	protected static int[][] gibstatischesFeld()
	{
		return statisches_feld;
	}

/*------Setter---------------------------------------------------------------*/

/*------Funktionalitaet------------------------------------------------------*/
	
	/**
	 * Diese Methode &uuml;bergibt der Liste der KIZuege eine leere 
	 * ArrayList, das Signal für eine Aufgabe.
	 */
	private void aufgeben()
	{
		if(CompSpieler.Wartungsmodus())
		{
			System.out.println("Computer entschied sich, das Spiel aufzugeben! Er hält keinen Zug mehr für möglich.");
		}
		
		bew_zuege.add(new KIZuege(null, this.Rechentiefe, this.Offensivgrad)); // Liefere eine leere Liste zurück.
	}
	
/*------Hilfsmethoden--------------------------------------------------------*/

	/**
	 * Startet die Sprungberechnung.
	 * @param x Die X-Position des Startsteins.
	 * @param y Die Y-Position des Startsteins.
	 * @param farbeIstSchwarz true, wenn der Spieler schwarz ist.
	 * @param istDame true, wenn Stein eine Dame ist.
	 */
	private void berechneSchlag(int x, int y, boolean farbeIstSchwarz, boolean istDame)
	{
		String zugPfad = "" + x + "" + y; // Zugpfad.
		
		if((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)) // Stein ist auf dem Spielfeld.
		{
			int[][] tempFeld; // Arbeitskopie des Spielfeldes anlegen.
			
			if(farbeIstSchwarz) // schwarze Dame oder Bauer.
			{	
				if(
					(
						((x - 2) >= 0) && 
						((x - 2) <= 7) && 
						((y - 2) >= 0) && 
						((y - 2) <= 7)
					) &&
					// Schlag nach links oben.
					(this.istMoeglichSchlag(x,
								y,
								(x - 2),
								(y - 2),
								farbeIstSchwarz,
								this.gibFeld()
								)
					)
				)
				{
					// Sprung auf Feld ausführen.
					tempFeld = springe(x, y, (x - 2), (y - 2), this.gibFeld());
					// Einmal rekursiv zum Restaurant am Ende des Universums...
					berechneSchlag(	(x - 2),
							(y - 2),
							farbeIstSchwarz,
							istDame,
							zugPfad,
							tempFeld);
				}
				if(
					(
						((x + 2) >= 0) &&
						((x + 2) <= 7) &&
						((y - 2) >= 0) &&
						((y - 2) <= 7)
					) &&
					// Schlag nach rechts oben.
					(this.istMoeglichSchlag(x,
								y,
								(x + 2),
								(y - 2),
								farbeIstSchwarz,
								this.gibFeld()
								)
					)
				)
				{
					// Sprung auf Feld ausführen.
					tempFeld = springe(x, y, (x + 2), (y - 2), this.gibFeld());
					// Einmal rekursiv zum Restaurant am Ende des Universums...
					berechneSchlag(	(x + 2),
							(y - 2),
							farbeIstSchwarz,
							istDame,
							zugPfad,
							tempFeld);
				}
				if(istDame)
				{
					if(
						(
							((x - 2) >= 0) &&
							((x - 2) <= 7) &&
							((y + 2) >= 0) &&
							((y + 2) <= 7)
						) &&
						// Schlag nach links unten.
						(this.istMoeglichSchlag(x,
									y,
									(x - 2),
									(y + 2),
									farbeIstSchwarz,
									this.gibFeld()
									)
						)
					)
					{
						// Sprung auf Feld ausführen.
						tempFeld = springe(x, y, (x - 2), (y + 2), this.gibFeld());
						// Einmal rekursiv zum Restaurant am Ende des Universums...
						berechneSchlag(	(x - 2),
								(y + 2),
								farbeIstSchwarz,
								istDame,
								zugPfad,
								tempFeld);
					}
					if(
						(
							((x + 2) >= 0) &&
							((x + 2) <= 7) &&
							((y + 2) >= 0) &&
							((y + 2) <= 7)
						) &&
						// Schlag nach rechts unten.
						(this.istMoeglichSchlag(x,
									y,
									(x + 2),
									(y + 2),
									farbeIstSchwarz,
									this.gibFeld()
									)
						)
					)
					{
						// Sprung auf Feld ausführen.
						tempFeld = springe(x, y, (x + 2), (y + 2), this.gibFeld());
						// Einmal rekursiv zum Restaurant am Ende des Universums...
						berechneSchlag(	(x + 2),
								(y + 2),
								farbeIstSchwarz,
								istDame,
								zugPfad,
								tempFeld);
					}
				}	
			}
			else if(!farbeIstSchwarz) // weiße Dame oder Bauer.
			{	
				if(
					(
							// Zugziel liegt im Spielfeld.
							((x - 2) >= 0) &&
							((x - 2) <= 7) &&
							((y + 2) >= 0) &&
							((y + 2) <= 7)
					) && 
					// Schlag nach links unten.
					(this.istMoeglichSchlag(x,
								y,
								(x - 2),
								(y + 2),
								farbeIstSchwarz,
								this.gibFeld()
								)
					)
				)
				{
					// Sprung auf Spielfeld ausführen.
					tempFeld = springe(x, y, (x - 2), (y + 2), this.gibFeld());
					// Einmal rekursiv zum Restaurant am Ende des Universums...
					berechneSchlag(	(x - 2),
							(y + 2),
							farbeIstSchwarz,
							istDame,
							zugPfad,
							tempFeld);
				}
				if(
					// Zugziel liegt im Spielfeld.
					(
						((x + 2) >= 0) &&
						((x + 2) <= 7) &&
						((y + 2) >= 0) &&
						((y + 2) <= 7)
					) &&
					// Schlag nach rechts unten.
					(this.istMoeglichSchlag(x,
								y,
								(x + 2),
								(y + 2),
								farbeIstSchwarz,
								this.gibFeld()
								)
					)
				)
				{
					// Sprung auf Feld ausführen.
					tempFeld = springe(x, y, (x + 2), (y + 2), this.gibFeld());
					// Einmal rekursiv zum Restaurant am Ende des Universums...
					berechneSchlag(	(x + 2),
							(y + 2),
							farbeIstSchwarz,
							istDame,
							zugPfad,
							tempFeld);
				}
				if(istDame)
				{
					if(
						// Zugziel liegt im Spielfeld.
						(
							((x - 2) >= 0) &&
							((x - 2) <= 7) &&
							((y - 2) >= 0) &&
							((y - 2) <= 7)
						) &&
						// Schlag nach links oben.
						(this.istMoeglichSchlag(x,
									y,
									(x - 2),
									(y - 2),
									farbeIstSchwarz,
									this.gibFeld()
									)
						)
					)
					{
						// Sprung auf Feld ausführen.
						tempFeld = springe(x, y, (x - 2), (y - 2), this.gibFeld());
						// Einmal rekursiv zum Restaurant am Ende des Universums...
						berechneSchlag(	(x - 2),
								(y - 2),
								farbeIstSchwarz,
								istDame,
								zugPfad,
								tempFeld);
					}
					if(
						// Zugziel liegt im Spielfeld.
						(
							((x + 2) >= 0) &&
							((x + 2) <= 7) &&
							((y - 2) >= 0) &&
							((y - 2) <= 7)
						) &&
						// Schlag nach rechts oben.
						(this.istMoeglichSchlag(x,
									y,
									(x + 2),
									(y - 2),
									farbeIstSchwarz,
									this.gibFeld()
									)
						)
					)
					{
						// Sprung auf Spielfeld ausführen.
						tempFeld = springe(x, y, (x + 2), (y - 2), this.gibFeld());
						// Einmal rekursiv zum Restaurant am Ende des Universums...
						berechneSchlag(	(x + 2),
								(y - 2),
								farbeIstSchwarz,
								istDame,
								zugPfad,
								tempFeld);
					}
				}	
			}
		}
	}
	
	
	/**
	 * Führt die Rekursion der Sprungsuche aus.
	 * @param x Die X-Position für die Berechnung.
	 * @param y Die Y-Position für die Berechnung.
	 * @param farbeIstSchwarz Die Spielerfarbe.
	 * @param istDame Haben wir eine Dame?
	 * @param zugPfad Der Zugpfad in der Form "07254321": (0, 7) -> (2, 5) -> (4, 3) -> (2, 1).
	 * @param tempFeld Eine Spielfeldkopie.
	 */
	private void berechneSchlag(int x, int y, boolean farbeIstSchwarz, boolean istDame, String zugPfad, int[][] tempFeld)
	{
		zugPfad += "" + x + "" + y; // Zugpfad erweitern.
		
		boolean sprungLO = istMoeglichSchlag(x, y, (x - 2), (y - 2), farbeIstSchwarz, tempFeld) && ((istDame && !farbeIstSchwarz) || farbeIstSchwarz); 		// Sprung nach links oben (nur schwarze Figuren und weiße Damen).
		boolean sprungRO = istMoeglichSchlag(x, y, (x + 2), (y - 2), farbeIstSchwarz, tempFeld) && ((istDame && !farbeIstSchwarz) || farbeIstSchwarz); 		// Sprung nach rechts oben (nur schwarze Figuren und weiße Damen).
		boolean sprungLU = (istMoeglichSchlag(x, y, (x - 2), (y + 2), farbeIstSchwarz, tempFeld)) && ((istDame && farbeIstSchwarz) || !farbeIstSchwarz); 	// Sprung nach links unten (nur weiße Figuren und schwarze Damen).
		boolean sprungRU = (istMoeglichSchlag(x, y, (x + 2), (y + 2), farbeIstSchwarz, tempFeld)) && ((istDame && farbeIstSchwarz) || !farbeIstSchwarz); 	// Sprung nach rechts unten (nur weiße Figuren und schwarze Damen).
		
		if(CompSpieler.Wartungsmodus())
		{
			System.out.println("SprungLO: " + sprungLO + ", SprungRO: " + sprungRO + ", SprungLU: " + sprungLU + ", SprungRU: " + sprungRU);
		}
		
		if(!sprungLO && !sprungLU && !sprungRO && !sprungRU) // Kein weiterer Sprung mehr möglich.
		{
			if(CompSpieler.Wartungsmodus())
			{
				System.out.println("Schlagstring: " + zugPfad);
			}
			this.SchlagStringZuZug(zugPfad); // Codierten String in Zug umwandeln.
		}
		else
		{
			if(farbeIstSchwarz) // schwarze Dame oder Bauer.
			{	
				if((((x - 2) >= 0) && ((x - 2) <= 7) && ((y - 2) >= 0) && ((y - 2) <= 7)) && 				// Zugziel liegt im Spielfeld.
						(this.istMoeglichSchlag(x, y, (x - 2), (y - 2), farbeIstSchwarz, tempFeld))) 		// Schlag nach links oben.
				{
					int[][] tempFeldLO = springe(x, y, (x - 2), (y - 2), tempFeld); 						// Sprung auf Spielfeld ausführen.
					berechneSchlag((x - 2), (y - 2), farbeIstSchwarz, istDame, zugPfad, tempFeldLO); 		// Einmal rekursiv zum Restaurant am Ende des Universums...
				}
				if((((x + 2) >= 0) && ((x + 2) <= 7) && ((y - 2) >= 0) && ((y - 2) <= 7)) && 				// Zugziel liegt im Spielfeld.
						(this.istMoeglichSchlag(x, y, (x + 2), (y - 2), farbeIstSchwarz, tempFeld))) 		// Schlag nach rechts oben.
				{
					int[][] tempFeldRO = springe(x, y, (x + 2), (y - 2), tempFeld); 						// Sprung auf Spielfeld ausführen.
					berechneSchlag((x + 2), (y - 2), farbeIstSchwarz, istDame, zugPfad, tempFeldRO); 		// Einmal rekursiv zum Restaurant am Ende des Universums...
				}
				if(istDame)
				{
					if((((x - 2) >= 0) && ((x - 2) <= 7) && ((y + 2) >= 0) && ((y + 2) <= 7)) && 			// Zugziel liegt im Spielfeld.
							(this.istMoeglichSchlag(x, y, (x - 2), (y + 2), farbeIstSchwarz, tempFeld))) 	// Schlag nach links unten.
					{
						int[][] tempFeldLU = springe(x, y, (x - 2), (y + 2), tempFeld); 					// Sprung auf Spielfeld ausführen.
						berechneSchlag((x - 2), (y + 2), farbeIstSchwarz, istDame, zugPfad, tempFeldLU); 	// Einmal rekursiv zum Restaurant am Ende des Universums...
					}
					if((((x + 2) >= 0) && ((x + 2) <= 7) && ((y + 2) >= 0) && ((y + 2) <= 7)) && 			// Zugziel liegt im Spielfeld.
							(this.istMoeglichSchlag(x, y, (x + 2), (y + 2), farbeIstSchwarz, tempFeld))) 	// Schlag nach rechts unten.
					{
						int[][] tempFeldRU = springe(x, y, (x + 2), (y + 2), tempFeld); 					// Sprung auf Spielfeld ausführen.
						berechneSchlag((x + 2), (y + 2), farbeIstSchwarz, istDame, zugPfad, tempFeldRU); 	// Einmal rekursiv zum Restaurant am Ende des Universums...
					}
				}	
			}
			else if(!farbeIstSchwarz) // weiße Dame oder Bauer.
			{	
				if((((x - 2) >= 0) && ((x - 2) <= 7) && ((y + 2) >= 0) && ((y + 2) <= 7)) && 				// Zugziel liegt im Spielfeld.
						(this.istMoeglichSchlag(x, y, (x - 2), (y + 2), farbeIstSchwarz, tempFeld))) 		// Schlag nach links unten.
				{
					int[][] tempFeldLU = springe(x, y, (x - 2), (y + 2), tempFeld); 						// Sprung auf Spielfeld ausführen.
					berechneSchlag((x - 2), (y + 2), farbeIstSchwarz, istDame, zugPfad, tempFeldLU); 		// Einmal rekursiv zum Restaurant am Ende des Universums...
				}
				if((((x + 2) >= 0) && ((x + 2) <= 7) && ((y + 2) >= 0) && ((y + 2) <= 7)) && 				// Zugziel liegt im Spielfeld.
						(this.istMoeglichSchlag(x, y, (x + 2), (y + 2), farbeIstSchwarz, tempFeld))) 		// Schlag nach rechts unten.
				{
					int[][] tempFeldRU = springe(x, y, (x + 2), (y + 2), tempFeld); 						// Sprung auf Spielfeld ausführen.
					berechneSchlag((x + 2), (y + 2), farbeIstSchwarz, istDame, zugPfad, tempFeldRU); 		// Einmal rekursiv zum Restaurant am Ende des Universums...
				}
				if(istDame)
				{
					if((((x - 2) >= 0) && ((x - 2) <= 7) && ((y - 2) >= 0) && ((y - 2) <= 7)) && 			// Zugziel liegt im Spielfeld.
							(this.istMoeglichSchlag(x, y, (x - 2), (y - 2), farbeIstSchwarz, tempFeld))) 	// Schlag nach links oben.
					{
						int[][] tempFeldLO = springe(x, y, (x - 2), (y - 2), tempFeld); 					// Sprung auf Spielfeld ausführen.
						berechneSchlag((x - 2), (y - 2), farbeIstSchwarz, istDame, zugPfad, tempFeldLO); 	// Einmal rekursiv zum Restaurant am Ende des Universums...
					}
					if((((x + 2) >= 0) && ((x + 2) <= 7) && ((y - 2) >= 0) && ((y - 2) <= 7)) && 			// Zugziel liegt im Spielfeld.
							(this.istMoeglichSchlag(x, y, (x + 2), (y - 2), farbeIstSchwarz, tempFeld))) 	// Schlag nach rechts oben.
					{
						int[][] tempFeldRO = springe(x, y, (x + 2), (y - 2), tempFeld); 					// Sprung auf Spielfeld ausführen.
						berechneSchlag((x + 2), (y - 2), farbeIstSchwarz, istDame, zugPfad, tempFeldRO); 	// Einmal rekursiv zum Restaurant am Ende des Universums...
					}
				}	
			}
		}
	}
	
	
	/**
	 * Berechnet den Zeilenversatz der relevanten Felder.
	 * @param y Die Y-Koordinate.
	 * @return 0, wenn Zeilennummer gerade, 1 wenn Zeilennummer ungerade. (intern genau invertiert)
	 */
	private static int berechneVersatz(int y)
	{
		int versatz = (y + 1) % 2; // Sorgt für die richtige Zeilenverschiebung.
		return versatz;
	}
	
	/**
	 * Berechnet den X-Wert in der Zeile abhängig vom Versatz.
	 * @param x Die virtuelle X-Koordinate.
	 * @param y Die Y-Koordinate.
	 * @return Die reelle X-Koordinate.
	 */
	protected static int berechneXPos(int x, int y)
	{
		int rueckgabewert = 2 * x + berechneVersatz(y);
		return rueckgabewert;
	}
	
	/**
	 * Diese Methode berechnet die möglichen Züge und gibt diese als
	 * verkettete Liste KIMoveItems zurück...
	 * @param Zeit Der Stand des Zeitkontos.
	 * @param Rechentiefe Die gew&uuml;nschte Rechentiefe.
	 * @param Offensivgrad Der gew&uuml;nschte Offensivgrad.
	 * @return Die bewerteten, möglichen Züge.
	 */
	protected LinkedList<KIZuege> berechneZug(long Zeit, int Rechentiefe, double Offensivgrad)
	{
		akt_zeit = System.currentTimeMillis(); 	// Aktuelle Systemzeit einlesen.
		Zeitkonto = Zeit; 						// Internes Zeitguthaben aktualisieren.
		bew_zuege.clear(); 						// Sicherheitshalber vor dem Berechnen der Züge die Liste
				       							// leeren. Kann ja nicht schaden.
		this.Rechentiefe = Rechentiefe;
		this.Offensivgrad = Offensivgrad;
		this.feld = parseFeld(Spielfeld.getSpielfeld()); // Das Spielfeld in eine KI-freundliche
		  												 // Form umwandeln.
		statisches_feld = kloneFeld(this.feld);			 // Das statische Feld für die KIZuegeklasse setzen.

		if(this.ersterZug) // Da der Spielstart für das Endergebnis relativ irrelevant
		{			       // ist, brauchen wir hier nicht tiefgreifender zu suchen.
			this.binIchSchwarz = Spielfeld.getZugSchwarz(); // Bei der Spielfeldverwaltung
		 										            // die Spielerfarbe erfragen.
			if(this.binIchSchwarz) // Zur Übersichtlichkeit wurden die Startzüge ausgelagert.
			{
				ersterZugSchwarz(); // Hard codierter, erster Startzug.
			}
			else
			{
				ersterZugWeiss(); // Hard codierter, erster Startzug.
			}
			this.ersterZug = false; // Der erste Zug ist beendet.
		}
		else
		{
			if(CompSpieler.Wartungsmodus())
			{
				System.out.println("SchlagMoeglich() == " + SchlagMoeglich(this.gibFeld(), this.binIchSchwarz) + ", ZugMoeglich() == " + ZugMoeglich(this.gibFeld())); 
			}
			if(SchlagMoeglich(this.gibFeld(), this.binIchSchwarz))
			{
				this.waehleSpruenge(); // Marvin, hättest du mal die möglichen Sprünge für mich?
			}
			else if(ZugMoeglich(this.gibFeld()))
			{
				this.waehleZuege(); // Eddy, schalt mal auf unendlichen Unwahrscheinlichkeitsantrieb. Wo kommen wir nun hin?
			}
			else
			{
				this.aufgeben(); // Kein Zug oder Sprung möglich, also Aufgabe signalisieren. 
			}					
		}
		
		return bew_zuege;
	}
	
		
	/**
	 * Setze ersten Zug für schwarz. Wir müssen ja nicht alle 
	 * unnötigen Felder betrachten.
	 * 
	 * Spielfeldcodierung:
	 * XX|00|XX|01|XX|02|XX|03
	 * 04|XX|05|XX|06|XX|07|XX
	 * XX|08|XX|09|XX|10|XX|11
	 * 12|XX|13|XX|14|XX|15|XX
	 * XX|16|XX|17|XX|18|XX|19
	 * 20|XX|21|XX|22|XX|23|XX
	 * XX|24|XX|25|XX|26|XX|27
	 * 28|XX|29|XX|30|XX|31|XX
	 */
	private void ersterZugSchwarz()
	{
		testZuege(0, 5); // Züge von Position 20.
		testZuege(2, 5); // Züge von Position 21.
		testZuege(4, 5); // Züge von Position 22.
		testZuege(6, 5); // Züge von Position 23.
	}
	
		
	/**
	 * Setze ersten Zug für weiß. Wir müssen ja nicht alle 
	 * unnötigen Felder betrachten.
	 * 
	 * Spielfeldcodierung:
	 * XX|00|XX|01|XX|02|XX|03
	 * 04|XX|05|XX|06|XX|07|XX
	 * XX|08|XX|09|XX|10|XX|11
	 * 12|XX|13|XX|14|XX|15|XX
	 * XX|16|XX|17|XX|18|XX|19
	 * 20|XX|21|XX|22|XX|23|XX
	 * XX|24|XX|25|XX|26|XX|27
	 * 28|XX|29|XX|30|XX|31|XX
	 */
	private void ersterZugWeiss()
	{
		testZuege(1, 2); // Züge von Position 8.
		testZuege(3, 2); // Züge von Position 9.
		testZuege(5, 2); // Züge von Position 10.
		testZuege(7, 2); // Züge von Position 11.
	}
	
		
	/**
	 * &Uuml;berprüft die Durchf&uuml;hrbarkeit eines Schlages.
	 * @param x Alte X-Position.
	 * @param y Alte Y-Position.
	 * @param X Neue X-Position.
	 * @param Y Neue Y-Position.
	 * @param spielerSchwarz Ist der Spieler schwarz?
	 * @param tempFeld Eine Arbeitskopie des Spielfeldes.
	 * @return true, wenn der Sprung im Spielfeld liegt und durchführbar ist.
	 */
	private boolean istMoeglichSchlag(int x, int y, int X, int Y, boolean spielerSchwarz, int[][] tempFeld)
	{
		if(((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7) && (X >= 0) && (X <= 7) && (Y >= 0) && (Y <= 7)) && // Koordinaten liegen innerhalb des Spielfeldes.
				(tempFeld[x][y] != 0) &&																	 // Sprungstart ist besetzt.
				(tempFeld[X][Y] == 0) &&															 		 // Sprungziel ist frei.
				(((tempFeld[(x + X) / 2][(y + Y) / 2] < 0) && spielerSchwarz) || 					 		 // Schwarzer Spieler will weißen Stein überspringen.
				((tempFeld[(x + X) / 2][(y + Y) / 2] > 0) && !spielerSchwarz)))  					 		 // Weißer Spieler will schwarzen Stein überspringen.
		{
			return true;
		}
		else
		{
			return false; // Es ist wohl doch kein Schlag möglich...
		}
	}
	
		
	/**
	 * Kopiert das aktuelle Spielfeld.
	 * @return Das kopierte Spielfeld.
	 */
	protected int[][] kloneFeld()
	{
		int[][] geklonetesFeld = new int[8][8];
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{	
				geklonetesFeld[x][y] = this.gibFeldPosition(x, y);
			}
		}
		return geklonetesFeld;
	}
	
		
	/**
	 * Kopiert ein Spielfeld.
	 * @param feld Das zu kopierende Spielfeld.
	 * @return Das kopierte Spielfeld.
	 */
	protected static int[][] kloneFeld(int[][] feld)
	{
		int[][] geklonetesFeld = new int[8][8];
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{	
				geklonetesFeld[x][y] = feld[x][y];
			}
		}
		return geklonetesFeld;
	}
	
		
	/**
	 * Diese Methode wandelt ein Feld in einen Int-Statuswert um.
	 * -2: weiße Dame
	 * -1: weißer Stein/Bauer
	 *  0: Feld frei
	 *  1: schwarzer Stein/Bauer
	 *  2: schwarze Dame
	 * @param position Das umzuwandelnde Feld.
	 * @return Den Integer-Code.
	 * @throws NoBlackFieldException
	 */
	private static int leseFeld(Feld position) throws NoBlackFieldException
	{
		if(!position.is_schwFeld()) // Hier arbeiten wir nur mit schwarzen Feldern,
		{							// ansonsten läuft hier etwas falsch.
			throw new NoBlackFieldException("Kein schwarzes Feld betreten.");
		}
		
		try
		{
			if(position.is_Besetzt()) // Leer oder besetzt?
			{
				if(position.is_schwStein()) // Schwarz oder weiß? 
				{
					if(position.is_Dame()) // Dame oder nicht?
					{
						return 2; // Schwarze Dame.
					}
					else
					{
						return 1; // Schwarzer Stein/Bauer.
					}
				}
				else
				{
					if(position.is_Dame()) // Dame oder nicht?
					{
						return -2; // Weiße Dame.
					}
					else
					{
						return -1; // Weißer Stein/Bauer.
					}
				}
			}
			else
			{
				return 0; // Leeres Feld.
			}
		}
		catch(EmptyFieldException ef) // Unsere Felder sind normalerweise generell gültig, also
		{							  // sollte dies soweit niemals eintreten.
			return -666; // Dieser Abschnitt sollte niemals erreicht werden.
		}
	}
	
		
	/**
	 * Wandelt mit der leseFeld-Methode die Felder in
	 * Integer-Statuswerde geparsed. 
	 * @return Ein für die KI aufbereitetes Spielfeld als int[].
	 */
	protected static int[][] parseFeld(Feld[][] Feld)
	{
		int[][] geparsetesFeld = new int[8][8]; // Dummy-Feld anlegen.
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				if(Feld[x][y].is_schwFeld())
				{
					try
					{
						geparsetesFeld[x][y] = leseFeld(Feld[y][x]);
					}
					catch(NoBlackFieldException nbf)
					{
						// Diese Position sollte niemals erreicht werden.
					}
				}
				else
				{
					geparsetesFeld[x][y] = 0; // Kann ja nur leer sein... ;)
				}
			}
		}
		
		if(CompSpieler.Wartungsmodus())
		{
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					System.out.print("| ");
					if(geparsetesFeld[j][i] >= 0)
					{
						System.out.print("+");
					}
					System.out.print(geparsetesFeld[j][i] + "\t");
					if(j == 7)
					{
						System.out.print("|\n");
					}
				}
			}
		}
		return geparsetesFeld; // Mach's gut und danke für den Fisch!
	}
	
	
	/**
	 * Pr&uuml;ft, ob ein Schlag m&ouml;glich ist.
	 * @param Spielfeld Das Spielfeld.
	 * @param binIchSchwarz Spiele ich Schwarz?
	 * @return true, wenn ein Schlag möglich ist.
	 */
	protected static boolean SchlagMoeglich(int[][] Spielfeld, boolean binIchSchwarz)
	{
		boolean schlagMoeglich = false;
		
		for(int y = 0; !schlagMoeglich && (y < 8); y++)
		{
			for(int x = 0; !schlagMoeglich && (x < 4); x++) // Prüfe nur die relevanten Felder!
			{
				int x_pos = berechneXPos(x, y); // Bestimmen der richtigen x-Position. 
				
				if(CompSpieler.Wartungsmodus())
				{
					System.out.println("getBoardPosition(" + x_pos + ", " + y + ") == " + Spielfeld[x_pos][y]);
				}
				
				if((Spielfeld[x_pos][y] != 0) && 							 // Feld ist nicht leer.
						((Spielfeld[x_pos][y] > 0) && binIchSchwarz) || 	 // Schwarzer Spieler und schwarzer Stein.
						((Spielfeld[x_pos][y] < 0) && !binIchSchwarz))  	 // Weißer Spieler und weißer Stein.
				{
					schlagMoeglich = SchlagMoeglich(x_pos, y, Spielfeld, binIchSchwarz);
				}
			}
		}
		return schlagMoeglich;
	}
	
		
	/**
	 * Berechnet, ob von Position (x, y) ein Sprung m&ouml;glich ist.
	 * @param x X-Koordinate auf dem Spielfeld.
	 * @param y X-Koordinate auf dem Spielfeld.
	 * @param Spielfeld Das Spielfeld.
	 * @param binIchSchwarz Spiele ich Schwarz?
	 * @return true, wenn ein Sprung möglich ist.
	 */
	protected static boolean SchlagMoeglich(int x, int y, int[][] Spielfeld, boolean binIchSchwarz)
	{
		int stein = Spielfeld[x][y]; // Liest den Spielsteintyp aus.
		
		if(CompSpieler.Wartungsmodus())
		{
			System.out.println("Stein: " + stein + ", Schwarz zieht weiß: " + ((stein < 0) && binIchSchwarz) + ", Weiß zieht schwarz: " + ((stein > 0) && !binIchSchwarz));
		}
		
		if((stein == 0) || ((stein < 0) && binIchSchwarz) || ((stein > 0) && !binIchSchwarz))
		{ // Spieler ist entweder nicht dran oder Spielfeld ist leer...
			return false;
		}
		else
		{
			switch(stein)
			{
				case -2: // Weiße Dame.
				{
					boolean schlagLO = SchlagMoeglich(x, y, x - 2, y - 2, Spielfeld); // Schlag nach links oben.
					boolean schlagRO = SchlagMoeglich(x, y, x + 2, y - 2, Spielfeld); // Schlag nach rechts oben.
					boolean schlagLU = SchlagMoeglich(x, y, x - 2, y + 2, Spielfeld); // Schlag nach links unten.
					boolean schlagRU = SchlagMoeglich(x, y, x + 2, y + 2, Spielfeld); // Schlag nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("SchlagMoeglich? " + (schlagLO || schlagLU || schlagRO || schlagRU) + "(weiße Dame)");
					}
					
					return (schlagLO || schlagLU || schlagRO || schlagRU); // Ist wenigstens eine der Optionen möglich?
				}
			
				case -1: // Weißer Bauer.
				{
					boolean schlagLU = SchlagMoeglich(x, y, x - 2, y + 2, Spielfeld); // Schlag nach links unten.
					boolean schlagRU = SchlagMoeglich(x, y, x + 2, y + 2, Spielfeld); // Schlag nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("SchlagMoeglich? " + (schlagLU || schlagRU) + " (weißer Bauer)");
					}
					
					return (schlagLU || schlagRU); // Ist wenigstens eine der Optionen möglich?
				}
			
				case 1: // Schwarzer Bauer.
				{
					boolean schlagLO = SchlagMoeglich(x, y, x - 2, y - 2, Spielfeld); // Schlag nach links oben.
					boolean schlagRO = SchlagMoeglich(x, y, x + 2, y - 2, Spielfeld); // Schlag nach rechts oben.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("SchlagMoeglich? " + (schlagLO || schlagRO) + " (schwarzer Bauer)");
					}
					
					return (schlagLO || schlagRO); // Ist wenigstens eine der Optionen möglich?
				}
			
				case 2: // Schwarze Dame.
				{
					boolean schlagLO = SchlagMoeglich(x, y, x - 2, y - 2, Spielfeld); // Schlag nach links oben.
					boolean schlagRO = SchlagMoeglich(x, y, x + 2, y - 2, Spielfeld); // Schlag nach rechts oben.
					boolean schlagLU = SchlagMoeglich(x, y, x - 2, y + 2, Spielfeld); // Schlag nach links unten.
					boolean schlagRU = SchlagMoeglich(x, y, x + 2, y + 2, Spielfeld); // Schlag nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("SchlagMoeglich? " + (schlagLO || schlagLU || schlagRO || schlagRU) + " (schwarze Dame)");
					}
					
					return (schlagLO || schlagLU || schlagRO || schlagRU); // Ist wenigstens eine der Optionen möglich?
				}
			
				default:
				{
					return false; //Wenn es bis hierhin durchläuft, konnte niemand springen.
				}
			}
		}
	}	
	
	/**
	 * Prüft, ob ein Sprung von (x, y) nach (X, Y) zul&auml;ssig ist.
	 * @param x X-Koordinate der Startposition.
	 * @param y Y-Koordinate der Startposition.
	 * @param X X-Koordinate der Zielposition.
	 * @param Y Y-Koordinate der Zielposition.
	 * @param Spielfeld Das Spielfeld.
	 * @return true, wenn der Sprung zulässig ist.
	 */
	protected static boolean SchlagMoeglich(int x, int y, int X, int Y, int[][] Spielfeld)
	{
		if(((x >= 0) && (X >= 0) && (y >= 0) && (Y >= 0) && (x <= 7) && (X <= 7) && (y <= 7) && (Y <= 7)) &&         // Felder sind im Spielfeld.
				(Spielfeld[X][Y] == 0) &&														         			 // Sprungziel ist noch frei.
				(((Spielfeld[x][y] < 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] > 0)) ||   			 			 // Weißer Spieler will schwarzen Stein überspringen.
				((Spielfeld[x][y] > 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] < 0))))     			 			 // Schwarzer Spieler will weißen Stein überspringen.
		{
			if(CompSpieler.Wartungsmodus())
			{
				System.out.println("Im Feld? " + ((x >= 0) && (X >= 0) && (y >= 0) && (Y >= 0) && (x <= 7) && (X <= 7) && (y <= 7) && (Y <= 7)) + ", Sprungziel leer? " + (Spielfeld[X][Y] == 0) + ", weiß überspringt schwarz? " + ((Spielfeld[x][y] < 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] > 0)) + ", schwarz überspringt weiß? " + ((Spielfeld[x][y] > 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] < 0)) + ", gesamt: " + (((x >= 0) && (X >= 0) && (y >= 0) && (Y >= 0) && (x <= 7) && (X <= 7) && (y <= 7) && (Y <= 7)) && (Spielfeld[X][Y] == 0) && (((Spielfeld[x][y] < 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] > 0)) || ((Spielfeld[x][y] > 0) && (Spielfeld[((x + X) / 2)][((y + Y) / 2)] < 0)))));
			}
			
			return true; // Somit müssten alle Sprungbedingungen erfüllt sein...
		}
		else // Ansonsten kann es schon nichts mehr werden... ;)
		{
			return false;
		}
	}
		

	/**
	 * Wandelt einen String in ein Array von Zugkoordinaten um, welches in 
	 * eine Instanz von KIZuege eingebettet wird und diese wiederum der 
	 * Zugliste hinzugefügt.
	 * @param SchlagString Der als String codierte Übergabecode.
	 */
	private void SchlagStringZuZug(String SchlagString)
	{
		char[] chars = SchlagString.toCharArray(); // String in Char-Array umwandeln.
		int charlaenge = chars.length;
		
		ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate>();
		
		for(int i = 0; i < charlaenge; i += 2)
		{
			Zugkoordinate zk = new Zugkoordinate(Integer.parseInt(Character.toString(chars[i])), 
											Integer.parseInt(Character.toString(chars[i + 1])));
			zug.add(zk);
		}
		bew_zuege.add(new KIZuege(zug, this.Rechentiefe, this.Offensivgrad)); // Sprung zur Zugliste hinzufügen.
	}
	
	/**
	 * Führt die Feldbearbeitung für einen Sprung an unserem 
	 * modifizierten Spielfeld aus.
	 * @param x Alte X-Koordinate.
	 * @param y Alte Y-Koordinate.
	 * @param X Neue X-Koordinate.
	 * @param Y Neue Y-Koordinate.
	 * @param tempFeld Das temporäre Spielfeld.
	 * @return Das temporäre Spielfeld mit durchgeführten Sprung.
	 */
	protected static int[][] springe(int x, int y, int X, int Y, int[][] tempFeld)
	{
		int[][] retFeld = KI.kloneFeld(tempFeld);	// Arbeitskopie anlegen.
		retFeld[X][Y] = retFeld[x][y]; 		 		// Setze Stein auf neue Position
		retFeld[(x + X) / 2][(y + Y) / 2] = 0; 		// Entferne übersprungenen Stein.
		retFeld[x][y] = 0; 					 		// Entferne Stein von Alter Zugposition
		
		return retFeld;						 		// Schicke das modifizierte Spielfeld zurück.
	}
	
	/**
	 * Diese Methode startet die Zugprüfung für die 
	 * jeweiligen Felder.
	 * @param x Die X-Position.
	 * @param y Die Y-Position.
	 */
	private void testZuege(int x, int y)
	{
		Zugkoordinate start = new Zugkoordinate(x, y); // Erstelle Startkoordinate.
		
		int stein = this.gibFeldPosition(x, y); 		// Lese den Stein auf dem Feld ein.
		
		switch(stein) // Was steht denn auf unserem Feld?
		{
			case -2: // weiße Dame.
			{
				testeZug(start, x - 1, y - 1); // Zug nach links oben.
				testeZug(start, x + 1, y - 1); // Zug nach rechts oben.
				testeZug(start, x - 1, y + 1); // Zug nach links unten.
				testeZug(start, x + 1, y + 1); // Zug nach rechts unten.
				
				break;
			}
			
			case -1: // weißer Bauer.
			{
				testeZug(start, x - 1, y + 1); // Zug nach links unten.
				testeZug(start, x + 1, y + 1); // Zug nach rechts unten.
				
				break;
			}
			
			case 2: // schwarze Dame.
			{
				testeZug(start, x - 1, y - 1); // Zug nach links oben.
				testeZug(start, x + 1, y - 1); // Zug nach rechts oben.
				testeZug(start, x - 1, y + 1); // Zug nach links unten.
				testeZug(start, x + 1, y + 1); // Zug nach rechts unten.
				
				break;
			}
			
			case 1: // schwarzer Bauer.
			{
				testeZug(start, x - 1, y - 1); // Zug nach links oben.
				testeZug(start, x + 1, y - 1); // Zug nach rechts oben.
				
				break;
			}
			
			default: // Dann kann das Feld nur noch leer sein.
			{
				break;
			}
		}
	}
	
	/**
	 * Diese Methode prüft den Zug und, falls er realisierbar 
	 * ist, fügt sie ihn zu der Zugliste hinzu.
	 * @param start Die Startposition.
	 * @param X Die X-Koordinate des Zielfeldes.
	 * @param Y Die Y-Koordinate des Zielfeldes.
	 */
	private void testeZug(Zugkoordinate start, int X, int Y)
	{
		if((X >= 0) && (X <= 7) && (Y >= 0) && (Y <= 7)) //Zugziel liegt im Feld.
		{
			if(this.gibFeldPosition(X, Y) == 0) // Ist das Feld denn überhaupt noch frei?
			{
				ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate>(); 				// Zug anlegen.
				zug.add(start);												   				// Startposition speichern.
				zug.add(new Zugkoordinate(X, Y));							   				// Zielposition speichern.
				bew_zuege.add(new KIZuege(zug, this.Rechentiefe, this.Offensivgrad)); 	   	// Bewerteten Zug einreichen.
				
				if(CompSpieler.Wartungsmodus())
				{
					System.out.println("testeZug(" + start.getX() + ", " + start.getY() + ", " + X + ", " + Y + ") wurde erfolgreich der Zugliste hinzugefügt.");
				}
			}
		}
	}
	
	/**
	 * Startet den Auswahlprozess der möglichen Sprünge.
	 */
	private void waehleSpruenge()
	{
		for(int x = 0; x < 4; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				int x_pos = berechneXPos(x, y); // Bestimmen der richtigen x-Position.
				
				if((((this.gibFeldPosition(x_pos, y) > 0) && this.binIchSchwarz) ||			// Spieler ist Schwarz und nutzt schwarzen Stein.
						((this.gibFeldPosition(x_pos, y) < 0) && !this.binIchSchwarz)) &&	// Spieler ist weiß und nutzt weißen Stein.
						SchlagMoeglich(x_pos, y, this.gibFeld(), this.binIchSchwarz))		// Schlag von der Position ist möglich.
				{
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("berechneSchlag(" + x_pos + ", " + y + ", " + this.binIchSchwarz + " (Farbe == Schwarz), " + ((this.gibFeldPosition(x_pos, y) == 2) || (this.gibFeldPosition(x_pos, y) == -2)) + " (Dame?))");
					}
					
					berechneSchlag(x_pos, y, this.binIchSchwarz, ((this.gibFeldPosition(x_pos, y) == 2) || (this.gibFeldPosition(x_pos, y) == -2))); // Einmal Rupert und zurück...
				}
			}
		}
	}
	
	/**
	 * Diese Methode startet die Suche nach g&uuml;ltigen Bewegungen.
	 */
	private void waehleZuege()
	{
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 4; x++)
			{
				int x_pos = berechneXPos(x, y); // Bestimmen der richtigen x-Position.
				
				if(((this.gibFeldPosition(x_pos, y)) > 0 && this.binIchSchwarz) ||     // Ist es eine schwarze Figur und ich schwarz?
						((this.gibFeldPosition(x_pos, y)) < 0 && !this.binIchSchwarz)) // Ist es eine weiße Figur und ich weiß?
				{
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("testZuege(" + x_pos + ", " + y + ")");
					}
					
					this.testZuege(x_pos, y); // Prüfe Züge und setze wenn möglich.
				}
				else // Ohne zu den Figuren passenden Spieler brauchst du auch nicht den Zug zu versuchen.
				{
					continue;
				}
			}
		}
	}
	

	
	/**
	 * Führt die Feldbearbeitung für einen Zug an unserem 
	 * modifizierten Spielfeld aus.
	 * @param x Alte X-Koordinate.
	 * @param y Alte Y-Koordinate.
	 * @param X Neue X-Koordinate.
	 * @param Y Neue Y-Koordinate.
	 * @param tempFeld Das temporäre Spielfeld.
	 * @return Das temporäre Spielfeld mit durchgeführten Zug.
	 */
	protected static int[][] ziehe(int x, int y, int X, int Y, int[][] tempFeld)
	{
		int[][] retFeld = KI.kloneFeld(tempFeld);	// Arbeitskopie anlegen.
		retFeld[X][Y] = retFeld[x][y]; 		 		// Setze Stein auf neue Position
		retFeld[x][y] = 0; 					 		// Entferne Stein von Alter Zugposition
		
		return retFeld;						 		// Schicke das modifizierte Spielfeld zurück.
	}
	
	/**
	 * Prüft, ob eine Bewegung möglich ist.
	 * @param Spielfeld Das Spielfeld.
	 * @return true, wenn Bewegung möglich ist.
	 */
	protected static boolean ZugMoeglich(int[][] Spielfeld)
	{
		boolean zugMoeglich = false;
		
		for(int y = 0; !zugMoeglich && (y < 8); y++)
		{
			for(int x = 0; !zugMoeglich && (x < 4); x++) 	// Prüfe nur die relevanten Felder!
			{
				int x_pos = berechneXPos(x, y); 			// Bestimmen der richtigen x-Position. 
				
				if(!(Spielfeld[x_pos][y] == 0)) 			// Feld ist besetzt.
				{
					zugMoeglich = ZugMoeglich(x_pos, y, Spielfeld);
				}
			}
		}
		
		return zugMoeglich;
	}
	
	/**
	 * Prüft, ob eine Bewegung des Steins von (x, y) aus möglich ist.
	 * @param x Die X-Koordinate.
	 * @param y Die Y-Koordinate.
	 * @param Spielfeld Das Spielfeld.
	 * @return true, wenn eine Bewegung möglich ist.
	 */
	protected static boolean ZugMoeglich(int x, int y, int[][] Spielfeld)
	{
		if((x >= 0) && (x <= 7) && (y >= 0) && (y <=7)) // Position ist auf dem Spielfeld.
		{
			int stein = Spielfeld[x][y]; // Art des Steins auslesen.
			
			switch(stein)
			{
				case 2: // schwarze Dame.
				{
					boolean zugLO = ZugMoeglich(x, y, x - 1, y - 1, Spielfeld); // Zug nach links oben.
					boolean zugLU = ZugMoeglich(x, y, x - 1, y + 1, Spielfeld); // Zug nach links unten.
					boolean zugRO = ZugMoeglich(x, y, x + 1, y - 1, Spielfeld); // Zug nach rechts oben.
					boolean zugRU = ZugMoeglich(x, y, x + 1, y + 1, Spielfeld); // Zug nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("ZugMoeglich(" + x + ", " + y + ") = " + (zugLO || zugLU || zugRO || zugRU) + " (schwarze Dame)");
					}
					
					return (zugLO || zugLU || zugRO || zugRU); // Geht einer der Züge?
				}
			
				case 1: // schwarzer Bauer.
				{
					boolean zugLO = ZugMoeglich(x, y, x - 1, y - 1, Spielfeld); // Zug nach links oben.
					boolean zugRO = ZugMoeglich(x, y, x + 1, y - 1, Spielfeld); // Zug nach rechts oben.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("ZugMoeglich(" + x + ", " + y + ") = " + (zugLO || zugRO) + " (schwarze Dame)");
					}
					
					return (zugLO || zugRO); // Geht einer der Züge?
				}
			
				case -1: // weißer Bauer.
				{
					boolean zugLU = ZugMoeglich(x, y, x - 1, y + 1, Spielfeld); // Zug nach links unten.
					boolean zugRU = ZugMoeglich(x, y, x + 1, y + 1, Spielfeld); // Zug nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("ZugMoeglich(" + x + ", " + y + ") = " + (zugLU || zugRU) + " (schwarze Dame)");
					}
					
					return (zugLU || zugRU); // Geht einer der Züge?
				}
			
				case -2: // weiße Dame.
				{
					boolean zugLO = ZugMoeglich(x, y, x - 1, y - 1, Spielfeld); // Zug nach links oben.
					boolean zugLU = ZugMoeglich(x, y, x - 1, y + 1, Spielfeld); // Zug nach links unten.
					boolean zugRO = ZugMoeglich(x, y, x + 1, y - 1, Spielfeld); // Zug nach rechts oben.
					boolean zugRU = ZugMoeglich(x, y, x + 1, y + 1, Spielfeld); // Zug nach rechts unten.
					
					if(CompSpieler.Wartungsmodus())
					{
						System.out.println("ZugMoeglich(" + x + ", " + y + ") = " + (zugLO || zugLU || zugRO || zugRU) + " (schwarze Dame)");
					}
					
					return (zugLO || zugLU || zugRO || zugRU); // Geht einer der Züge?
				}
			
				default: // Es könnte ja hier nur noch ein leeres Feld sein.
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Prüft, ob ein Zug von (x, y) nach (X, Y) gültig ist.
	 * @param x X-Koordinate vom Startpunkt.
	 * @param y Y-Koordinate vom Startpunkt.
	 * @param X X-Koordinate vom Zielpunkt.
	 * @param Y Y-Koordinate vom Zielpunkt.
	 * @param Spielfeld Das Spielfeld.
	 * @return true, wenn Zug gültig ist.
	 */
	protected static boolean ZugMoeglich(int x, int y, int X, int Y, int[][] Spielfeld)
	{
		if(((x >= 0) && (X >= 0) && (y >= 0) && (Y >= 0) && (x <= 7) && (X <= 7) && (y <= 7) && (Y <= 7)) &&	// Sind die Koordinaten auf dem Feld?
				(Spielfeld[x][y] != 0) &&																		// Ist das Ausgangsfeld besetzt?
				(Spielfeld[X][Y] == 0))																			// Ist das Zielfeld besetzt?
		{
			return true;
		}
		else // Passt wohl nicht...
		{
			return false;
		}
	}
}
