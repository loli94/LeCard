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



public class PanelKarteiVerwalten {

	private JFrame mainFrame;
	private JPanel hauptPanel;
	private JPanel vorhandeneKartei;
	private JPanel hinzufuegenKartei;

	private JPanel inhaltKarteiVorhanden;
	private JPanel buttonPanelVorhanden;
	private JLabel vorhandeneKarteien;

	private JButton wechseln;
	private JComboBox<String> lernSprachenMenu;

	private JPanel inhaltKarteiHinzufuegen;
	private JPanel buttonPanelHinzufuegen;
	private JButton hinzufuegen;
	private JLabel sprache1;
	private JTextField sprache1hinzufuegen;
	private JLabel sprache2;
	private JTextField sprache2hinzufuegen;

	public PanelKarteiVerwalten() {
		initComponents();
		bindListener();
		paint();
		final Dimension d = mainFrame.getToolkit().getScreenSize();
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 2.6),
				(int) ((d.getHeight() - mainFrame.getHeight()) / 2.6));

	}

	private void initComponents() {
		// TODO Auto-generated method stub
		mainFrame = new JFrame("Bearbeiten");
		hauptPanel = new JPanel();
		vorhandeneKartei = new JPanel();
		hinzufuegenKartei = new JPanel();
		inhaltKarteiVorhanden = new JPanel();
		buttonPanelVorhanden = new JPanel();
		inhaltKarteiHinzufuegen = new JPanel();
		buttonPanelHinzufuegen = new JPanel();
		wechseln = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("wechseln"));
		wechseln.setLocation(100, 120);
		wechseln.setSize(220, 30);
		hinzufuegen = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		hinzufuegen.setLocation(100, 120);
		hinzufuegen.setSize(220, 30);
		vorhandeneKarteien = new JLabel();

		sprache1 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("sprache1"));
		sprache1.setPreferredSize(new Dimension(220, 22));
		sprache2 = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("sprache2"));
		sprache2.setPreferredSize(new Dimension(220, 22));
		sprache1hinzufuegen = new JTextField();
		sprache2hinzufuegen = new JTextField();

		lernSprachenMenu = new JComboBox<String>();

		for (Sprache s : main.daten1.getSprachen()) {
			lernSprachenMenu.addItem(s.getSprachPaar());
		}
	}

	public void paint() {
		mainFrame.setSize(750, 530);
		hauptPanel.setLayout(new GridLayout(2, 1));
		vorhandeneKartei.setLayout(new GridLayout(1, 2));
		vorhandeneKartei.add(lernSprachenMenu);
		buttonPanelVorhanden.setLayout(null);
		buttonPanelVorhanden.add(wechseln);
		vorhandeneKartei.add(buttonPanelVorhanden);

		hinzufuegenKartei.setLayout(new GridLayout(1, 2));
		inhaltKarteiHinzufuegen.setLayout(new GridLayout(2, 2));
		buttonPanelHinzufuegen.add(hinzufuegen);
		buttonPanelHinzufuegen.setLayout(null);
		inhaltKarteiHinzufuegen.add(sprache1);
		inhaltKarteiHinzufuegen.add(sprache2);
		inhaltKarteiHinzufuegen.add(sprache1hinzufuegen);
		inhaltKarteiHinzufuegen.add(sprache2hinzufuegen);
		hinzufuegenKartei.add(inhaltKarteiHinzufuegen);
		hinzufuegenKartei.add(buttonPanelHinzufuegen);

		hauptPanel.add(vorhandeneKartei);
		hauptPanel.add(hinzufuegenKartei);

		mainFrame.add(hauptPanel);
		mainFrame.setVisible(true);

	}

	private void bindListener() {
		// TODO Auto-generated method stub

	}

}
