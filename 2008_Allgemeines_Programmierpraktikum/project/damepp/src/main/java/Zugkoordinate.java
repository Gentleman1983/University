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
 * Die Klasse Zugkoordinate ist als geordnete Form zur Speicherung bzw.
 * &Uuml;bergabe von Paaren von X- und Y-Koordinaten geeignet.
 * 
 * @author Stefanie Mühlhausen
 */

public class Zugkoordinate {

/*======Variablen============================================================*/

	/** Die X-Koordinate */
	private int x;

	/** Die Y-Koordinate */
	private int y;

/*======Methoden=============================================================*/

/*------Konstruktoren--------------------------------------------------------*/

	/**
	 * Der Standardkonstruktor ohne Parameter.
	 */
	public Zugkoordinate() {
	}

	/**
	 * Der Standardkonstruktor mit zwei Parametern.
	 * 
	 * @param x Die X-Koordinate.
	 * @param y Die Y-Koordinate.
	 */
	public Zugkoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

/*------Getter---------------------------------------------------------------*/

	/**
	 * Getter f&uuml;r die X-Koordinate.
	 * 
	 * @return Die X-Koordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter f&uuml;r die Y-Koordinate.
	 * 
	 * @return Die Y-Koordinate.
	 */
	public int getY() {
		return y;
	}

/*------Setter---------------------------------------------------------------*/

	/**
	 * Setter f&uuml;r die X-Koordinate.
	 * 
	 * @param x Die X-Koordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter f&uuml;r die Y-Koordinate.
	 * 
	 * @param y Die Y-Koordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Setter f&uuml;r beide Koordinaten.
	 * @param x Die X-Koordinate.
	 * @param y Die Y-Koordinate.
	 */
	void setKoord(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

/*------Funktionalitaet------------------------------------------------------*/

	/**
	 * Die toString-Methode.
	 * @return Eine String-Version der Koordinate.
	 */
	public String toString() {
		String retString = " x:" + x + " y: " + y;
		return retString;
	}
}
