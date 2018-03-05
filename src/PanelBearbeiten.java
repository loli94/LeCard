import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

	private JFrame mainFrame;
	private JPanel hauptsprache, fremdsprache, hinzufuegenPanelButton;
	private JLabel lSprache1, lSprache2;
	private JTextField tSprache1, tSprache2;
	private JButton hinzufuegenButton;

	public PanelBearbeiten() {
		initComponents();
		bindListener();
		final Dimension d = mainFrame.getToolkit().getScreenSize();
		mainFrame.setLocation((int) ((d.getWidth() - mainFrame.getWidth()) / 2.6),
				(int) ((d.getHeight() - mainFrame.getHeight()) / 2.6));

	}

	/*
	 * Komponenten werden iniziert
	 */
	private void initComponents() {
		mainFrame = new JFrame(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
		lSprache1 = new JLabel(Main.daten1.getAktuelleSprache().getSpracheA());
		lSprache2 = new JLabel(Main.daten1.getAktuelleSprache().getSpracheB());
		tSprache1 = new JTextField(Main.daten1.getAktuelleKarte().getWortA());
		tSprache1.setPreferredSize(new Dimension(220, 22));
		tSprache2 = new JTextField(Main.daten1.getAktuelleKarte().getWortB());
		tSprache2.setPreferredSize(new Dimension(220, 22));
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

		hauptsprache = new JPanel();
		fremdsprache = new JPanel();
		fremdsprache.setLocation(100, 100);
		hinzufuegenPanelButton = new JPanel();
		hinzufuegenButton = new JButton(
				ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
	}

	/*
	 * ActionListener werden zusammen gefügt
	 */
	private void bindListener() {
		hinzufuegenButton.addActionListener(new ButtonListenerBearbeiten());
	}

	/*
	 * Paint von dem Fenster
	 */
	public void paint() {
		mainFrame.setSize(400, 200);
		hauptsprache.add(lSprache1);
		hauptsprache.add(tSprache1);
		fremdsprache.add(lSprache2);
		fremdsprache.add(tSprache2);
		hinzufuegenPanelButton.add(hinzufuegenButton);

		mainFrame.add(hauptsprache, BorderLayout.NORTH);
		mainFrame.add(fremdsprache, BorderLayout.CENTER);
		mainFrame.add(hinzufuegenPanelButton, BorderLayout.SOUTH);

		mainFrame.setVisible(true);
	}

	/*
	 * ButtonListener für das hinzufügen von einer Karte
	 */
	class ButtonListenerBearbeiten implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Abfrage ob ein Feld leer ist
			if (!tSprache1.getText().isEmpty() || !tSprache2.getText().isEmpty()) {
				// Abfrage ob ein Feld Zahlen enthält
				if (tSprache1.getText().matches("[a-zA-Z]+") && tSprache2.getText().matches("[a-zA-Z]+")) {
					Main.daten1.karteLoeschen(Main.daten1.getAktuelleKarte());
					Karte k1 = new Karte(Main.daten1.getAktuellesSprachpaar(), tSprache1.getText(),
							tSprache2.getText());
					Main.daten1.karteHinzufuegen(k1);
					JOptionPane.showMessageDialog(mainFrame, "" + tSprache1.getText()
							+ ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextBearbeiten1"));
					tSprache1.setText("");
					tSprache2.setText("");
					Main.daten1.lernkarteiSpeichern(Main.pfad);
					JButton b = (JButton) e.getSource();
					((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
				} else {
					JOptionPane.showMessageDialog(mainFrame,
							ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextHinzufügen2"));
				}

			} else {
				JOptionPane.showMessageDialog(mainFrame,
						ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("infoTextHinzufügen3"));
			}

		}

	}

}
