package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Logik.Kartei;

/** @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 *  @version 0.3
 *  Datum:11.03.2018
 */
public class LoginFrame extends JFrame {

	/**
	 * Die Klasse ermöglicht es sich mit einem Benutzer und Passwort zu authentifizieren 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel loginPanel;
	private JPanel buttonPanel;
	private JButton login;
	private JButton neuerUser;
	private JComboBox<String> sprachenMenu;
	private Locale lokal;
	private String country;
	private String language;
	private JTextField tUser;
	private JPasswordField pPasswort;
	private JLabel lBenutzerlogin;
	private JLabel lPasswort;

	public LoginFrame() {
		initComponents();
		bindListener();
		// setzt das Fenster in die Mitte des Bildschirms
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.6), (int) ((d.getHeight() - this.getHeight()) / 2.6));
	}

	public JLabel getlBenutzerlogin() {
		return lBenutzerlogin;
	}
	/**
	 * Intialisiert die einzelnen Komponenten und setzt die entsprechende Farbe
	 */
	private void initComponents() {
		this.setTitle("Login");
		loginPanel = new JPanel();
		buttonPanel = new JPanel();
		loginPanel.setBackground(Color.white);
		buttonPanel.setBackground(Color.white);
		login = new JButton("Login");
		this.language = "de";
		this.country = "DE";
		this.lokal = new Locale("de", "DE");
		// Hier kann die Sprache ausgewählt werden 
		String spracheBox[] = { "Deutsch", "English", "Francaise", "Italiano" };
		sprachenMenu = new JComboBox<String>(spracheBox);
		tUser = new JTextField();
		pPasswort = new JPasswordField();
		//Hier wird das Bild für das Logo angezogen
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("LeCard.png")).getImage());
		
		pPasswort.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					login.doClick();
				}
			}
		});
		//Buttons mit den entsprechenden Bundel für die Sprachen bezeichnungen 
		lBenutzerlogin = new JLabel(ResourceBundle.getBundle("Bundle", lokal).getString("Benutzer"));
		lPasswort = new JLabel(ResourceBundle.getBundle("Bundle", lokal).getString("Passwort"));
		neuerUser = new JButton(ResourceBundle.getBundle("Bundle", lokal).getString("neuerUser"));

	}
	/**
	 * listener für Sprache Login und User
	 */
	public void bindListener() {
		sprachenMenu.addActionListener(new DropDownListenerSprache());
		login.addActionListener(new ButtonListenerLogin());
		neuerUser.addActionListener(new ButtonListenerNeuerBenutzer());
	}
	
	/**
	 * deklariert die einzelnen Panel mit den dazugehörigen Layouts
	 */
	public void paint() {
		this.setSize(450, 130);

		loginPanel.setLayout(new GridLayout(2, 2));
		buttonPanel.setLayout(new GridLayout(1, 3));

		loginPanel.add(lBenutzerlogin);
		loginPanel.add(tUser);
		loginPanel.add(lPasswort);
		loginPanel.add(pPasswort);

		buttonPanel.add(sprachenMenu);
		buttonPanel.add(neuerUser);
		buttonPanel.add(login);

		this.add(loginPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
		// setzt das Panel sichtbar
		this.setVisible(true);
	}
	/**
	 * Listener für das SprachMenu 
	 */
	class DropDownListenerSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String selection = (String) sprachenMenu.getSelectedItem();

			switch (selection) {
			case "Deutsch":
				country = new String("DE");
				language = new String("de");
				break;
			case "English":
				country = new String("EN");
				language = new String("en");
				break;
			case "Francaise":
				country = new String("FR");
				language = new String("fr");
				break;
			case "Italiano":
				country = new String("IT");
				language = new String("it");
				break;

			}

			lokal = new Locale(language, country);
			lBenutzerlogin.setText(ResourceBundle.getBundle("Bundle", lokal).getString("Benutzer"));
			lPasswort.setText(ResourceBundle.getBundle("Bundle", lokal).getString("Passwort"));
			neuerUser.setText(ResourceBundle.getBundle("Bundle", lokal).getString("neuerUser"));
		}

	}
	/**
	 * Listener für den Login Button
	 * Prüft ob der User bereits vorhanden ist 
	 *
	 */
	class ButtonListenerLogin implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			@SuppressWarnings("deprecation")
			// prüft für den entsprechenden User das entsprechende Passwort
			Boolean userExist = Kartei.getInstance().benutzerLaden(tUser.getText(), pPasswort.getText());
			//Wenn es richtig ist
			if (userExist == true) {

				Kartei.getInstance().setLocale(lokal);
				Hauptfenster.getInstance().paint();
				((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
			} 
			//Falls das Passwort Falsch eingegeben wurde 
			else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundle", lokal).getString("falschesPasswort"));

			}
		}
	}
	/**
	 * Listener für den Button Neuer Benutzer
	 */
	class ButtonListenerNeuerBenutzer implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			PanelNeuerBenutzer h1 = new PanelNeuerBenutzer(lokal);
			h1.paint();
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
		}
	}


}
