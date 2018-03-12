package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

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
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach")
							+ " " + i);
		}
	}

	public void bindListener() {

		for (int i = 0; i <= 5; i++) {
			boxAuswahl[i].addActionListener(new ButtonListenerSidebar());
		}
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new ShortcutListener());

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
		boxAuswahl[0].setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale())
				.getString("ButtonKartei"));
		for (int i = 1; i <= 5; i++) {
			boxAuswahl[i].setText(
					ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Fach")
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
	
	class ShortcutListener implements KeyEventDispatcher {
		 public boolean dispatchKeyEvent(java.awt.event.KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_1) {
					boxAuswahl[1].doClick();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_2) {
					boxAuswahl[2].doClick();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_3) {
					boxAuswahl[3].doClick();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_4) {
					boxAuswahl[4].doClick();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_5) {
					boxAuswahl[5].doClick();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_H) {
					boxAuswahl[0].doClick();
				}

				return false;
			}
	}

	public JButton[] getBoxAuswahl() {
		return boxAuswahl;
	}

}

