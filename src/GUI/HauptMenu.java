package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Logik.Karte;
import Logik.Kartei;


	/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
	 * @version 0.7
	 * Datum:08.03.2018
	 */

public class HauptMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu dateiMenu, lernsprachMenu, anzeigeMenu, kartenMenu, karteiMenu, lernsprachenMenu;
	private JMenuItem menuItem;

	public HauptMenu() {
		/**
		 * Erstellt das Datei-Menue
		 */
	
		dateiMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Datei"));

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Importieren"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("importieren");
		dateiMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Beenden"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("beenden");
		dateiMenu.add(menuItem);

		this.add(dateiMenu);
		
		/**
		 * Menu für Karteibearbeitungen
		 */
		
		karteiMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Lernkartei"));
		
		kartenMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Karte"));
		
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		menuItem.setName("karteHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("bearbeiten"));
		menuItem.setName("karteBearbeiten");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("loeschen"));
		menuItem.setName("karteLoeschen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);
			
		karteiMenu.add(kartenMenu);
		
		lernsprachenMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Lernsprache"));
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		menuItem.setName("spracheHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		lernsprachenMenu.add(menuItem);
		
		karteiMenu.add(lernsprachenMenu);
		
		this.add(karteiMenu);
		
		/**
		 * Menu für Anzeige
		 */
	
		anzeigeMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Anzeige"));
		/**
		 * Submenu für Spracheinstellung
		 */
		 
		lernsprachMenu = new JMenu(ResourceBundle.getBundle("Bundles\\Bundle", Kartei.getInstance().getLocale()).getString("Sprache"));

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
	
	/**
	 * Intialisiert die einzelnen Komponenten auf dem HauptMenu
	 * @beenden Schliesst das Programm
	 * @importieren Kann ein .csv File mit Wörter importieren
	 * @karteHinzufuegen neue Lern Karte wird hinzugefuegt
	 * @karteLoeschen die entsprechende Lernkarte wird geloescht
	 * @karteBearbeiten die entsprechende Karte kann bearbeitet werden
	 * @SpracheHinzufuegen eine neue Lernsprache kann hinzugefügt werden
	 */
	class ListenerHauptMenu implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem menuitem = (JMenuItem) e.getSource();
			String selection = menuitem.getName();
			System.out.println(selection);

			switch (selection) {
			case "beenden":
				System.exit(WHEN_FOCUSED);
				break;
			case "importieren":
				@SuppressWarnings("unused")
				PanelImport imp = new PanelImport();
				Kartei.getInstance().lernkarteiSpeichern();
				Kartei.getInstance().faecherBefuellen();
				break;
			case "karteHinzufuegen":
				PanelHinzufuegen ph = new PanelHinzufuegen();
				ph.paint();
				break;
			case "karteLoeschen":
					int result = JOptionPane.showConfirmDialog(null, "Delete this Card?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						Karte kl = Kartei.getInstance().getAktuelleKarte();
						Kartei.getInstance().karteLoeschen(kl);
						Hauptfenster.getInstance().getPanelLernen().loadCard();
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
				Kartei.getInstance().setLocale(new Locale(selection.toLowerCase(), selection));
				Hauptfenster.getInstance().spracheWechseln();
				break;
			case "spracheHinzufuegen":
				PanelKarteiVerwalten gui1 = new PanelKarteiVerwalten();
				gui1.paint();
				break;

			}
		}
		
	}

}
