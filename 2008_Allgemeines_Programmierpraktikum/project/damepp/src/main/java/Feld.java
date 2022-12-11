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
 * Auf der Klasse {@code Feld} basiert die Klasse {@code Spielfeld}. Hier wird
 * ein Datenbeh&auml;lter f&uuml;r die grundlegenden m&ouml;glichen
 * Eigenschaften (Feldfarbe, besetzt oder unbesetzt...) eines einzelnen Feldes
 * auf dem Spielfeld erzeugt.
 * 
 * @author Christian Beulke
 * @author Franziska Klingner
 */

public class Feld {

/*======Variablen============================================================*/

	/**
	 * Zeigt an, ob das Feld die Farbe schwarz hat und damit besetzt werden
	 * kann.
	 */
	private boolean is_schwFeld;

	/**
	 * Zeigt an, ob das Feld, sollte es besetzt sein, mit einem schwarzen Stein
	 * besetzt ist.
	 */
	private boolean is_schwStein;

	/**
	 * Zeigt an, ob das Feld &uuml;berhaupt besetzt ist.
	 */
	private boolean is_besetzt;

	/**
	 * Zeigt an, ob das Feld mit einer Dame besetzt ist. Dies gilt nat&uuml;rlich
	 * nur dann, wenn das Feld &uuml;berhaupt besetzt ist.
	 */
	private boolean is_dame;

	private boolean is_uebersprungen;

/*======Methoden=============================================================*/

/*------Konstruktoren--------------------------------------------------------*/

	/**
	 * Erzeugt eine neue Instanz der Klasse {@code Feld} (Konstruktor).
	 * 
	 * @param is_schwFeld
	 *            Ist das Feld schwarz?
	 * @param is_schwStein
	 *            Ist der darauf stehende Stein schwarz?
	 * @param is_besetzt
	 *            Ist das Feld &uuml;berhaupt besetzt?
	 * @param is_dame
	 *            Ist der Stein eine Dame oder ein normaler Stein?
	 */
	public Feld(boolean is_schwFeld, boolean is_schwStein, boolean is_besetzt,
			boolean is_dame) {
		this.is_schwFeld = is_schwFeld;
		this.is_schwStein = is_schwStein;
		this.is_besetzt = is_besetzt;
		this.is_dame = is_dame;
		this.is_uebersprungen = false;
	}

/*------Getter---------------------------------------------------------------*/

	/**
	 * Abrufen, ob das ausgew&auml;hlte Feld bereits zuvor &uuml;bersprungen wurde
	 * 
	 * @return true, wenn das Feld/Stein bereits zuvor &uuml;bersprungen wurde
	 */
	public boolean is_uebersprungen() {
		return this.is_uebersprungen;
	}

	/**
	 * Ist das Feld schwarz?
	 * 
	 * @return true, wenn das Feld schwarz ist.
	 */
	public boolean is_schwFeld() {
		return is_schwFeld;
	}

	/**
	 * Ist der Spielstein schwarz?
	 * 
	 * @return true, wenn der Stein schwarz ist.
	 * @throws EmptyFieldException
	 *             wenn Feld leer ist.
	 */
	public boolean is_schwStein() throws EmptyFieldException {
		if (this.is_besetzt) {
			return is_schwStein;
		} else {
			throw new EmptyFieldException();
		}
	}

	/**
	 * Ist das Feld besetzt?
	 * 
	 * @return true, wenn das Feld belegt ist.
	 */
	public boolean is_Besetzt() {
		return is_besetzt;
	}

	/**
	 * Ist der Stein eine Dame?
	 * 
	 * @return true, wenn der Stein eine Dame ist.
	 * @throws EmptyFieldException
	 *             wenn Feld leer ist.
	 */
	public boolean is_Dame() throws EmptyFieldException {
		if (this.is_besetzt) {
			return is_dame;
		} else {
			throw new EmptyFieldException();
		}
	}

/*------Setter---------------------------------------------------------------*/

	/**
	 * Setzt das Feld auf Zustand wei&szlig;.
	 */
	public void makeFeldWeiss() {
		is_schwFeld = false;
	}

	/**
	 * Setzt das Feld auf Zustand schwarz.
	 */
	public void makeFeldSchwarz() {
		is_schwFeld = true;
	}

	/**
	 * Setzt den Stein auf Spieler wei&szlig;.
	 * 
	 * @throws EmptyFieldException
	 *             wenn kein Stein vorhanden.
	 */
	public void makeSteinWeiss() throws EmptyFieldException {
		if (this.is_besetzt) {
			this.is_schwStein = false;
		} else {
			throw new EmptyFieldException();
		}
	}

	/**
	 * Setzt den Stein auf Spieler schwarz.
	 * 
	 * @throws EmptyFieldException
	 *             wenn kein Stein vorhanden.
	 */
	public void makeSteinSchwarz() throws EmptyFieldException {
		if (this.is_besetzt) {
			this.is_schwStein = true;
		} else {
			throw new EmptyFieldException();
		}
	}

/*------Funktionalitaet------------------------------------------------------*/

	/**
	 * Besetzt ein Feld.
	 * 
	 * @throws BlockedFieldException
	 *             wenn Feld bereits besetzt.
	 */
	public void besetzen() throws BlockedFieldException {
		if (this.is_besetzt) {
			throw new BlockedFieldException();
		} else {
			this.is_besetzt = true;
		}
	}

	/**
	 * Gibt ein Feld frei.
	 * 
	 * @throws EmptyFieldExeption
	 *             wenn Feld leer ist.
	 */
	public void freigeben() throws EmptyFieldException {
		if (this.is_besetzt) {
			this.is_besetzt = false;
		} else {
			throw new EmptyFieldException();
		}
	}

	/**
	 * Definiert einen Stein als Dame.
	 * 
	 * @throws EmptyFieldException
	 *             wenn Feld leer.
	 */
	public void makeDame() throws EmptyFieldException {
		if (this.is_besetzt) {
			this.is_dame = true;
		} else {
			throw new EmptyFieldException();
		}
	}

	/** Macht das Umwandeln in eine Dame wieder r&uuml;ckg&auml;ngig. */
	public void undoDame() {
		this.is_dame = false;
	}

	/**
	 * Definiert einen Stein als Stein; wenn ein früher von einer Dame besetztes
	 * Feld wieder von einem Stein belegt wird, ist dieses nötig.
	 * 
	 * @throws EmptyFieldException
	 *             wenn Feld leer ist.
	 */
	public void makeStein() throws EmptyFieldException {
		if (this.is_besetzt) {
			this.is_dame = false;
		} else {
			throw new EmptyFieldException();
		}
	}

	/**
	 * Mit dieser Methode kann das Feld als "&uuml;bersprungen" gekennzeichnet
	 * werden. Dies ist f&uuml;r die &Uuml;berpr&uuml;fung der richtigen
	 * Z&uuml;ge von N&ouml;ten.
	 */
	public void ueberspringen() {
		this.is_uebersprungen = true;
	}

	/**
	 * Hiermit wird die Auswirkung der Methode {@code ueberspringen()}
	 * r&uuml;ckg&auml;ngig gemacht.
	 */
	public void undoUeberspringen() {
		this.is_uebersprungen = false;
	}

	/**
	 * @return String mit einer Auflistung der Eigenschaften der aufrufenden
	 *         Feldinstanz.
	 */
	public String toString() {
		String s = "";

		try {
			s = s + "Feld schwarz?\t" + this.is_schwFeld() + "\n";
			s = s + "Besetzt?\t" + this.is_Besetzt() + "\n";
			s = s + "Stein schwarz?\t" + this.is_schwStein() + "\n";
			s += "Feld mit Dame besetzt?" + this.is_dame + "\n";
		} catch (DamePPException e) {
			e.printStackTrace();
		}

		return s;
	}

}
