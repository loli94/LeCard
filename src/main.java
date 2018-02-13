
public class main {
	
	public static Kartei daten1;
	public static String pfad;

	public static void main(String[] args) {
		
		pfad = "C:\\temp\\lernkartei_kombiniert.xml";
				
		try {
			daten1 = new Kartei(pfad);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		LoginFrame l1 = new LoginFrame();
		l1.paint();

	}
	
	
}	