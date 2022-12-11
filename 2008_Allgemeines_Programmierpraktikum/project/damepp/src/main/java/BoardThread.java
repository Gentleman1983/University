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

import javax.swing.*;

/**
 * Der Boardthread erstellt einen Thread mit dem das Spielfeld parallel zur sonstigen Arbeit aktualisiert werden kann.
 * @author Sven Withus
 * @author Christian Otto
 */

public class BoardThread extends Thread {
	private Board gboard;

	private Spielfeld feld;

	private JTextField TextFeld;

	private JPanel Panel;

	/**
	 * Der Konstruktor unseres BoardThreads.
	 * @param GBoard Die Instanz des grafischen Boards.
	 * @param Feld Die logische Instanz des Spielfeldes der Spielverwaltung.
	 * @param Textfeld Das JTextField.
	 * @param JPBoard Das JPanel.
	 */

	public BoardThread(Board GBoard, Spielfeld Feld, JTextField Textfeld,
			JPanel JPBoard) {
		this.gboard = GBoard;
		this.feld = Feld;
		this.TextFeld = Textfeld;
		this.Panel = JPBoard;
	}

	/**
	 * Die Run-Methode, die ausgeführt wird, wenn der Thread gestartet wird.
	 */

	public void run() {

		JFrame f = new JFrame("DamePP-2AD");
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.add(this.gboard);
		f.pack();

		f.setVisible(true);
//		f.setResizable(false);

		while (this.feld.getZuegeOhneSchlag() < 30
				&& (this.feld.getSchwarzeSteine() > 1
						&& this.feld.getWeisseSteine() > 0 || this.feld
						.getSchwarzeSteine() > 0
						&& this.feld.getWeisseSteine() > 1))
			try {
				this.feld.naechsterZug();
			} catch (Exception E) {
			}

		if (feld.getWeisseSteine() == 0)
			TextFeld.setText("TATAAAA! Schwarz hat gewonnen!!!");
		else if (feld.getSchwarzeSteine() == 0)
			TextFeld.setText("JIPPIEEE! Weiss hat gewonnen!!!");
		else if (feld.getWeisseSteine() == 1 && feld.getSchwarzeSteine() == 1)
			TextFeld.setText("Leider ein Unentschieden...");
		else if (feld.getZuegeOhneSchlag() == 60)
			TextFeld.setText("Laaangweilig. Leider ein Unentschieden.");
	}

}
