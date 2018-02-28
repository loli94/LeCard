/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.2
 * Datum:24.02.2018
 */
public class Main {
	
	public static Kartei daten1;
	public static String pfad;
	public static Hauptfenster hauptFenster;

	public static void main(String[] args) {
		
		pfad = "C:\\temp\\lernkartei_kombiniert.xml";
				
		try {
			daten1 = new Kartei(pfad);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		LoginFrame l1 = new LoginFrame();
		l1.paint();
		
		daten1.spracheHinzugfuegen("de-fr", "Deutsch", "Francaise");
		daten1.spracheHinzugfuegen("de-en", "Deutsch", "English");
		
		daten1.lernkarteiSpeichern(pfad);
		
		

	}
	
	
}	