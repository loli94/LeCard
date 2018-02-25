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
	private JButton lernen;
	private JPanel grafischeStat;
	private JPanel datenStatistik;
	private JLabel ausgewaehlteKartei;
	private JLabel richtigeAntwort;
	private JLabel falscheAntwort;
	private JLabel total;
<<<<<<< HEAD
	private JPanel statistik;
=======
	private PanelKarteiVerwalten panelKarteiVerwalten; 
>>>>>>> branch 'master' of https://github.com/loli94/LeCard.git

	public PanelKartei() {
		initComponents();
		bindListener();
		paint();
	}

	public void repaint() {
		initComponents();
		bindListener();
		
	}

	public void initComponents() {
		mainFrame = new JPanel();
		statistik = new StatistikPanel();
		grafischeStat = statistik;
		grafischeStat.setPreferredSize(new Dimension(400, 400));
		
		datenStatistik = new JPanel();
		kartei = new JPanel();
		stat = new JPanel();
		start = new JPanel();
		richtigeAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("richtigeAntworten"));
		falscheAntwort = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("falscheAntworten"));
		total = new JLabel("Total: ");

		aktuelleKartei = new JLabel(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("aktuelleKartei"));
		ausgewaehlteKartei = new JLabel("Fach: 1    "+main.daten1.getFach(5).gibAnzahlKarten());

		karteiBearbeiten = new JButton(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("karteiBearbeiten"));
		lernen = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("jetztLernen"));

	}

	private void bindListener() {

		lernen.addActionListener(new ButtonListenerKarteiJetztLernen());
		karteiBearbeiten.addActionListener(new ButtonListenerKarteiBearbeiten());

	}

	public void paint() {
		datenStatistik.setLayout(new GridLayout(3, 2));
		datenStatistik.add(richtigeAntwort);
		datenStatistik.add(falscheAntwort);
		datenStatistik.add(total);

		//mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.PAGE_AXIS));

		//kartei.setLayout(new GridLayout(1, 3));
		kartei.setLayout(new FlowLayout());
		//stat.setLayout(new GridLayout(1, 2));
		stat.setLayout(new FlowLayout());
		
		kartei.add(aktuelleKartei);
		kartei.add(ausgewaehlteKartei);
		kartei.add(karteiBearbeiten);

		stat.add(grafischeStat);
		stat.add(datenStatistik);
		

		start.add(lernen, BorderLayout.CENTER);

		mainFrame.add(kartei);
		mainFrame.add(stat);
		mainFrame.add(start);

		add(mainFrame);

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
