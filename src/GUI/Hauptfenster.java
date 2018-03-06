package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

import Logik.Sprache;

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
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			
			@Override
			public boolean dispatchKeyEvent(java.awt.event.KeyEvent e) {
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_1) {
					panelSidebar.getBoxAuswahl()[1].doClick();
				} else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_2) {
					panelSidebar.getBoxAuswahl()[2].doClick();
				} else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_3) {
					panelSidebar.getBoxAuswahl()[3].doClick();
				} else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_4) {
					panelSidebar.getBoxAuswahl()[4].doClick();
				} else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_5) {
					panelSidebar.getBoxAuswahl()[5].doClick();
				} else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_H) {
					panelSidebar.getBoxAuswahl()[0].doClick();
				}
				
				return false;
			}
		});
		this.panelKartei = new PanelKartei();
		this.icon = new ImageIcon(getClass().getClassLoader().getResource("Images\\LeCard.png"));
		this.panelLernen = new PanelLernen();
		initComponents();
	}
	
	public static Hauptfenster getInstance() {
		if(instance == null) {
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
		statPanel.add(new PanelKartei());
		statPanel.validate();
		statPanel.repaint();
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