import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;
/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.3
 * Datum:24.02.2018
 */
public class LoginFrame {

	private JFrame mainFrame;
	private JPanel loginPanel;
	private JPanel buttonPanel;
	private JButton login;
	private JButton neuerUser;
	private JComboBox sprachenMenu;
	private Locale locale;
	private String country;
	private String language;
	private JTextField tUser;
	private JPasswordField pPasswort;
	private JLabel lBenutzerlogin;
	private JLabel lPasswort;

	public LoginFrame() {
		initComponents();
		bindListener();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension d = mainFrame.getToolkit().getScreenSize(); 
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 2.6), (int) ((d.getHeight() - mainFrame.getHeight()) / 2.6));
	}

	public JLabel getlBenutzerlogin() {
		return lBenutzerlogin;
	}

	private void initComponents() {
		mainFrame = new JFrame("Login", null);
		loginPanel = new JPanel();
		buttonPanel = new JPanel();
		loginPanel.setBackground(Color.white);
		buttonPanel.setBackground(Color.white);
		login = new JButton("Login");
		this.language = "de";
		this.country = "DE";
		this.locale = new Locale(language, country);
		String spracheBox[] = { "Deutsch", "English", "Francaise", "Italiano" };
		sprachenMenu = new JComboBox(spracheBox);
		tUser = new JTextField();
		pPasswort = new JPasswordField();
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
		lBenutzerlogin = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lPasswort = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Passwort"));
		neuerUser = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("neuerUser"));

	}

	public void bindListener() {
		sprachenMenu.addActionListener(new DropDownListenerSprache());
		login.addActionListener(new ButtonListenerLogin());
		neuerUser.addActionListener(new ButtonListenerNeuerBenutzer());
	}

	public void paint() {
		mainFrame.setSize(450, 130);

		loginPanel.setLayout(new GridLayout(2, 2));
		buttonPanel.setLayout(new GridLayout(1, 3));

		loginPanel.add(lBenutzerlogin);
		loginPanel.add(tUser);
		loginPanel.add(lPasswort);
		loginPanel.add(pPasswort);

		buttonPanel.add(sprachenMenu);
		buttonPanel.add(neuerUser);
		buttonPanel.add(login);

		mainFrame.add(loginPanel, BorderLayout.NORTH);
		mainFrame.add(buttonPanel, BorderLayout.CENTER);

		mainFrame.setVisible(true);
	}

	class DropDownListenerSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			String msg = (String) cb.getSelectedItem();
			System.out.println(msg);

			switch (msg) {
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

			locale = new Locale(language, country);
			lBenutzerlogin.setText(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
			lPasswort.setText(ResourceBundle.getBundle("Bundle", locale).getString("Passwort"));
			neuerUser.setText(ResourceBundle.getBundle("Bundle", locale).getString("neuerUser"));
		}

	}

	class ButtonListenerLogin implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			// System.out.println(tUser.getText());
			// System.out.println(pPasswort.getPassword());
			Boolean userExist = Main.daten1.benutzerLaden(tUser.getText(), pPasswort.getText());
			
			if (userExist == true) {

				Main.hauptFenster = new Hauptfenster(locale);
				Main.hauptFenster.paint();
				((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
			} else {
				JOptionPane.showMessageDialog(mainFrame, ResourceBundle.getBundle("Bundle", locale).getString("falschesPasswort"));

			}
		}
	}

	class ButtonListenerNeuerBenutzer implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			PanelNeuerBenutzer h1 = new PanelNeuerBenutzer(locale);
			h1.paint();
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
		}
	}


}
