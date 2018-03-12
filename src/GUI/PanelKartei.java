package GUI;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logik.Kartei;

/**
 * @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.8
 * Datum:24.02.2018
 */
public class PanelKartei extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel kartei;
	private JPanel stat;
	private JPanel start;
	private PanelStatistik grafischeStat;
	private JPanel datenStatistik;
	private JLabel richtigeAntwort;
	private JLabel falscheAntwort;

	public PanelKartei() {
		initComponents();
		setBalkendiagramm();
		paint();
	}


	/**
	 * Hier werden alle Objekte vom Panel initiert.
	 */
	public void initComponents() {

		grafischeStat = new PanelStatistik();
		grafischeStat.setPreferredSize(new Dimension(400, 400));

		datenStatistik = new JPanel();
		kartei = new JPanel();
		stat = new JPanel();
		start = new JPanel();
		richtigeAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("richtigeAntworten") + " "
						+ Kartei.getInstance().getRichtigeAntwort());
		falscheAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("falscheAntworten") + " "
						+ Kartei.getInstance().getFalscheAntwort());

	}

	/**
	 * Hier wird das Panel gezeichnet und die verschiedenen Komponenten dem Panel hinzugefügt.
	 */
	public void paint() {
		datenStatistik.setLayout(new GridLayout(3, 2));
		datenStatistik.add(richtigeAntwort);
		datenStatistik.add(falscheAntwort);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


		kartei.setLayout(new FlowLayout());

		stat.setLayout(new FlowLayout());

		stat.add(grafischeStat);
		stat.add(datenStatistik);

		this.add(stat);
		this.add(start);

	}
	
	/**
	 * Hier werden die Werte für das Balkendiagramm gesetzt.
	 */
	

	public void setBalkendiagramm() {
		// Setzen von Barwidth
		grafischeStat.setKart1_WIDTH(Kartei.getInstance().getFachGroesse(0));
		grafischeStat.setKart2_WIDTH(Kartei.getInstance().getFachGroesse(1));
		grafischeStat.setKart3_WIDTH(Kartei.getInstance().getFachGroesse(2));
		grafischeStat.setKart4_WIDTH(Kartei.getInstance().getFachGroesse(3));
		grafischeStat.setKart5_WIDTH(Kartei.getInstance().getFachGroesse(4));
		spracheWechseln();

	}
	
	/**
	 * Der Text für Richtige oder Falsche wird gesetzt.
	 */

	public void spracheWechseln() {
		this.richtigeAntwort.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("richtigeAntworten") + " " + Kartei.getInstance().getRichtigeAntwort());
		this.falscheAntwort.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("falscheAntworten") + " "	+ Kartei.getInstance().getFalscheAntwort());
	}


}
