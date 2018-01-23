import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLernen extends JPanel {
	private JPanel jp2;
	private JButton b2;
	private JPanel pLernen;
	private JPanel pSpracheEins;
	private JPanel pSpracheZwei;
	private JPanel pPruefen;
	private JPanel pAuswertung;
	private JButton bWechsel;
	private JButton bPruefen;
	private JLabel lSpracheEins;
	private JLabel lSpracheEinsFrage;
	private JLabel lSpracheZwei;
	private JTextField tSpracheZweiAntwort;
	private JLabel lLoesung;

	public PanelLernen() {
		pLernen = new JPanel();
		pSpracheEins = new JPanel();
		pSpracheZwei = new JPanel();
		pPruefen = new JPanel();
		pAuswertung = new JPanel();
		bPruefen = new JButton("Prüfen");
		bWechsel = new JButton("<->");
		lSpracheEins = new JLabel("Deutsch:");
		lSpracheEinsFrage = new JLabel("Schlange");
		lSpracheZwei = new JLabel("Englisch:");
		tSpracheZweiAntwort = new JTextField();
		lLoesung = new JLabel("Richtig/Flasch");
		
		pLernen.setLayout(new GridLayout(2, 3));

		pSpracheEins.add(lSpracheEins);
		pSpracheEins.add(lSpracheEinsFrage);
		pSpracheZwei.add(lSpracheZwei);
		pSpracheZwei.add(tSpracheZweiAntwort);
		pLernen.add(pSpracheEins);
		pLernen.add(pSpracheZwei);
		add(pLernen);
	}

}
