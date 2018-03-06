import GUI.LoginFrame;
import Logik.Kartei;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.2
 * Datum:24.02.2018
 */
public class Main {
	

	public static void main(String[] args) {
						
		try {
			Kartei.getInstance().karteiOeffnen("C:\\temp\\lernkartei_kombiniert.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}


		//Standardsprache auswählen
		Kartei.getInstance().spracheWaehlen("de-en");
		
		LoginFrame l1 = new LoginFrame();
		l1.paint();
		
	}
	
	
	
	
}	