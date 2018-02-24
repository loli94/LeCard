import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.2
 * Datum:24.02.2018
 */
public class PanelNeuerBenutzer {

	private JFrame mainFrame_1;
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

	private void initComponents() {
		mainFrame_1 = new JFrame(ResourceBundle.getBundle("Bundle", locale).getString("neuerbenutzer"));
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

	public void paint() {
		mainFrame_1.setSize(400, 110);
		pBenutzer.setLayout(new GridLayout(1, 2));
		pPasswort.setLayout(new GridLayout(1, 2));
		pErstellen.setLayout(new BorderLayout());

		pBenutzer.add(lBenutzer);
		pBenutzer.add(tBenutzer);
		pPasswort.add(lPasswort);
		pPasswort.add(tPasswort);
		pErstellen.add(bErstellen, BorderLayout.CENTER);

		mainFrame_1.add(pBenutzer, BorderLayout.NORTH);
		mainFrame_1.add(pPasswort, BorderLayout.CENTER);
		mainFrame_1.add(pErstellen, BorderLayout.SOUTH);

		mainFrame_1.setVisible(true);
	}
	
	public void verify() {
		
	}

	class ButtonListenerBenutzerErstellen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (main.daten1.benutzerExistiert(tBenutzer.getText()) == false){
				main.daten1.benutzerHinzufuegen(tBenutzer.getText(), tPasswort.getText());
				JOptionPane.showMessageDialog(mainFrame_1, ResourceBundle.getBundle("Bundle", locale).getString("Benutzererstellt"));
				main.daten1.lernkarteiSpeichern(main.pfad);
				mainFrame_1.dispose();
				LoginFrame gui1 = new LoginFrame();
				gui1.paint();
				
				
			}
			else {
				JOptionPane.showMessageDialog(mainFrame_1, ResourceBundle.getBundle("Bundle", locale).getString("Benutzerexistiert"));
				
			}
			

			

			
		}

	}

	/*public static void main(String[] args) {
		// TODO 
		 String country = "DE";
		 String language = "de";
		Locale locale = new Locale(language, country);
		
		PanelNeuerBenutzer h1 = new PanelNeuerBenutzer(locale);
		h1.paint();

	}*/
}
