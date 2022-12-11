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

import java.rmi.*;
import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Dies ist die ausf&uuml;hrbare Klasse f&uuml;r das Spiel &uuml;bers Internet
 * Diese initialisiert ein neues Spielfeld, l&auml;sst das Spiel beginnen und h&auml;lt es so lange am
 * laufen, bis ein Spieler gewonnen hat oder es mit einem unentschieden beendet
 * wird.
 * @author Stefanie M&uuml;hlhausen
 */
public class DameNet extends UnicastRemoteObject implements ServerInterface {

private static final long serialVersionUID = 1L;

	DameNet() throws RemoteException {
    		super();
    	}

	/**
	 * Die main-Methode. 
	 * @param (args[0]) Host 
	 * @param (args[1]) Servername
	 */
	public static void main(String[] args) {
		// der Host
		String host;     
		// der Servername
		String name;
		
		try {
		    if (args.length == 2) {

			// args[0] Host
			// args[1] Servername
		    	
			host = args[0];
			name = args[1];
			
			LanBekanntMachen(host, name);
			LanSpielen(host, name);
		    }
		    else {
		    	System.out.println("Error. Der korrekte Aufruf ist (Host/IP) (Name).");
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	}
	/**
	 *In dieser Methode wird die Anwendung bei dem lokalen Server registriert.
	 * @param host Die Serverkennung und die neue Instanz des Servers werden übergeben.
	 * @param name Servername
	 */
		public static void LanBekanntMachen(String host, String name) {
		
			try {
			    Naming.rebind("rmi://" + host + "/" + name, new SpielerFactory());
			    System.out.println("Manager (" + name + ") published and running");
		
			} catch (MalformedURLException ex) {
			    ex.printStackTrace();
			} catch (RemoteException ex) {
			    ex.printStackTrace();
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
		
		}

	/**
	 * In dieser Methode wird die Remote-Refernz vom Server geholt und die Methoden des ServerInterfaces aufgerufen.
	 * Dies umfasst auch den eigentlichen Spielablauf. Die Ausgabe erfolgt nur auf der Konsole.
	 * @param host Die Serverkennung
	 * @param name Der Servername
	 */
		public static void LanSpielen(String host, String name) {
			try {
			    ServerFactory factory = 
				(ServerFactory) Naming.lookup("rmi://" + host + "/" + name);
			    
			    SpielerFuerLan SpielerEntfernt = factory.create();
			    SpielerFuerLan SpielerLokal = new LANSpieler();
		        SpielerLokal.opponent((Spieler) SpielerEntfernt);
			    SpielerEntfernt.opponent((Spieler) SpielerLokal);

			    System.out.println("Manager (" + name + ") linked, start loop");

//		Schleife für den Spielablauf

			    int status = Status.OK;
			    while (status == Status.OK) {

				// Zug vom Gegner wird erfragt
				SpielerLokal.run();

				if (SpielerLokal.status() == SpielerEntfernt.status()) {
				    status = SpielerLokal.status();
				}
				else
				    throw new RuntimeException();


				if (status == Status.OK) {

				    // Zug von unserem Spieler
				    SpielerEntfernt.run();

				    if (SpielerLokal.status() == SpielerEntfernt.status()) {
					status = SpielerEntfernt.status();
				    }
				    else
					throw new RuntimeException();
				}
			    }
			    
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
		}


        public Spieler create() throws RemoteException {
               CompSpieler S = new CompSpieler();
               return S;
         }
}







