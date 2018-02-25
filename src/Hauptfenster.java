import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Hauptfenster {
	public static Locale locale;

	private JFrame mainFrame;
	private JButton[] boxAuswahl;
	private JComboBox<String>  lernSprachenMenu;
	private JLabel lBenutzer, lAngBenutzer, lLernSprache;
	private String country, language;
	private JPanel statPanel, karteiPanel, menuPanel;
	private PanelLernen panelLernen;
	private PanelKartei panelKartei;
	
	public Hauptfenster(Locale lokal) {
		Hauptfenster.locale = lokal;
		mainFrame = new JFrame("LeCard", null);
		karteiPanel = new JPanel();
		menuPanel = new JPanel();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension d = mainFrame.getToolkit().getScreenSize();
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 3.8),
				(int) ((d.getHeight() - mainFrame.getHeight()) / 3.8));
		initComponents();
		bindListener();
	}

	private void initComponents() {
		statPanel = new JPanel();
		panelKartei = new PanelKartei();
		
		boxAuswahl = new JButton[6];
		boxAuswahl[0] = new JButton("Home");
		for (int i=1; i <=5; i++ ) {
			boxAuswahl[i] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " " + i);
		}

		lAngBenutzer = new JLabel(main.daten1.getBenutzer().getBenutzername());
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lLernSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Lernsprache"));
		
		//Panel initiieren
		panelLernen = new PanelLernen();


		//Dropdown Lernsprachen
		lernSprachenMenu = new JComboBox<String>();
		
		for(Sprache s : main.daten1.getSprachen()) {
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
		karteiPanel.setLayout(new GridLayout(6, 1));
		menuPanel.setLayout(new GridLayout(1, 3));
		mainFrame.add(karteiPanel, BorderLayout.WEST);

		statPanel.add(panelKartei);
		
		for (int i=0; i <=5; i++ ) {
			karteiPanel.add(boxAuswahl[i]);
			boxAuswahl[i].setBackground(Color.lightGray);
		}
		boxAuswahl[0].setBackground(Color.CYAN);

		menuPanel.add(lBenutzer);
		menuPanel.add(lAngBenutzer);
		
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
			main.daten1.spracheWaehlen(selection);
			System.out.println(selection);
		}

	}

	class DropDownListenerKarten implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			int msg = cb.getSelectedIndex();
			System.out.println(msg);

			switch (msg) {
			case 0:
				// Methode bearbeiten
				// Muss erst gemacht werden
				break;
			case 1:
				// löschen
				break;

			case 2:
				PanelHinzufuegen gui1 = new PanelHinzufuegen();
				gui1.paint();
				break;
			}

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
				statPanel.removeAll();
				statPanel.add(panelKartei);
				statPanel.validate();
				statPanel.repaint();
			}
			else {
				boxAuswahl[str].setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(str);
				main.daten1.gibNaechsteKarte();
				System.out.println(main.daten1.getAktuelleKarte());
				panelLernen.loadCard();
				statPanel.removeAll();
				statPanel.add(panelLernen);
				statPanel.validate();
				statPanel.repaint();
			}

			
		}

	}
	
	class ListenerDateiMenu implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem menuitem = (JMenuItem) e.getSource();
			String msg = menuitem.getText();
			System.out.println(msg);

			switch (msg) {
			case "Beenden":
				break;
			case "Import":
				break;
			}
		}

	}

	public void spracheWechseln() {
		
		// Sprache der Labels ändern
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

}