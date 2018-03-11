package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logik.Karte;
import Logik.Kartei;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6
 * Datum:24.02.2018
 */
public class PanelHinzufuegen extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel hauptsprache, fremdsprache, hinzufuegenPanelButton;
	private JLabel lSprache1, lSprache2;
	private JTextField tSprache1, tSprache2;
	private JButton hinzufuegenButton;

	public PanelHinzufuegen() {
		initComponents();
		bindListener();
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.6),
				(int) ((d.getHeight() - this.getHeight()) / 2.6));
	}
	
	/*
	 * Die verschiedenen Objekte werden Initiert. Zudem werden auch die verschiedenen Labels mit dem jeweiligen Text befüllt.
	 * 
	 * */

	private void initComponents() {
		this.setTitle(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		this.setIconImage(Hauptfenster.getInstance().getIcon().getImage());
		lSprache1 = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheA());
		lSprache2 = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheB());
		tSprache1 = new JTextField();
		tSprache1.setPreferredSize(new Dimension(220, 22));
		tSprache1.setLocation(0, 15);
		tSprache2 = new JTextField();
		tSprache2.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					hinzufuegenButton.doClick();
				}
			}
		});

		tSprache2.setPreferredSize(new Dimension(220, 22));

		hauptsprache = new JPanel();
		fremdsprache = new JPanel();
		hinzufuegenPanelButton = new JPanel();
		hinzufuegenButton = new JButton(
				ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
	}
	
	/*
	 * In dieser Methode wird den einzelnen Objekten der Listener angebunden.
	 * Dem Button Hinzufügen wird der Listener angebunden.
	 * */

	private void bindListener() {
		hinzufuegenButton.addActionListener(new ButtonListenerHinzufuegen());
	}
	
	/*
	 * In dieser MEthode wird das Frame gezeichnet. Die einzelnen Labels, Buttons, etc. werden dem Frame zugeordnet.
	 */

	public void paint() {
		this.setSize(400, 200);

		hauptsprache.add(lSprache1);
		hauptsprache.add(tSprache1);
		fremdsprache.add(lSprache2);
		fremdsprache.add(tSprache2);
		hinzufuegenPanelButton.add(hinzufuegenButton);

		this.add(hauptsprache, BorderLayout.NORTH);
		this.add(fremdsprache, BorderLayout.CENTER);
		this.add(hinzufuegenPanelButton, BorderLayout.SOUTH);

		this.setVisible(true);
	}
	
	/*
	 * In diesem Listener (welcher zum Button Hinzufügen gehört) wird geprüft, ob die eingeabe der verschiedenen Textfelder nicht Leehr sind, ob nur Buchstaben verwendet werden.
	 * Falls dies zutrifft, wird eine neue Karte erstellt und der Kartei im Fach 1 hinzugefügt.
	 */

	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (!tSprache1.getText().isEmpty() || !tSprache2.getText().isEmpty()) {

				if (tSprache1.getText().matches("[a-zA-Z]+") && tSprache2.getText().matches("[a-zA-Z]+")) {
					Karte k1 = new Karte(Kartei.getInstance().getAktuellesSprachpaar(), tSprache1.getText(), tSprache2.getText());
					Kartei.getInstance().karteHinzufuegen(k1);
					Kartei.getInstance().getFach(1).karteHinzufuegen(k1);
					JOptionPane.showMessageDialog(null, "" + tSprache1.getText() + " "
							+ ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("infoTextHinzufügen1"));
					tSprache1.setText("");
					tSprache2.setText("");
					Kartei.getInstance().lernkarteiSpeichern();
					Hauptfenster.getInstance().paintPanelStat();
					Hauptfenster.getInstance().getPanelKartei().validate();
					tSprache1.requestFocus();

				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("infoTextHinzufügen2"));
				}

			} else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("infoTextHinzufügen3"));
			}

		}

	}

}
