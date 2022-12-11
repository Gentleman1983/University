
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
import java.rmi.server.UnicastRemoteObject;
import java.net.*;




/**
 * Diese Klasse implementiert alle Funktionalitäten, die
 * ein Spieler über das LAN-Interface benötigt.
 * @author Stefanie Muehlhausen
 * @author Christian Otto (Zeitkonto-Methoden)
 */
public class LANSpieler extends UnicastRemoteObject implements SpielerFuerLan, Spieler, Status, Remote 
{
	// Klassenvariablen:
//  private int status = Status.OK;	
	private long Zeitkonto = 60000; // Guthaben auf dem Zeitkonto mit Startwert von
									// 60.000 ms = 60 Sek = 1 Min.
	
	private ArrayList<Zugkoordinate> zug = new ArrayList<Zugkoordinate>();
	// Die Zugliste
	private char[] letzterZug = new char[42];
	// Die Zugliste für entfernten Spieler
	
	private SpielerFuerLan SpielerLokal;
	private Spielfeld brettLan;
	private SpielerFuerLan opponent;
	
	
	// Konstruktor des Remote-Objektes
	protected LANSpieler() throws RemoteException {
		SpielerLokal = (SpielerFuerLan) new CompSpieler();
		brettLan  = new Spielfeld((Spieler) SpielerLokal, true);  
	}
	
	// Klassenmethoden:
	
	/**
	 * Dummy-Methode die von einem LAN-Spieler einen Zug holt.
	 * Durch request ersetzt.
	 */
	public ArrayList<Zugkoordinate> holZug() {
		//durch request ersetzt
		return zug;
	}
	/**
	 * Ersetzt die Methode holZug(). Ein Zug unserer KI wird erfragt.
	 * @return Gibt den Zug des lokalen Spielers als char[] zurück
	 * @throws RemoteException
	 */
	public char[] request() throws RemoteException {
		zug = holZug();
		letzterZug = erstelleCharArray(SpielerLokal.holZug());
		return letzterZug;
	}
	
	
	/**
	 * Dummy-Methode die einen Zug bestaetigt.
	 * Durch confirm ersetzt.
	 */
	public void bestaetigen() {
		// durch confirm ersetzt
		
	}
	/**
	 * Die Bestätigen-Funktion. Falls ein Zug ungueltig ist, wird eine RemoteException ausgeloest. 
	 * Sonst wird der Zug ausgefuert.
	 * @throws RemoteException
	 */
    public void confirm() throws RemoteException {
		this.request();
		zug = erstelleArrayList(letzterZug);
		try {
	       	Spielfeld.versucheZug(zug);
            Spielfeld.fuehreZugAus(zug);
	   	} catch(DamePPException e) {
				throw new RemoteException();
	   	}
	   	
    }	
	

	  /**
	   * Ein Zug des entfernten Spieler wird erfragt und geprueft. 
	   * Solange der Zug unguetig ist, wird ein neuer Zug erfragt.
	   * @throws RemoteException
	   * @throws DamePPException
	   */
	 public void run() throws RemoteException {
		if (status() != Status.OK)  {
			throw new RemoteException();
		}
		boolean mglZug = false;
		while (!mglZug) {
		    char[] letzterZug =  opponent.request();
		    try {
			Spielfeld.versucheZug(erstelleArrayList(letzterZug));
			mglZug = true;
		    } catch (DamePPException e) {
			// Der Zug ist ungueltig.
		    }
		}
		opponent.confirm();
	 }
	    
	 
	 	/**
	 	 * Der Gegenspieler
	 	 * @return eine Instanz des entfernten Spielers
	 	 * @throws RemoteException
	 	 */
	  public Spieler opponent() throws RemoteException {
		return this.opponent();
	  }
	  	/**
	  	 * Zwei Instanzen des Spielers werden als Gegenspieler angelegt.
	  	 * @param op der jeweilige, andere Spieler
	  	 * @throws RemoteException
	  	 */
	  public void opponent(Spieler op) throws RemoteException {
		this.opponent = (SpielerFuerLan) op;
	  }
	  
	  	/**
         * Diese Methode gibt den aktuellen Status des Spielbrettes wieder.
	  	 * @return der Status des Spielfeldes
	  	 * @throws RemoteException
	  	 */
	  public int status() throws RemoteException { 
		return Spielfeld.status;
	  }
	

	
	
	/**
	 * Methode zum Setzen des Wertes des Zeitkontos.
	 * @param neu Der neue Kontostand.
	 * @throws NegativeTimeException
	 */
	
	public void setzeZeitkonto(long neu) throws NegativeTimeException
	{
		if(neu < 0)								// Sollte nie auftreten, da sobald die Zeit ab-
		{										// gelaufen ist, die Spielfeldverwaltung das
			throw new NegativeTimeException();  // Spiel als Verloren werten sollte.
		}
		else
		{
			this.Zeitkonto = neu; // Speichere den neuen Kontostand
		}
	}
	
	/**
	 * Methode zum Auslesen des aktuellen Zeitkontostandes.
	 * @return Der aktuelle Kontostand.
	 */
	
	public long holZeitkonto()
	{
		return this.Zeitkonto; // Liefere den aktuellen Kontostand zurück
	}
	
	/**
	 * Methode um den rundenweisen Zeitbonus zu vergeben.
	 */
	
	public void gebeZeitbonus()
	{
		this.Zeitkonto += 5000; // LAN-Spieler erhalten pro Spielzug 5000 ms.
	}
	
	/**
	 * Methode zum Umrechnen von Zugkoordinaten in Schachkoordinaten
	 * @param zug Liste mit Zugkoordinaten
	 * @return Char-Array an den entfernten Spielers
	 */
	
	public char[] erstelleCharArray (ArrayList<Zugkoordinate> zug){
		int laenge = zug.size();
		char[] ch = new char[(laenge * 2)];
		Zugkoordinate zugK = new Zugkoordinate();
			
		for(int i = 0; i < laenge; i++) {
			zugK = zug.get(i);
// Umwandlung von X
			ch[2*i] =(char) ((char) zugK.getX() + 'a');

// Umwandlung von Y			
			ch[2*i +1] =(char) ((8 - zugK.getY()) + '0');
		}
		
		return ch;
	}
	
	/**
	 * Methode zum Umrechnen von Schachkoordinaten in Zugkoordinaten
	 * @param ch Char-Array von dem entfernten Spieler
	 * @return Liste mit Zugkoordinaten. NICHT geprüft, ob innerhalb der Feldgrenzen
	 */
	
	public ArrayList<Zugkoordinate> erstelleArrayList (char[] ch) {
		int laenge = ch.length;
		Zugkoordinate zugK = new Zugkoordinate();
		
		for(int i = 0; i < laenge; i++) {
// Umwandlung von X			
			if(i % 2 == 0) {
				zugK = new Zugkoordinate();
				if(Character.isUpperCase(ch[i])) {
					ch[i] = Character.toLowerCase(ch[i]);
				}
				zugK.setX((int) ch[i] - 'a');
			}
// Umwandlung von Y			
			else {
				zugK.setY((int) ('8' - ch[i]));
				zug.add(zugK);
			}
		}
		return zug;
	}

	

		
}


