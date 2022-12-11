/**
 * Allgemeines Programmierpraktikum Sommersemester 2008
 * Georg-August-Universitaet Goettingen (Dr. H. Brosenne)
 * Projektarbeit zum Thema DamePP
 * 
 * Gruppe: DamePP-2AD
 * Teamleitung: Christian Otto
 * Projektbetreuung: David-Alexandre Guiraud
 * 
 * Entwickler:
 * Kathrin Asshauer, Christian Beulke, Franziska Klingner,
 * Stefanie Muehlhausen, Christian Otto, Martin Schewe,
 * Sven Withus
 */

package damepp;

import java.util.ArrayList;

/**
 * Diese Klasse implementiert die von der KI verwendeten Move-Items.
 * @author Christian Otto
 */
public class KIZuege
{
/*======Variablen============================================================*/
	
	/** Die Zugbewertung. */
	private double bewertung = 1.0;

	/** Die durchschnittlichen geschlagenen Gegnersteine. */
	private double gewinne = 0.0;

	/** Die durchschnittlichen eigenen, verlorenen Steine. */
	private double verluste = 0.0;

	/** Datenaustausch-Array */
	private double[] GewinneUndVerluste = {0.0, 0.0};

	/** Die Anzahl der Züge aus denen sich die Gewinn-Verlust-Rechnung zusammensetzt. */
	private int zuege = 0;

	/** Die Anzahl Züge, die die KI vorberechnet um den Gegenzug zu berechnen. Werte >= 1 erlaubt. */
	private int Rechentiefe;

	/** Wie stark werden übersprungene Steine zu verlorenen gewertet? */
	private double Offensivgrad;

	/** Die Zugliste. */
	private ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate>();
	
/*======Methoden=============================================================*/
	
/*------Konstruktoren--------------------------------------------------------*/

	/**
	 * Standard-Konstruktor.
	 * @param zug Der jeweilige Zug. 
	 * @param Rechentiefe Wie viele Züge wird in die Zukunft geschaut?
	 * @param Offensivgrad Wie offensiv soll der Computer spielen?
	 */
	KIZuege(ArrayList<Zugkoordinate> zug, int Rechentiefe, double Offensivgrad)
	{
		this.zug = zug;
		this.Rechentiefe = Rechentiefe;
		this.Offensivgrad = Offensivgrad;
		if(CompSpieler.Wartungsmodus())
		{
			System.out.print("Zug: ");
			for(int i = 0; i < zug.size(); i++)
			{
				System.out.print("(" + (zug.get(i).getX() + 1) + ", " + (zug.get(i).getY() + 1) + ")");
				
				if(i < (zug.size() - 1))
				{
					System.out.print(" -> ");
				}
				else
				{
					System.out.print("\n");
				}
			}
		}
		if(this.zug.size() > 0)
		{
			this.bewerteZug();
		}
	}

/*------Getter---------------------------------------------------------------*/

	/**
	 * Liefert die Zugbewertung zur&uuml;ck.
	 * @return Die Zugbewertung.
	 */
	public double Bewertung()
	{
		return this.bewertung;
	}

	/**
	 * Liefert die Zahl der geschlagenen Steine zur&uuml;ck.
	 * @return Die Zahl der geschlagenen Steine.
	 */
	public double Gewinne()
	{
		return this.gewinne;
	}

	/**
	 * Liefert die Zahl der eigenen Verluste zur&uuml;ck.
	 * @return Die Zahl der eigenen Verluste.
	 */
	public double Verluste()
	{
		return this.verluste;
	}
	
	/**
	 * Die Methode, die die jeweils gew&auml;hlte Zugliste zur&uuml;ckliefert.
	 * @return Der passende Zug.
	 */
	public ArrayList<Zugkoordinate> Zug()
	{
		return this.zug;
	}
	
	/**
	 * Gibt die Anzahl der Z&uuml;ge zur&uuml;ck, die bislang eingegangen sind.
	 * @return Die Anzahl der Zuege.
	 */
	protected int Zuege()
	{
		return this.zuege;
	}

/*------Setter---------------------------------------------------------------*/

	/**
	 * Setzt einen neuen Wert f&uuml;r Z&uuml;ge.
	 * @param zuganzahl Die Anzahl der berechneten Züge.
	 */
	protected void Zuege(int zuganzahl)
	{
		this.zuege = zuganzahl;
	}
	
/*------Funktionalitaet------------------------------------------------------*/
	
	/**
	 * Berechnet die Optimale Zahl der Verluste im n&auml;chsten Zug und die
	 * Zahl der &uuml;bersprungenen Steine in diesem Zug.
	 */
	private void berechneGewinneUndVerluste()
	{
		double Gewinne = 0.0, Verluste = 0.0; 			// Rechenvariablen anlegen.
		int[][] feld = KI.gibstatischesFeld();
		
		if(!((((this.zug.get(0).getX() - this.zug.get(1).getX()) == 1) ||		// X_alt = (X_neu + 1) => Bewegung um ein Feld nach rechts.
				((this.zug.get(0).getX() - this.zug.get(1).getX()) == -1)) &&	// X_alt = (X_neu - 1) => Bewegung um ein Feld nach links.
				(((this.zug.get(0).getY() - this.zug.get(1).getY()) == 1) ||	// Y_alt = (Y_neu + 1) => Bewegung um ein Feld nach unten.
				((this.zug.get(0).getY() - this.zug.get(1).getY()) == -1))))	// Y_alt = (Y_neu - 1) => Bewegung um ein Feld nach oben.
		{																		// Damit insgesamt ein Zug und KEIN Sprung! Negiert: Wir haben einen Sprung!
			if(CompSpieler.Wartungsmodus())
			{
				System.out.println("Setze Gewinne auf: " + (this.zug.size() - 1));
			}
			
			Gewinne = this.zug.size() - 1; 										// Anzahl der Koordinaten = Startkoordinate jedes Teilsprungs + Endkoordinate => (zug.size() - 1) Steine übersprungen.
			
			for(int i = 0; i < zug.size() - 1; i++) 							// Führe Sprünge auf dem Spielfeld aus.
			{
				feld = KI.springe(zug.get(i).getX(), zug.get(i).getY(), zug.get(i + 1).getX(), zug.get(i + 1).getY(), feld);
			}
		}
		else
		{
			feld = KI.ziehe(zug.get(0).getX(), zug.get(0).getY(), zug.get(1).getX(), zug.get(1).getY(), feld); // Führe Zug durch.
		}
		
		this.berechneGewinneUndVerluste(Spielfeld.getZugSchwarz(), !Spielfeld.getZugSchwarz(), Gewinne, Verluste, ((Rechentiefe * 2) - 1), feld, ((this.zug.get(this.zug.size() - 1).getY() == 0) || (this.zug.get(this.zug.size() - 1).getY() == 7))); // Berechne die Gewinne und Verluste für die nächsten max. 3 Züge.
		
		this.gewinne = GewinneUndVerluste[0];	// Setze Gewinne-Variable.
		this.verluste = GewinneUndVerluste[1];	// Setze Verluste-Variable.
	}
	
	/**
	 * Berechnet die anzahl der eigenen und Fremden Verluste bis
	 * zu einer Tiefe von (Suchtiefe / 2) Z&uuml;gen. (Ben&ouml;tigt jedoch
	 * die eine oder andere KI-Methode.)
	 * @param farbeIstSchwarz Spielen wir schwarz?
	 * @param aktIstSchwarz Ist dies gerade der Zug von Schwarz?
	 * @param Gewinne Wieviele Steine haben wir bislang geschlagen?
	 * @param Verluste Wieviele Steine haben wir bislang verloren?
	 * @param Suchtiefe Wie tief sollen wir noch suchen?
	 * @param Spielfeld Das temporäre Spielfeld.
	 * @param wurdeDame Ist gerade eine Figur zur Dame geworden?
	 * @return Die durchschnittlichen Gewinne und Verluste.
	 */
	private void berechneGewinneUndVerluste(boolean farbeIstSchwarz, boolean aktIstSchwarz, double Gewinne, double Verluste, int Suchtiefe, int[][]Spielfeld, boolean wurdeDame)
	{
		double[] GewinneUndVerluste = {Gewinne, Verluste}; // Ergebnis-Array mit Werten füllen.
		
		if(Suchtiefe < 0) // Maximale Suchtiefe erreicht.
		{
			this.GewinneUndVerluste(GewinneUndVerluste);
			return;
		}
		
		long aktzeit = System.currentTimeMillis(); // Aktuelle Zeit bestimmen.
		long zeitdifferenz = aktzeit - KI.AktuelleZeit();
		
		if((KI.Zeitkonto() - zeitdifferenz) > 5500) // Mindestens noch 5,5 Sekunden für den nächsten Zug aufsparen.
		{
			if(wurdeDame) 					// Eine Figur wurde Dame.
			{
				this.setzeDamen(Spielfeld); // Passe Spielfeld entsprechend an.
			}
			
			if(KI.SchlagMoeglich(Spielfeld, aktIstSchwarz))
			{
				for(int y = 0; y < 8; y++) // Sprungprüfung für alle Spielfelder.
				{
					for(int x = 0; x < 4; x++)
					{
						int x_pos = KI.berechneXPos(x, y);
						
						if(KI.SchlagMoeglich(x_pos, y, Spielfeld, aktIstSchwarz)) // Von aktueller Position kann geschlagen werden.
						{
							this.Schlagbehandlung(x_pos, y, Spielfeld, farbeIstSchwarz, aktIstSchwarz, Gewinne, Verluste, Suchtiefe);
						}
					}
				}
			}
			else if(KI.ZugMoeglich(Spielfeld))
			{
				for(int y = 0; y < 8; y++) // Zugprüfung für alle Spielfelder.
				{
					for(int x = 0; x < 4; x++)
					{
						int x_pos = KI.berechneXPos(x, y);
						
						if(((aktIstSchwarz && (Spielfeld[x_pos][y] > 0)) ||										// Spieler Schwarz und schwarzer Stein.
								(!aktIstSchwarz && (Spielfeld[x_pos][y] < 0))) &&								// Weißer Spieler und weißer Stein.
								(!aktIstSchwarz || (aktIstSchwarz && Spielfeld[x_pos][y] == 2)) &&				// Weißer Zug oder schwarze Dame.
								KI.ZugMoeglich(x_pos, y, x_pos + 1, y + 1, Spielfeld))							// Der Zug kann ausgeführt werden.
						{																						// Zug nach rechts unten.
							this.berechneGewinneUndVerluste(farbeIstSchwarz, !aktIstSchwarz, Gewinne, Verluste, Suchtiefe -1, KI.ziehe(x_pos, y, x_pos + 1, y + 1, Spielfeld), (y + 1 == 7));
						}
						if(((aktIstSchwarz && (Spielfeld[x_pos][y] > 0)) ||										// Spieler Schwarz und schwarzer Stein.
								(!aktIstSchwarz && (Spielfeld[x_pos][y] < 0))) &&								// Weißer Spieler und weißer Stein.
								(!aktIstSchwarz || (aktIstSchwarz && Spielfeld[x_pos][y] == 2)) &&				// Weißer Zug oder schwarze Dame.
								KI.ZugMoeglich(x_pos, y, x_pos - 1, y + 1, Spielfeld))							// Der Zug kann ausgeführt werden.
						{																						// Zug nach links unten.
							this.berechneGewinneUndVerluste(farbeIstSchwarz, !aktIstSchwarz, Gewinne, Verluste, Suchtiefe -1, KI.ziehe(x_pos, y, x_pos - 1, y + 1, Spielfeld), (y + 1 == 7));
						}
						if(((aktIstSchwarz && (Spielfeld[x_pos][y] > 0)) ||										// Spieler Schwarz und schwarzer Stein.
								(!aktIstSchwarz && (Spielfeld[x_pos][y] < 0))) &&								// Weißer Spieler und weißer Stein.
								(aktIstSchwarz || (!aktIstSchwarz && Spielfeld[x_pos][y] == -2)) &&				// Weißer Zug oder schwarze Dame.
								KI.ZugMoeglich(x_pos, y, x_pos + 1, y - 1, Spielfeld))							// Der Zug kann ausgeführt werden.
						{																						// Zug nach rechts oben.
							this.berechneGewinneUndVerluste(farbeIstSchwarz, !aktIstSchwarz, Gewinne, Verluste, Suchtiefe -1, KI.ziehe(x_pos, y, x_pos + 1, y - 1, Spielfeld), (y - 1 == 0));
						}
						if(((aktIstSchwarz && (Spielfeld[x_pos][y] > 0)) ||										// Spieler Schwarz und schwarzer Stein.
								(!aktIstSchwarz && (Spielfeld[x_pos][y] < 0))) &&								// Weißer Spieler und weißer Stein.
								(aktIstSchwarz || (!aktIstSchwarz && Spielfeld[x_pos][y] == -2)) &&				// Weißer Zug oder schwarze Dame.
								KI.ZugMoeglich(x_pos, y, x_pos - 1, y - 1, Spielfeld))							// Der Zug kann ausgeführt werden.
						{																						// Zug nach links oben.
							this.berechneGewinneUndVerluste(farbeIstSchwarz, !aktIstSchwarz, Gewinne, Verluste, Suchtiefe -1, KI.ziehe(x_pos, y, x_pos - 1, y - 1, Spielfeld), (y - 1 == 0));
						}
					}
				}
			}
		}
		this.GewinneUndVerluste(GewinneUndVerluste);
	}
	

/*------Hilfsmethoden--------------------------------------------------------*/
	/**
	 * Diese Funktion berechnet die Punktbewertung des jeweiligen Zugs.
	 */
	private void bewerteZug()
	{
		this.berechneGewinneUndVerluste();													// Gewinne und Verluste berechnen.
		this.bewertung = (double) (this.gewinne + 1) / (double) (this.verluste + 1);		// Zugbewertung durchführen.
		this.bewertung *= this.Offensivgrad;												// Justiere, wie offensiv die KI spielt. (1.25 heißt Werte geschlagene Steine mit 125 %.)
	}
	
	/**
	 * Setzt Damen auf die Spielfelder, wenn sie gesetzt
	 * werden m&uuml;ssen.
	 * @param Spielfeld Das Spielfeld.
	 * @return Das korrigierte Spielfeld.
	 */
	private int[][] setzeDamen(int[][] Spielfeld)
	{
		for(int x = 0; x < 4; x++) // Schwarze Damen setzen.
		{
			int x_pos = KI.berechneXPos(x, 0);
			
			if(Spielfeld[x_pos][0] == 1)
			{
				Spielfeld[x_pos][0] = 2;
			}
		}
		for(int x = 0; x < 4; x++) // Weiße Damen setzen.
		{
			int x_pos = KI.berechneXPos(x, 7);
			
			if(Spielfeld[x_pos][7] == -1)
			{
				Spielfeld[x_pos][7] = -2;
			}
		}
		
		return Spielfeld;
	}
	

	
	/**
	 * Setzen des Wertes von GewinneUndVerluste als Array.
	 * @param GUV Das neue Array, dass GewinneUndVerluste angibt.
	 */
	private void GewinneUndVerluste(double[] GUV)
	{
		double Gewinne = this.GewinneUndVerluste[0] * this.zuege + GUV[0];	// Gewinne gewichtet berechnen.
		double Verluste = this.GewinneUndVerluste[1] * this.zuege + GUV[1];	// Verluste gewichtet berechnen.
		this.zuege++;														// Berechnung aus einem weiteren Zug.
		Gewinne /= this.zuege;												// Gewichtung zurück setzen.
		Verluste /= this.zuege;												// Gewichtung zurück setzen.
		this.GewinneUndVerluste[0] = Gewinne;								// Gewinne setzen.
		this.GewinneUndVerluste[1] = Verluste;								// Verluste setzen.
	}
	
	/**
	 * Rechnet Sprünge von der Position (x, y) aus.
	 * @param x Die X-Koordinate.
	 * @param y Die Y-Koordinate.
	 * @param Spielfeld Das Spielfeld.
	 * @param farbeIstSchwarz Bin ich Schwarz?
	 * @param aktIstSchwarz Ist schwarz gerade am Zug?
	 * @param Gewinne Wie viele Steine wurden von mir bereits übersprungen?
	 * @param Verluste Wir viele Steine habe ich bereits übersprungen?
	 * @param Suchtiefe Wie tief wurde gesucht?
	 */
	private void Schlagbehandlung(int x, int y, int[][] Spielfeld, boolean farbeIstSchwarz, boolean aktIstSchwarz, double Gewinne, double Verluste, int Suchtiefe)
	{
		if(KI.SchlagMoeglich(x, y, Spielfeld, aktIstSchwarz))
		{
			this.berechneGewinneUndVerluste(farbeIstSchwarz, !aktIstSchwarz, Gewinne, Verluste, Suchtiefe -1, Spielfeld, ((y == 0) || (y == 7)));
		}
		else
		{
			if(aktIstSchwarz == farbeIstSchwarz) 	// Ist das gerade unser Zug?
			{
				Gewinne = Gewinne + 1.0;			// Wenn ja, haben wir einen Stein übersprungen.
			}
			else
			{
				Verluste = Verluste + 1.0;			// Wenn nein, wurde einer unserer Steine übersprungen.
			}
			
			if((!(Spielfeld[x][y] == -1)) &&							// Weißer Bauer.
					KI.SchlagMoeglich(x, y, x - 2, y - 2, Spielfeld)) 	// Schlag nach links oben.
			{
				this.Schlagbehandlung(x - 2, y - 2, KI.springe(x, y, x - 2, y - 2, Spielfeld), farbeIstSchwarz, aktIstSchwarz, Gewinne, Verluste, Suchtiefe); // Können wir diesen Sprung noch weiter führen?
			}
			if((!(Spielfeld[x][y] == -1)) &&							// Weißer Bauer.
					KI.SchlagMoeglich(x, y, x + 2, y - 2, Spielfeld)) 	// Schlag nach rechts oben.
			{
				this.Schlagbehandlung(x + 2, y - 2, KI.springe(x, y, x + 2, y - 2, Spielfeld), farbeIstSchwarz, aktIstSchwarz, Gewinne, Verluste, Suchtiefe); // Können wir diesen Sprung noch weiter führen?
			}
			if((!(Spielfeld[x][y] == 1)) &&								// Schwarzer Bauer.
					KI.SchlagMoeglich(x, y, x - 2, y + 2, Spielfeld)) 	// Schlag nach links unten.
			{
				this.Schlagbehandlung(x - 2, y + 2, KI.springe(x, y, x - 2, y + 2, Spielfeld), farbeIstSchwarz, aktIstSchwarz, Gewinne, Verluste, Suchtiefe); // Können wir diesen Sprung noch weiter führen?
			}
			if((!(Spielfeld[x][y] == 1)) &&								// Schwarzer Bauer.
					KI.SchlagMoeglich(x, y, x + 2, y + 2, Spielfeld)) 	// Schlag nach rechts unten.
			{
				this.Schlagbehandlung(x + 2, y + 2, KI.springe(x, y, x + 2, y + 2, Spielfeld), farbeIstSchwarz, aktIstSchwarz, Gewinne, Verluste, Suchtiefe); // Können wir diesen Sprung noch weiter führen?
			}
		}
	}
	
}
