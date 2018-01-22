
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Sprache s1 = new Sprache("de", "en");
		
		Karte k1 = new Karte(s1, "Tree", "Baum");
		Karte k2 = new Karte(s1, "Wolke", "Cloud");
		
		Kartei lk1 = new Kartei();
		
		lk1.karteHinzufuegen(k1);
		lk1.karteHinzufuegen(k2);

		//lk1.lernkarteiSpeichern("C:\\temp\\file.xml");
		
		//lk1.karteiEinlesen("C:\\temp\\file.xml");
		
		Karte k3 = new Karte(s1, "Auto", "Car");
		lk1.karteHinzufuegen(k3);
		//lk1.karteLoeschen(k2);
		
		//lk1.lernkarteiSpeichern("C:\\temp\\file2.xml");
		
		BenutzerListe bv1 = new BenutzerListe();
		
		Benutzer b1 = new Benutzer("Franz");
		Benutzer b2 = new Benutzer("Peter");
		
		BenutzerListe bl1 = new BenutzerListe();
		
		bl1.benutzerHinzufuegen(b1);
		bl1.benutzerHinzufuegen(b2);
		
		bl1.benutzerlisteSpeichern("C:\\temp\\benutzerliste.xml");
		
		System.out.println(bv1);
		
		FaecherListe fl1 = new FaecherListe();
		
		fl1.faecherBefuellen(b1, lk1, s1);
		
		fl1.kartenAuflisten();
		
		b1.kartenStatusAnpassen(k1.getId(), 3);
		
		b1.benutzerStatusAnzeigen();
		
	}

}
