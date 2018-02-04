import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel statistik;
	private JLabel ausgewaehlteKartei;

	public PanelKartei() {
		mainFrame = new JPanel();
		grafischeStat = new JPanel();
		statistik = new JPanel();
		kartei = new JPanel();
		stat = new JPanel();
		start = new JPanel();

		aktuelleKartei = new JLabel("Aktuelle Kartei");
		ausgewaehlteKartei = new JLabel("!!!!!!!!!!!!!!!");

		karteiBearbeiten = new JButton("Kartei wechseln/hinzufügen");
		lernen = new JButton("Jetzt lernen");
		lernen.addActionListener(new ButtonListenerKarteiJetztLernen());

		mainFrame.setSize(700, 500);
		mainFrame.setLayout(new GridLayout(3, 1));

		kartei.setLayout(new GridLayout(1, 3));
		stat.setLayout(new GridLayout(1, 2));

		kartei.add(aktuelleKartei, BorderLayout.WEST);
		kartei.add(ausgewaehlteKartei, BorderLayout.CENTER);
		kartei.add(karteiBearbeiten, BorderLayout.EAST);

		stat.add(grafischeStat);
		stat.add(statistik);

		start.add(lernen, BorderLayout.CENTER);

		mainFrame.add(kartei);
		mainFrame.add(stat);
		mainFrame.add(start);

		add(mainFrame);

	}

	class ButtonListenerKarteiJetztLernen implements ActionListener {

		public void actionPerformed(ActionEvent e) {

		}

	}
}
