/*
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

import java.util.regex.*;
import java.util.ArrayList;
import java.awt.*;


/**
 * Diese Klasse implementiert alle Methoden, die ein grafischer Spieler ben&ouml;tigt.
 * 
 * @author Stefanie Muehlhausen
 * @author Martin Schewe (holZug)
 * @author Christian Otto (Zeitkonto-Methoden)
 */
public class GraSpieler implements Spieler 
{
	private static ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate> ();
	private long Zeitkonto = 1800000;	// Guthaben auf dem Zeitkonto mit Startwert von 1800s = 30min	
	private Board board;

	public GraSpieler(Board board) {
		this.board = board;
	}

	/**
	 * Fordert vom Board einen Zug an und liefert diesen zur&uuml;ck
	 *
	 * @return Zug als <code>ArrayList</code> &uuml;ber <code>Zugkoordinate</code>n
	 */
	public ArrayList<Zugkoordinate> holZug() {
		ArrayList<Point> zug_p = board.hol_zug();

		// Wandelt die Points in Zugkoordinaten um
		zug = new ArrayList<Zugkoordinate>();
		for (int i = 0; i < zug_p.size(); i++)
			zug.add(new Zugkoordinate((int) zug_p.get(i).getX(),
						  (int) zug_p.get(i).getY()));
		return zug;
	}		
	
	/**
	 * Teilt dem Spieler mit, dass der Zug ausgefuehrt wurde
	 */
	public void bestaetigen()
	{
		System.out.println("Der Zug wurde ausgefuehrt.");
	}
	
	/**
	 * Methode zum Setzen des Wertes des Zeitkontos
	 *
	 * @param  neu	Der neue Kontostand
	 * @throws NegativeTimeException
	 */
	public void setzeZeitkonto(long neu) throws NegativeTimeException
	{
		if (neu < 0)					// Sollte nie auftreten, da, sobald die Zeit
			throw new NegativeTimeException();	// abgelaufen ist, die Spielfeldverwaltung das
								// Spiel als Verloren werten sollte
		Zeitkonto = neu;
	}
	
	/**
	 * Auslesen des aktuellen Zeitkontostandes
	 *
	 * @return Der aktuelle Kontostand
	 */
	public long holZeitkonto()
	{
		return Zeitkonto;
	}
	
	/**
	 * Vergibt den rundenweisen Zeitbonus
	 */
	public void gebeZeitbonus()
	{
		Zeitkonto += 10000;				// Spieler erhalten pro Spielzug 10000 ms extra
	}
}
