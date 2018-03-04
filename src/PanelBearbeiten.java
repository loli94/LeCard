import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextField;
/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6
 * Datum:24.02.2018
 */
public class PanelBearbeiten {

	private JFrame mainFrame_1;
	private JPanel hinzufuegenPanelText;
	private JPanel hinzufuegenPanelButton; 
	private JLabel lSprache1;
	private JLabel lSprache2;
	private JTextField tSprache1;
	private JTextField tSprache2;
	private JButton hinzufuegenButton;
	
	

	public PanelBearbeiten() {
		initComponents();
		bindListener();
		
	}

	private void initComponents() {
		mainFrame_1 = new JFrame(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
		lSprache1 = new JLabel(Main.daten1.getAktuelleKarte().getWortA());
		lSprache2 = new JLabel(Main.daten1.getAktuelleKarte().getWortB());
		tSprache1 = new JTextField();
		
		tSprache2 = new JTextField();
		hinzufuegenPanelText = new JPanel();
		hinzufuegenPanelButton = new JPanel(); 
		hinzufuegenButton = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen")); 
	}

	private void bindListener() {
		hinzufuegenButton.addActionListener(new ButtonListenerHinzufuegen());
	}
	
	public void paint() {
		mainFrame_1.setSize(800, 450);
		hinzufuegenPanelText.setLayout(new GridLayout(2, 1));
		
		hinzufuegenPanelText.add(lSprache1);
		hinzufuegenPanelText.add(tSprache1);
		hinzufuegenPanelText.add(lSprache2);
		hinzufuegenPanelText.add(tSprache2);
		hinzufuegenPanelButton.add(hinzufuegenButton); 
		
		
		
		mainFrame_1.add(hinzufuegenPanelText, BorderLayout.CENTER);
		mainFrame_1.add(hinzufuegenPanelButton, BorderLayout.SOUTH); 
		
		mainFrame_1.setVisible(true);
	}
	
	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if ( !tSprache1.getText().isEmpty() || !tSprache2.getText().isEmpty()) {

				if (tSprache1.getText().matches("[a-zA-Z]+") && tSprache2.getText().matches("[a-zA-Z]+")) {
					Karte k1 = new Karte(Main.daten1.getAktuellesSprachpaar(), tSprache1.getText(),
							tSprache2.getText());
					Main.daten1.karteHinzufuegen(k1);
					JOptionPane.showMessageDialog(mainFrame_1, "" + tSprache1.getText() + ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextBearbeiten1"));
				} else {
					JOptionPane.showMessageDialog(mainFrame_1,ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextBearbeiten2"));
				}

			} else {
				JOptionPane.showMessageDialog(mainFrame_1,ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextBearbeiten3"));
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanelBearbeiten h1 = new PanelBearbeiten();
		h1.paint();

	}

}
