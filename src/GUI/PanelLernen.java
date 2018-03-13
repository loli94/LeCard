package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import Logik.Kartei;

/** @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * 	@version 0.6
 * 	Datum:10.03.2018
 */
public class PanelLernen extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton bWechsel, bPruefen;
	private JPanel pLernen, pSpracheA, pSpracheB, pPruefen, pAuswertung;
	private JLabel lSpracheA, lSpracheB, lLoesung;
	private JTextField tSpracheA, tSpracheB;
	private boolean learnReverse;
	private static Timer timer;
	private int x;

	
	
	public PanelLernen() {
		initComponents();
		bindListener();
		init();
		paint();
		learnReverse = false;
		
	}

	public void init() {
		initComponents();
		loadCard();
		bindListener();
	}

		/**
		 * Intialisiert die einzelnen Komponenten 
		 * Weisst die Komponenten den entsprechenden Layout zu
		 */
	
	private void initComponents() {
		pLernen = new JPanel();
		pSpracheA = new JPanel();
		pSpracheB = new JPanel();
		pPruefen = new JPanel();
		pAuswertung = new JPanel();
		bPruefen = new JButton(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("pruefen"));
		bWechsel = new JButton("<->");
		bWechsel.setPreferredSize(new Dimension(300, 30));
		lSpracheA = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheA());
		lSpracheA.setPreferredSize(new Dimension(300, 30));
		tSpracheA = new JTextField();
		tSpracheA.setPreferredSize(new Dimension(300, 30));
		tSpracheA.addKeyListener(new textfeldListener());
		tSpracheA.setEditable(false);
		tSpracheA.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lSpracheB = new JLabel(Kartei.getInstance().getAktuelleSprache().getSpracheB());
		lSpracheB.setPreferredSize(new Dimension(300, 30));
		tSpracheB = new JTextField();
		tSpracheB.setPreferredSize(new Dimension(300, 30));
		tSpracheB.addKeyListener(new textfeldListener());
		tSpracheB.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lLoesung = new JLabel("");
		pLernen.setLayout(new GridLayout(4, 1));
		pSpracheA.setLayout(new GridLayout(1, 2));
		pSpracheB.setLayout(new GridLayout(1, 2));
		pPruefen.setLayout(new GridLayout(1, 2));

	} 

	/**
	 * Prüft die Antwort und gibt die entsprechende Karte aus
	 */
	
	
	public void loadCard() {
		if (Kartei.getInstance().gibNaechsteKarte() == true) {
			if (learnReverse == false) {
				tSpracheA.setText(Kartei.getInstance().getAktuelleKarte().getWortA());
				pPruefen.setVisible(true);
			} else {
				tSpracheB.setText(Kartei.getInstance().getAktuelleKarte().getWortB());
				pPruefen.setVisible(true);

			}

		}
		/**
		 * Dialog keine Karte vorhanden und "Prüfen Button" ausblenden
		 * Antwort TextField wieder neu intialisieren
		 */
	
		else {

			if (learnReverse == false) {
				if (Kartei.getInstance().getAktuellesFach() != 0) {
					JOptionPane.showMessageDialog(pLernen,
							ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
									.getString("keinKarteVorhanden"));
					tSpracheB.setText("");
					tSpracheA.setText("-");
					pPruefen.setVisible(false);
				}
			} else {
				if (Kartei.getInstance().getAktuellesFach() != 0) {
					JOptionPane.showMessageDialog(pLernen,
							ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
									.getString("keinKarteVorhanden"));
					tSpracheA.setText("");
					tSpracheB.setText("-");
					pPruefen.setVisible(false);
				}

			}
		}

	}
	
	private void bindListener() {
		bWechsel.addActionListener(new ButtonListenerSpracheWechseln());
		bPruefen.addActionListener(new ButtonListenerPruefen());

	}

	public void paint() {
		// Adding Components

		pSpracheA.add(tSpracheA, BorderLayout.CENTER);

		pAuswertung.add(lLoesung);
		pSpracheA.add(lSpracheA, BorderLayout.CENTER);
		pSpracheA.add(tSpracheA, BorderLayout.CENTER);
		pSpracheB.add(lSpracheB, BorderLayout.EAST);
		pSpracheB.add(tSpracheB, BorderLayout.EAST);
		pPruefen.add(bWechsel, BorderLayout.WEST);
		pPruefen.add(bPruefen, BorderLayout.EAST);

		pLernen.add(pSpracheA);
		pLernen.add(pSpracheB);
		pLernen.add(pPruefen);
		pLernen.add(pAuswertung);
		add(pLernen);
	}
	
	/**
	 * Methode um die Lern Sprache zu wechseln
	 */

	class ButtonListenerSpracheWechseln implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (learnReverse == false) {

				tSpracheA.setEditable(true);
				tSpracheB.setEditable(false);
				tSpracheA.setText("");
				learnReverse = true;
			} else {
				tSpracheA.setEditable(false);
				tSpracheB.setEditable(true);
				tSpracheB.setText("");
				learnReverse = false;
			}

			loadCard();

		}
	}

	/**
	 * Methode um die Antwort mit der Frage zu überprüfen für beide Sprachrrichtungen
	 *
	 *
	 */
	
	class ButtonListenerPruefen implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String frage = "";
			String antwort = "";

			if (learnReverse == false) {
				frage = Kartei.getInstance().getAktuelleKarte().getWortB();
				antwort = tSpracheB.getText();
			} else {
				frage = Kartei.getInstance().getAktuelleKarte().getWortA();
				antwort = tSpracheA.getText();
			}

			if (frage.equalsIgnoreCase(antwort)) {

				Kartei.getInstance().karteVerschieben(Kartei.getInstance().getAktuelleKarte(),
						Kartei.getInstance().getAktuellesFach() + 1);
				if (learnReverse == false) {
					tSpracheB.setText("");
				} else {
					tSpracheA.setText("");
				}

				lLoesung.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
						.getString("richtigeAntwort"));
				lLoesung.setForeground(Color.GREEN);
				textAusblenden(0, 255, 0, 5, lLoesung);
				Kartei.getInstance().setRichtigeAntwort();

			}

			else {
				Kartei.getInstance().karteVerschieben(Kartei.getInstance().getAktuelleKarte(), 1);
				lLoesung.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
						.getString("falscheAntwort") + " :" + Kartei.getInstance().getAktuelleKarte().getWortB());
				lLoesung.setForeground(Color.RED);
				tSpracheB.setText("");
				textAusblenden(255, 0, 0, 5, lLoesung);
				Kartei.getInstance().setFalscheAntwort();

			}

			Kartei.getInstance().lernkarteiSpeichern();
			loadCard();

		}

	}

	
	public void spracheWechseln() {
		bPruefen.setText(
				ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("pruefen"));
		lSpracheA.setText(Kartei.getInstance().getAktuelleSprache().getSpracheA());
		lSpracheB.setText(Kartei.getInstance().getAktuelleSprache().getSpracheB());

	}
	
	/**
	 * Listener für die Textfelder
	 * 
	 */
	
	class textfeldListener implements KeyListener {

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

	}
	/**
	 * Methode um den Antwort Hinweiss verzögert auszublenden
	 * @param re Farbe Rot
	 * @param gr Frarbe Grau
	 * @param bl Frabe Blau
	 * @param speed die Geschwindigkeit für das Ausblenden
	 * @param label 
	 */
	
	private void textAusblenden(int re, int gr, int bl, int speed, JLabel label) {

		if (timer !=null) timer.stop();
		x = 0;
		label.setForeground(new Color(re, gr, bl, 255));

		timer = new Timer(speed, new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				label.setForeground(new Color(re, gr, bl, 255 - x++));
				if (x == 255)
					timer.stop();
			}
		});

		timer.setInitialDelay(2000);
		timer.start();
	}

}


