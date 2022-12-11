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
 * Die Schnittstelle Spieler wird von allen anderen {@code Spieler}-Klassen
 * implementiert. Sie stellt sicher, dass alle {@code Spieler}-Klassen, egal
 * ob es sich um einen interaktiven Spieler, einen KI-Spieler oder um die
 * Netzwerk-Schnittstelle handelt, die gleiche Funktionalit&auml;t f&uuml;r
 * die Spielfeldverwaltung bereit h&auml;lt.
 * 
 * @author Stefanie M&uuml;hlhausen
 * @author Christian Otto
 */

public interface Spieler
{	
	/**
	 * {@code getZug()} liefert eine ArrayList mit Instanzen von
	 * {@code Zugkoordinate} zur&uuml;ck, welche die Bewegung eines Spielsteines
	 * auf dem Spielfeld repr&auml;sentiert. Das erste Element ist die
	 * Startposition des zu bewegenden Steines. Es folgt die Sprung- oder
	 * Zugposition, gefolgt von ggf. weiteren Positionen. Dies kann
	 * notwendig sein, wenn es zu einem Mehrfachsprung kommt.
	 *
	 * @return Aktuell get&auml;tigter Zug.
	 */
	public ArrayList<Zugkoordinate> holZug();
	
	/**
	 * {@code bestaetigen()} liefert dem LANSpieler eine R&uuml;ckmeldung dar&uuml;ber, 
	 * ob sein Zug korrekt war und unser Spielfeld entsprechend ge&auml;ndert wurde.
	 */
	public void bestaetigen() ;
	
	/**
	 * {@code setzeZeitkonto(neu)} setzt das interne Zeitkonto auf den Wert neu.
	 * 
	 * @param neu Der neue Kontostand in Millisekunden.
	 * @throws NegativeTimeException falls der aktuelle Zeitwert negativ ist.
	 */
	public void setzeZeitkonto(long neu) throws NegativeTimeException;
	
	/**
	 * {@code holZeitkonto()} liefert den aktuellen Zeitkontostand in Millisekunden.
	 * @return Der aktuelle Kontostand.
	 */
	public long holZeitkonto();
	
	/**
	 * {@code gebeZeitbonus()} erhöht das Zeitguthaben des aktuellen Spielers für einen neuen Zug.
	 * KI-Spieler: +5 Sekunden.
	 * Menschliche Spieler: + 10 Sekunden.
	 */
	public void gebeZeitbonus();
	
}