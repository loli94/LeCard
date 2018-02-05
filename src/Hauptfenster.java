import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public Hauptfenster(Locale lokal) {
		this.locale = lokal;
		mainFrame = new JFrame("LeCard");
		karteiPanel = new JPanel();
		menuPanel = new JPanel();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent jc = (JComponent) mainFrame.getContentPane();
		initComponents();
		bindListener();
	}

	private void initComponents() {
		statPanel = new JPanel();
		p1 = new PanelLernen();
		k1 = new PanelKartei();
		kartei1 = new JButton("1");
		kartei2 = new JButton("2");
		kartei3 = new JButton("3");
		kartei4 = new JButton("4");
		kartei5 = new JButton("5");
		lAngBenutzer = new JLabel("Roman");
		home = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("ButtonKartei"));
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lKarten = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Karten"));
		lSprache = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Sprache"));
		// Dropdown Sprachenmenu
		String spracheBox[] = { "Deutsch", "English", "Francaise", "Italiano" };
		sprachenMenu = new JComboBox(spracheBox);
		// Dropdown Karteimenu
		kartenMenuBox = new ArrayList<String>();

		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("loeschen"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("bearbeiten"));
		kartenMenuBox.add(ResourceBundle.getBundle("Bundle", locale).getString("hinzufuegen"));

		kartenMenu = new JComboBox();

		for (int i = 0; i < kartenMenuBox.size(); i++) {
			kartenMenu.addItem(kartenMenuBox.get(i));
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

		home.setBackground(Color.CYAN);
		kartei1.setBackground(Color.lightGray);
		kartei2.setBackground(Color.lightGray);
		kartei3.setBackground(Color.lightGray);
		kartei4.setBackground(Color.lightGray);
		kartei5.setBackground(Color.lightGray);

		menuPanel.add(lBenutzer);
		menuPanel.add(lAngBenutzer);
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
		
			//Locale mk = mainFrame.getLocale();
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
			switch (msg) {
			case "Kartei":
				home.setBackground(Color.CYAN);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.lightGray);
				statPanel.removeAll();
				statPanel.add(k1);
				statPanel.validate();
				statPanel.repaint();

				break;
			case "Index":
				home.setBackground(Color.CYAN);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.lightGray);
				statPanel.removeAll();
				statPanel.add(k1);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "1":
				home.setBackground(Color.lightGray);
				kartei1.setBackground(Color.CYAN);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.lightGray);
				statPanel.removeAll();
				statPanel.add(p1);
				statPanel.validate();
				statPanel.repaint();
				break;
			case "2":
				home.setBackground(Color.lightGray);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.CYAN);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.lightGray);
				break;
			case "3":
				home.setBackground(Color.lightGray);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.CYAN);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.lightGray);
				break;
			case "4":
				home.setBackground(Color.lightGray);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.CYAN);
				kartei5.setBackground(Color.lightGray);
				break;
			case "5":
				home.setBackground(Color.lightGray);
				kartei1.setBackground(Color.lightGray);
				kartei2.setBackground(Color.lightGray);
				kartei3.setBackground(Color.lightGray);
				kartei4.setBackground(Color.lightGray);
				kartei5.setBackground(Color.CYAN);
				break;
			}

		}

	}

	public static void main(String[] args) {
	
		Hauptfenster gui1 = new Hauptfenster(locale);
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exceptiondd
		}

		gui1.paint();
	}
}