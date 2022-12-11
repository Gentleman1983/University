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

/**
 * Dieses Interface ist ein Teil, der für den internetfähigen LanSpieler benötigt wird.
 * @author Stefanie Mühlhausen
 * @author Henrik Brosenne
 */

public interface ServerInterface extends Remote {
	public Spieler create() throws RemoteException;
}
