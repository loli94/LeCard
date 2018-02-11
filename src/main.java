
public class main {
	
	public static Kartei daten1;

	public static void main(String[] args) {
		
		try {
			daten1 = new Kartei("C:\\temp\\lernkartei_kombiniert.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame l1 = new LoginFrame();
		l1.paint();

	}
	
	
}