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
 * Kathrin Aßhauer, Christian Beulke, Franziska Klingner,
 * Stefanie Mühlhausen, Christian Otto, Martin Schewe, 
 * Sven Withus
 */

package damepp;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Dieses Interface ist ein Teil, der für den internetfähigen LanSpieler benötigt wird.
 * @author Stefanie Mühlhausen
 */

public class SpielerFactory extends UnicastRemoteObject implements ServerFactory {
	

	private static final long serialVersionUID = 1L;

	protected SpielerFactory() throws RemoteException {
		}

	public SpielerFuerLan create() throws RemoteException {
			return new LANSpieler();
		}
}

