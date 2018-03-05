import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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
import javax.swing.border.Border;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.4
 * Datum:24.02.2018
 */
public class PanelLernen extends JPanel {
	private JButton bWechsel, bPruefen;
	private JPanel pLernen, pSpracheEins, pSpracheZwei, pPruefen, pAuswertung;
	private JLabel lSpracheEins, lSpracheEinsFrage, lSpracheZwei, lLoesung;
	private JTextField tSpracheZweiAntwort;

	public PanelLernen() {
		initComponents();
		bindListener();
		init();
		paint();
		repaint();
	}

	public void init() {
		initComponents();
		loadCard();
		bindListener();
	}

	private void initComponents() {
		pLernen = new JPanel();
		pSpracheEins = new JPanel();
		pSpracheZwei = new JPanel();
		pPruefen = new JPanel();
		pAuswertung = new JPanel();
		bPruefen = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("pruefen"));
		bWechsel = new JButton("<->");
		bWechsel.setPreferredSize(new Dimension(220, 22));
		lSpracheEins = new JLabel(Main.daten1.getAktuelleSprache().getSpracheA());
		//lSpracheEins.setForeground(Color.orange);
		lSpracheEins.setPreferredSize(new Dimension(220, 22));
		lSpracheEinsFrage = new JLabel();
		lSpracheEinsFrage.setPreferredSize(new Dimension(220, 22));
		lSpracheZwei = new JLabel(Main.daten1.getAktuelleSprache().getSpracheB());
		lSpracheZwei.setPreferredSize(new Dimension(220, 22));
		//lSpracheZwei.setForeground(Color.blue);
		tSpracheZweiAntwort = new JTextField();
		tSpracheZweiAntwort.setPreferredSize(new Dimension(220, 22));
		//tSpracheZweiAntwort.setForeground(Color.blue);
		tSpracheZweiAntwort.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					bPruefen.doClick();
				}

			}

		});

		lLoesung = new JLabel("");
		// initiiere Layout von den Panles
		pLernen.setLayout(new GridLayout(4, 1));
		pSpracheEins.setLayout(new GridLayout(1, 2));
		pSpracheZwei.setLayout(new GridLayout(1, 2));
		// pAuswertung.setLayout(new GridLayout(1, 2));
		pPruefen.setLayout(new GridLayout(1, 2));

	}

	// Prüft die Antwort und gibt die entsprechende Karte aus
	public void loadCard() {
		if (Main.daten1.gibNaechsteKarte() == true) {
			lSpracheEinsFrage.setText(Main.daten1.getAktuelleKarte().getWortA());
			// lSpracheEinsFrage.setForeground(Color.orange);
			pPruefen.setVisible(true);
		}

		// Dialog keine Karte vorhanden und "Prüfen Button" ausblenden
		else {
			if (Main.daten1.getAktuellesFach() != 0) {
				JOptionPane.showMessageDialog(pLernen,
						ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("keinKarteVorhanden"));
				tSpracheZweiAntwort.setText("");
				lSpracheEinsFrage.setText("-");
				pPruefen.setVisible(false);
			}
		}

	}

	private void bindListener() {
		bWechsel.addActionListener(new ButtonListenerSpracheWechseln());
		bPruefen.addActionListener(e -> verifyAnswer());
		bPruefen.addActionListener(new ButtonListenerPruefen());
		tSpracheZweiAntwort.addActionListener(new JTextFieldListener());

	}

	public void paint() {
		// Adding Components

		pSpracheEins.add(lSpracheEinsFrage, BorderLayout.CENTER);

		pAuswertung.add(lLoesung);
		pSpracheEins.add(lSpracheEins, BorderLayout.CENTER);
		pSpracheEins.add(lSpracheEinsFrage, BorderLayout.CENTER);
		pSpracheZwei.add(lSpracheZwei, BorderLayout.EAST);
		pSpracheZwei.add(tSpracheZweiAntwort, BorderLayout.EAST);
		pPruefen.add(bWechsel, BorderLayout.WEST);
		pPruefen.add(bPruefen, BorderLayout.EAST);

		pLernen.add(pSpracheEins);
		pLernen.add(pSpracheZwei);
		pLernen.add(pPruefen);
		pLernen.add(pAuswertung);
		add(pLernen);
	}

	class ButtonListenerSpracheWechseln implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		
			System.out.println("Sprache wird gewechselt");

		}
	}

	// Prueft ob der"Prüfen Button" angezeigt wird oder nicht

	public void verifyAnswer() {

	}

	class ButtonListenerPruefen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int r = 1;
			int f = 1;

			if (Main.daten1.getAktuelleKarte().getWortB().equalsIgnoreCase(tSpracheZweiAntwort.getText())) {

				System.out.println("Korrekt");
				Main.daten1.karteVerschieben(Main.daten1.getAktuelleKarte(), Main.daten1.getAktuellesFach() + 1);
				tSpracheZweiAntwort.setText("");
				lLoesung.setText("Richtig");
				lLoesung.setFont(lLoesung.getFont().deriveFont(22f));
				lLoesung.setForeground(Color.GREEN);
				Main.daten1.setRichtigeAntwort();

			}

			else {
				System.out.println("Falsch");
				Main.daten1.karteVerschieben(Main.daten1.getAktuelleKarte(), 1); 
				lLoesung.setText("Falsch"+ "  "+ Main.daten1.getAktuelleKarte().getWortB());
				lLoesung.setForeground(Color.RED);
				lLoesung.setFont(lLoesung.getFont().deriveFont(22f));
				tSpracheZweiAntwort.setText("");
				Main.daten1.setFalscheAntwort();

			}
			Main.daten1.lernkarteiSpeichern(Main.pfad);

			loadCard();

		}

	}

	class JTextFieldListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			System.out.println(tSpracheZweiAntwort.getText());
		}

	}

}
