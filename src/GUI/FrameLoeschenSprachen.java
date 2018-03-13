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

import Logik.Kartei;

/**
 * 
 * Diese Klasse ebeinhaltet das löschen des Sprachpaares. Erfasste Sprachpaare können gelöscht werden.
 * 
 * @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.7 Datum:12.03.2018
 */

public class FrameLoeschenSprachen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JPanel textPanel, buttonPanel;
	private JButton ja, nein;

	public FrameLoeschenSprachen() {
		initComponents();
		bindListener();
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2.6),
				(int) ((d.getHeight() - this.getHeight()) / 2.6));

	}

	/**
	 * Listener werden zusammengeführt
	 */
	private void bindListener() {
		ja.addActionListener(new ButtonListenerJa());
		nein.addActionListener(new ButtonListenerNein());
	}

	/**
	 * Komponenten werden iniziert
	 */
	private void initComponents() {
		text = new JLabel(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen4"));
		ja = new JButton(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Ja"));
		nein = new JButton(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Nein"));
		textPanel = new JPanel();
		buttonPanel = new JPanel();
	}

	/**
	 * Frame wird gezeichnet
	 */
	public void paint() {
		this.setSize(600, 150);
		textPanel.add(text);
		buttonPanel.add(ja);
		buttonPanel.add(nein);
		this.add(textPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	/**
	 * Button Ja für das Löschen der Karte
	 */
	class ButtonListenerJa implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Kartei.getInstance().sprachpaarLoeschen(Kartei.getInstance().getAktuellesSprachpaar());
			Kartei.getInstance().statusBereinigen();
			Hauptfenster.getInstance().getPanelUserMenu().getLernSprachenMenu().removeItem(Hauptfenster.getInstance().getPanelUserMenu().getLernSprachenMenu().getSelectedItem());
			Kartei.getInstance().spracheWaehlen(Kartei.getInstance().getSprachen().get(0).getSprachPaar());
			
			Hauptfenster.getInstance().getPanelLernen().loadCard();
			JButton b = (JButton) e.getSource();
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
			JOptionPane.showMessageDialog(null,
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen5"));
			Kartei.getInstance().lernkarteiSpeichern();
		}
	}

	/**
	 * Button Nein - Vorgang wird abgebrochen
	 */
	class ButtonListenerNein implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			JOptionPane.showMessageDialog(null,
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("InfoLoeschen6"));
			((JFrame) b.getParent().getParent().getParent().getParent().getParent()).setVisible(false);
		}
	}
}
