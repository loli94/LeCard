
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Sprache s1 = new Sprache("de", "en");
		/*
		
		Karte k1 = new Karte(s1, "Tree", "Baum");
		Karte k2 = new Karte(s1, "Wolke", "Cloud");
		lk1.karteHinzufuegen(k1);
		lk1.karteHinzufuegen(k2);
		*/
		
		Kartei lk2 = new Kartei("C:\\temp\\lernkartei.xml");
		
		Karte k3 = new Karte(s1, "Auto", "Car");
		lk2.karteHinzufuegen(k3);
		
		
		BenutzerListe bl1 = new BenutzerListe();
		
		/*
		Benutzer b1 = new Benutzer("Franz");
		Benutzer b2 = new Benutzer("Peter");
		bl1.benutzerHinzufuegen(b1);
		bl1.benutzerHinzufuegen(b2);
		*/
		
		bl1.benutzerListeLaden("C:\\temp\\benutzerliste.xml");

		Benutzer b3 = bl1.benutzerLaden("Franz");
		
		System.out.println(b3.toString());
		
		Faecher fl1 = new Faecher();
		
		fl1.faecherBefuellen(b3, lk2, s1);
		
		//b1.kartenStatusAnpassen(k1.getId(), 3);
		
		//b1.benutzerStatusAnzeigen();

		//lk2.kartenAnzeigen();
	
		
		lk2.lernkarteiSpeichern("c:\\temp\\lernkartei2.xml");
		
		bl1.benutzerlisteSpeichern("C:\\temp\\benutzerliste.xml");

	}

}
