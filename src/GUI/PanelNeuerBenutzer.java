package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Logik.Kartei;
/** @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * 	@version 0.2
 * 	Datum:11.03.2018
 */
public class PanelNeuerBenutzer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pBenutzer;
	private JPanel pPasswort;
	private JPanel pErstellen;
	private JLabel lBenutzer;
	private JLabel lPasswort;
	private JTextField tBenutzer;
	private JPasswordField tPasswort;
	private JButton bErstellen;
	private Locale locale;

	public PanelNeuerBenutzer(Locale lokal) {
		this.locale = lokal;
		initComponents();
		bindListener();
		paint();
	}
	/**
	 * intialisiert die Komponenten 
	 */

	private void initComponents() {
		this.setTitle(ResourceBundle.getBundle("Bundle", locale).getString("neuerbenutzer"));
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("/LeCard.png")).getImage());
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Benutzer"));
		lPasswort = new JLabel(ResourceBundle.getBundle("Bundle", locale).getString("Passwort"));

		tBenutzer = new JTextField();
		tPasswort = new JPasswordField();
		pBenutzer = new JPanel();
		pPasswort = new JPanel();
		pErstellen = new JPanel();
		bErstellen = new JButton(ResourceBundle.getBundle("Bundle", locale).getString("erstellen"));
	}

	private void bindListener() {
		bErstellen.addActionListener(new ButtonListenerBenutzerErstellen());
	}

	/**
	 * Setzt die einzelnen Komponenten und teilt die dem richtigen Layout hinzu
	 * 
	 */
	public void paint() {
		this.setSize(400, 110);
		pBenutzer.setLayout(new GridLayout(1, 2));
		pPasswort.setLayout(new GridLayout(1, 2));
		pErstellen.setLayout(new BorderLayout());

		pBenutzer.add(lBenutzer);
		pBenutzer.add(tBenutzer);
		pPasswort.add(lPasswort);
		pPasswort.add(tPasswort);
		pErstellen.add(bErstellen, BorderLayout.CENTER);

		this.add(pBenutzer, BorderLayout.NORTH);
		this.add(pPasswort, BorderLayout.CENTER);
		this.add(pErstellen, BorderLayout.SOUTH);
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.0), (int) ((d.getHeight() - this.getHeight()) / 2.3));

		this.setVisible(true);
	}
	
	public void verify() {
		
	}
	/**
	 * Methode um einen neuen Benutzer zu erstellen 
	 * Prüft ob der Benutzer bereits vorhanden ist
	 */

	class ButtonListenerBenutzerErstellen implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if (Kartei.getInstance().benutzerExistiert(tBenutzer.getText()) == false){
				Kartei.getInstance().benutzerHinzufuegen(tBenutzer.getText(), tPasswort.getText());
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundle", locale).getString("Benutzererstellt"));
				Kartei.getInstance().lernkarteiSpeichern();
				dispose();
				LoginFrame gui1 = new LoginFrame();
				gui1.paint();
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundle", locale).getString("Benutzerexistiert"));
				
			}
			

			

			
		}

	}

}
