
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class HauptMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu dateiMenu, lernsprachMenu, anzeigeMenu, kartenMenu, karteiMenu, lernsprachenMenu;
	private JMenuItem menuItem;

	public HauptMenu() {

		// Dateimenu erstellen.
		dateiMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Datei"));

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Importieren"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("importieren");
		dateiMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Beenden"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("beenden");
		dateiMenu.add(menuItem);

		this.add(dateiMenu);

		// Menu für Karteibearbeitenung
		karteiMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Lernkartei"));
		
		kartenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Karte"));
		
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		menuItem.setName("karteHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
		menuItem.setName("karteBearbeiten");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("loeschen"));
		menuItem.setName("karteLoeschen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);
			
		karteiMenu.add(kartenMenu);
		
		lernsprachenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Lernsprache"));
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		menuItem.setName("spracheHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		lernsprachenMenu.add(menuItem);
		
		karteiMenu.add(lernsprachenMenu);
		
		this.add(karteiMenu);

		// Menu für Anzeige.
		anzeigeMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Anzeige"));

		// Submenu für Spracheinstellung
		lernsprachMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Sprache"));

		menuItem = new JMenuItem("Deutsch");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("DE");
		lernsprachMenu.add(menuItem);

		menuItem = new JMenuItem("English");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("EN");
		lernsprachMenu.add(menuItem);

		menuItem = new JMenuItem("Francaise");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("FR");
		lernsprachMenu.add(menuItem);

		menuItem = new JMenuItem("Italiano");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("IT");
		lernsprachMenu.add(menuItem);

		anzeigeMenu.add(lernsprachMenu);
		this.add(anzeigeMenu);

	}

	class ListenerHauptMenu implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem menuitem = (JMenuItem) e.getSource();
			String selection = menuitem.getName();
			System.out.println(selection);

			switch (selection) {
			case "beenden":
				break;
			case "importieren":
				@SuppressWarnings("unused")
				PanelImport imp = new PanelImport();
				Main.daten1.lernkarteiSpeichern(Main.pfad);
				Main.daten1.faecherBefuellen();
				break;
			case "karteHinzufuegen":
				PanelHinzufuegen ph = new PanelHinzufuegen();
				ph.paint();
				break;
			case "karteLoeschen":
					int result = JOptionPane.showConfirmDialog(null, "Delete this Card?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						Karte kl = Main.daten1.getAktuelleKarte();
						Main.daten1.karteLoeschen(kl);
						Main.hauptFenster.getPanelLernen().loadCard();
					} else if (result == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				break;
			case "karteBearbeiten":
				PanelBearbeiten pb = new PanelBearbeiten();
				pb.paint();
				break;
			case "DE":
			case "EN":
			case "IT":
			case "FR":
				Hauptfenster.locale = new Locale(selection.toLowerCase(), selection);
				Main.hauptFenster.spracheWechseln();
				break;
			case "spracheHinzufuegen":
				
				break;

			}
		}
		
	}

}
