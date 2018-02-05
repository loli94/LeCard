import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public PanelKartei() {
		initComponents();		
		bindListener();
		paint(); 
	}
	
	private void initComponents() {
		mainFrame = new JPanel();
		grafischeStat = new JPanel();
		datenStatistik = new JPanel();
		kartei = new JPanel();
		stat = new JPanel();
		start = new JPanel();
		richtigeAntwort = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("richtigeAntworten"));
		falscheAntwort = new JLabel("falsche Antworten: ");
		total = new JLabel("Total: ");

		aktuelleKartei = new JLabel("Aktuelle Kartei");
		ausgewaehlteKartei = new JLabel("!!!!!!!!!!!!!!!");

		karteiBearbeiten = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("karteiBearbeiten"));
		lernen = new JButton("Jetzt lernen");
	}
	
	public void paint() {
		datenStatistik.setLayout(new GridLayout(3, 2));
		datenStatistik.add(richtigeAntwort);
		datenStatistik.add(falscheAntwort);
		datenStatistik.add(total);

		mainFrame.setLayout(new GridLayout(3, 1));

		kartei.setLayout(new GridLayout(1, 3));
		stat.setLayout(new GridLayout(1, 2));

		kartei.add(aktuelleKartei, BorderLayout.WEST);
		kartei.add(ausgewaehlteKartei, BorderLayout.CENTER);
		kartei.add(karteiBearbeiten, BorderLayout.EAST);

		stat.add(grafischeStat);
		stat.add(datenStatistik);

		start.add(lernen, BorderLayout.CENTER);

		mainFrame.add(kartei);
		mainFrame.add(stat);
		mainFrame.add(start);

		add(mainFrame);
		
	}

	private void bindListener() {

		lernen.addActionListener(new ButtonListenerKarteiJetztLernen());
		karteiBearbeiten.addActionListener(new ButtonListenerKarteiBearbeiten());

	}

	class ButtonListenerKarteiJetztLernen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Lernen");
		}

	}

	class ButtonListenerKarteiBearbeiten implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("wechseln");

		}
	}
}
