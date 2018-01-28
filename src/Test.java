
public class Test {

	public Test() {
		
	}

	public static void main(String[] args) throws Exception {

		// Sprache s1 = new Sprache("de", "en");
		
		Kartei lk2 = new Kartei("C:\\temp\\lernkartei.xml");
		
		Karte k3 = new Karte("de-en", "Bild", "Picture");
		
		lk2.karteHinzufuegen(k3);

		BenutzerListe bl1 = new BenutzerListe();

		bl1.benutzerListeLaden("C:\\temp\\benutzerliste.xml");

		Benutzer akutellerBenutzer = bl1.benutzerLaden("Franz");
		
		KartenBox fl1 = new KartenBox(akutellerBenutzer);
		
		fl1.faecherBefuellen(lk2);
		
		System.out.println("Lernfortschritt B3:" + akutellerBenutzer.getLernfortschritte());

		lk2.lernkarteiSpeichern("c:\\temp\\lernkartei.xml");
		
		Karte k4 = fl1.gibNaechsteKarte(1, "de-en");
		
		System.out.println("aktuelle Karte" + k4);	
		
		fl1.karteVerschieben(k4, 1, 2);
		
		
		bl1.benutzerlisteSpeichern("C:\\temp\\benutzerliste.xml");
		
		
		
				
		
	}

}
