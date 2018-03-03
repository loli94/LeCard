import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hauptfenster extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Locale locale;

	private JPanel statPanel;
	private PanelLernen panelLernen;
	private PanelKartei panelKartei;
	private ImageIcon icon;
	private PanelSidebar panelSidebar;
	private PanelUserMenu panelUserMenu;

	public Hauptfenster(Locale lokal) {
		Hauptfenster.locale = lokal;
		panelSidebar = new PanelSidebar();
		panelUserMenu = new PanelUserMenu();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 3.8),
				(int) ((d.getHeight() - this.getHeight()) / 3.8));
		initComponents();
	}

	private void initComponents() {
		statPanel = new JPanel();
		panelKartei = new PanelKartei();
		icon = new ImageIcon(this.getClass().getResource("LeCard.png"));
		panelLernen = new PanelLernen();

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
		
		paintPanelLernen();
		paintPanelStat();

		this.setJMenuBar(new HauptMenu());

	}

	public void paintPanelLernen() {
		statPanel.removeAll();
		statPanel.add(new PanelLernen());
		statPanel.validate();
		statPanel.repaint();
	}

	public void paintPanelStat() {
		statPanel.removeAll();
		panelKartei.setBalkendiagramm();
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


	

}