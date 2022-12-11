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

import java.util.ArrayList;
import java.rmi.*;

/**
 * Die Schnittstelle SpielerFuerLan ist das Remoteinterface f&uuml;r den LanSpieler.
 * @author Stefanie Mühlhausen, Christian Otto
 */

public interface SpielerFuerLan extends Remote, Status
{	
	/**
	 * {@code holZug()} liefert eine ArrayList mit Instanzen von
     * Zugkoordinate zur&uuml;ck, welche die Bewegung eines Spielsteines
     * auf dem Spielfeld repr&auml;sentiert. Das erste Element ist die
     * Startposition des zu bewegenden Steines. Es folgt die Sprung- oder
     * Zugposition, gefolgt von ggf. weiteren Positionen. Dies kann
     * notwendig sein, wenn es zu einem Mehrfachsprung kommt.
     * 
	 * @return Der aktuell get&auml;tigte Zug.
	 */
	public ArrayList<Zugkoordinate> holZug();
	
	/**
	 * Request ist die holZug-Methode fuer den LanSpieler.
	 * @throws RemoteException
	 */
	public char[] request() throws RemoteException;
	
	/**
	 * {@code bestaetigen()} liefert dem LANSpieler die Best&auml;tigung, 
	 * dass sein Zug korrekt war und unser Spielfeld entsprechend ge&auml;ndert wurde.
	 */
	void bestaetigen() ;
	/**
	 * Ersetzt die bestaetigen-Methode.
	 * @throws RemoteException
	 */
	public void confirm() throws RemoteException;
	/**
	 * Dies ist die Methode, die tats&auml;chlich &uuml;ber RMI von beiden Spielern 
	 * aufgerufen wird. Sie greift auf request, und confirm zu.
	 * @throws RemoteException
	 */
	public void run() throws RemoteException;
	
	/**
	 * Dieser Konstruktor erzeugt ein Objekt (LanSpieler).
	 *  @throws RemoteException
	 */
	public Spieler opponent() throws RemoteException;
	
	/**
	 * Mit dieser Methoden werden die beiden Spielerinstanzen gegenerische Spielerinstanzen.
	 * @throws RemoteException
	 */
	public void opponent(Spieler opponent) throws RemoteException;
	
	 /**
	  * Diese Methode gibt den aktuellen Status des Spielfeldes zur&uuml;ck.
	  * @throws RemoteException
	  */
	public int status() throws RemoteException;
	
	/**
	 * {@code setzeZeitkonto(neu)} setzt das interne Zeitkonto auf den Wert neu.
	 * 
	 * @param neu Der neue Kontostand in Millisekunden.
	 * @throws NegativeTimeException falls der aktuelle Zeitkontostand negativ ist.
	 */	
	public void setzeZeitkonto(long neu) throws NegativeTimeException;
	
	/**
	 * {@code getZeitkonto()} liefert den aktuellen Kontostand in Millisekunden.
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