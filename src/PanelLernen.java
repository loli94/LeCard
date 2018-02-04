import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
		//initiiere Layout von den Panles
		pLernen.setLayout(new GridLayout(4, 1));
		pSpracheZwei.setLayout(new GridLayout(1, 2));
		pPruefen.setLayout(new BorderLayout());
		pAuswertung.setLayout(new BorderLayout());

		//Adding Components
		pSpracheEins.add(lSpracheEins);
		pSpracheEins.add(lSpracheEinsFrage);
		pSpracheZwei.add(lSpracheZwei);
		pSpracheZwei.add(tSpracheZweiAntwort);
		pPruefen.add(bPruefen, BorderLayout.CENTER);
		pAuswertung.add(lLoesung, BorderLayout.EAST);
		
		
		pLernen.add(pSpracheEins);
		pLernen.add(pSpracheZwei);
		pLernen.add(pPruefen);
		pLernen.add(pAuswertung);
		add(pLernen);
	}

}
