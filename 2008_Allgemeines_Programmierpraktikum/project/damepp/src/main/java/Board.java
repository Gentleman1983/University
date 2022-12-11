/*
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

import static java.lang.Math.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.EventHandler;
import java.util.ArrayList;

/**
 * <code>Board</code> ist ein Swing-basiertes Kontrollelement zur Darstellung beliebigdimensionaler
 * Schachbretter einschlie&szlig;lich frei w&auml;hlbarer Spielsteine ({@link Stein}).
 * Ein Zug kann vom Anwender angefordert werden und wird als {@link Point}-{@link ArrayList}
 * zur&uuml;ckgegeben.  Die Spielsteine werden aus Bitmapgrafiken mit transparentem Hintergrund erzeugt.
 *
 * @author Martin Schewe
 */
public class Board extends JLayeredPane {
	final static Color SCHWARZ	 = new Color(0x4040a0);	// Farbkonstanten fuer schwarze,
	final static Color WEISS	 = new Color(0xa0a0a0);	// weisse und
	final static Color HERVORGEHOBEN = new Color(0x8080f0); // hervorgehobene Felder

	ArrayList<Point> zug;					// Die aktuell angeforderte Zugfolge
	JLabel		 f[][];					// Die Felder werden auf JLabels abgebildet
	int		 breite, anzahl;			// Kantenlaenge und Anzahl der Felder

	/**
	 * Spielstein
	 */
	public class Stein extends JLabel implements MouseListener, MouseMotionListener {
		ArrayList<JLabel> spur;				// Liste der zugzusammensetzenden Felder
		Timer		  mark_timer;			// Herunterzaehlender Timer zur Feldauswahl
		int		  x, y,				// Aktuelle Spalte/Zeile des bewegten Steins
				  offx, offy;			// Pixel-Offset des Mauszeigers zum Stein

		/**
		 * Erzeugt einen neuen Spielstein anhand der &uuml;bergebenen Bitmapdatei und setzt ihn an
		 * die gew&uuml;nschte Position.
		 *
		 * @param icon	Quellpfad zur Bitmapdatei (z.B. PNG)
		 * @param x	Spalte (links beginnend bei 0)
		 * @param y	Zeile (oben beginnend bei 0)
		 */
		public Stein(String icon, int x, int y) {
			super(new ImageIcon(icon));

			verschieben(x, y);
			setSize(breite, breite);
			addMouseMotionListener(this);
			addMouseListener(this);

			// Ein Timer wird eingerichtet (aber noch nicht gestartet), der im Falle des breiteren
			// Verweilens (1000ms) eines gezogenen Steines ueber einem Feld dieses markieren laesst
			mark_timer = new Timer(1000, EventHandler.create(ActionListener.class, this, "mark"));
		}

		/**
		 * Markiert ein Feld als neues Element der aktuellen Zugfolge
		 */
		public void mark() {
			mark_timer.stop();

			// Sofern das zu markierende Feld nicht bereits zuletzt markiert worden ist und die
			// erlaubte Feldfarbe (schwarz) hat, wird es farblich hervorgehoben und der Liste
			// hinzugefuegt
			if (spur.lastIndexOf(f[x][y]) < max(spur.size() - 1, 0) && (x + y) % 2 == 1) {
				f[x][y].setBackground(HERVORGEHOBEN);
				spur.add(f[x][y]);
			}
		}

		/**
		 * Entfernt einen Spielstein vom Brett
		 */
		public void remove() {
			Container p = getParent();
			p.remove(this);
			p.repaint();
		}

		/**
		 * Bewegt den Spielstein an die gew&uuml;nschte Position
		 *
		 * @param x	Spalte (links beginnend bei 0)
		 * @param y	Zeile (oben beginnend bei 0)
		 */
		public void verschieben(int x, int y) {
			setLayer(this, DEFAULT_LAYER, 0);
			setLocation((this.x = x) * breite, (this.y = y) * breite);
			spur = new ArrayList<JLabel>();
		}

		/**
		 * Eventhandler, der beim Dr&uuml;cken der Maustaste aufgerufen wird und somit das Ziehen
		 * eines Spielsteins veranlasst
		 */
		public void mousePressed(MouseEvent e) {
			setLayer(this, DRAG_LAYER);
			offx = e.getX();
			offy = e.getY();
			mark();
		}

		/**
		 * Eventhandler, der beim Ziehen der Maus den Spielstein (innerhalb des Bretts) mitbewegt
		 */
		public void mouseDragged(MouseEvent e) {
			int mx = min(max(getX() + e.getX() - offx, 0), (anzahl - 1) * breite);
			int my = min(max(getY() + e.getY() - offy, 0), (anzahl - 1) * breite);

			x = (mx + breite / 2) / breite;		// Spalte und Zeile des darunterliegenden Feldes
			y = (my + breite / 2) / breite;		// neu ermitteln

			setLocation(mx, my);			// Stein bewegen
			mark_timer.restart();			// Timer zuruecksetzen (soll nur in Ruhe ausloesen)
		}

		/**
		 * Eventhandler, der beim Loslassen der Maustaste den Zugwunsch abschlie&szlig;t
		 */
		public void mouseReleased(MouseEvent e) {
			synchronized (getParent()) {
				// Das letzte Feld wird markiert, die hervogehobenen Felder erhalten wieder ihre
				// urspruengliche Farbe und die endgueltige Zugliste wird aus Points aufgebaut.
				mark();
				zug = new ArrayList<Point>();
				for (int i = 0; i < spur.size(); i++) {
					spur.get(i).setBackground(SCHWARZ);
					zug.add(new Point(spur.get(i).getX() / breite,
							  spur.get(i).getY() / breite));
				}
				// Der Stein wird vorerst an seine urspruengliche Position zurueckgesetzt und
				// die evtl. wartende hol_zug-Funktion wird geweckt
				verschieben((int) zug.get(0).getX(), (int) zug.get(0).getY());
				getParent().notify();
			}
		}

		// Eventhandlerleichen, deren (leere) Implementation vom Interface gefordert wird
		public void mouseExited(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseClicked(MouseEvent e) { }
		public void mouseMoved(MouseEvent e) { }
	}

	/**
	 * Erzeugt ein leeres Brett
	 *
	 * @param anzahl	Anzahl der Felder (Zeilen = Spalten)
	 * @param breite	Kantenl&auml;nge der Felder
	 */
	public Board(int anzahl, int breite) {
		super();

		this.anzahl = anzahl;
		this.breite = breite;
		this.f	    = new JLabel[anzahl][anzahl];

		setPreferredSize(new Dimension(anzahl * breite, anzahl * breite));

		for (int x = 0; x < anzahl; x++)
			for (int y = 0; y < anzahl; y++) {
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setBounds(x * breite, y * breite, breite, breite);
				l.setBackground((x + y) % 2 == 0 ? WEISS : SCHWARZ);
				add(l, FRAME_CONTENT_LAYER);
				f[x][y] = l;
			}
	}

	/**
	 * Liefert den auf der gew&uuml;nschten Position liegenden Spielstein zur&uuml;ck
	 *
	 * @param x	Spalte (links beginnend bei 0)
	 * @param y	Zeile (oben beginnend bei 0)
	 * @return	Spielstein, der sich auf besagter Position befindet
	 */
	Stein stein(int x, int y) {
		return (Stein) getComponentAt(x * breite, y * breite);
	}

	/**
	 * Fordert einen Zug an und liefert ihn zur&uuml;ck, sobald er durchgef&uuml;hrt worden ist
	 *
	 * @return	Zugliste aus <code>Point</code>s (x entspricht den Spalten und y den Zeilen)
	 */
	synchronized public ArrayList<Point> hol_zug() {
		try { wait(); } catch (InterruptedException e) { }
		return zug;
	}
}
