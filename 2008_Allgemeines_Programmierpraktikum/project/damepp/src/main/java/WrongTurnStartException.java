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

/**
 * Fehlerklasse, die geworfen wird, wenn ein Spieler versucht, seinen Zug
 * mit einer falschen Koordinate zu beginnen.
 * @author Christian Beulke
 */

public class WrongTurnStartException extends TurnException
{
	private static final long serialVersionUID = 1L;
	
	// Konstruktoren:
	
	/**
	 * Standardkonstruktor.
	 */
	
	WrongTurnStartException()
	{
		super();
	}
	
	/**
	 * Standardkonstruktor mit Übergabestring s.
	 * @param s Übergabestring für Fehlermeldung.
	 */
	
	WrongTurnStartException(String s)
	{
		super(s);
	}
}
