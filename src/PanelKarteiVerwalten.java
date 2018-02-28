import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.0
 * Datum:28.02.2018
 */

public class PanelKarteiVerwalten {

	private JFrame mainFrame;
	private JPanel hauptPanel;

	private JPanel hinzufuegenKartei;

	private JPanel sprachen;
	private JPanel zweiBuchstaben;
	private JPanel spracheAusgeschrieben;
	private JLabel strich;

	private JPanel buttonPanelHinzufuegen;
	private JLabel info1;
	private JLabel info2;
	private JButton hinzufuegen;
	private JLabel sprache1;
	private JTextField sprache1hinzufuegenZweiBuchstaben;
	private JTextField sprache1hinzufuegenAusgeschrieben;
	private JLabel sprache2;
	private JTextField sprache2hinzufuegenZweiBuchstaben;
	private JTextField sprache2hinzufuegenAusgeschrieben;

	public PanelKarteiVerwalten() {
		initComponents();
		bindListener();
		paint();
		final Dimension d = mainFrame.getToolkit().getScreenSize();
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 2.6),
				(int) ((d.getHeight() - mainFrame.getHeight()) / 2.6));

	}

	// Comonents initieren
	
	private void initComponents() {
		// TODO Auto-generated method stub
		mainFrame = new JFrame(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		hauptPanel = new JPanel();
		hinzufuegenKartei = new JPanel();
		strich = new JLabel("-");

		buttonPanelHinzufuegen = new JPanel();
		info1 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("info1"));
		info2 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("info2"));
		hinzufuegen = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		hinzufuegen.setLocation(200, 20);
		hinzufuegen.setSize(220, 30);

		sprache1 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("sprache1"));
		sprache1.setPreferredSize(new Dimension(220, 22));
		sprache2 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("sprache2"));
		sprache2.setPreferredSize(new Dimension(220, 22));
		sprache1hinzufuegenZweiBuchstaben = new JTextField();
		sprache1hinzufuegenZweiBuchstaben.setPreferredSize(new Dimension(80, 22));
		sprache1hinzufuegenAusgeschrieben = new JTextField();
		sprache1hinzufuegenAusgeschrieben.setPreferredSize(new Dimension(220, 22));

		sprache2hinzufuegenZweiBuchstaben = new JTextField();
		sprache2hinzufuegenZweiBuchstaben.setPreferredSize(new Dimension(80, 22));
		sprache2hinzufuegenAusgeschrieben = new JTextField();
		sprache2hinzufuegenAusgeschrieben.setPreferredSize(new Dimension(220, 22));

		sprachen = new JPanel();
		zweiBuchstaben = new JPanel();
		spracheAusgeschrieben = new JPanel();

	}

	public void paint() {
		mainFrame.setSize(650, 430);
		hauptPanel.setLayout(new GridLayout(6, 1));

		hinzufuegenKartei.setLayout(new GridLayout(1, 2));

		buttonPanelHinzufuegen.add(hinzufuegen);
		buttonPanelHinzufuegen.setLayout(null);

		sprachen.add(sprache1);
		sprachen.add(strich);
		sprachen.add(sprache2);
		zweiBuchstaben.add(sprache1hinzufuegenZweiBuchstaben);
		zweiBuchstaben.add(strich);
		zweiBuchstaben.add(sprache2hinzufuegenZweiBuchstaben);
		spracheAusgeschrieben.add(sprache1hinzufuegenAusgeschrieben);
		spracheAusgeschrieben.add(strich);
		spracheAusgeschrieben.add(sprache2hinzufuegenAusgeschrieben);

		hauptPanel.add(sprachen);
		hauptPanel.add(info1);
		hauptPanel.add(zweiBuchstaben);
		hauptPanel.add(info2);
		hauptPanel.add(spracheAusgeschrieben);
		hauptPanel.add(buttonPanelHinzufuegen);

		mainFrame.add(hauptPanel);
		mainFrame.setVisible(true);

	}

	private void bindListener() {
		hinzufuegen.addActionListener(new ButtonListenerHinzufuegen());


		// TODO Auto-generated method stub

	}

	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String c = sprache1hinzufuegenZweiBuchstaben.getText() + "-" + sprache2hinzufuegenZweiBuchstaben.getText();
			System.out.println(c);
			Main.daten1.spracheHinzugfuegen(c, sprache1hinzufuegenAusgeschrieben.getText(), sprache2hinzufuegenAusgeschrieben.getText());
		
		}

	}
	
}
