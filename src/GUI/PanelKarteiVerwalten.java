package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Logik.Kartei;

/**
 * Klasse beinhaltet das Erstellen eines neuen Sprachpaares. Sprachpaare werden
 * mit dem Buchstabencode (zwei Zweichen) sowie das ausschreiben der kompletten
 * Sprache erstellt.
 * 
 * @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.0 Datum:28.02.2018
 */

public class PanelKarteiVerwalten {

	private JFrame mainFrame;
	private JPanel hauptPanel, sprachen, zweiBuchstaben, spracheAusgeschrieben, buttonPanelHinzufuegen;
	private JLabel strich, info1, info2, sprache1, sprache2;
	private JButton hinzufuegen;
	private JTextField sprache1hinzufuegenZweiBuchstaben, sprache1hinzufuegenAusgeschrieben,
			sprache2hinzufuegenZweiBuchstaben, sprache2hinzufuegenAusgeschrieben;

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
		mainFrame = new JFrame(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		hauptPanel = new JPanel();
		strich = new JLabel("-");
		buttonPanelHinzufuegen = new JPanel();
		info1 = new JLabel(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info1"));
		info2 = new JLabel(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info2"));
		hinzufuegen = new JButton(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		hinzufuegen.setLocation(200, 20);
		hinzufuegen.setSize(220, 30);
		sprache1 = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("sprache1"));
		sprache1.setPreferredSize(new Dimension(220, 22));
		sprache1.setBounds(235, 35, 220, 30);
		sprache2 = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("sprache2"));
		sprache2.setPreferredSize(new Dimension(220, 22));
		sprache2.setBounds(330, 35, 220, 30);
		sprache1hinzufuegenZweiBuchstaben = new JTextField(new MaxGroesseTextfeld(), "", 0);
		sprache1hinzufuegenZweiBuchstaben.setPreferredSize(new Dimension(80, 22));
		sprache1hinzufuegenAusgeschrieben = new JTextField();
		sprache1hinzufuegenAusgeschrieben.setPreferredSize(new Dimension(220, 22));

		sprache2hinzufuegenZweiBuchstaben = new JTextField(new MaxGroesseTextfeld(), "", 0);
		sprache2hinzufuegenZweiBuchstaben.setPreferredSize(new Dimension(80, 22));
		sprache2hinzufuegenAusgeschrieben = new JTextField();
		sprache2hinzufuegenAusgeschrieben.setPreferredSize(new Dimension(220, 22));
		sprache2hinzufuegenAusgeschrieben.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					hinzufuegen.doClick();
				}
			}
		});

		sprachen = new JPanel(null);
		zweiBuchstaben = new JPanel();
		spracheAusgeschrieben = new JPanel();

	}

	public void paint() {
		mainFrame.setSize(650, 430);
		hauptPanel.setLayout(new GridLayout(6, 1));

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
		sprache1hinzufuegenZweiBuchstaben.addFocusListener(new KeyListenerSprache1());
		sprache2hinzufuegenZweiBuchstaben.addFocusListener(new KeyListenerSprache2());

	}

	/**
	 * ButtonListener für die Sprachhinzufügung. JTextFiels werden nach dem Anlegen
	 * wieder clear gemacht.
	 */
	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String c = sprache1hinzufuegenZweiBuchstaben.getText().toUpperCase() + "-"
					+ sprache2hinzufuegenZweiBuchstaben.getText().toUpperCase();
			String d = sprache1hinzufuegenAusgeschrieben.getText();
			String f = sprache2hinzufuegenAusgeschrieben.getText();

			if (Kartei.getInstance().spracheHinzugfuegen(c, d, f) == true) {
				Kartei.getInstance().spracheHinzugfuegen(c, sprache1hinzufuegenAusgeschrieben.getText(),
						sprache2hinzufuegenAusgeschrieben.getText());
				JOptionPane.showMessageDialog(mainFrame,
						ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info4"));
				Kartei.getInstance().lernkarteiSpeichern();
				Hauptfenster.getInstance().getPanelUserMenu().getLernSprachenMenu().addItem(c);
				sprache1hinzufuegenZweiBuchstaben.setText("");
				sprache2hinzufuegenZweiBuchstaben.setText("");
				sprache1hinzufuegenAusgeschrieben.setText("");
				sprache2hinzufuegenAusgeschrieben.setText("");
			} else if (Kartei.getInstance().spracheHinzugfuegen(c, d, f) == false) {
				JOptionPane.showMessageDialog(mainFrame,
						ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info5"));
				sprache1hinzufuegenZweiBuchstaben.setText("");
				sprache2hinzufuegenZweiBuchstaben.setText("");
				sprache1hinzufuegenAusgeschrieben.setText("");
				sprache2hinzufuegenAusgeschrieben.setText("");
			}
		}

	}

	/**
	 * FocusListener für Abfrage ob es sicher zwei Buchstaben sind mit Meldung wenn
	 * ein Fehler auftritt
	 */
	class KeyListenerSprache1 implements FocusListener {

		@Override
		public void focusLost(FocusEvent arg0) {

			if (sprache1hinzufuegenZweiBuchstaben.getText().length() < 2) {
				JOptionPane.showMessageDialog(mainFrame,
						ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info3"));

			}

		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * FocusListener für Abfrage ob es sicher zwei Buchstaben sind mit Meldung wenn
	 * ein Fehler auftritt
	 */
	class KeyListenerSprache2 implements FocusListener {

		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {

			if (sprache2hinzufuegenZweiBuchstaben.getText().length() < 2) {
				JOptionPane.showMessageDialog(mainFrame,
						ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("info3"));

			} else
				System.out.println("ok");

		}
	}

	/**
	 * Klasse um die Felderbeschränkung auf 2 Zeichen zu setzen
	 */
	class MaxGroesseTextfeld extends PlainDocument {
		private static final long serialVersionUID = 1L;
		int maxSize;

		public MaxGroesseTextfeld() {
			this.maxSize = 2;
		}

		/**
		 * Methode für das Einfügen des Textes und Abfange um nicht mehr als zwei
		 * Zeichen einzufügen
		 */
		public void insertString(final int offset, final String text, final AttributeSet attributeSet)
				throws BadLocationException {
			if (laengeUeberpruefen(text))
				super.insertString(offset, text, attributeSet);
			else
				Toolkit.getDefaultToolkit().beep();
		}

		/**
		 * Überprüfung des Inhaltes
		 * 
		 * @param text: Eingabe beim JTextField
		 */
		protected boolean laengeUeberpruefen(final String text) {
			if (getLength() + text.length() <= maxSize)
				return true;
			return false;
		}
	}

}
