import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.security.provider.VerificationProvider;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.8
 * Datum:24.02.2018
 */
public class PanelKartei extends JPanel {

	private JPanel mainFrame;
	private JPanel kartei;
	private JPanel stat;
	private JPanel start;
	private JLabel aktuelleKartei;
	private JButton karteiBearbeiten;
	private StatistikPanel grafischeStat;
	private JPanel datenStatistik;
	private JLabel ausgewaehlteKartei;
	private JLabel richtigeAntwort;
	private JLabel falscheAntwort;
	private PanelKarteiVerwalten panelKarteiVerwalten;

	public PanelKartei() {
		initComponents();
		setBalkendiagramm();
		bindListener();
		paint();
	}

	public void repaint() {
		initComponents();
		bindListener();
		setBalkendiagramm();

	}

	public void initComponents() {
		mainFrame = new JPanel();

		grafischeStat = new StatistikPanel();
		grafischeStat.setPreferredSize(new Dimension(400, 400));

		datenStatistik = new JPanel();
		kartei = new JPanel();
		stat = new JPanel();
		start = new JPanel();
		richtigeAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("richtigeAntworten") + " " + Main.daten1.getRichtigeAntwort());
		falscheAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("falscheAntworten") + " " + Main.daten1.getFalscheAntwort());

		aktuelleKartei = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("aktuelleKartei"));
		ausgewaehlteKartei = new JLabel("Fach: 1    " + Main.daten1.getFach(5).gibAnzahlKarten());

		karteiBearbeiten = new JButton(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("karteiBearbeiten"));

	}

	private void bindListener() {

		karteiBearbeiten.addActionListener(new ButtonListenerKarteiBearbeiten());

	}

	public void paint() {
		datenStatistik.setLayout(new GridLayout(3, 2));
		datenStatistik.add(richtigeAntwort);
		datenStatistik.add(falscheAntwort);

		// mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.PAGE_AXIS));

		// kartei.setLayout(new GridLayout(1, 3));
		kartei.setLayout(new FlowLayout());
		// stat.setLayout(new GridLayout(1, 2));
		stat.setLayout(new FlowLayout());

		kartei.add(aktuelleKartei);
		kartei.add(ausgewaehlteKartei);
		kartei.add(karteiBearbeiten);

		stat.add(grafischeStat);
		stat.add(datenStatistik);



		mainFrame.add(kartei);
		mainFrame.add(stat);
		mainFrame.add(start);

		add(mainFrame);

	}
	
	public void setBalkendiagramm() {
		//Setzen von Barwidth
		grafischeStat.setKart1_WIDTH(Main.daten1.getFachGroesse(0));
		grafischeStat.setKart2_WIDTH(Main.daten1.getFachGroesse(1));
		grafischeStat.setKart3_WIDTH(Main.daten1.getFachGroesse(2));
		grafischeStat.setKart4_WIDTH(Main.daten1.getFachGroesse(3));
		grafischeStat.setKart5_WIDTH(Main.daten1.getFachGroesse(4));
		richtigeAntwort.setText(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("richtigeAntworten") + " " + Main.daten1.getRichtigeAntwort());
		falscheAntwort.setText(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("falscheAntworten") + " " + Main.daten1.getFalscheAntwort());

	}

	class ButtonListenerKarteiJetztLernen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Lernen");
		}

	}

	class ButtonListenerKarteiBearbeiten implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PanelKarteiVerwalten gui1 = new PanelKarteiVerwalten();
			gui1.paint();

		}
	}
}
