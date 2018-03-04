import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

	// Components initieren

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
		sprache1hinzufuegenZweiBuchstaben = new JTextField(new MaxSizeDocument(50), "", 0);
		sprache1hinzufuegenZweiBuchstaben.setPreferredSize(new Dimension(80, 22));
		sprache1hinzufuegenAusgeschrieben = new JTextField();
		sprache1hinzufuegenAusgeschrieben.setPreferredSize(new Dimension(220, 22));

		sprache2hinzufuegenZweiBuchstaben = new JTextField(new MaxSizeDocument(50), "", 0);
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
		sprache1hinzufuegenZweiBuchstaben.addFocusListener(new KeyListenerSprache1());
		sprache2hinzufuegenZweiBuchstaben.addFocusListener(new KeyListenerSprache2());
		// TODO Auto-generated method stub

	}

	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String c = sprache1hinzufuegenZweiBuchstaben.getText() + "-" + sprache2hinzufuegenZweiBuchstaben.getText();
			System.out.println(c);
			Main.daten1.spracheHinzugfuegen(c, sprache1hinzufuegenAusgeschrieben.getText(),
					sprache2hinzufuegenAusgeschrieben.getText());

		}

	}

	class KeyListenerSprache1 implements FocusListener {

		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

			if (sprache1hinzufuegenZweiBuchstaben.getText().length() < 2) {
				JOptionPane.showMessageDialog(mainFrame, ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("info3"));

			} else
				System.out.println("ok");

		}
	}

	class KeyListenerSprache2 implements FocusListener {

		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {

			if (sprache2hinzufuegenZweiBuchstaben.getText().length() < 2) {
				JOptionPane.showMessageDialog(mainFrame, ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("info3"));

			} else
				System.out.println("ok");

		}
	}

	class MaxSizeDocument extends PlainDocument {
		int maxSize;

		public MaxSizeDocument(int maxSize) {
			this.maxSize = 2;
		}

		public void insertString(final int offset, final String text, final AttributeSet attributeSet)
				throws BadLocationException {
			if (laengeUeberpruefen(text))
				super.insertString(offset, text, attributeSet);
			else
				Toolkit.getDefaultToolkit().beep();
		}

		protected boolean laengeUeberpruefen(final String text) {
			if (getLength() + text.length() <= maxSize) 
				return true;
			return false;
		}
	}

}
