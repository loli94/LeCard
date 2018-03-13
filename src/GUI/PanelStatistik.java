package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import Logik.Kartei;

/**
 * @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.0 Datum:28.02.2018
 */

public class PanelStatistik extends JPanel {

	private static final long serialVersionUID = 1L;
	private int Kart1_X = 100;
	private int Kart1_Y = 20;
	private int Kart1_WIDTH = 0;
	private int Kart1_HEIGHT = 50;
	private int Kart2_X = 100;
	private int kart2_Y = 80;
	private int kart2_WIDTH = 0;
	private int kart2_Height = 50;
	private int kart3_X = 100;
	private int kart3_Y = 140;
	private int kart3_WIDTH = 0;
	private int kart3_Height = 50;
	private int kart4_X = 100;
	private int kart4_Y = 200;
	private int kart4_WIDTH = 0;
	private int kart4_Height = 50;
	private int kart5_X = 100;
	private int kart5_Y = 260;
	private int kart5_WIDTH = 0;
	private int kart5_Height = 50;

	/**
	 * Hier wird das Balkendiagramm auf einer Graphicsfläche gezeichnet.
	 * 
	 * @param g Zeichnungsfläche
	 *            
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillRect(Kart1_X, Kart1_Y, Kart1_WIDTH, Kart1_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach") + " 1",
				10, 50);
		g.drawString("" + Kartei.getInstance().getFachGroesse(0), 80, 50);

		g.setColor(Color.RED);
		g.fillRect(Kart2_X, kart2_Y, kart2_WIDTH, kart2_Height);
		g.setColor(Color.BLACK);
		g.drawString(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach") + " 2",
				10, 110);
		g.drawString("" + Kartei.getInstance().getFachGroesse(1), 80, 110);

		g.setColor(Color.CYAN);
		g.fillRect(kart3_X, kart3_Y, kart3_WIDTH, kart3_Height);
		g.setColor(Color.BLACK);
		g.drawString(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach") + " 3",
				10, 175);
		g.drawString("" + Kartei.getInstance().getFachGroesse(2), 80, 175);

		g.setColor(Color.GREEN);
		g.fillRect(kart4_X, kart4_Y, kart4_WIDTH, kart4_Height);
		g.setColor(Color.BLACK);
		g.drawString(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach") + " 4",
				10, 225);
		g.drawString("" + Kartei.getInstance().getFachGroesse(3), 80, 225);

		g.setColor(Color.ORANGE);
		g.fillRect(kart5_X, kart5_Y, kart5_WIDTH, kart5_Height);
		g.setColor(Color.BLACK);
		g.drawString(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach") + " 5",
				10, 285);
		g.drawString("" + Kartei.getInstance().getFachGroesse(4), 80, 285);
	}

	/**
	 * 
	 * 
	 * @return int Karte1_WIDTH Karte1_WIDTh wird zurück gegeben
	 */
	public int getKart1_WIDTH() {
		return Kart1_WIDTH;
	}

	/**
	 * 
	 * 
	 * @param kart1_WIDTH Hier wird die Breite des ersten Balken gesetzt.
	 */

	public void setKart1_WIDTH(int kart1_WIDTH) {
		Kart1_WIDTH = kart1_WIDTH * 10;
	}

	/**
	 * 
	 * 
	 * @return Karte2_WIDTH Karte2_WIDTH wird zurück gegeben
	 */

	public int getKart2_WIDTH() {
		return kart2_WIDTH;
	}

	/**
	 * 
	 * 
	 * @param kart2_WIDTH Hier wird die Breite des zweiten Balken gesetzt
	 */

	public void setKart2_WIDTH(int kart2_WIDTH) {
		this.kart2_WIDTH = kart2_WIDTH * 10;
	}

	/**
	 * 
	 * @return Karte3_WIDTH Karte3_WIDTH wird zurück gegeben
	 */
	public int getKart3_WIDTH() {
		return kart3_WIDTH;
	}

	/**
	 * 
	 * 
	 * @param kart3_WIDTH Hier wird die Breite des dritten Balken gesetzt
	 */
	public void setKart3_WIDTH(int kart3_WIDTH) {
		this.kart3_WIDTH = kart3_WIDTH * 10;
	}

	/**
	 *
	 * 
	 * @return int Karte4_WIDTH Karte4_WIDTH wird zurück gegeben
	 */

	public int getKart4_WIDTH() {
		return kart4_WIDTH;
	}

	/**
	 *
	 * 
	 * @param kart4_WIDTH Hier wird die Breite des vierten Balken gesetzt
	 */
	public void setKart4_WIDTH(int kart4_WIDTH) {
		this.kart4_WIDTH = kart4_WIDTH * 10;
	}

	/**
	 * 
	 * 
	 * @return int Karte5_WIDTH Karte5_WIDTH wird zurück gegeben
	 */

	public int getKart5_WIDTH() {
		return kart5_WIDTH;
	}

	/**
	 * 
	 * 
	 * @param kart5_WIDTH Hier wird die Breite des fünften Balken gesetzt
	 */

	public void setKart5_WIDTH(int kart5_WIDTH) {
		this.kart5_WIDTH = kart5_WIDTH * 10;
	}

}
