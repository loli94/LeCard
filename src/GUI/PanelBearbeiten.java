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

import Logik.Kartei;

/**
 * 
 * Klasse beinhaltet das Bearbeiten von Karten. Karte wird in einem neuen Panel
 * angezeigt mit aktueller Karte welche bearbeitet werden soll.
 * 
 * @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6 Datum:24.02.2018
 */
public class PanelBearbeiten extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel hauptsprache, fremdsprache, hinzufuegenPanelButton;
	private JLabel lSprache1, lSprache2;
	private JTextField tSprache1, tSprache2;
	private JButton hinzufuegenButton;

	public PanelBearbeiten() {
		initComponents();
		bindListener();
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.6),
				(int) ((d.getHeight() - this.getHeight()) / 2.6));

	}

	private void initComponents() {
		this.setTitle(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("bearbeiten"));
		this.setIconImage(Hauptfenster.getInstance().getIcon().getImage());
		lSprache1 = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheA());
		lSprache2 = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheB());
		tSprache1 = new JTextField(Kartei.getInstance().getAktuelleKarte().getWortA());
		tSprache1.setPreferredSize(new Dimension(220, 22));
		tSprache2 = new JTextField(Kartei.getInstance().getAktuelleKarte().getWortB());
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
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("bearbeiten"));
	}

	private void bindListener() {
		hinzufuegenButton.addActionListener(new ButtonListenerBearbeiten());
	}

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

	/**
	 * ButtonListener für das Bearbeiten von einer Karte. Sobald der Bearbeiten
	 * Button angewählt wird, wird die Karte gespeichert. 
	 */
	class ButtonListenerBearbeiten implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Abfrage ob ein Feld leer ist
			if (!tSprache1.getText().isEmpty() || !tSprache2.getText().isEmpty()) {
				// Abfrage ob ein Feld Zahlen enthält
				if (tSprache1.getText().matches("[a-zA-Z]+") && tSprache2.getText().matches("[a-zA-Z]+")) {
					Kartei.getInstance().getAktuelleKarte().setWortA(tSprache1.getText());
					Kartei.getInstance().getAktuelleKarte().setWortB(tSprache2.getText());
					JOptionPane.showMessageDialog(null, "" + tSprache1.getText() + ResourceBundle
							.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("infoTextBearbeiten1"));
					tSprache1.setText("");
					tSprache2.setText("");
					Kartei.getInstance().lernkarteiSpeichern();
					JButton b = (JButton) e.getSource();
					((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle
							.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("infoTextHinzufügen2"));
				}

			} else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
						.getString("infoTextHinzufügen3"));
			}

		}

	}

}
