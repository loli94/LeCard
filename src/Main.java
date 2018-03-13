import GUI.FrameLogin;
import Logik.Kartei;

/**
 *  @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.3
 * Datum:24.02.2018
 */
public class Main {
	

	public static void main(String[] args) {
						
		try {
			Kartei.getInstance().karteiOeffnen("lernkartei_kombiniert.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}


		//Standardsprache auswählen
		Kartei.getInstance().spracheWaehlen("de-en");
		
		FrameLogin l1 = new FrameLogin();
		l1.paint();
		
	}
	
	
	
	
}	