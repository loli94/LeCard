package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Logik.Kartei;

public class PanelSidebar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[] boxAuswahl;

	public PanelSidebar() {
		initComponents();
		bindListener();
		paint();

	}

	public void initComponents() {
		boxAuswahl = new JButton[6];
		boxAuswahl[0] = new JButton("Home");
		for (int i = 1; i <= 5; i++) {
			boxAuswahl[i] = new JButton(
					ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Fach")
							+ " " + i);
			boxAuswahl[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
	}

	public void bindListener() {

		for (int i = 0; i <= 5; i++) {
			boxAuswahl[i].addActionListener(new ButtonListenerSidebar());
		}

	}

	public void paint() {
		for (int i = 0; i <= 5; i++) {
			this.add(boxAuswahl[i]);
			boxAuswahl[i].setBackground(Color.lightGray);
		}
		boxAuswahl[0].setBackground(Color.CYAN);

		this.setLayout(new GridLayout(6, 1, 5, 5));
		Border border = this.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		this.setBorder(new CompoundBorder(border, margin));

	}

	public void spracheWechseln() {
		boxAuswahl[0].setText(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale())
				.getString("ButtonKartei"));
		for (int i = 1; i <= 5; i++) {
			boxAuswahl[i].setText(
					ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Fach")
							+ " " + i);
		}
	}

	class ButtonListenerSidebar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			for (int i = 0; i <= 5; i++) {
				boxAuswahl[i].setBackground(Color.lightGray);
			}

			int fach = Integer.parseInt(0 + b.getText().replaceAll("\\D+", ""));
			Kartei.getInstance().setAktuellesFach(fach);

			if (fach == 0) {
				boxAuswahl[0].setBackground(Color.CYAN);
				Hauptfenster.getInstance().paintPanelStat();
			}

			else {
				boxAuswahl[fach].setBackground(Color.CYAN);
				Kartei.getInstance().gibNaechsteKarte();
				Hauptfenster.getInstance().paintPanelLernen();
			}
		}
	}

	public JButton[] getBoxAuswahl() {
		return boxAuswahl;
	}
}
