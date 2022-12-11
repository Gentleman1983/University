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

import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;


/**
 * Diese Klasse implementiert alle Methoden, um ein DamePP-2ad-Spiel
 * mit einem menschlichen Spieler zu bestreiten.
 *
 * @author Stefanie Muehlhausen
 * @author Christian Otto (Zeitkonto-Methoden)
 */
public class IntSpieler implements Spieler {

/*======Variablen============================================================*/

	/** Beinhaltet das Zeitkonto des menschlichen Spielers. Der Startwert ist
	 * 1,800,000 ms = 1,800 sek = 30 min.
	 */
	private long Zeitkonto = 1800000;	// Guthaben auf dem Zeitkonto mit Startwert von
						// 1.800.000 ms = 1.800 Sek = 30 Min.	

/*======Methoden=============================================================*/

/*------Getter---------------------------------------------------------------*/

	/**
	 * Methode zum Auslesen des aktuellen Zeitkontostandes.
	 * @return Der aktuelle Kontostand.
	 */
	public long holZeitkonto()
	{
		return this.Zeitkonto; // Liefere den aktuellen Kontostand zurück
	}

/*------Setter---------------------------------------------------------------*/

	/**
	 * Methode zum Setzen des Wertes des Zeitkontos.
	 * @param neu Der neue Kontostand.
	 * @throws NegativeTimeException
	 */
	public void setzeZeitkonto(long neu) throws NegativeTimeException
	{
		if(neu < 0)	// Sollte nie auftreten, da sobald die Zeit ab-
				// gelaufen ist, die Spielfeldverwaltung das
				// Spiel als Verloren werten sollte.
		{
			throw new NegativeTimeException();
		}
		else
		{
			this.Zeitkonto = neu; // Speichere den neuen Kontostand
		}
	}

/*------Funktionalitaet------------------------------------------------------*/

	/**
	 * Kommunikation des Programms mit dem menschlichen Spieler auf der
	 * Kommandozeile. Es werden die Koordinaten des zu bewegenden Steines
	 * und die Zielkoordinate abgefragt. Ggf. werden weitere Koordinaten
	 * abgefragt, falls ein Mehrfachsprung vorliegt. Sollte die Eingabe
	 * leer sein, so wird vor der endg&uuml;ltigen Aufgabe des Spielers
	 * noch einmal eine Best&auml;tigung von diesem gefordert.
	 *
	 * @see Spieler#holZug()
	 * 
	 * @return Liste mit Zugkoordinate-Objekten (von--ueber--nach)
	 */
	public ArrayList<Zugkoordinate> holZug() {
		ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate> ();
		Zugkoordinate zugK = new Zugkoordinate();
		String s;                  // Eingabe
		int x, y, wahl;            // x,y: Koordinate; wahl: Entscheidung des Spielers
		boolean b = false;         // soll ein weiterer Zug eingelesen werden?
		System.out.println("Eingabeformat: zwei ganze Zahlen (1-8),"
				+" durch 'ENTER' getrennt. "
				+"Aufgeben: nur 'ENTER' druecken.");
		
		System.out.print("Spalte: ");
		s = einlesen();
		
		// Eingabe war ENTER...
		if ( s.length() == 0) { 
			//... aus Versehen?
			 System.out.println("Wollen Sie wirklich aufgeben? \n1: Ja\n2: Nein");
			 wahl = Integer.parseInt(nichtAufgeben(einlesen()));
			 switch(wahl){
			 	case 1: {
			 		System.out.println("Das Spiel ist beendet.");
			 		return zug = null;
			 	}
			 	case 2: {
			 		System.out.println("Das Spiel geht weiter.");
			 		b = true;      //weiterer Zug wird eingelesen 
			 		break;
			 	}
				default: {
					System.out.println("Ungueltige Eingabe. Das Spiel geht weiter.");
					b = true;       //weiterer Zug wird eingelesen  
					break;
				}
			 }
			
			 // falls weitergespielt werden soll: 
			 // welcher Stein soll bewegt werden?
			 System.out.println("Bitte zwei ganze Zahlen eingeben:\nSpalte: ");
			 x = Integer.parseInt(nichtAufgeben(einlesen()));
		 	 zugK.setX(x - 1);

		 	 System.out.println("Zeile: ");
		 	 y = Integer.parseInt(nichtAufgeben(einlesen()));
		 	 zugK.setY(y - 1);

		 	 zug.add(zugK);
		}
		else {
			
			x = Integer.parseInt(s);
			zugK.setX(x - 1);
			
			System.out.print("Zeile: ");
			y = Integer.parseInt(nichtAufgeben(einlesen()));
			zugK.setY(y - 1);
			
			zug.add(zugK);
			
			b = true;
		}
		
		// liest beliebig viele Zuege ein
			while (b) {
				System.out.println("Wohin soll der Stein " + (zugK.getX() + 1) + (zugK.getY() + 1) +
						" bewegt werden? ");
				zugK = new Zugkoordinate(); 
				
				System.out.print("Spalte: ");
				x = Integer.parseInt(nichtAufgeben(einlesen()));
				
				zugK.setX(x - 1);
				
				System.out.print("Zeile: ");
				y = Integer.parseInt(nichtAufgeben(einlesen()));
				zugK.setY(y - 1);
				
				// anhaengen an ArrayList
				zug.add(zugK);
				
				System.out.println("Wollen Sie den Zug fortfuehren? \n1: Ja\n2: Nein");
				
				wahl = Integer.parseInt(nichtAufgeben(einlesen()));
				switch(wahl){
				case 1: {
					b = true;
					break;
				}
				case 2: {
					b = false;
					break;
				}
				default: {
					System.out.println("Ungueltige Eingabe. Der Zug ist beendet.");
					b = false;
					break;
				}
				}
				
			}

		System.out.println("Der Zug wird ueberprueft.");
		return zug;
	}

/*------Hilfsmethoden--------------------------------------------------------*/

	/**
	 * Liest Eingabe des Spielers von der Kommandozeile ein und &uuml;berpr&uuml;ft sie.
	 * Vergleicht mit reg. Ausdruck => nur g&uuml;ltige Eingaben werden zur&uuml;ckgegeben
	 * @return String (Zahlen 1-8, bzw. ENTER)
	 */
	public String einlesen() {
		String wert = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			wert = in.readLine().trim();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		// nur Eingaben 1-8 bzw. ENTER sollen akzeptiert werden
		
		//aufgeben: 
		if (wert.length() == 0) {
			return wert;
		}
		// alle anderen Eingaben mit dem reg. Ausdruck vergleichen 
		while (! (matches(wert)) ) {
			System.out.println("Ungueltige Eingabe. Nur Zahlen zwischen 1 und 8 (bzw. 'ENTER') sind erlaubt.");
			 wert = einlesen();
		}
		
		return wert;
	}
	
	/**
	 * Regulaerer Ausdruck: [1-8]
	 * @param s eingelesener String 
	 * @return boolean: true, wenn gueltige Eingabe (d.h. 1-8)
	 * 					false, sonst
	 */
	public boolean matches(String s){
		Pattern p = Pattern.compile("[1-8]");
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	/**
	 * "Einlesen ohne Aufgeben":
	 * Der leere String wird nicht akzeptiert
	 * @return String, Zahlen 1-8
	 */
	public String nichtAufgeben(String s) {
		while(s.length() == 0){
			s = einlesen();
		}
		return s;
	}

	/**
	 * Die Best&auml;tigen-Funktion. Teilt dem Spieler mit,
	 * dass der Zug ausgefuehrt wurde.
	 *
	 */
	public void bestaetigen()
	{
		System.out.println("Der Zug wurde ausgefuehrt.");
	}
	
	/**
	 * Methode um den rundenweisen Zeitbonus zu vergeben.
	 */
	public void gebeZeitbonus()
	{
		this.Zeitkonto += 10000; // Spieler erhalten pro Spielzug 10000 ms.
	}
}
