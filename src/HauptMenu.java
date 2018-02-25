
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

 
public class HauptMenu  extends JMenuBar{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu dateiMenu, sprachMenu, anzeigeMenu, kartenMenu;
    private JMenuItem menuItem;
	private String country, language;
   
    public HauptMenu() {

    	//Hauptmenu erstellen
    	    			
    			//Dateimenu erstellen.
    			dateiMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Datei"));
    			
    			menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Importieren"));
    			menuItem.addActionListener(new ListenerDateiMenu());
    			dateiMenu.add(menuItem);
    			
    			menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Beenden"));
    			menuItem.addActionListener(new ListenerDateiMenu());
    			dateiMenu.add(menuItem);
    			
    			this.add(dateiMenu);

    			
    			//Menu für Kartenbearbeitenung
    			kartenMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Karte"));
    			
    			menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
    			menuItem.addActionListener(new ListenerDateiMenu());
    			kartenMenu.add(menuItem);

    			menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("bearbeiten"));
    			menuItem.addActionListener(new ListenerDateiMenu());
    			kartenMenu.add(menuItem);
    			
    			menuItem = new JMenuItem(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("loeschen"));
    			menuItem.addActionListener(new ListenerDateiMenu());
    			kartenMenu.add(menuItem);
    			
    			this.add(kartenMenu);


    			//Menu für Anzeige.
    			anzeigeMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Anzeige"));
    			
    			//Submenu für Spracheinstellung		
    			sprachMenu = new JMenu(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Sprache"));
    					
    			menuItem = new JMenuItem("Deutsch");
    			menuItem.addActionListener(new ListenerSprachMenu());
    			sprachMenu.add(menuItem);
    			
    			menuItem = new JMenuItem("English");
    			menuItem.addActionListener(new ListenerSprachMenu());
    			sprachMenu.add(menuItem);
    			
    			menuItem = new JMenuItem("Francaise");
    			menuItem.addActionListener(new ListenerSprachMenu());
    			sprachMenu.add(menuItem);
    			
    			menuItem = new JMenuItem("Italiano");
    			menuItem.addActionListener(new ListenerSprachMenu());
    			sprachMenu.add(menuItem);
    			
    			anzeigeMenu.add(sprachMenu);
    			this.add(anzeigeMenu);

    			
    }
    
    class ListenerDateiMenu implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem menuitem = (JMenuItem) e.getSource();
			String msg = menuitem.getText();
			System.out.println(msg);

			switch (msg) {
			case "Beenden":
				break;
			case "Import":
				break;
			}
		}

	}
   
	class ListenerSprachMenu implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem menuitem = (JMenuItem) e.getSource();
			String msg = menuitem.getText();
			System.out.println(msg);

			switch (msg) {
			case "Deutsch":
				country = new String("DE");
				language = new String("de");
				break;
			case "English":
				country = new String("EN");
				language = new String("en");
				break;
			case "Francaise":
				country = new String("FR");
				language = new String("fr");
				break;
			case "Italiano":
				country = new String("IT");
				language = new String("it");
				break;

			}
			
			Hauptfenster.locale = new Locale(language, country);
			main.hauptFenster.spracheWechseln();
			
			
		}

	}
	
}