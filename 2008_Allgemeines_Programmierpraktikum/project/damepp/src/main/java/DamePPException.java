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
 * Klasse der allgemeinen Dame-Ausnahmen.
 * @author Christian Otto
 */

public class DamePPException extends Exception
{
	private static final long serialVersionUID = 1L;

	// Konstruktoren:
	
		/**
	 * Standardkonstruktor.
	 */
	
	DamePPException()
	{
		super();
	}
	
	/**
	 * Standardkonstruktor mit Übergabestring s.
	 * @param s Übergabestring für Fehlermeldung.
	 */
	
	DamePPException(String s)
	{
		super(s);
	}
}
