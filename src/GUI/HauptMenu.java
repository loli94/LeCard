package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Logik.Kartei;


	/** 
	 * @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
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
	
		dateiMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Datei"));

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Importieren"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("importieren");
		dateiMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Beenden"));
		menuItem.addActionListener(new ListenerHauptMenu());
		menuItem.setName("beenden");
		dateiMenu.add(menuItem);

		this.add(dateiMenu);
		
		/**
		 * Menu für Karteibearbeitungen
		 */
		
		karteiMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Lernkartei"));
		
		kartenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Karte"));
		
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		menuItem.setName("karteHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("bearbeiten"));
		menuItem.setName("karteBearbeiten");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);

		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("loeschen"));
		menuItem.setName("karteLoeschen");
		menuItem.addActionListener(new ListenerHauptMenu());
		kartenMenu.add(menuItem);
			
		karteiMenu.add(kartenMenu);
		
		lernsprachenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Lernsprache"));
		
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("hinzufuegen"));
		menuItem.setName("spracheHinzufuegen");
		menuItem.addActionListener(new ListenerHauptMenu());
		lernsprachenMenu.add(menuItem);
		
		
		menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("loeschen"));
		menuItem.setName("spracheLoeschen");
		menuItem.addActionListener(new ListenerHauptMenu());
		lernsprachenMenu.add(menuItem);
		
		karteiMenu.add(lernsprachenMenu);
		
		this.add(karteiMenu);
		
		/**
		 * Menu für Anzeige
		 */
	
		anzeigeMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Anzeige"));
		/**
		 * Submenu für Spracheinstellung
		 */
		 
		lernsprachMenu = new JMenu(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Sprache"));

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

			switch (selection) {
			case "beenden":
				System.exit(WHEN_FOCUSED);
				break;
			case "importieren":
				@SuppressWarnings("unused")
				FrameImport imp = new FrameImport();
				Kartei.getInstance().lernkarteiSpeichern();
				Kartei.getInstance().faecherBefuellen();
				break;
			case "karteHinzufuegen":
				FrameHinzufuegen ph = new FrameHinzufuegen();
				ph.paint();
				break;
			case "karteLoeschen":
				FrameLoeschen pl = new FrameLoeschen(); 
				pl.paint(); 
				break;
			case "karteBearbeiten":
				FrameBearbeiten pb = new FrameBearbeiten();
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
				PanelKarteiVerwalten pkv = new PanelKarteiVerwalten();
				pkv.paint();
				break;
			case "spracheLoeschen":
				FrameLoeschenSprachen pls = new FrameLoeschenSprachen();
				pls.paint();
				break;

			}
		}
		
	}

}
