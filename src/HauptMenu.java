
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HauptMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu dateiMenu, sprachMenu, anzeigeMenu, kartenMenu;
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

		// Menu für Kartenbearbeitenung
		kartenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Karte"));

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		menuItem.setName("hinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
		menuItem.setName("bearbeiten");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("loeschen"));
		menuItem.setName("loeschen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		this.add(kartenMenu);

		// Menu für Anzeige.
		anzeigeMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Anzeige"));

		// Submenu für Spracheinstellung
		sprachMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Sprache"));

		menuItem = new JMenuItem("Deutsch");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("DE");
		sprachMenu.add(menuItem);

		menuItem = new JMenuItem("English");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("EN");
		sprachMenu.add(menuItem);

		menuItem = new JMenuItem("Francaise");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("FR");
		sprachMenu.add(menuItem);

		menuItem = new JMenuItem("Italiano");
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("IT");
		sprachMenu.add(menuItem);

		anzeigeMenu.add(sprachMenu);
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
				panelImport imp = new panelImport();
				Main.daten1.lernkarteiSpeichern(Main.pfad);
				Main.daten1.faecherBefuellen();
				break;
			case "hinzufuegen":
				PanelHinzufuegen ph = new PanelHinzufuegen();
				ph.paint();
				break;
			case "loeschen":
				Main.hauptFenster.karteLoeschen();
				break;
			case "bearbeiten":
				break;
			case "DE":
			case "EN":
			case "IT":
			case "FR":
				Hauptfenster.locale = new Locale(selection.toLowerCase(), selection);
				Main.hauptFenster.spracheWechseln();
				break;

			}
		}

	}

}
