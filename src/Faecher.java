import java.util.ArrayList;

public class Faecher {

	private Fach[] faecher;	
	
	public Faecher() {
		this.faecher = new Fach[5];
	}

public void faecherBefuellen(Benutzer b, Kartei kartei, Sprache s) {
	
		//TODO: überprüfen ob Variablen gültig sind
	
		ArrayList<Karte> alleKarten = kartei.getLernkartei();
		ArrayList<KartenStatus> alleStatus = b.getLernfortschritte();
		
		// Faecher 1-5 erstellen und gegebenenfalls bereits vorhandene Faecher löschen
		for(int i=1; i<6; i++) {
			faecher[i-1] = new Fach(i-1);
			
		}
						
			// Karten aus der Kartei in Fach laden, dazugehörigen Benutzerstatus prüfen und ins jeweilige Fach ablegen.
				
			for (Karte k : alleKarten) {
								
				System.out.println("Bearbeite Karte: " + k.toString());
								
				boolean found = false;
				
				for (KartenStatus st : alleStatus){
					if (k.getId().equals(st.getUid())){
						faecher[st.getFach()-1].karteHinzufuegen(k);
						System.out.println("Fach" + st.getFach() + " "  + k.toString());
						found = true;
					}
					else {
						found = false;
					}
					}
				
				if (found == false) {
					faecher[0].karteHinzufuegen(k);
					alleStatus.add(new KartenStatus(k.getId(), 1));	
				}
	
				
				}
			
				}			
	
public void kartenAuflisten() {
	for(int i=0; i<5; i++) {
		Fach fach = faecher[i];
		for (Karte k : fach.gibKarten()) {
			System.out.println(k.toString());
		}
			
	}
}

}
