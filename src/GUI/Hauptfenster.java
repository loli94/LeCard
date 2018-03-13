package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Logik.Kartei;
import Logik.Sprache;

/** @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 *  @version 1
 *  Datum:11.03.2018
 */

public class Hauptfenster extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Hauptfenster instance = null;

	private JPanel statPanel;
	private PanelLernen panelLernen;
	private PanelKartei panelKartei;
	private ImageIcon icon;
	private PanelSidebar panelSidebar;
	private PanelUserMenu panelUserMenu;

	protected Hauptfenster() {
		this.panelSidebar = new PanelSidebar();
		this.panelUserMenu = new PanelUserMenu();
		this.statPanel = new JPanel();
		this.panelKartei = new PanelKartei();
		this.icon = new ImageIcon(getClass().getClassLoader().getResource("LeCard.png"));
		this.panelLernen = new PanelLernen();
		initComponents();
	}

	public static Hauptfenster getInstance() {
		if (instance == null) {
			instance = new Hauptfenster();
		}
		return instance;
	}

	public Sprache getAktuelleSprache() {
		return getAktuelleSprache();
	}

	private void initComponents() {
		this.setTitle("LeCard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 3.8),
				(int) ((d.getHeight() - this.getHeight()) / 3.8));
	}

	public void paint() {

		this.setSize(1000, 500);
		this.setIconImage(icon.getImage());

		this.add(panelSidebar, BorderLayout.WEST);

		statPanel.add(panelKartei);

		this.add(statPanel, BorderLayout.CENTER);
		this.add(panelUserMenu, BorderLayout.NORTH);
		this.setJMenuBar(new HauptMenu());

		this.setVisible(true);

	}

	public void spracheWechseln() {

		panelUserMenu.spracheWechseln();
		panelSidebar.spracheWechseln();
		panelLernen.spracheWechseln();
		panelKartei.spracheWechseln();
		if (Kartei.getInstance().getAktuellesFach() == 0) {
			paintPanelStat();
		}
		else {
			paintPanelLernen();
		}
		
		
		this.setJMenuBar(new HauptMenu());

	}

	public void paintPanelLernen() {
		statPanel.removeAll();
		statPanel.add(panelLernen);
		panelLernen.loadCard();
		statPanel.validate();
		statPanel.repaint();
	}

	public void paintPanelStat() {
		statPanel.removeAll();
		statPanel.add(panelKartei);
		statPanel.validate();
		statPanel.repaint();
		panelKartei.setBalkendiagramm();
	}

	public PanelLernen getPanelLernen() {
		return panelLernen;
	}

	public PanelKartei getPanelKartei() {
		return panelKartei;
	}

	public PanelUserMenu getPanelUserMenu() {
		return panelUserMenu;
	}

	public ImageIcon getIcon() {
		return icon;
	}
}
