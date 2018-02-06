
public class Test {

	public Test() {
		
	}

	public static void main(String[] args) throws Exception {

		// Sprache s1 = new Sprache("de", "en");
		
		Kartei lk2 = new Kartei("C:\\temp\\lernkartei_kombiniert.xml");
				
		lk2.karteHinzufuegen(new Karte("de-en", "Haus", "House"));
		
		Benutzer b1 = new Benutzer("Hubert");
		
		lk2.benutzerHinzufuegen(b1);
		
		lk2.benutzerLaden("Hubert");
				
		lk2.faecherBefuellen();
		
		int aktFach = 2;
		
		Karte k4 = lk2.gibNaechsteKarte(aktFach, "de-en");
		
		System.out.println("aktuelle Karte" + k4);	
		
		lk2.karteVerschieben(k4, aktFach, aktFach+1);
		
		lk2.lernkarteiSpeichern("c:\\temp\\lernkartei_kombiniert2.xml");
		
		
				
		
	}

}
