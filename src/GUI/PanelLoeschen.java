package GUI;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logik.Karte;
import Logik.Kartei;

/**
 * Klasse beinhaltet das Löschen der Karte in dem Fach. Mit dem Ja Button wird die Karte gelöscht und mit dem Nein Button wird
 * der Vorgang abgebrochen.
 * 
 * @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.7 Datum:12.03.2018
 */

public class PanelLoeschen extends JFrame {

	private JLabel text;
	private JPanel textPanel, buttonPanel;
	private JButton ja, nein;

	public PanelLoeschen() {
		initComponents();
		bindListener();
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.6),
				(int) ((d.getHeight() - this.getHeight()) / 2.6));

	}

	private void bindListener() {
		ja.addActionListener(new ButtonListenerJa());
		nein.addActionListener(new ButtonListenerNein());
	}

	private void initComponents() {
		text = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen1"));
		ja = new JButton(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Ja"));
		nein = new JButton(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Nein"));
		textPanel = new JPanel();
		buttonPanel = new JPanel();
	}

	public void paint() {
		this.setSize(350, 150);
		textPanel.add(text);
		buttonPanel.add(ja);
		buttonPanel.add(nein);
		this.add(textPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	/**
	 * ButtonListener Ja für das Löschen der Karte
	 */
	class ButtonListenerJa implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Karte kl = Kartei.getInstance().getAktuelleKarte();
			Kartei.getInstance().karteLoeschen(kl);
			Hauptfenster.getInstance().getPanelLernen().loadCard();
			JButton b = (JButton) e.getSource();
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
			JOptionPane.showMessageDialog(null,
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen2"));
			Kartei.getInstance().lernkarteiSpeichern();
		}
	}

	/**
	 * ButtonListener Nein - Vorgang wird abgebrochen
	 */
	class ButtonListenerNein implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			JOptionPane.showMessageDialog(null,
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen3"));
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
		}
	}
}
