import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Hauptfenster {
	public static Locale locale;

	private JFrame mainFrame;
	private JButton[] boxAuswahl;
	private JComboBox<String>  lernSprachenMenu;
	private JLabel lBenutzer, lLernSprache;
	private JPanel statPanel, karteiPanel, menuPanel;
	private PanelLernen panelLernen;
	private PanelKartei panelKartei;
	private ImageIcon icon;
	
	public Hauptfenster(Locale lokal) {
		Hauptfenster.locale = lokal;
		mainFrame = new JFrame("LeCard", null);
		karteiPanel = new JPanel();
		menuPanel = new JPanel();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setResizable(false);
		final Dimension d = mainFrame.getToolkit().getScreenSize();
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 3.8),
				(int) ((d.getHeight() - mainFrame.getHeight()) / 3.8));
		initComponents();
		bindListener();
	}

	private void initComponents() {
		statPanel = new JPanel();
		panelKartei = new PanelKartei();
		icon = new ImageIcon(this.getClass().getResource("LeCard.png"));
		
		boxAuswahl = new JButton[6];
		boxAuswahl[0] = new JButton("Home");
		for (int i=1; i <=5; i++ ) {
			boxAuswahl[i] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " " + i);
		}

		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer") + ": "+ Main.daten1.getBenutzer().getBenutzername());
		lLernSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Lernsprache") + ": ");
		lLernSprache.setHorizontalAlignment(JTextField.RIGHT);
		
		//Panel initiieren
		panelLernen = new PanelLernen();


		//Dropdown Lernsprachen
		lernSprachenMenu = new JComboBox<String>();
		
		for(Sprache s : Main.daten1.getSprachen()) {
			lernSprachenMenu.addItem(s.getSprachPaar());
		}
		

	}

	public void bindListener() {
		lernSprachenMenu.addActionListener(new DropDownListenerLernSprache());
		
		for (int i = 0; i<=5; i++) {
			boxAuswahl[i].addActionListener(new ButtonListenerKartei());
		}

	}

	public void paint() {
		mainFrame.setSize(1000, 500);
		mainFrame.setIconImage(icon.getImage());
		
		
		karteiPanel.setLayout(new GridLayout(6, 1, 5, 5));
		Border border = karteiPanel.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		karteiPanel.setBorder(new CompoundBorder(border, margin));
		
		menuPanel.setLayout(new GridLayout(1, 2));
		menuPanel.setBorder(new CompoundBorder(border, margin));
		
		mainFrame.add(karteiPanel, BorderLayout.WEST);

		statPanel.add(panelKartei);
		
		for (int i=0; i <=5; i++ ) {
			karteiPanel.add(boxAuswahl[i]);
			boxAuswahl[i].setBackground(Color.lightGray);
		}
		boxAuswahl[0].setBackground(Color.CYAN);

		menuPanel.add(lBenutzer);		
		menuPanel.add(lLernSprache);
		menuPanel.add(lernSprachenMenu);

		mainFrame.add(statPanel, BorderLayout.CENTER);
		mainFrame.add(menuPanel, BorderLayout.NORTH);
		mainFrame.setJMenuBar(new HauptMenu());

		mainFrame.setVisible(true);

	}

	
	class DropDownListenerLernSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			String selection = (String) cb.getSelectedItem();
			Main.daten1.spracheWaehlen(selection);
			System.out.println(selection);
		}

	}

	class ButtonListenerKartei implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			for (int i=0; i <=5; i++ ) {
				karteiPanel.add(boxAuswahl[i]);
				boxAuswahl[i].setBackground(Color.lightGray);
			}
			int str = Integer.parseInt(0 + b.getText().replaceAll("\\D+",""));
			if (str == 0) {
				boxAuswahl[0].setBackground(Color.CYAN);
				Main.daten1.setAktuellesFach(str);
				statPanel.removeAll();
				panelKartei.setBalkendiagramm();
				statPanel.add(panelKartei);
				statPanel.validate();
				statPanel.repaint();
			}
			else {
				boxAuswahl[str].setBackground(Color.CYAN);
				Main.daten1.setAktuellesFach(str);
				Main.daten1.gibNaechsteKarte();
				System.out.println(Main.daten1.getAktuelleKarte());
				panelLernen.loadCard();
				statPanel.removeAll();
				statPanel.add(panelLernen);
				statPanel.validate();
				statPanel.repaint();
			}
		}
	}
	

	public void spracheWechseln() {
		
		// Sprache der Labels �ndern
		lBenutzer.setText(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lLernSprache.setText(ResourceBundle.getBundle("Bundle", locale).getString("Lernsprache"));
		boxAuswahl[0].setText(ResourceBundle.getBundle("Bundle", locale).getString("ButtonKartei"));
		for (int i=1; i <=5; i++ ) {
			boxAuswahl[i].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " "+ i);
		}
		// KarteiPanel aktualisieren auf neue Sprache
		panelKartei.removeAll();
		panelKartei.repaint();
		panelKartei.paint();
		
		// PanelLernen aktualisieren auf neue Sprache
		panelLernen.removeAll();
		panelLernen.repaint();
		panelLernen.paint();
		
		// Hauptmenu neu erstellen
		mainFrame.setJMenuBar(new HauptMenu());
		
		
	}

	public void karteLoeschen() {
		
		int result = JOptionPane.showConfirmDialog(null, "Delete this Card?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			Karte kl = Main.daten1.getAktuelleKarte();
			Main.daten1.karteLoeschen(kl);
			panelLernen.loadCard();
		} else if (result == JOptionPane.NO_OPTION) {
		    System.exit(0);
		} 
		

		
	}
	
	

}