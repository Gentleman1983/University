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

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse beinhaltet das grafische Auswahlmen&uuml; und die stellt die
 * ausf&uuml;hrbare Klasse des Programms dar.
 * 
 * @author Sven Withus
 */
public class DameGUI extends javax.swing.JFrame {
	
/*======Variablen============================================================*/

/*------grafische Elemente---------------------------------------------------*/

	/** Beinhaltet das grafische Spielfeld. */
	private Board board;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	static private JTextPane jTextPane2;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	static private JTextPane jTextPane1;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuBar Menu;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenu Spiel;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JPanel jPanelBoard;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JPanel jPanelKnopf;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JPanel jPanelTextfeld;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Computer;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Spieler;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Anleitung;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private static JButton Konsole;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private static JButton GUI;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JTextField jTextFeld1;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Version;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Lan_ki;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Aufgeben;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenuItem Start;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private JMenu Info;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private static JFrame v;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private static JFrame St;

	/** Grafisches Element f&uuml;r die Darstellung der Fenster. */
	private static JFrame A;


/*------sonstige Elemente----------------------------------------------------*/

	private static final long serialVersionUID = 1L;

	/** ??? */
	private int x;

	private boolean loeschen = false;
	
	private BoardThread BT;

/*======Methoden=============================================================*/

/*-------Konstruktoren-------------------------------------------------------*/

	/**
	 * Konstruktor für das grafische Damespiel
	 */
	
	public DameGUI() {
		super();
		initialisiere();
		initGUI();
	}

/*-------Getter--------------------------------------------------------------*/

/*-------Setter--------------------------------------------------------------*/

/*-------Funktionalitaet-----------------------------------------------------*/

	/**
	 * Mainmethode die den ausführbaren Quelltext beinhaltet.
	 * Startet als Erstes das Auswahlfenster, in welchem gew&auml;hlt werden kann,
	 * ob auf Konsole oder per Grafik gespielt werden soll. Danach folgt je nach
	 * Auswahl das Hauptspiel oder die Konsole.
	 * @param args
	 */
	public static void main(String[] args) {

		// Start- /Auswahlfenster für die Auswahl ob Grafik oder Konsole
		St = new JFrame("Willkommen bei Dammepp-2AD");
		St.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		St.setVisible(true);
		St.setSize(200, 150);
		St.setResizable(true);
		BorderLayout StLayout = new BorderLayout();
		St.setPreferredSize(new java.awt.Dimension(200, 150));
		St.getContentPane().setLayout(StLayout);
		

		// Knopf um die Konsoleneingabe zu Starten
		{
			Konsole = new JButton();
			St.getContentPane().add(Konsole, BorderLayout.NORTH);
			Konsole.setText("Konsole");
			Konsole.setPreferredSize(new java.awt.Dimension(200, 50));
			Konsole.setForeground(new java.awt.Color(255, 255, 255));
			Konsole.setBackground(new java.awt.Color(0, 0, 0));
			Konsole.setFont(new java.awt.Font("Futuna", 1, 16));
			Konsole.setPreferredSize(new java.awt.Dimension(128, 50));
			Konsole.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonKonsole_actionPerformed(e);
				}
			});

		}

		// Knopf um die Grafik zu Starten
		{
			GUI = new JButton();
			St.getContentPane().add(GUI, BorderLayout.CENTER);
			GUI.setText("Grafik");
			GUI.setPreferredSize(new java.awt.Dimension(200, 100));
			GUI.setForeground(new java.awt.Color(0, 177, 95));
			GUI.setBackground(new java.awt.Color(89, 95, 216));
			GUI.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 20));

			GUI.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonGUI_actionPerformed(e);
					}
				}
			);

		}

		/*
		 * Button Anleitung
		 * Liegt im Menü
		 * Zeigt die Anleitung
		 */
		A = new JFrame("Anleitung");
		A.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		A.setVisible(false);
		A.setResizable(false);
		A.setPreferredSize(new java.awt.Dimension(640, 721));
		A.setSize(540, 751);
		{
			jTextPane1 = new JTextPane();
			A.getContentPane().add(jTextPane1, BorderLayout.CENTER);
			jTextPane1.setText("\n*Anleitung*\n\n\nDies ist ein"
				+" Damespiel welches im Programmierpraktikum"
				+" 2008 entstand. Die beteiligten Personen sind"
				+" unter Version Aufgelistet. Sie haben die"
				+" Möglichkeit graphisch zu spielen oder auf der"
				+" Konsole. Unter \"Spiel Starten\" können sie"
				+" sich die Art des Spieles aussuchen.\n\n" 
				+"© in 2008 Programmierpaktikumsgruppe DamePP-2AD\n\n\n" 
				+"*Spielregeln*\n\n\n"
				+"1. Schwarz beginnt das Spiel.\n\n"
				+"2. Gespielt wird auf den schwarzen Feldern.\n\n"
				+"3. Ein Stein wird immer ein Feld schräg vorwärts gezogen.\n\n"
				+"4. Ein Stein kann vorwärts einen Stein oder eine"
				+"Dame des anderen Spielers überspringen. Jedoch"
				+" muss das Feld hinter dem überspungenen Stein leer sein.\n\n"
				+"5. Es können mehrere Steine nacheinander übersprungen werden.\n\n"
				+"6. Erreicht ein Stein die gegenüberliegende Reihe"
				+" des Damebretts, so wird dieser als Dame gekrönt.\n\n"
				+"7. Eine Dame darf zusätzlich zu den Zügen eines"
				+" Steins ebenfalls entgegen ihrer Zugrichtung ziehen und springen.\n\n"
				+"8. Erreicht ein Stein in einem Sprung die"
				+" gegenüberliegende Seite des Spielfeldes, wird dieser"
				+" Dame und der Zug wird beendet. Ein möglicher weiterer"
				+" Mehrfachsprung, den die Dame ausführen könnte, wird damit unterbrochen.\n\n"
				+"8. Es besteht Sprungpflicht. Das heißt ist ein"
				+" Sprung möglich darf nicht gezogen werden.\n\n"
				+"9. Wer keinen Zug mehr machen kann, hat die Partie verloren.\n\n"
				+"10. Wenn 30 Züge (Zug Schwarz und Zug Weiß)"
				+" ohne Zug erfolgten, endet das Spiel unentschieden.\n\n\n"
				+"Viel Spaß beim Spielen...");
			
			jTextPane1.setPreferredSize(new java.awt.Dimension(520, 724));
			jTextPane1.setEditable(false);
			jTextPane1.setBackground(new java.awt.Color(144, 167, 255));
			jTextPane1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1,
					false));
			jTextPane1.setFont(new java.awt.Font("Dialog", 1, 12));

		}

		/*
		 * Button Version
		 * Liegt im Menü
		 * Zeigt die Version und die Mitwirkenden
		 */

		v = new JFrame("Version");
		v.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		{
			jTextPane2 = new JTextPane();
			v.getContentPane().add(jTextPane2, BorderLayout.CENTER);
			jTextPane2.setText("*Version*: \t0.42\n\nMitwirkende:\n\n"
				+"Christian Otto, Sven Withus, Christian Beulke,"
				+" Franziska Klingner, Martin Schewe, Kathrin Asshauer"
				+" und Stefanie Muehlhausen  ");
			jTextPane2.setEditable(false);
			jTextPane2.setBackground(new java.awt.Color(144, 167, 255));
			jTextPane2.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1,
					false));
			jTextPane2.setFont(new java.awt.Font("Dialog", 1, 14));
			jTextPane2.setPreferredSize(new java.awt.Dimension(450, 678));
		}
		v.setVisible(false);
		v.setSize(240, 200);
		v.setResizable(false);
		v.setPreferredSize(new java.awt.Dimension(240, 205));
	}


/*______________onClick()____________________________________________________*/

	/**
	 * OnClick Aktion auf den Button "Version" im Menu.
	 * @param e
	 */
	void buttonVersion_actionPerformed(ActionEvent e) {
		v.setVisible(true);
	}

	/**
	 * OnClick Aktion auf den Button "Anleitung" im Menu.
	 * @param e
	 */
	void buttonAnleitung_actionPerformed(ActionEvent e) {
		A.setVisible(true);
	}

	/**
	 * OnClick Aktion auf den Knopf "Aufgeben" im Menu.
	 * @param e
	 */
	void buttonAufgeben_actionPerformed(ActionEvent e) {

		if (x % 2 == 0)
			jTextFeld1.setText("Spieler 1 hat aufgegeben");
		else
			jTextFeld1.setText("Spieler 2 hat aufgegeben");
		
		leeren();
		loeschen = false;
	}

	/**
	 * OnClick Aktion beim Klicken auf den Knopf "Grafik" im Anfagsauswahlbildschirm.
	 * @param e
	 */
	static void buttonGUI_actionPerformed(ActionEvent e) {

		St.setVisible(false);
		Spielfeld.setGrafikmodus(true);
		SwingUtilities.invokeLater(new Runnable() {
		 	public void run() {
		 		DameGUI inst = new DameGUI();
		 		inst.setLocationRelativeTo(null);
		 		inst.setVisible(true);
		 	}
		 }
		);
	}

	/**
	 * OnClick Aktion beim Klicken auf den Knopf "Konsole" im Anfagsauswahlbildschirm.
	 * @param e
	 * @throws EndGameException
	 */
	static void buttonKonsole_actionPerformed(ActionEvent e) {

		St.setVisible(false);
		Spielfeld.setGrafikmodus(false);

		// Auswahl der Spielart, solange die Grafik noch nicht eingebunden ist
		System.out.println("Bitte wählen Sie die Spielart: \n"
				+ "1: Mensch gegen Mensch \n" + "2: Mensch gegen Computer \n"
				+ "3: Computer gegen Computer\n");
		int art = Integer.parseInt(einlesen());
		Spieler Schwarz;
		Spieler Weiss;
		switch (art) {
		case 1:
			Schwarz = new IntSpieler();
			Weiss = new IntSpieler();
			break;
		case 2:
			Schwarz = new IntSpieler();
			Weiss = new CompSpieler();
			break;
		case 3:
			Schwarz = new CompSpieler();
			Weiss = new CompSpieler();
			break;

		default:
			Schwarz = new IntSpieler();
			Weiss = new IntSpieler();
			break;
		}

		// Ein neues Spielfeld wird initialisiert.
		Board board = new Board(8,50);
		Spielfeld feld = new Spielfeld(Schwarz, Weiss, board);

		boolean spiel_laeuft = true;

		// Schleife fuer den Spielablauf. Wird beendet, wenn eine Sieg- bzw. Pattbedingung
		// erfuellt ist.
		while (feld.getWeisseSteine() != 0
				&& feld.getSchwarzeSteine() != 0
				&& !(feld.getWeisseSteine() == 1 && feld.getSchwarzeSteine() == 1)
				&& feld.getZuegeOhneSchlag() != 60 && spiel_laeuft) {
			System.out.println(feld.toString());
			try {
				feld.naechsterZug();
			} catch (DamePPException eee) {
				System.out.println("Ein Fehler ist in der"
						+ " ausfuehrbaren Klasse aufgetreten...");
				eee.printStackTrace();
			} catch (NullPointerException eee) {
				System.out.println("Ein Spieler hat aufgegeben.");
				spiel_laeuft = false;
			}
		}
		// Siegmeldungen fuer die entsprechenden Spieler.
		if (feld.getWeisseSteine() == 0)
			System.out.println("TATAAAA! Schwarz hat gewonnen!!!");
		else if (feld.getSchwarzeSteine() == 0)
			System.out.println("JIPPIEEE! Weiss hat gewonnen!!!");
		else if (feld.getWeisseSteine() == 1 && feld.getSchwarzeSteine() == 1)
			System.out.println("Leider ein Unentschieden...");
		else if (feld.getZuegeOhneSchlag() == 60)
			System.out.println("Laaangweilig. Leider ein Unentschieden.");

	}

/*______________KonsolenSpiel________________________________________________*/

	/**
	 * Mit {@code einlesen()} wird auf der Konsole eine Zahl zwischen 1-4
	 * eingelesen, die sp&auml;ter dazu verwendet wird, einen Spielmodus
	 * auszuw&auml;hlen.
	 */
	public static String einlesen() {
		String wert = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			wert = in.readLine().trim();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Nur Eingaben 1-4 sollen akzeptiert werden,
		// alle anderen Eingaben mit dem reg. Ausdruck vergleichen 
		while (!(matches(wert))) {
			System.out.println("Ungueltige Eingabe. Nur Zahlen"
					+ " zwischen 1 und 8 (bzw. 'ENTER')"
					+ " sind erlaubt.");
			wert = einlesen();
		}

		return wert;
	}

/*______________grafisches Spiel_____________________________________________*/

	/**
	 * Wird Aktiviert beim Klicken auf den Men&uuml;punkt: Spieler vs PC
	 * @param e
	 */
	void buttonComputer_actionPerformed(ActionEvent e) {
		jTextFeld1.setText(" Spieler vs CPU: Spieler 1 (Schwarz) beginnt");
		board = new Board(8, 50);
		
		Spieler A = new GraSpieler(board);
		Spieler B = new CompSpieler();


		Spielfeld feld = new Spielfeld(A, B, board,jTextFeld1);
		BT = new BoardThread(board, feld, jTextFeld1, jPanelBoard);
		BT.start();
	}

	/**
	 * Wird Aktiviert beim Klicken auf den Men&uuml;punkt: Spieler vs Spieler
	 * @param e
	 */
	void buttonSpieler_actionPerformed(ActionEvent e) {
		
		jTextFeld1.setText(" Spiel vs Spieler: Spieler 1 (Schwarz) beginnt");
		// festgelegt auf zwei interaktive Spieler.
		

		board = new Board(8, 50);
		
		Spieler A = new GraSpieler(board);
		Spieler B = new GraSpieler(board);


		Spielfeld feld = new Spielfeld(A, B, board,jTextFeld1);
		BT = new BoardThread(board, feld, jTextFeld1, jPanelBoard);
		BT.start();
	}

	/**
	 * Wird Aktiviert beim Klicken auf den Men&uuml;punkt LAN: KI vs KI 
	 * zu testzwecken mit unserer KI gegen unsere KI
	 * @param e
	 */
	void buttonLan_ki_actionPerformed(ActionEvent e) {
		jTextFeld1.setText(" Zu Testzwecken: Computer vs. Computer");
		board = new Board(8, 50);
		
		Spieler A = new CompSpieler();
		Spieler B = new CompSpieler();

		Spielfeld feld = new Spielfeld(A, B, board,jTextFeld1);
		
		BT = new BoardThread(board, feld, jTextFeld1, jPanelBoard);
		BT.start();
	}


/*-------Hilfsmethoden-------------------------------------------------------*/

	/**
	 * Diese Methode initialisiert das "Mainframe" bzw. setzt die Gr&ouml;&szlig;e
	 * des Auswahlfensters fest.
	 * 
	 */
	private void initialisiere() {
		this.setSize(new java.awt.Dimension(357, 369));

	}
	
	/**
	 * {@code matches()} liefert einen {@code boolean} zur&uuml;ck, um anzuzeigen,
	 * ob &uuml;bergebene Parameter dem angegebenen Muster entspricht. Akzeptiert
	 * nur Ziffern zwischen 1 und 4.
	 *
	 * @param s Zu &uuml;berpr&uuml;fende Eingabe
	 * @return true, wenn die Eingabe eine Ziffer zwischen 1 und 4 ist.
	 */
	public static boolean matches(String s) {
		Pattern p = Pattern.compile("[1-4]");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * Leert das Brett Grafisch und in der Verwaltung (alle Spielsteine werden
	 * vom Spielbrett entfernt).
	 */
	private void leeren() {
		if ((Spielfeld.getSpielfeld() != null)) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 4; x++) {
					int x_pos = KI.berechneXPos(x, y);
					if ((Spielfeld.getSpielfeld()[y][x_pos].is_Besetzt())
							&& (board.stein(x_pos, y) != null)) {
						board.stein(x_pos, y).remove();
					}
				}
			}
		}
		Spielfeld.FeldLeeren();
	}

	/**
	 * Initialisierung des Hauptfensters mit Bordlayout
	 * Setzt das Hauptfenster 
	 * Setzt das Brett Nach Norden
	 * Setzt Das Textfeld in die Mitte
	 * Setzt das Menü
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			BorderLayout thisLayout = new BorderLayout();
			this.setTitle("Dame");
			this.setResizable(false);
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(89, 95, 216));

			{
				// Layout wo was Auf dem Frame liegt dafür wurden
				//mehrere Panels angelegt die nach dem BorderLayout aufgeteilt wurden
				
				board = new Board(8, 50);

				jPanelBoard = new JPanel();
				jPanelKnopf = new JPanel();
				jPanelTextfeld = new JPanel();

				getContentPane().add(jPanelBoard, BorderLayout.NORTH);
				getContentPane().add(jPanelTextfeld, BorderLayout.CENTER);
				jPanelBoard.setBackground(new java.awt.Color(89, 95, 216));
				jPanelTextfeld.setBackground(new java.awt.Color(89, 95, 216));
//				jPanelBoard.add(board);
				board.setSize(420, 480);
				{
					// Textfeld um mit dem Benutzer zu kommunizieren

					jTextFeld1 = new JTextField();
					jPanelTextfeld.add(jTextFeld1);
					jTextFeld1.setText("Willkommen - Bitte"
						+" suche dir unter Optionen eine Spielart aus!");
					jTextFeld1.setPreferredSize(new java.awt.Dimension(400, 30));
				}
		
				// Das Menü und seine Komponenten
				{
					// Menüpunkt Spiel
					Menu = new JMenuBar();
					setJMenuBar(Menu);
					{
						Spiel = new JMenu();
						Menu.add(Spiel);
						Spiel.setText("Spiel");
						{
							Aufgeben = new JMenuItem();
							Spiel.add(Aufgeben);
							Aufgeben.setText("Aufgeben");
							Aufgeben.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(ActionEvent e) {
									buttonAufgeben_actionPerformed(e);
								}
							});
						}
						{
							Start = new JMenu();
							Spiel.add(Start);
							Start.setText("Start");
							{
								Computer = new JMenuItem();
								Start.add(Computer);
								Computer.setText("Spieler vs. Computer");
								Computer.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(ActionEvent e) {
										buttonComputer_actionPerformed(e);
									}
								});

							}
							{
								Spieler = new JMenuItem();
								Start.add(Spieler);
								Spieler.setText("Spieler vs. Spieler");
								Spieler.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(ActionEvent e) {
										buttonSpieler_actionPerformed(e);
									}
								});
							}
							{
								Lan_ki = new JMenuItem();
								Start.add(Lan_ki);
								Lan_ki.setText("Computer vs. Computer");
								Lan_ki.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(ActionEvent e) {
										buttonLan_ki_actionPerformed(e);
									}
								});
							}
						}
					}
					// Menüpunkt Info
					{
						Info = new JMenu();
						Menu.add(Info);
						Info.setText("Info");
						{
							Version = new JMenuItem();
							Info.add(Version);
							Version.setText("Version");
							Version.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(ActionEvent e) {
									buttonVersion_actionPerformed(e);
								}
							});
						}
						{
							Anleitung = new JMenuItem();
							Info.add(Anleitung);
							Anleitung.setText("Anleitung");
							Anleitung.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(ActionEvent e) {
									buttonAnleitung_actionPerformed(e);
								}
							});
						}
					}
				}
				pack();
				this.setSize(420, 100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
