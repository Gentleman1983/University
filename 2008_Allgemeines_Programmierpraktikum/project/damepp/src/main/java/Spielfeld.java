/**
 * Allgemeines Programmierpraktikum Sommersemester 2008
 * Georg-August-Universitaet Goettingen (Dr. H. Brosenne)
 * Projektarbeit zum Thema DamePP
 * 
 * Gruppe: DamePP-2AD
 * Teamleitung: Christian Otto
 * Projektbetreuung: David-Alexandre Guiraud
 * 
 * 
 * Entwickler:
 * Kathrin Aßhauer, Christian Beulke, Franziska Klingner,
 * Stefanie Mühlhausen, Christian Otto, Martin Schewe, 
 * Sven Withus
 */

package damepp;

import java.util.ArrayList;
import javax.swing.*;
import damepp.Board.Stein;
import java.rmi.*;

/**
 * Die Klasse {@code Spielfeld} repr&auml;sentiert den zentralen Ablauf des
 * Spiels. Das eigentliche Spielfeld wird mittels eines Arrays aus {@code Feld}-
 * Instanzen erstellt. Diese beinhalten je nach Position die jeweiligen
 * Attribute.
 * 
 * @author Kathrin Aßhauer
 * @author Christian Beulke
 * @author Franziska Klingner
 * @author Stefanie Mühlhausen
 */
class Spielfeld {

	/* =======Datenfelder========================================================= */
	/** Die gr&ouml;e des Spielfeldes */
	private final static int dimension = 8;

	private static Board board;

	/** Array aus Feld-Instanzen zur Repr&auml;sentation des Spielfeldes */
	private static Feld[][] Spielfeld = new Feld[dimension][dimension];

	/** Instanz des schwarzen Spielers */
	private Spieler SpielerSchwarz;

	/** Instanz des weissen Spielers */
	private Spieler SpielerWeiss;

	/** Instanz des lokalen Spielers fuer */
	private Spieler SpielerLokal;

	/** Anzahl der Steine des schwarzen Spielers auf dem Spielfeld */
	private static int SteineSchwarz;

	/** Anzahl der Steine des wei&szlig;en Spielers auf dem Spielfeld */
	private static int SteineWeiss;

	/**
	 * Anzahl der vergangenen Z&uuml;ge, ohne dass ein Spielstein vom Feld
	 * entfernt wurde (f&uuml;r die 30-Z&uuml;ge-ohne-Schlag-Regel).
	 */
	private static int ZuegeOhneSchlag = 0;

	/**
	 * Zeigt an, ob der schwarze Spieler am Zug ist. Im ersten Zug immer true,
	 * da Schwarz Startspieler ist.
	 */
	private static boolean ZugSpielerSchwarz = true;

	/** Zeigt an, ob unser Lanspieler schwarz ist. */
	private static boolean LanSchwarz = true;

	/** Status des Spielfeldes, nur für den LanSpieler */
	public static int status = Status.OK;

	/**
	 * Beinhaltet die im aktuellen Zug zu entfernenden Steine (Felder auf
	 * besetzt=false setzen).
	 */
	private static ArrayList<Zugkoordinate> steineEntfernen = new ArrayList<Zugkoordinate>();

	/**
	 * b.add(b.new Stein("weisse_dame.png", 3, 0)); Nutzen wir den Grafikmodus?
	 */
	private static boolean Grafikmodus = true;

	public String ortStein = "./images";

	/**
	 * Beinhaltet die im aktuellen Zug zu setzenden Steine (Felder auf
	 * besetzt=true setzen).
	 */
	private static ArrayList<Zugkoordinate> steineSetzen = new ArrayList<Zugkoordinate>();

	/**
	 * Beinhaltet die im aktuellen Zug eingenommenen Zwischenpositionen im Falle
	 * eines Mehrfachsprungs fr das entfernen bei der Auswahl der graphischen
	 * Oberfl&aumlche Hier m&uumlssen anschliessend die Steine wieder entfernt
	 * werden.
	 */
	private static ArrayList<Zugkoordinate> zwischenSteineEntfernen = new ArrayList<Zugkoordinate>();

	private static JTextField JTextFeld;

	/* ========Setter============================================================== */

	private static void setWirSchwarz(boolean b) {
		LanSchwarz = b;
	}

	/* =======Getter============================================================== */
	/*
	 * Die Getter sind fuer die Bereitstellung der Daten in Spielfeld da, ohne
	 * dabei ein veraendern dieser Daten zu ermoeglichen!
	 */

	/**
	 * Gettermethode f&uuml;r den Grafikmodus.
	 */

	private static boolean getGrafikmodus() {
		return Grafikmodus;
	}

	/** Getter f&uuml;r das Spielfeld. */
	public static Feld[][] getSpielfeld() {
		return Spielfeld;
	}

	/** Zeigt an, ob der schwarze Spieler gerade am Zug ist (Getter). */
	public static boolean getZugSchwarz() {
		return ZugSpielerSchwarz;
	}

	/** Getter f&uuml;r die Gr&ouml;&szlig;e des Spielfeldes. */
	public int getDimension() {
		return dimension;
	}

	/**
	 * Getter f&uuml;r einzelne Feld-Instanzen im Spielfeld-Array an der
	 * Position X/Y.
	 */
	public Feld feld(int x, int y) {
		return Spielfeld[x][y];
	}

	/** Getter f&uuml;r die Anzahl der schwarzen Steine auf dem Spielfeld */
	public int getSchwarzeSteine() {
		return SteineSchwarz;
	}

	/** Getter f&uuml;r die Anzahl der wei&szlig;en Steine auf dem Spielfeld */
	public int getWeisseSteine() {
		return SteineWeiss;
	}

	/**
	 * Beinhaltet die Anzahl vergangener Z&uuml;ge, w&auml;hrend denen kein
	 * Schlag stattgefunden hat (f&uuml;r die 30-Z&uuml;ge-ohne-Schlag-Regel).
	 */
	public int getZuegeOhneSchlag() {
		return ZuegeOhneSchlag;
	}

	/**
	 * Diese Methode leert das Spielfeld und setzt die Z&uuml;ge ohne Schlag
	 * zur&uuml;ck.
	 */

	public static void FeldLeeren() {
		Spielfeld = new Feld[dimension][dimension];
		ZuegeOhneSchlag = 0;

	}

	/* =======Konstruktoren======================================================= */

	/**
	 * Konstruktor f&uuml;r ein Spielfeld. (Spieler als {@code new XXXSpieler}
	 * &uuml;bergeben).
	 * 
	 * @param SpielerSchwarz
	 *            Schwarzer Spieler.
	 * @param SpielerWeiss
	 *            Wei&szlig;er Spieler.
	 * @param JTF
	 *            Das JavaTextFeld.
	 */
	Spielfeld(Spieler SpielerSchwarz, Spieler SpielerWeiss, JTextField JTF) {
		JTextFeld = JTF;
		setSpieler(SpielerSchwarz, SpielerWeiss);
		this.initialisieren();
	}

	/**
	 * Konstruktor f&uuml;r ein Spielfeld. (Spieler als {@code new XXXSpieler}
	 * &uuml;bergeben).
	 * 
	 * @param Schwarzer
	 *            Spieler.
	 * @param Wei&szlig;er
	 *            Spieler.
	 */
	Spielfeld(Spieler SpielerSchwarz, Spieler SpielerWeiss) {
		setSpieler(SpielerSchwarz, SpielerWeiss);
		this.initialisieren();
	}

	/**
	 * Konstruktor f&uuml;r ein Spielfeld. (Spieler als {@code new XXXSpieler}
	 * &uuml;bergeben).
	 * 
	 * @param lokaler
	 *            Spieler
	 * 
	 * 
	 */
	Spielfeld(Spieler SpielerLokal, boolean schwarzdran) {
		this.SpielerLokal = SpielerLokal;
		LanSchwarz = schwarzdran;
		this.initialisieren();
	}

	/**
	 * Konstruktor f&uuml;r ein Spielfeld.
	 * 
	 * @param SpielerSchwarz  Der schwarze Spieler.
	 * @param SpielerWeiss Der wei&szlig;e Spieler.
	 * @param gboard Das grafische Spielfeld.
	 * @param JTF Dad JTextField.
	 */
	Spielfeld(Spieler SpielerSchwarz, Spieler SpielerWeiss, Board gboard,
			JTextField JTF) {
		setSpieler(SpielerSchwarz, SpielerWeiss);
		board = gboard;
		JTextFeld = JTF;
		this.initialisieren();
	}

	/**
	 * Konstruktor mit Spielerinstanzen und grafischem Board.
	 * @param SpielerSchwarz  Der schwarze Spieler.
	 * @param SpielerWeiss Der wei&szlig;e Spieler.
	 * @param gboard Das grafische Spielfeld.
	 */
	Spielfeld(Spieler SpielerSchwarz, Spieler SpielerWeiss, Board gboard) {
		setSpieler(SpielerSchwarz, SpielerWeiss);
		board = gboard;
		this.initialisieren();
	}

	/* =======Methoden============================================================ */

	/**
	 * Initialisiert ein neues Spielfeld.
	 */
	private void initialisieren() {
		// Erste for-Schleife durchlaeuft alle Y
		for (int i = 0; i < dimension; i++) {
			// Zweite for-Schleife ist in die Erste geschachtelt und
			// durchlaeuft alle X
			for (int j = 0; j < dimension; j++) {
				/*
				 * Bedingung fuer weisse Felder: gerades Y UND gerades X oder
				 * ungerades Y UND ungerades X, d.h. Summe x+y gerade
				 */
				if (((i % 2 == 0) && (j % 2 == 0))
						|| ((i % 2 != 0) && (j % 2 != 0))) {
					Spielfeld[i][j] = new Feld(false, false, false, false);
				}
				/*
				 * Bedingung fuer schwarze Felder: gerades Y und UNgerades X
				 * oder UNgerades Y und gerades X
				 */
				else if (((i % 2 == 0) && (j % 2 != 0))
						|| ((i % 2 != 0) && (j % 2 == 0))) {
					Spielfeld[i][j] = new Feld(true, false, false, false);
				}
			}
		}

		try {
			// Schleife durchlaeuft alle Y
			for (int i = 0; i < dimension; i++) {
				// Schleife durchlaeuft alle X
				for (int j = 0; j < dimension; j++) {
					// Die ersten drei Reihen schwarze Felder
					// werden mit weissen Spielsteinen besetzt
					if (i < 3) {
						if (Spielfeld[i][j].is_schwFeld()) {
							Spielfeld[i][j].besetzen();
							Spielfeld[i][j].makeSteinWeiss();
							SteineWeiss++;
							board.add(board.new Stein(ortStein
									+ "/weisser_stein.png", j, i));
						}
					}
					// Die letzten drei Reihen schwarze Felder
					// werden mit schwarzen Spielsteinen besetzt
					else if (i > 4) {
						if (Spielfeld[i][j].is_schwFeld()) {
							Spielfeld[i][j].besetzen();
							Spielfeld[i][j].makeSteinSchwarz();
							SteineSchwarz++;
							board.add(board.new Stein(ortStein
									+ "/schwarzer_stein.png", j, i));
						}
					}
				}
			}
		} catch (DamePPException e) {
			if (Grafikmodus) {
				JTextFeld.setText("Es ist ein Fehler beim"
						+ " generieren des Spielfeldes aufgetreten!");
			} else {
				System.out.println("Es ist ein Fehler beim"
						+ " generieren des Spielfeldes aufgetreten!");
			}
			e.printStackTrace();
		}
	}

	/**
	 * Settermethode f&uuml;r den Grafikmodus.
	 * 
	 * @param Grafik
	 *            true, wenn der Grafikmodus aktiv ist.
	 */

	protected static void setGrafikmodus(boolean Grafik) {
		Grafikmodus = Grafik;
	}

	/**
	 * Hier werden die Instanzen von Spieler &uuml;bergeben, die gegeneinander
	 * spielen sollen. Aufruf per {@code new XXXSpieler}.
	 * 
	 * @param SpielerSchwarz
	 *            Der schwarze Spieler.
	 * @param SpielerWeiss
	 *            Der wei&szlig;e Spieler.
	 */
	private void setSpieler(Spieler SpielerSchwarz, Spieler SpielerWeiss) {
		this.SpielerSchwarz = SpielerSchwarz;
		this.SpielerWeiss = SpielerWeiss;
	}

	/**
	 * {@code naechsterSpieler()} &uuml;bergibt das Spiel an den n&auml;chsten Spieler.
	 * Ab sofort kann der alte Spieler keine Aktionen mehr durchf&uuml;hren, bis
	 * der neue Spieler seinen Zug ausgef&uuml;hrt hat.
	 */
	public void naechsterSpieler() {
		ZugSpielerSchwarz  = !ZugSpielerSchwarz; // ZugSpielerSchwarz  negieren
	}

	/**
	 * Der Spieler, der gerade an der Reihe ist, muss seinen Zug eingeben. Die
	 * daraus resultierende ArrayList wird dann verarbeitet, wobei das
	 * allererste Element dieser ArrayList die Koordinate des Steines ist,
	 * welcher bewegt werden soll. Wurde
	 * 
	 * @throws WrongTurnStartException
	 * @throws DamePPException
	 */
	public void naechsterZug() throws DamePPException {
		// "zug" wird im spaeteren Programmverlauf den vom Spieler
		// abgefragten Spielzug enthalten.
		ArrayList<Zugkoordinate> zug = null;
		// Es wird geprueft, welcher Spieler an der Reihe ist, eine Meldung
		// ausgegeben und dann der aktuelle Zug vom Spieler abgefragt.
		if (ZugSpielerSchwarz) {
			if (Grafikmodus) {
				JTextFeld.setText("Spieler schwarz ("
						+ this.getSchwarzeSteine() + " Steine) an der Reihe.");
			} else {
				System.out.println("Spieler schwarz ("
						+ this.getSchwarzeSteine() + " Steine) an der Reihe.");
			}
			zug = SpielerSchwarz.holZug();
		} else if (!ZugSpielerSchwarz) {
			if (Grafikmodus) {
				JTextFeld.setText("Spieler weiss (" + this.getWeisseSteine()
						+ " Steine) an der Reihe.");
			} else {
				System.out.println("Spieler weiss (" + this.getWeisseSteine()
						+ " Steine) an der Reihe.");
			}
			zug = SpielerWeiss.holZug();
		}

		// Die erste Koordinate aus dem Spielzug wird abgefragt und in Start
		// gespeichert.
		// Diese Koordinate repraesentiert den Stein, welcher bewegt werden
		// soll.
		String s = "";
		if (ZugSpielerSchwarz) {
			s += "Schwarz: ";
		} else {
			s += "Weiss: ";
		}
		for (int i = 0; i < zug.size(); i++) {
			s += zug.get(i);
		}
		// System.out.println(s);

		Zugkoordinate start = zug.remove(0);

		/*
		 * Die if-Bedingung prueft, ob auf dem Feld mit der ersten Koordinate
		 * ueberhaupt ein Stein steht bzw. wenn dies so ist, ob der Stein auch
		 * dem gerade setzenden Spieler gehoert.
		 */
		if (!Spielfeld[start.getY()][start.getX()].is_Besetzt()
				|| (Spielfeld[start.getY()][start.getX()].is_schwStein() && !ZugSpielerSchwarz)
				|| (!Spielfeld[start.getY()][start.getX()].is_schwStein() && ZugSpielerSchwarz)) {
			throw new WrongTurnStartException("Der Zug soll mit einer"
					+ " nicht gueltigen Koordinate begonnen werden!");
		}

		/*
		 * In der folgenden while-Schleife wird die ArrayList so lange
		 * durchlaufen, bis alle Elemente (= Zuege) entfernt und ausgewertet
		 * wurden.
		 */
		// Die alte Position des Spielsteines
		Zugkoordinate koord_alt = start;
		boolean sprungPflicht = false;

		// Die Schleife wird so lange durchlaufen, bis keine Koordinaten mehr im
		// Zug vorhanden sind.
		while (!zug.isEmpty()) {
			// Die zukuenftige Position des Spielsteines
			Zugkoordinate koord_neu = zug.remove(0);

			// "Spielzug" prueft, ob es sich um einen gueltigen Spielzug handelt
			// (Regeln).
			if (Spielzug(koord_alt, koord_neu, sprungPflicht, ZugSpielerSchwarz, start)) {
				// Fuer den Fall, dass weitere Zuege folgen (Mehrfachsprung)
				// wird die Zielkoordinate als neue Startkoordinate gespeichert.
				koord_alt = koord_neu;
				// Meldung fuer den Spieler.
			} else {
				// Sollte es sich um einen nicht korrekten Zug handeln, so wird
				// keine Veraenderung am Spielfeld vorgenommen und dies auf der
				// Konsole mitgeteilt.
				if (Grafikmodus) {
					JTextFeld.setText("Der uebergebene Zug ist nicht gueltig.");
				} else {
					System.out
							.println("Der uebergebene Zug ist nicht gueltig.");
				}
				for (int i = 0; i < steineSetzen.size(); i++) {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].undoUeberspringen();
				}
				for (int i = 0; i < steineEntfernen.size(); i++) {
					Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen
							.get(i).getX()].undoUeberspringen();
				}
				// Die erstellten Listen der zu entfernenden und zu setzenden
				// Steine werden geleert.
				zwischenSteineEntfernen.clear();
				steineSetzen.clear();
				steineEntfernen.clear();
				return;
			}
			sprungPflicht = true;
		}
		// Nachdem alle uebergebenen Koordinaten auf die Richtigkeit ueberprueft
		// wurden, werden alle Steine gesetzt bzw. Steine von den alten
		// Positionen/uebersprungene Steine entfernt
		// Beim setzen muss hierbei zwischen weissen und schwarzen Steinen
		// unterschieden werden
		// ACHTUNG! Hier werden sowohl gesetzte Steine von ihren alten
		// Positionen entfernt, als auch uebersprungene Steine. Daher
		// muss getestet werden, ob der zu entfernende Stein dieselbe Farbe
		// hat wie der aktuelle Spieler (Stein wurde bloss versetzt) oder ob
		// es ein gegnerischer Stein ist (Stein wird ganz vom Brett genommen).
		// Zu unterscheiden ist hierbei zwischen graphischer Oberflaeche und
		// Konsole.
		// Bei graphischer Oberflaeche muessen die Steine zunaechst alle
		// entfernt werden und dann gesetzt werden, ansonsten besteht das
		// Problem, dass mehrere Steine ueberienander ueberlagert werden.
		// Ausserdem muessen die Zwischenpositionen von Mehrfachspruengen
		// explizit zum Schluss mit Hilfe der Liste zwischenSteineEntfernen
		// entfernt werden.

		// Bei Auswahl der graphischen Oberlfaeche muessen zunaechst alle zu
		// entfernenden Steine (Startpositionen, uebersprungene Steine) entfernt
		// werden. Dies darf nur auf Feldern geschehen, die auch durch einen
		// Stein tatsaechlich besetzt sind, dies ist bei Mehrfachspruengen bei
		// den Zwischenschritten beispielsweise nicht gegeben.
		for (int i = 0; i < steineEntfernen.size(); i++) {
			if (Grafikmodus) {
				if (Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen
						.get(i).getX()].is_Besetzt()) {
					board.stein(steineEntfernen.get(i).getX(),
							steineEntfernen.get(i).getY()).remove();
				}
			}
		}

		// Setzen der Steine auf die neuen Positionen.
		// Hierzu muessen zunaechst alle Felder als besetzt gekennzeichnet
		// werden.
		for (int i = 0; i < steineSetzen.size(); i++) {
			Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i).getX()]
					.besetzen();

			// Zu unterscheiden ist zwischen schwarzen und weien Steinen, ob
			// eine Dame gesetzt werden muss oder nicht (2 Moeglichkeiten: Dame
			// ist bereits Dame, Stein erreicht gegnerische Spielfeldkante und
			// wird zur Dame)

			// Bei schwarzen Steinen
			if (ZugSpielerSchwarz) {
				// Dame wurde versetzt.
				if (Spielfeld[start.getY()][start.getX()].is_Dame()) {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinSchwarz();
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeDame();
					if (Grafikmodus) {
						board.add(board.new Stein(ortStein
								+ "/schwarze_dame.png", steineSetzen.get(i)
								.getX(), steineSetzen.get(i).getY()));
					}
				}
				// normaler Stein wurde versetzt
				// Unterscheidung bei graphischer Oberflaeche, ob gegnerische
				// Spielfeldgrenze erreicht oder
				// nicht, wenn ja, setzten einer Dame
				// Setzen von Damen bei Konsole geschieht an spaeterer Stelle
				else {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinSchwarz();
					if (Grafikmodus) {
						// Dame muss gesetzt werden
						if (steineSetzen.get(i).getY() == 0) {
							board.add(board.new Stein(ortStein
									+ "/schwarze_dame.png", steineSetzen.get(
									i).getX(), steineSetzen.get(i).getY()));
						}
						// normaler Stein muss gesetzt werden
						else {
							board
									.add(board.new Stein(ortStein
											+ "/schwarzer_stein.png",
											steineSetzen.get(i).getX(),
											steineSetzen.get(i).getY()));
						}
					}
				}
			}
			// Bei weissen Steinen
			else {
				// Analog zu schwarzem Stein:
				// Dame wurde versetzt.
				if (Spielfeld[start.getY()][start.getX()].is_Dame()) {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinWeiss();
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeDame();
					if (Grafikmodus) {
						board.add(board.new Stein(ortStein
								+ "/weisse_dame.png", steineSetzen.get(i)
								.getX(), steineSetzen.get(i).getY()));
					}
				}
				// Analog zu schwarzem Stein:
				// normaler Stein wurde versetzt
				// Unterscheidung bei graphischer Oberflaeche, ob gegnerische
				// Spielfeldgrenze erreicht oder
				// nicht, wenn ja, setzten einer Dame
				// Setzen von Damen bei Konsole geschieht an spaeterer Stelle
				else {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinWeiss();
					if (Grafikmodus) {
						// Dame muss gesetzt werden
						if (steineSetzen.get(i).getY() == 7) {
							board.add(board.new Stein(ortStein
									+ "/weisse_dame.png", steineSetzen.get(
									i).getX(), steineSetzen.get(i).getY()));
						}
						// normaler Stein muss gesetzt werden
						else {
							board
									.add(board.new Stein(ortStein
											+ "/weisser_stein.png",
											steineSetzen.get(i).getX(),
											steineSetzen.get(i).getY()));
						}
					}
				}
			}
			// Wenn die gegnerische Spielfeldgrenze erreicht wurde, muss der
			// Stein die Markierung Dame erhalten.
			// Schwarzer Stein erreicht die gegenerische Spielfeldgrenze
			if (ZugSpielerSchwarz && steineSetzen.get(i).getY() == 0) {
				Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
						.getX()].makeDame();
			}
			// Weisser Stein erreicht die gegnerische Spielfeldgrenze
			else if (!ZugSpielerSchwarz && steineSetzen.get(i).getY() == 7) {
				Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
						.getX()].makeDame();
			}
		}

		// Entfernen aller bewegten und uebersprungenen Steine.
		for (int i = 0; i < steineEntfernen.size(); i++) {
			// Spieler Weiss ist am Zug, es soll ein schwarzer Stein
			// entfernt werden -> Anzahl schwarzer Steine dekrementieren!
			if (Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].is_schwStein()
					&& !ZugSpielerSchwarz) {
				entferneStein(ZugSpielerSchwarz);
				// Da am Ende JEDEN Zuges die 30-Zuege...-Variable inkrementiert
				// wird, ist
				// sie am Ende DIESEN Zuges = 0.
				ZuegeOhneSchlag = -1;
			}
			// Spieler Schwarz ist am Zug, es soll ein weisser Stein
			// entfernt werden -> Anzahl weisser Steine dekrementieren!
			else if (!Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen
					.get(i).getX()].is_schwStein()
					&& ZugSpielerSchwarz) {
				entferneStein(ZugSpielerSchwarz);
				// Da am Ende JEDEN Zuges die 30-Zuege...-Variable inkrementiert
				// wird, ist
				// sie am Ende DIESEN Zuges = 0.
				ZuegeOhneSchlag = -1;
			}
			// Stein vom Spielfeld entfernen.
			Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].freigeben();
			Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].undoDame();
		}

		// Bei Auswahl der graphischen Oberflaeche muessen an dieser Stelle
		// explizit die Zwischenpositionen des springenden Steines entfernt
		// werden.
		if (Grafikmodus) {
			for (int i = 0; i < zwischenSteineEntfernen.size(); i++) {
				board.stein(zwischenSteineEntfernen.get(i).getX(),
						zwischenSteineEntfernen.get(i).getY()).remove();
			}
		}
		// Leeren der Listen.
		zwischenSteineEntfernen.clear();
		steineEntfernen.clear();
		steineSetzen.clear();
		// "naechsterSpieler()" bewirkt, dass der naechste Spieler an die Reihe kommt.
		this.naechsterSpieler();
		// Fuer die 30-Zuege-ohne-Schlag-Regel wird das entsprechende Datenfeld
		// in jedem Zug um 1
		// inkrementiert.
		ZuegeOhneSchlag++;
		System.out.println(this.toString());
	}

	/**
	 * Zug&uumlberpr&uumlfung f&uumlr den Lanspieler
	 */

	public static void versucheZug(ArrayList<Zugkoordinate> zug)
			throws DamePPException {

		Zugkoordinate start = zug.remove(0);

		/*
		 * Die if-Bedingung prueft, ob auf dem Feld mit der ersten Koordinate
		 * ueberhaupt ein Stein steht bzw. wenn dies so ist, ob der Stein auch
		 * dem gerade setzenden Spieler gehoert.
		 */
		if (!Spielfeld[start.getY()][start.getX()].is_Besetzt()
				|| (Spielfeld[start.getY()][start.getX()].is_schwStein() && !LanSchwarz)
				|| (!Spielfeld[start.getY()][start.getX()].is_schwStein() && LanSchwarz)) {
			throw new WrongTurnStartException("Der Zug soll mit einer"
					+ " nicht gueltigen Koordinate begonnen werden!");
		}

		/*
		 * In der folgenden while-Schleife wird die ArrayList so lange
		 * durchlaufen, bis alle Elemente (= Zuege) entfernt und ausgewertet
		 * wurden.
		 */
		// Die alte Position des Spielsteines
		Zugkoordinate koord_alt = start;
		boolean sprungPflicht = false;

		// Die Schleife wird so lange durchlaufen, bis keine Koordinaten mehr im
		// Zug vorhanden sind.
		while (!zug.isEmpty()) {
			// Die zukuenftige Position des Spielsteines
			Zugkoordinate koord_neu = zug.remove(0);

			if (!Spielzug(koord_alt, koord_neu, sprungPflicht, LanSchwarz,
					start)) {
				throw new DamePPException();
			}

			sprungPflicht = true;
		}
	}

	// Nachdem alle uebergebenen Koordinaten auf die Richtigkeit ueberprueft
	// wurden, werden alle Steine gesetzt bzw. Steine von den alten
	// Positionen/uebersprungene Steine entfernt
	// Beim setzen muss hierbei zwischen weissen und schwarzen Steinen
	// unterschieden werden
	/**
	 * Zug ausführen f&uuml;r den LanSpieler.
	 * 
	 * @param Die
	 *            ArrayListe der &uuml;bergebenen Koordinaten.
	 */

	public static void fuehreZugAus(ArrayList<Zugkoordinate> zug)
			throws DamePPException {
		Zugkoordinate start = zug.remove(0);
		for (int i = 0; i < steineSetzen.size(); i++) {
			Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i).getX()]
					.besetzen();
			if (LanSchwarz) {
				if (Spielfeld[start.getY()][start.getX()].is_Dame()) {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinSchwarz();
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeDame();
				} else {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinSchwarz();
				}
			} else {
				if (Spielfeld[start.getY()][start.getX()].is_Dame()) {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinWeiss();
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeDame();
				} else {
					Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
							.getX()].makeSteinWeiss();
				}
			}

			if (ZugSpielerSchwarz && steineSetzen.get(i).getY() == 0)
				Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
						.getX()].makeDame();
			else if (!LanSchwarz && steineSetzen.get(i).getY() == 7)
				Spielfeld[steineSetzen.get(i).getY()][steineSetzen.get(i)
						.getX()].makeDame();
		}
		// ACHTUNG! Hier werden sowohl gesetzte Steine von ihren alten
		// Positionen entfernt, als auch uebersprungene Steine. Daher
		// muss getestet werden, ob der zu entfernende Stein dieselbe Farbe
		// hat wie der aktuelle Spieler (Stein wurde bloss versetzt) oder ob
		// es ein gegnerischer Stein ist (Stein wird ganz vom Brett genommen).
		for (int i = 0; i < steineEntfernen.size(); i++) {
			// Spieler Weiss ist am Zug, es soll ein schwarzer Stein
			// entfernt werden -> Anzahl schwarzer Steine dekrementieren!
			if (Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].is_schwStein()
					&& !ZugSpielerSchwarz) {
				entferneStein(ZugSpielerSchwarz);
				// Da am Ende JEDEN Zuges die 30-Zuege...-Variable inkrementiert
				// wird, ist
				// sie am Ende DIESEN Zuges = 0.
				ZuegeOhneSchlag = -1;
			}
			// Spieler Schwarz ist am Zug, es soll ein weisser Stein
			// entfernt werden -> Anzahl weisser Steine dekrementieren!
			else if (!Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen
					.get(i).getX()].is_schwStein()
					&& ZugSpielerSchwarz) {
				entferneStein(ZugSpielerSchwarz);
				// Da am Ende JEDEN Zuges die 30-Zuege...-Variable inkrementiert
				// wird, ist
				// sie am Ende DIESEN Zuges = 0.
				ZuegeOhneSchlag = -1;
			}
			// Stein vom Spielfeld entfernen.
			Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].freigeben();
			Spielfeld[steineEntfernen.get(i).getY()][steineEntfernen.get(i)
					.getX()].undoDame();
		}
		steineEntfernen.clear();
		steineSetzen.clear();

		// Fuer die 30-Zuege-ohne-Schlag-Regel wird das entsprechende Datenfeld
		// in jedem Zug um 1
		// inkrementiert.
		ZuegeOhneSchlag++;

		// Nach jedem Zug wird die Anzahl der noch verbleibenden Steine
		// gezählt:
		// der Status des Spielfeldes wird geändert, falls einer der beiden
		// Spieler gewonnen hat, bzw. Patt
		if (SteineWeiss == 0)
			status = Status.BLACK_WINS;
		else if (SteineSchwarz == 0)
			status = Status.WHITE_WINS;
		else if (SteineWeiss == 1 && SteineSchwarz == 1)
			status = Status.DRAW;
	}

	/**
	 * Ueberpruefung, ob die Uebergebenen Koordinaten einem zulaessigen Spielzug
	 * entsprechen.
	 * 
	 * @param k1
	 *            Startkoordinate
	 * @param k2
	 *            Zielkoordinate
	 * @param sprungPflicht
	 *            Variable gibt an, ob es sich bei dem uebergebenen Spielzug um
	 *            einen Sprung handeln muss.
	 * @param schwarz
	 *            Variable gibt an, ob gerader der Spieler mit der Spielfarbe
	 *            schwarz am Zug ist
	 * @param start
	 *            Startkoordinate des zu bewegenden Steins
	 * @return Ob es sich um einen korrekten Zug handelt oder nicht
	 * @throws DamePPException
	 */
	private static boolean Spielzug(Zugkoordinate k1, Zugkoordinate k2,
			boolean sprungPflicht, boolean schwarz, Zugkoordinate start)
			throws DamePPException {
		// Rueckgabewert, zeigt an, ob es sich um einen korrekten Zug handelt
		boolean is_korrZug = false;
		// Sollte eine der uebergebenen Zugkoordinaten "null" sein, so wird
		// false
		// zurueckgeliefert und eine Meldung a.d. Konsole ausgegeben.
		if (k1 == null || k2 == null) {
			if (Grafikmodus) {
				JTextFeld
						.setText("Minimale Anzahl initialisierter Zugkoordinaten = 2");
			} else {
				System.out
						.println("Minimale Anzahl initialisierter Zugkoordinaten = 2");
			}
		} else {
			// Falls es sich bei den uebergebenen Koordinaten um gueltige
			// Koordinaten handelt, wird ueberprueft, ob es sich bei dem
			// Spielzug um einen korrekten Sprung handelt
			// Wenn ja, wird die alte Position in die Liste der zu entfernenden
			// Steine und die neue in die zu setzenden Steine eingefuegt.
			// Der uebersprungene Stein wird in die Liste der zu entfernenden
			// Steine bei Ueberpruefung auf den korrekten Zug aufgenommen.
			if (isSprung(k1, k2, sprungPflicht, start)) {
				is_korrZug = true;
				steineEntfernen.add(k1);
				steineSetzen.add(k2);
			} else if (!sprungPflicht) {
				// Falles es sich um keinen Sprung und auch keine Sprungpflicht
				// besteht, wird ueberprueft, ob evtl. ein Sprung moeglich ist.
				// Hierzu werden alle auf dem Brett stehenden Steine des
				// aktuellen Spielers ermittelt und anschliessend alle
				// potentiellen Sprunge auf korrekte Spruenge ueberprueft.
				is_korrZug = false;
				// ist ein Sprung moeglich fuer aktuelle Spielerfarbe?
				// Ermittlung aller in Frage kommender Steine fuer einen Sprung.
				ArrayList<Zugkoordinate> mglSteine2;
				try {
					mglSteine2 = mglSteine(schwarz);
					boolean mglSprung = mglSprung(mglSteine2);
					// Falls ein Sprung moeglich ist, wird der Spieler erneut
					// aufgefordert einen Zug einzugeben, der einem Sprung
					// entspricht.
					if (mglSprung) {
						if (Grafikmodus) {
							JTextFeld
									.setText("Sprung ist moeglich, waehlen"
											+ " Sie einen der moeglichen Spruenge aus...");
						} else {
							System.out
									.println("Sprung ist moeglich, waehlen"
											+ " Sie einen der moeglichen Spruenge aus...");
						}
						is_korrZug = false;
						// Falls aktuell kein Sprung ausgefuehrt werden kann,
						// wird ueberprueft, ob der uebergebene Spielzug einem
						// "normalen Zug" entspricht.
					} else {
						// Falls es sich um einen korrekten Zug handelt, wird
						// die alte Position in die Liste der zu entfernenden
						// Steine und die neue in die zu setzenden STeine
						// eingefuegt.
						if (isZug(k1, k2)) {
							if (Grafikmodus) {

								JTextFeld.setText("Gueltiger Spielzug.");
							} else {
								System.out.println("Gueltiger Spielzug.");
							}
							is_korrZug = true;
							steineEntfernen.add(k1);
							steineSetzen.add(k2);
							// Da es sich bei dem uebergebenen Spielzug weder um
							// einen gueltigen Sprung, noch Zug handelt, sind
							// die uebergebenen Koordinaten nicht gueltig, der
							// Spieler wird erneut gebeten einen gueltigen
							// Spielzug einzugeben.
						} else {
							if (Grafikmodus) {
								JTextFeld
										.setText("Es ist kein Sprung moeglich,"
												+ " waehlen sie einen gueltigen Spielzug aus.");
							} else {
								System.out
										.println("Es ist kein Sprung moeglich,"
												+ " waehlen sie einen gueltigen Spielzug aus.");
							}
							is_korrZug = false;
						}
					}

				} catch (EmptyFieldException e) {
					e.printStackTrace();
				}
			} else {
				if (Grafikmodus) {
					JTextFeld
							.setText("Sprung ist Pflicht, uebergebene Koordinaten"
									+ " sind kein (gueltiger) Sprung");
				} else {
					System.out
							.println("Sprung ist Pflicht, uebergebene Koordinaten"
									+ " sind kein (gueltiger) Sprung");
				}
				is_korrZug = false;
			}
		}

		return is_korrZug;
	}

	/**
	 * Auflistung aller noch auf dem Brett stehenden Steine einer der beiden
	 * Spielfarben
	 * 
	 * @param schwarz
	 *            ob es sich bei den zu suchenden Steinen um schwarze handeln
	 *            soll
	 * @return Liste der sich auf dem Spielbrett befindenden Steine der
	 *         uebergebenen Spielerfarbe
	 * @throws EmptyFieldException
	 */
	private static ArrayList<Zugkoordinate> mglSteine(boolean schwarz)
			throws EmptyFieldException {
		ArrayList<Zugkoordinate> steine = new ArrayList<Zugkoordinate>();
		// Fuer jede Spielbrettposition ueberpruefen, ob hierauf ein Stein der
		// gewuenschten Farbe befindet.
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				Feld tmp = Spielfeld[i][j];
				// Auflisten aller sich auf dem Brett befindenden schwarzen
				// Steine
				if (schwarz && tmp.is_Besetzt() && tmp.is_schwStein()) {
					steine.add(new Zugkoordinate(j, i));
				}
				// Auflisten aller sich auf dem Brett befindenden weissen Steine
				else if (!schwarz && tmp.is_Besetzt() && !tmp.is_schwStein()) {
					steine.add(new Zugkoordinate(j, i));
				}
			}
		}
		return steine;
	}

	/**
	 * Ueberpruefung, ob einer der uebergebenen Steine in der Lage ist einen
	 * Sprung auszufuehren
	 * 
	 * @param mglSteine
	 *            ArrayList aller Zugkoordinaten der Steine die noch (von einer
	 *            Farbe) auf dem Brett stehen
	 * @return ob ein Sprung moeglich ist
	 * @throws DamePPException
	 */
	private static boolean mglSprung(ArrayList<Zugkoordinate> mglSteine)
			throws DamePPException {

		// Rueckgabevariable
		boolean sprungMgl = false;

		// Nur Bewegung in Spielsteinfarbenrichtung
		boolean alleRichtungen = false;

		// Bewegung in positive Richtung z.B. 12 --> 23
		boolean posBew = true;

		for (int i = 0; i < mglSteine.size(); i++) {
			Zugkoordinate tmpKoord = mglSteine.get(i);
			Feld tmpFeld = Spielfeld[tmpKoord.getY()][tmpKoord.getX()];
			// Falls auf dem Feld eine Dame befindet, darf diese sich in alle
			// vier diagonalen Richtungen bewegen.
			if (tmpFeld.is_Dame()) {
				alleRichtungen = true;
			} else
				alleRichtungen = false;
			// Schwarze Steine duerfen sich nur in Richtung gegnerischer
			// Spielfeldgrenze bewegen (negative Bewegungsrichtung)
			if (tmpFeld.is_schwStein()) {
				posBew = false;
			}
			// Weisse Steine duerfen sich nur in Richtung gegnerischer
			// Spielfeldgrenze bewegen (positive Bewegungsrichtung)
			else
				posBew = true;

			Zugkoordinate k2 = new Zugkoordinate();
			// Ueberpruefung aller vier Diagonalrichtungen auf Moeglichkeit
			// eines Sprungs
			if (alleRichtungen) {
				k2.setKoord(tmpKoord.getY() + 2, tmpKoord.getX() + 2);
				boolean sprung1 = zulaessigerSprung(tmpKoord, k2);
				k2.setKoord(tmpKoord.getY() - 2, tmpKoord.getX() + 2);
				boolean sprung2 = zulaessigerSprung(tmpKoord, k2);
				k2.setKoord(tmpKoord.getY() + 2, tmpKoord.getX() - 2);
				boolean sprung3 = zulaessigerSprung(tmpKoord, k2);
				k2.setKoord(tmpKoord.getY() - 2, tmpKoord.getX() - 2);
				boolean sprung4 = zulaessigerSprung(tmpKoord, k2);
				if (sprung1 | sprung2 | sprung3 | sprung4) {
					sprungMgl = true;
				}
			}
			// Ueberpruefung der normalen Steine auf die Moeglichkeit eines
			// Sprungs in den zulaessigen Richtungen
			else {
				// Ueberpruefung der weissen Steine auf die Moglichkeit eines
				// Sprungs in den zulaessigen Richtungen
				if (posBew) {
					k2.setKoord((tmpKoord.getX() + 2), (tmpKoord.getY() + 2));
					boolean sprung1 = zulaessigerSprung(tmpKoord, k2);
					k2.setKoord(tmpKoord.getX() - 2, tmpKoord.getY() + 2);
					boolean sprung2 = zulaessigerSprung(tmpKoord, k2);
					if (sprung1 | sprung2) {
						sprungMgl = true;
					}
				}
				// Ueberpruefung der schwarzen Steine auf die Moglichkeit eines
				// Sprungs in den zulaessigen Richtungen
				else {
					k2.setKoord((tmpKoord.getX() + 2), (tmpKoord.getY() - 2));
					boolean sprung3 = zulaessigerSprung(tmpKoord, k2);
					k2.setKoord((tmpKoord.getX() - 2), (tmpKoord.getY() - 2));
					boolean sprung4 = zulaessigerSprung(tmpKoord, k2);

					if (sprung3 | sprung4) {
						sprungMgl = true;
					}
				}
			}
		}
		return sprungMgl;
	}

	/**
	 * &Uumlberpr&uumlfung eines m&oumlglichen Sprungs auf die zul&aumlssigen
	 * Differenzen der Start- und Endposition, sowie der Bewegungsrichtungen
	 * 
	 * @param start
	 *            Koordinate des Ausgangssteins
	 * @param k1
	 *            Startkoordinate des Sprungs
	 * @param k2
	 *            Zielkoordinate des Sprungs
	 * @return Handelt es sich bei dem &Uumlbergebenen Sprung um eine g&uumltige
	 *         Richtung und Differenz zwischen Start- und Zielkoordinate?
	 */
	private static boolean korrSprungRichtung(Zugkoordinate start,
			Zugkoordinate k1, Zugkoordinate k2) {
		boolean richtung = false;
		try {
			// Bestimmung der Differenz zwischen der Start- und Zielkoordinate
			int diffX = k1.getX() - k2.getX();
			int diffY = k1.getY() - k2.getY();
			// Ueberpruefung der Zugbewegung im Falle einer Dame
			if (Spielfeld[start.getY()][start.getX()].is_Dame()) {
				if (((diffX == -2) || (diffX == 2))
						&& ((diffY == 2) || (diffY == -2))) {
					richtung = true;
				}
			}
			// Ueberpruefung der Zugbewegung fur einen normalen Stein
			else {
				// Ueberpruefung fur einen schwarzen Stein
				if (Spielfeld[start.getY()][start.getX()].is_schwStein()) {
					if (((diffX == -2) || (diffX == 2)) && ((diffY == 2))) {
						richtung = true;
					}
				}
				// Ueberpruefung fur einen weissen Stein
				else {
					if (((diffX == -2) || (diffX == 2)) && ((diffY == -2))) {
						richtung = true;
					}
				}
			}
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
		return richtung;
	}

	/**
	 * Ueberpruefung, ob es sich bei den uebergebenen Koordinaten um einen
	 * gueltigen Sprung handelt
	 * 
	 * @param k1
	 *            Startkoordinate
	 * @param k2
	 *            Zielkoordinate
	 * @return ob es sich bei dem uebergebenen Sprung von der Zugkoordinate
	 *         "von" nach Zugkoordinate "nach" um einen gueltigen Sprung handelt
	 */
	private static boolean zulaessigerSprung(Zugkoordinate k1, Zugkoordinate k2) {
		boolean sprung = false;
		Zugkoordinate ueber = new Zugkoordinate(((k1.getX() + k2.getX()) / 2),
				((k1.getY() + k2.getY()) / 2));
		if (inRange(k1) && inRange(k2) && inRange(ueber)) {
			Feld tmpStart = Spielfeld[k1.getY()][k1.getX()];
			Feld tmpGegner = Spielfeld[ueber.getY()][ueber.getX()];
			Feld tmpEnde = Spielfeld[k2.getY()][k2.getX()];

			// wenn der Gegner bereits zuvor uebersprungen wurde, darf er
			// nicht noch einmal uebersprungen werden
			if (tmpGegner.is_uebersprungen()) {
				sprung = false;
			} else {
				try {
					// schwarz ueber weiss
					if (tmpStart.is_Besetzt() && tmpStart.is_schwStein()
							&& tmpGegner.is_Besetzt()
							&& !tmpGegner.is_schwStein()
							&& !tmpEnde.is_Besetzt()) {
						steineEntfernen.add(ueber);
						sprung = true;
					}
					// weiss ueber schwarz
					if (tmpStart.is_Besetzt() && !tmpStart.is_schwStein()
							&& tmpGegner.is_Besetzt()
							&& tmpGegner.is_schwStein()
							&& !tmpEnde.is_Besetzt()) {
						steineEntfernen.add(ueber);
						sprung = true;
					}
				} catch (EmptyFieldException e) {
					e.printStackTrace();
				}
			}
		}
		return sprung;
	}

	/**
	 * &Uuml;berpruefung, ob die Zugkoordinate innerhalb des g&uuml;ltigen Bereichs des
	 * Spielbretts sich befindet
	 * 
	 * @param k
	 *            zu &uuml;berpruefende Zugkoordinate
	 * @return ob die Zugkoordinate im g&uuml;ltigen Spielbrettbereich liegt
	 */
	private static boolean inRange(Zugkoordinate k) {
		boolean inRange = false;
		if (k.getX() >= 0 && k.getX() < 8 && k.getY() >= 0 && k.getY() < 8) {
			inRange = true;
		}
		return inRange;
	}

	/**
	 * Ueberpruefung, ob es sich beiden beiden Zugkoordinaten um einen
	 * zulaessigen Sprung handelt
	 * 
	 * @param k1
	 *            Startkoordinate
	 * @param k2
	 *            Zielkoordinate
	 * @param sprungPflicht
	 *            muss wenn m&oumlglich ein Sprung erfolgen
	 * @param start
	 *            Koordinate des Startsteins dieses Zuges
	 * @return ob es sich bei dem Sprung um einen gueltigen handelt
	 */
	private static boolean isSprung(Zugkoordinate k1, Zugkoordinate k2,
			boolean sprungPflicht, Zugkoordinate start) {
		boolean isSprung = false;
		// zunaechst ueberpruefen, ob es sich auf Grund der Zugrichtung um einen
		// zulaessigen Sprung handeln kann
		isSprung = korrSprungRichtung(start, k1, k2);
		// Falls die Richtung fuer den Startstein zualessig war, werden die
		// Spruenge genauer ueberprueft.
		if (isSprung) {
			// Unterscheidung zwischen Erstsprung und Folgesprung
			if (!sprungPflicht) {
				isSprung = zulaessigerSprung(k1, k2);
			} else {
				isSprung = isFolgesprung(k1, k2);
			}
		}
		return isSprung;
	}

	/**
	 * &Uumlberpr&uumlng, ob es sich bei den &Uumlbergebenen Koordinaten um
	 * einen Folgesprung handelt.
	 * 
	 * @param k1
	 *            Startkoordinate
	 * @param k2
	 *            Zielkoordinate
	 * @return Handelt es sich um einen g&uumlltigen Folgesprung?
	 */
	private static boolean isFolgesprung(Zugkoordinate k1, Zugkoordinate k2) {
		boolean folgesprung = false;
		// Ermittlung der Koordinate Zwischen Start- und Zielkoordinate
		Zugkoordinate ueber = new Zugkoordinate(((k1.getX() + k2.getX()) / 2),
				((k1.getY() + k2.getY()) / 2));
		// Sind die drei Koordinaten im Gueltigkeitsbereich des Spielbrettes
		if (inRange(k1) && inRange(k2) && inRange(ueber)) {
			Feld tmpGegner = Spielfeld[ueber.getY()][ueber.getX()];
			Feld tmpEnde = Spielfeld[k2.getY()][k2.getX()];
			// wenn der Gegner bereits zuvor uebersprungen wurde, darf er
			// nicht noch einmal uebersprungen werden
			if (tmpGegner.is_uebersprungen()) {
				folgesprung = false;
			} else {
				try {
					// schwarz ueber weiss
					if (ZugSpielerSchwarz && tmpGegner.is_Besetzt()
							&& !tmpGegner.is_schwStein()
							&& !tmpEnde.is_Besetzt()) {
						steineEntfernen.add(ueber);
						folgesprung = true;
					}
					// weiss ueber schwarz
					if (!ZugSpielerSchwarz && tmpGegner.is_Besetzt()
							&& tmpGegner.is_schwStein()
							&& !tmpEnde.is_Besetzt()) {
						steineEntfernen.add(ueber);
						folgesprung = true;
					}
				} catch (EmptyFieldException e) {
					e.printStackTrace();
				}
			}
		}
		if (folgesprung) {
			zwischenSteineEntfernen.add(k1);
		}
		return folgesprung;
	}

	/**
	 * &Uuml;berpr&uuml;fung, ob es sich bei den beiden Zugkoordinaten um einen
	 * g&uuml;ltigen Zug handelt.
	 * 
	 * @param kVon
	 *            Zugkoordinate, von der der Zug gestartet wird.
	 * @param kNach
	 *            Zugkoordinate, nach der der Zug ausgef&uuml;hrt werden soll.
	 * 
	 * @return ob es sich bei dem Zug um einen g&uuml;ltigen Zug handelt.
	 * 
	 * @throws EmptyFieldException
	 */
	private static boolean isZug(Zugkoordinate kVon, Zugkoordinate kNach)
			throws EmptyFieldException {

		boolean isZug = false;

		// Prueft, ob "von" und "nach" im Spielfeld liegen
		if (inRange(kVon) && inRange(kNach)) {

			// Beinhaltet spaeter die Differenzen der Zuege von-nach
			int diffX;
			int diffY;

			// Auslesen der Feld-Instanzen aus dem Spielfeld
			Feld von = Spielfeld[kVon.getY()][kVon.getX()];
			Feld nach = Spielfeld[kNach.getY()][kNach.getX()];

			diffX = kVon.getX() - kNach.getX();
			diffY = kVon.getY() - kNach.getY();

			// Wenn der zu setzende Stein eine Dame ist...
			if (von.is_Dame()) {

				// Damen duerfen sich in alle Richtungen bewegen, bei
				// einem ZUG aber nur um ein Feld.
				if (((diffX == 1) | diffX == -1)
						&& ((diffY == 1) | diffY == -1)) {
					// Prueft, ob der Zug auch zulaessig ist!
					isZug = zulaessigerZug(von, nach);
				}
				// Wenn es sich nicht um einen Zug handelt, kann man
				// sich die Gueltigkeitspruefung sparen.
				else {
					isZug = false;
				}
			}
			// Wenn der zu setzende Stein KEINE Dame ist...
			else {
				// Gueltige Zugrichtung eines schwarzen Steins?
				if (von.is_schwStein()) {
					if (((diffX == 1) | (diffX == -1)) && (diffY == 1)) {
						isZug = zulaessigerZug(von, nach);
					} else {
						isZug = false;
					}
				} 
				// Gueltige Zugrichtung eines weissen Steins?
				else {
					if (((diffX == 1) | (diffX == -1)) && (diffY == -1)) {
						isZug = zulaessigerZug(von, nach);
					} else {
						isZug = false;
					}
				}
			}
		} else {
			isZug = false;
		}
		return isZug;
	}

	/**
	 * Pr&uuml;ft, ob der Zug des Steines von dem Feld {@code von} zum Feld
	 * {@code nach} ein zul&auml;ssiger Zug ist und gibt eine Antwort als
	 * {@code boolean} zur&uuml;ck.
	 * 
	 * @param von
	 *            Beinhaltet das Feld, von dem gezogen werden soll.
	 * @param nach
	 *            Beinhaltet das Feld, auf das gezogen werden soll.
	 */
	static boolean zulaessigerZug(Feld von, Feld nach) {

		boolean zulaessig = false;

		try {
			// Ist "von" ein schwarzes Feld mit einem schwarzen Stein
			// und "nach" ein leeres, schwarzes Feld?
			if (von.is_schwFeld() && von.is_Besetzt() && von.is_schwStein()
					&& nach.is_schwFeld() && !nach.is_Besetzt()) {
				zulaessig = true;
			}
			// Oder ist "von" ein schwarzes Feld mit einem weissen Stein
			// und "nach" ein leeres, schwarzes Feld?
			else if (von.is_schwFeld() && von.is_Besetzt()
					&& !von.is_schwStein() && nach.is_schwFeld()
					&& !nach.is_Besetzt()) {
				zulaessig = true;
			}
			// Wenn nicht, ist der Zug nicht zulaessig.
			else
				zulaessig = false;
		} catch (EmptyFieldException e) {

			e.printStackTrace();

		}

		return zulaessig;
	}

	/**
	 * Entfernt einen Stein aus dem internen Datenfeld f&uuml;r wei&szlig;e bzw.
	 * schwarze Spielsteine (Z&auml;hlfelder), sprich die Z&auml;hlvariable wird
	 * dekrementiert.
	 * 
	 * @param schwarz
	 *            Ist der entfernende (!) Spieler schwarz, d.h. der zu
	 *            entfernende Stein wei&szlig;?
	 */
	private static void entferneStein(boolean schwarz) {
		// Ist der schwarze Spieler an der Reihe, so wird beim Schlagen ein
		// weisser Spielstein entfernt.
		if (schwarz)
			SteineWeiss--;
		// Ansonsten ein Schwarzer...
		else
			SteineSchwarz--;
	}

	/* =======Standardmethoden==================================================== */

	/**
	 * Gibt das aktuelle Spielfeld als ASCII-Zeichen aus. "0" kennzeichnet dabei
	 * leere schwarze Felder, Spielsteine werden mit "s" und "S" (Dame) f&uuml;r
	 * Schwarz und "w" bzw. "W" f&uuml;r Wei&szlig; dargestellt.
	 */
	public String toString() {
		// Initialisierung der zurueckzuliefernden Variablen
		String s = "";
		try {
			s = "Anzahl Zuege ohne Schlag: " + getZuegeOhneSchlag() + "\n\n";
			// Beschriftung der X-Koordinaten
			s = s + "    1 | 2 | 3 | 4 | 5 | 6 | 7 | 8\n";
			// Schleife zum Durchlaufen aller Y
			for (int i = 0; i < dimension; i++) {
				/*
				 * Die Variable X ist nur als Hilfe fuer die korrekte
				 * Beschriftung der Y-Koordinaten da, da intern mit den
				 * Dimensionen 0-7 gerechnet, extern dem Benutzer aber ein
				 * System mit 1-8 gezeigt wird.
				 */
				int x = i + 1;
				// Beschriftung der Y-Koordinaten
				s = s + x + " |";
				// Schleife zum Durchlaufen aller X
				for (int j = 0; j < dimension; j++) {
					/*
					 * Falls die Y-Koordinaten mit einem Spielstein belegt ist,
					 * so wird weiter spezifiziert.
					 */
					if (Spielfeld[i][j].is_Besetzt()) {
						/*
						 * Ist der Spielstein schwarz, so wird das Feld mit
						 * einem s oder S (als Dame) gezeigt
						 */
						if (Spielfeld[i][j].is_schwStein()) {
							if (Spielfeld[i][j].is_Dame())
								s = s + " S";
							else
								s = s + " s";
						}
						/*
						 * Ist der Spielstein weiss, so wird das Feld mit einem
						 * w oder W (als Dame) gezeigt
						 */
						else {
							if (Spielfeld[i][j].is_Dame())
								s = s + " W";
							else
								s = s + " w";
						}
					}
					// Ist das Feld leer, so wird...
					else {
						// ...fuer ein schwarzes Feld eine "0" und...
						if (Spielfeld[i][j].is_schwFeld()) {
							s = s + " 0";
						}
						// ...fuer ein weisses Feld kein Zeichen gezeigt.
						else {
							s = s + "  ";
						}
					}

					// Abschliessend noch ein paar Formatierungs-Leerzeichen
					s = s + "  ";
				}
				// Naechste Zeile
				s = s + "\n";
			}
		} catch (DamePPException e) {

			if (Grafikmodus) {
				JTextFeld
						.setText("Beim Abrufen des Spielfeldes ist ein Fehler aufgetreten.");
			} else {
				System.out
						.println("Beim Abrufen des Spielfeldes ist ein Fehler aufgetreten.");
			}
			e.printStackTrace();
		}
		// "s" wird an die rufende Methode zurueckgegeben.
		return s;
	}
}
