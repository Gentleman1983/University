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
 * Fehlerklasse, die Ausnahmen sammelt, die in Beziehung
 * zum Spielfeld stehen.
 * @author Christian Otto
 */

public class FieldException extends DamePPException
{
	private static final long serialVersionUID = 1L;

	// Konstruktoren:
	
	/**
	 * Standardkonstruktor.
	 */
	
	FieldException()
	{
		super();
	}
	
	/**
	 * Standardkonstruktor mit Übergabestring s.
	 * @param s Übergabestring für Fehlermeldung.
	 */
	
	FieldException(String s)
	{
		super(s);
	}
}
