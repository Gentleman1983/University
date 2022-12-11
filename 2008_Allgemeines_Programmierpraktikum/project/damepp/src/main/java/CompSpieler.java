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

import java.util.*;

/**
 * Diese Klasse implementiert alle Funktionalit&auml;ten
 * eines KI-Spielers in DamePP-2ad.
 *
 * @author Christian Otto
 */
public class CompSpieler implements Spieler {

/*======Variablen============================================================*/

	/** Eine Liste m&ouml;glicher Z&uuml;ge. */
	private LinkedList<KIZuege> bew_zuege = new LinkedList<KIZuege>(); // Die möglichen Züge.

	/** Die KI des Computerspielers.
	 * @see KI
	 */
	private KI compi = new KI(); // Die Spieler-KI.

	/** Ein Zufallsgenerator. */
	private Random rand = new Random(zufall.nextLong()); // Der Zufallsgenerator.

	/** Variable f&uuml;r das Zeitkonto. Startwert ist 60000 ms = 60 sek = 1 min. */
	private long Zeitkonto = 60000; // Guthaben auf dem Zeitkonto mit Startwert von 60.000 ms = 60 Sek = 1 Min.

	/** Ein "Hilfszufallsgenerator" auf Basis der aktuellen Zeit in Millisekunden. */
	private static Random zufall = new Random(System.currentTimeMillis()); // Hilfszufallsgenerator.

	/** Die Zugliste, die letztendlich an die Spielfeldverwaltung geliefert wird. */
	private static ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate>(); // Die Zugliste.

	/** Rechentiefe der k&uuml;nstlichen Intelligenz. Diese repr&auml;sentiert die Anzahl der
	 * nachfolgenden Z&uuml;ge, die f&uuml;r die Auswahl des besten Zuges im Vorraus berechnet werden.
	 */
	private int Rechentiefe = 3; // Die Anzahl Züge, die die KI vorberechnet um den Gegenzug zu berechnen. Werte >= 1 erlaubt.

	/** Der Offensivgrad der K&uuml;nstlichen Intelligenz. Dieser zeigt an, wie viel der KI
	 * ein &uuml;bersprungener Stein im vergleich zu einem verlorenen Stein wert ist.
	 */
	private double Offensivgrad = 0.90; // Wie stark werden übersprungene Steine zu verlorenen gewertet?

	/** Zeigt an, ob sich das Programm gerade im Debugging-Modus befindet. */
	private final static boolean wartung = false; // Debuggen wir gerade?

/*======Methoden=============================================================*/

/*------Konstruktoren--------------------------------------------------------*/

	/**
	 * Standardkonstruktor (Rechentiefe von 3 Zügen und 
	 * Offensivgrad von 90%).
	 */
	CompSpieler() {
	}

	/**
	 * Konstruktor mit manuell eingerichter Rechentiefe
	 * (Offensivgrad = 90%).
	 * @param Rechentiefe Die gewählte Rechentiefe in Zügen.
	 */
	CompSpieler(int Rechentiefe) {
		this.Rechentiefe = Rechentiefe;
	}

	/**
	 * Konstruktor mit manuell eingerichtetem Offensivgrad
	 * (Rechentiefe = 3 Züge).
	 * @param Offensivgrad Offensivgrad (1.25 = 125%, d.h.
	 * ein geschlagener, gegnerischer Stein ist der KI 1.25x
	 * soviel Wert wie ein verlorener eigener Stein).
	 */
	CompSpieler(double Offensivgrad) {
		this.Offensivgrad = Offensivgrad;
	}

	/**
	 * Konstruktor mit manuell eingerichtetem Offensivgrad
	 * und Rechentiefe.
	 * @param Rechentiefe Die Rechentiefe in Zügen.
	 * @param Offensivgrad Der Offensivgrad (1.25 =125%).
	 */
	CompSpieler(int Rechentiefe, double Offensivgrad) {
		this.Rechentiefe = Rechentiefe;
		this.Offensivgrad = Offensivgrad;
	}

	/**
	 * Konstruktor mit manuell eingerichtetem Offensivgrad
	 * und Rechentiefe.
	 * @param Rechentiefe Die Rechentiefe in Zügen.
	 * @param Offensivgrad Der Offensivgrad in Prozent.
	 */
	CompSpieler(int Rechentiefe, int Offensivgrad) {
		this.Rechentiefe = Rechentiefe;
		this.Offensivgrad(Offensivgrad);
	}

/*------Getter---------------------------------------------------------------*/

	/**
	 * Auslesen, ob die KI gerade im Debugging-Modus arbeitet.
	 * @return true, wenn Debugging aktiv ist.
	 */
	protected static boolean Wartungsmodus() {
		return wartung;
	}

	/**
	 * Methode zum Auslesen des aktuellen Zeitkontostandes.
	 * @return Der aktuelle Kontostand.
	 */
	public long holZeitkonto() {
		return this.Zeitkonto; // Liefere den aktuellen Kontostand zurück
	}

	/**
	 * Liefert den Offensivgrad zurück.
	 * @return Den Offensivgrad als double (1.00 = 100 %).
	 */
	public double Offensivgrad() {
		return this.Offensivgrad;
	}

	/**
	 * Liefert die aktuelle Rechentiefe zurück.
	 * @return Die aktuelle Rechentiefe (in Zügen).
	 */
	public int Rechentiefe() {
		return this.Rechentiefe;
	}



/*------Setter---------------------------------------------------------------*/

	/**
	 * Setzt den Offensivgrad.
	 * @param offensivgrad Offensivgrad als double (z.B. 0.50 für 50 %).
	 */
	public void Offensivgrad(double offensivgrad) {
		this.Offensivgrad = offensivgrad;
	}

	/**
	 * Setzt den Offensivgrad in Prozent.
	 * @param offensivgrad Offensivgrad in Prozenten (100 = 100%).
	 */
	public void Offensivgrad(int offensivgrad) {
		this.Offensivgrad = ((double) offensivgrad) / ((double) 100);
	}

	/**
	 * Setzt die gewünschte Rechentiefe.
	 * @param Rechentiefe Die Rechentiefe (in Zügen).
	 */
	public void Rechentiefe(int Rechentiefe) {
		this.Rechentiefe = Rechentiefe;
	}

	/**
	 * Methode zum Setzen des Wertes des Zeitkontos.
	 * @param neu Der neue Kontostan.
	 * @throws NegativeTimeException
	 */
	public void setzeZeitkonto(long neu) throws NegativeTimeException {
		if (neu < 0) {
			/**
			 * Sollte nie auftreten, da sobald die Zeit abgelaufen ist, 
			 * die Spielfeldverwaltung das Spiel als Verloren werten sollte.
			 */

			throw new NegativeTimeException(
					"Negative Zeitangaben sind ungültig.");
		} else {
			this.Zeitkonto = neu; // Speichere den neuen Kontostand
		}
	}



/*------Funktionalitaet------------------------------------------------------*/

	/**
	 * Methode um den rundenweisen Zeitbonus zu vergeben. Der Computerspieler
	 * erh&auml;lt pro Zug zus&auml;tzliche 5 Sekunden.
	 */
	public void gebeZeitbonus() {
		this.Zeitkonto += 5000; // Computerspieler erhalten pro Spielzug 5000 ms.
	}

	/**
	 * Die Methode, die von einem KI-Spieler einen Zug abruft. Diese Methode
	 * ruft selbst wiederum {@code waehleZug()} auf, um den danach in {@code zug}
	 * gespeicherten Spielzug an die Spielfeldverwaltung zur&uuml;ckzuliefern.
	 * @return Der berechnete Zug.
	 */
	public ArrayList<Zugkoordinate> holZug() {
		/*
		 * Berechne die möglichen Spielzüge.
		 */
		this.bew_zuege = compi.berechneZug(this.Zeitkonto - 500,
				this.Rechentiefe, this.Offensivgrad);

		waehleZug();

		if (Wartungsmodus()) {
			System.out.println(this.bew_zuege.size());
			System.out.println(zug);
		}

		return zug;
	}

/*------Hilfsmethoden--------------------------------------------------------*/

	/**
	 * Diese Methode wählt einen optimalen Spielzug aus der Liste 
	 * der möglichen KI-Spielzüge und speichert diesen in {@code zug}.
	 */
	private void waehleZug() {
		double lokaleBewertung = 0.0; // Damit kleiner als alle möglichen Scores.
		rand.setSeed(System.currentTimeMillis());

		// Sortiere die Züge nach ihrer Gewichtung.
		for (int i = 0; i < this.bew_zuege.size(); i++) {
			if (Wartungsmodus()) {
				System.out.println("size = " + this.bew_zuege.size() + ", i = "
						+ i + ", Score = " + this.bew_zuege.get(i).Bewertung());
			}
			double zugBewertung = this.bew_zuege.get(i).Bewertung();
			if (zugBewertung == lokaleBewertung) {
				this.bew_zuege.addFirst(this.bew_zuege.remove(i)); // Stelle aktuelles Element an den Anfang.
			} else if (zugBewertung > lokaleBewertung) {
				lokaleBewertung = zugBewertung; // Neue localscore festlegen.
				this.bew_zuege.addFirst(this.bew_zuege.remove(i)); // Stelle aktuelles Element an den Anfang.
				i = 0; // Fange wieder vorne mit der Suche nach kleinstmöglichen Elementen an.
			}
		} // Nun haben wir die am höchsten bewerteten Elemente am Anfang unserer LinkedList.

		int anzahl = 0; // Wie viele mögliche optimale Züge habe ich zur Verfügung?
		for (int i = 0; (i < this.bew_zuege.size())
				&& (lokaleBewertung == this.bew_zuege.get(i).Bewertung()); i++) {
			anzahl++; //Zähle die Anzahl.
		}

		// Somit wissen wir, welche Züge wir wählen können.

		int listenPosition = this.rand.nextInt();
		if (listenPosition < 0) // Wir können nunmal keine negativen Listenpositionen bestimmen.
		{
			listenPosition *= -1;
		}

		if (CompSpieler.Wartungsmodus()) {
			System.out.println("anzahl = " + anzahl + ", listenPosition = "
					+ listenPosition);
		}

		listenPosition %= anzahl; // Schränke durch Modulo den Wertebereich ein.

		zug = this.bew_zuege.get(listenPosition).Zug(); // Wähle einen zufälligen besten Zug aus.

		if (CompSpieler.Wartungsmodus()) {
			for (int i = 0; i < zug.size(); i++) {
				System.out.print(" ->  (" + (zug.get(i).getX() + 1) + ", "
						+ (zug.get(i).getY() + 1) + ")");
			}
			System.out.print("\n");
		}
	}

/*------sonstige Methoden----------------------------------------------------*/

	/**
	 * Die Bestätigen-Funktion. Hier ohne Bedeutung.
	 */
	public void bestaetigen() {

	}

}
