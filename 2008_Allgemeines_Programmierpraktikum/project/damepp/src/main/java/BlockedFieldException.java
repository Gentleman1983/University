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
 * Fehlerklasse, die geworfen wird, wenn bspw. ein 
 * bereits belegtes Feld mit noch einem Stein belegt
 * werden soll.
 * @author Christian Otto
 */

public class BlockedFieldException extends FieldException
{
	private static final long serialVersionUID = 1L;

	// Konstruktoren:
	
	/**
	 * Standardkonstruktor.
	 */
	
	BlockedFieldException()
	{
		super();
	}
	
	/**
	 * Standardkonstruktor mit Übergabestring s.
	 * @param s Übergabestring für Fehlermeldung.
	 */
	
	BlockedFieldException(String s)
	{
		super(s);
	}
}
