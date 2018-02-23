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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.scene.control.ComboBox;

public class Hauptfenster {
	public static Locale locale;

	private JFrame mainFrame;
	private JLabel lSprache;
	private JButton[] boxAuswahl;
	private JComboBox kartenMenu;
	private JComboBox sprachenMenu;
	private JComboBox lernSprachenMenu;
	private JLabel lBenutzer;
	private JLabel lAngBenutzer;
	private JLabel lKarten;
	private String country;
	private String language;
	private JPanel statPanel;
	// muss noch geändert werden
	private PanelLernen p1;
	private PanelKartei k1;

	private ArrayList<String> kartenMenuBox;
	// -------------
	private JPanel karteiPanel;
	private JPanel menuPanel;

	private JButton importButton;

	public Hauptfenster(Locale lokal) {
		this.locale = lokal;
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
		boxAuswahl[1] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 1");
		boxAuswahl[2] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 2");
		boxAuswahl[3] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 3");
		boxAuswahl[4] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 4");
		boxAuswahl[5] = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 5");
		lAngBenutzer = new JLabel(main.daten1.getBenutzer().getBenutzername());
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lKarten = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Karten"));
		lSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Sprache"));
		//Panel initiieren
		p1 = new PanelLernen();

		// Dropdown Sprachenmenu
		String spracheBox[] = { "Deutsch", "English", "Francaise", "Italiano" };
		sprachenMenu = new JComboBox(spracheBox);

		// Dropdown Karteimenu
		kartenMenuBox = new ArrayList<String>();

		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("loeschen"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("bearbeiten"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("hinzufuegen"));

		kartenMenu = new JComboBox();

		importButton = new JButton("Import");

		for (int i = 0; i < kartenMenuBox.size(); i++) {
			kartenMenu.addItem(kartenMenuBox.get(i));
		}

		System.out.println(ResourceBundle.getBundle("Bundle", locale).getLocale().getLanguage());
		switch (ResourceBundle.getBundle("Bundle", locale).getLocale().getLanguage()) {
		case "de":
			sprachenMenu.setSelectedIndex(0);
			break;
		case "en":
			sprachenMenu.setSelectedIndex(1);
			break;
		case "fr":
			sprachenMenu.setSelectedIndex(2);
			break;
		case "it":
			sprachenMenu.setSelectedIndex(3);
			break;
		default:
			break;
		}
		
		//Dropdown SpracheLernen
		lernSprachenMenu = new JComboBox();
		
		for(String s : main.daten1.getSprachen()) {
			lernSprachenMenu.addItem(s);
			System.out.println(s);
		}
		
		

	}

	public void bindListener() {
		sprachenMenu.addActionListener(new DropDownListenerSprache());
		kartenMenu.addActionListener(new DropDownListenerKarten());
		boxAuswahl[0].addActionListener(new ButtonListenerKartei());
		boxAuswahl[1].addActionListener(new ButtonListenerKartei());
		boxAuswahl[2].addActionListener(new ButtonListenerKartei());
		boxAuswahl[3].addActionListener(new ButtonListenerKartei());
		boxAuswahl[4].addActionListener(new ButtonListenerKartei());
		boxAuswahl[5].addActionListener(new ButtonListenerKartei());
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
		// mainFrame.setLayout(new GridLayout(1,2));
		karteiPanel.setLayout(new GridLayout(6, 1));
		menuPanel.setLayout(new GridLayout(1, 3));
		// menuPanel.setLayout(new GridLayout(2,1));
		mainFrame.add(karteiPanel, BorderLayout.WEST);

		statPanel.add(k1);

		karteiPanel.add(boxAuswahl[0]);
		karteiPanel.add(boxAuswahl[1]);
		karteiPanel.add(boxAuswahl[2]);
		karteiPanel.add(boxAuswahl[3]);
		karteiPanel.add(boxAuswahl[4]);
		karteiPanel.add(boxAuswahl[5]);

		boxAuswahl[0].setBackground(Color.CYAN);
		boxAuswahl[1].setBackground(Color.lightGray);
		boxAuswahl[2].setBackground(Color.lightGray);
		boxAuswahl[3].setBackground(Color.lightGray);
		boxAuswahl[4].setBackground(Color.lightGray);
		boxAuswahl[5].setBackground(Color.lightGray);

		menuPanel.add(lBenutzer);
		menuPanel.add(lAngBenutzer);
		menuPanel.add(lernSprachenMenu);
		menuPanel.add(lKarten);
		menuPanel.add(this.kartenMenu);
		menuPanel.add(lSprache);
		menuPanel.add(this.sprachenMenu);

		mainFrame.add(statPanel, BorderLayout.CENTER);
		mainFrame.add(menuPanel, BorderLayout.NORTH);

		mainFrame.setVisible(true);

	}

	class DropDownListenerSprache implements ActionListener {

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
			boxAuswahl[1].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 1");
			boxAuswahl[2].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 2");
			boxAuswahl[3].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 3");
			boxAuswahl[4].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 4");
			boxAuswahl[5].setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 5");
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
			boxAuswahl[0].setBackground(Color.lightGray);
			boxAuswahl[1].setBackground(Color.lightGray);
			boxAuswahl[2].setBackground(Color.lightGray);
			boxAuswahl[3].setBackground(Color.lightGray);
			boxAuswahl[4].setBackground(Color.lightGray);
			boxAuswahl[5].setBackground(Color.lightGray);
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