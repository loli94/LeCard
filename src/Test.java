
public class Test {

	public Test() {
		
	}

	public static void main(String[] args) throws Exception {

		// Sprache s1 = new Sprache("de", "en");
		
		Kartei lk2 = new Kartei("C:\\temp\\lernkartei_kombiniert.xml");
				
		lk2.benutzerHinzufuegen("Lars", "1234");
		
		lk2.benutzerLaden("Heinz", "1234");
				
		lk2.faecherBefuellen();
		
		int aktFach = 2;
		
		Karte k4 = lk2.gibNaechsteKarte(aktFach, "de-en");
		
		System.out.println(k4);
		
		System.out.println("aktuelle Karte" + k4);	
		
		lk2.karteVerschieben(k4, aktFach, aktFach+1);
		
		lk2.karteHinzufuegen(new Karte("de-en", "Haus", "House"));
		
		lk2.lernkarteiSpeichern("c:\\temp\\lernkartei_kombiniert.xml");
		

			
	}
	

}
