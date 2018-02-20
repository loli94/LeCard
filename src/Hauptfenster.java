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
	private JButton home;
	private JButton kartei1;
	private JButton kartei2;
	private JButton kartei3;
	private JButton kartei4;
	private JButton kartei5;
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
	private PanelLernen p2;
	private PanelLernen p3;
	private PanelLernen p4;
	private PanelLernen p5;
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
		JComponent jc = (JComponent) mainFrame.getContentPane();
		initComponents();
		bindListener();
	}

	private void initComponents() {
		statPanel = new JPanel();
		k1 = new PanelKartei();
		kartei1 = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 1");
		kartei2 = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 2");
		kartei3 = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 3");
		kartei4 = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 4");
		kartei5 = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 5");
		lAngBenutzer = new JLabel(main.daten1.getBenutzer().getBenutzername());
		home = new JButton("Home");
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lKarten = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Karten"));
		lSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Sprache"));
		//Panel initiieren
		p1 = new PanelLernen();
		p2 = new PanelLernen();
		p3 = new PanelLernen();
		p4= new PanelLernen();
		p5 = new PanelLernen();

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
		home.addActionListener(new ButtonListenerKartei());
		kartei1.addActionListener(new ButtonListenerKartei());
		kartei2.addActionListener(new ButtonListenerKartei());
		kartei3.addActionListener(new ButtonListenerKartei());
		kartei4.addActionListener(new ButtonListenerKartei());
		kartei5.addActionListener(new ButtonListenerKartei());
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

		// statPanel.add(p1);
		statPanel.add(k1);

		karteiPanel.add(home);
		karteiPanel.add(kartei1);
		karteiPanel.add(kartei2);
		karteiPanel.add(kartei3);
		karteiPanel.add(kartei4);
		karteiPanel.add(kartei5);
		// karteiPanel.add(importButton);

		home.setBackground(Color.CYAN);
		kartei1.setBackground(Color.lightGray);
		kartei2.setBackground(Color.lightGray);
		kartei3.setBackground(Color.lightGray);
		kartei4.setBackground(Color.lightGray);
		kartei5.setBackground(Color.lightGray);

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
			home.setText(ResourceBundle.getBundle("Bundle", locale).getString("ButtonKartei"));
			kartei1.setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 1");
			kartei2.setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 2");
			kartei3.setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 3");
			kartei4.setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 4");
			kartei5.setText(ResourceBundle.getBundle("Bundle", locale).getString("Fach") + " 5");
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
			String msg = (String) b.getText();
			home.setBackground(Color.lightGray);
			kartei1.setBackground(Color.lightGray);
			kartei2.setBackground(Color.lightGray);
			kartei3.setBackground(Color.lightGray);
			kartei4.setBackground(Color.lightGray);
			kartei5.setBackground(Color.lightGray);
			String str = msg.replaceAll("\\D+","");
			System.out.println(str);
			switch (str) {
			case "":
				home.setBackground(Color.CYAN);
				statPanel.removeAll();
				statPanel.add(k1);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "1":
				kartei1.setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(1);
				main.daten1.gibNaechsteKarte();
				statPanel.removeAll();
				statPanel.add(p1);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "2":
				kartei2.setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(2);
				main.daten1.gibNaechsteKarte();
				statPanel.removeAll();
				statPanel.add(p2);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "3":
				kartei3.setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(3);
				main.daten1.gibNaechsteKarte();
				statPanel.removeAll();
				statPanel.add(p3);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "4":
				kartei4.setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(4);
				main.daten1.gibNaechsteKarte();
				statPanel.removeAll();
				statPanel.add(p4);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "5":
				kartei5.setBackground(Color.CYAN);
				main.daten1.setAktuellesFach(5);
				main.daten1.gibNaechsteKarte();
				statPanel.removeAll();
				statPanel.add(p5);
				statPanel.validate();
				statPanel.repaint();
				break;
			}

		}

	}

	public static void main(String[] args) {

		Hauptfenster gui1 = new Hauptfenster(locale);
		try {
			// Set cross-platform Java L&F (also called o"Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle ii
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exceptiondd
		}

		gui1.paint();
	}
}