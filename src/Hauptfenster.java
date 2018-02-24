import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hauptfenster {
	public static Locale locale;

	private JFrame mainFrame;
	private JLabel lSprache;
	private JButton[] boxAuswahl;
	private JComboBox<String>  kartenMenu;
	private JComboBox<String>  anzeigeSprachenMenu;
	private JComboBox<String>  lernSprachenMenu;
	private JLabel lBenutzer;
	private JLabel lAngBenutzer;
	private JLabel lKarten;
	private String country;
	private String language;
	private JPanel statPanel;
	private PanelLernen p1;
	private PanelKartei k1;

	private ArrayList<String> kartenMenuBox;
	// -------------
	private JPanel karteiPanel;
	private JPanel menuPanel;

	private JButton importButton;

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
		k1 = new PanelKartei();
		
		boxAuswahl = new JButton[6];
		boxAuswahl[0] = new JButton("Home");
		for (int i=1; i <=5; i++ ) {
			boxAuswahl[i] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " " + i);
		}

		lAngBenutzer = new JLabel(main.daten1.getBenutzer().getBenutzername());
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lKarten = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Karten"));
		lSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Sprache"));
		
		//Panel initiieren
		p1 = new PanelLernen();

		// Dropdown Sprachenmenu
		String spracheBox[] = { "Deutsch", "English", "Francaise", "Italiano" };
		anzeigeSprachenMenu = new JComboBox<String>(spracheBox);

		// Dropdown Karteimenu
		kartenMenuBox = new ArrayList<String>();

		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("loeschen"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("bearbeiten"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("hinzufuegen"));

		
		kartenMenu = new JComboBox<String>();

		importButton = new JButton("Import");

		for (int i = 0; i < kartenMenuBox.size(); i++) {
			kartenMenu.addItem(kartenMenuBox.get(i));
		}

		switch (ResourceBundle.getBundle("Bundle", locale).getLocale().getLanguage()) {
		case "de":
			anzeigeSprachenMenu.setSelectedIndex(0);
			break;
		case "en":
			anzeigeSprachenMenu.setSelectedIndex(1);
			break;
		case "fr":
			anzeigeSprachenMenu.setSelectedIndex(2);
			break;
		case "it":
			anzeigeSprachenMenu.setSelectedIndex(3);
			break;
		default:
			break;
		}
		
		//Dropdown Lernsprachen
		
		lernSprachenMenu = new JComboBox<String>();
		
		for(Sprache s : main.daten1.getSprachen()) {
			lernSprachenMenu.addItem(s.getSprachPaar());
		}
		
		

	}

	public void bindListener() {
		anzeigeSprachenMenu.addActionListener(new DropDownListenerAnzeigeSprache());
		lernSprachenMenu.addActionListener(new DropDownListenerLernSprache());
		kartenMenu.addActionListener(new DropDownListenerKarten());
		
		for (int i=0; i <=5; i++ ) {
			boxAuswahl[i].addActionListener(new ButtonListenerKartei());
		}

		importButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// open filechooser & import data
				opendialog dialog = new opendialog();
				File f = dialog.showDialog(("C:"));
				try {
					new LoadCsv(f.getAbsolutePath(), ";");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

	public void paint() {
		mainFrame.setSize(1000, 500);
		karteiPanel.setLayout(new GridLayout(6, 1));
		menuPanel.setLayout(new GridLayout(1, 3));
		mainFrame.add(karteiPanel, BorderLayout.WEST);

		statPanel.add(k1);
		
		for (int i=0; i <=5; i++ ) {
			karteiPanel.add(boxAuswahl[i]);
			boxAuswahl[i].setBackground(Color.lightGray);
		}
		boxAuswahl[0].setBackground(Color.CYAN);

		menuPanel.add(lBenutzer);
		menuPanel.add(lAngBenutzer);
		menuPanel.add(lernSprachenMenu);
		menuPanel.add(lKarten);
		menuPanel.add(kartenMenu);
		menuPanel.add(lSprache);
		menuPanel.add(anzeigeSprachenMenu);

		mainFrame.add(statPanel, BorderLayout.CENTER);
		mainFrame.add(menuPanel, BorderLayout.NORTH);

		mainFrame.setVisible(true);

	}

	class DropDownListenerAnzeigeSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			int msg = cb.getSelectedIndex();
			System.out.println(msg);

			switch (msg) {
			case 0:
				country = new String("DE");
				language = new String("de");
				break;
			case 1:
				country = new String("EN");
				language = new String("en");

				break;
			case 2:
				country = new String("FR");
				language = new String("fr");
				break;
			case 3:
				country = new String("IT");
				language = new String("it");
				break;

			}
			// Sprache der Labels ändern
			locale = new Locale(language, country);
			lBenutzer.setText(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
			lKarten.setText(ResourceBundle.getBundle("Bundle", locale).getString("Karten"));
			lSprache.setText(ResourceBundle.getBundle("Bundle", locale).getString("Sprache"));
			boxAuswahl[0].setText(ResourceBundle.getBundle("Bundle", locale).getString("ButtonKartei"));
			for (int i=1; i <=5; i++ ) {
				boxAuswahl[i].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " "+ i);
			}
			// KarteiPanel aktualisieren auf neue Sprache
			k1.removeAll();
			k1.repaint();
			k1.paint();
			// PanelLernen aktualisieren auf neue Sprache
			p1.removeAll();
			p1.repaint();
			p1.paint();

			// Locale mk = mainFrame.getLocale();
			System.out.println(locale);
			// Dropdown KartenMenu
			kartenMenuBox.clear();
			kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("loeschen"));
			kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("bearbeiten"));
			kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("hinzufuegen"));
			kartenMenu.removeAllItems();

			for (int i = 0; i < kartenMenuBox.size(); i++) {

				kartenMenu.addItem(kartenMenuBox.get(i));
			}

		}

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
				statPanel.add(k1);
				statPanel.validate();
				statPanel.repaint();
			}
			else {
				boxAuswahl[str].setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(str);
				main.daten1.gibNaechsteKarte();
				System.out.println(main.daten1.getAktuelleKarte());
				p1.loadCard();
				statPanel.removeAll();
				statPanel.add(p1);
				statPanel.validate();
				statPanel.repaint();
			}

			
		}

	}

}