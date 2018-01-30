import java.util.ArrayList;

public class KartenBox {

	private Fach[] fach;	
	private Benutzer benutzer;
	
	public KartenBox(Benutzer b) {
		this.fach = new Fach[5];
		this.benutzer = b;
	}

public void faecherBefuellen(Kartei kartei) {

	
		ArrayList<Karte> alleKarten = kartei.getLernkartei();
		
		if (benutzer.getLernfortschritte() == (null)){
			benutzer.setLernfortschritte(new ArrayList<KartenStatus>());			
		}
		
		ArrayList<KartenStatus> alleStatus = benutzer.getLernfortschritte();
		
		
		// Faecher 1-5 erstellen und gegebenenfalls bereits vorhandene Faecher löschen
		for(int i=1; i<6; i++) {
			fach[i-1] = new Fach(i-1);
			
		}
						
			// Karten aus der Kartei in Fach laden, dazugehörigen Benutzerstatus prüfen und ins jeweilige Fach ablegen.
				
			for (Karte k : alleKarten) {
				
				boolean found = false;
				
				System.out.println(k);
				System.out.println(alleStatus);
				
				for (KartenStatus st : alleStatus){
					if (k.getId().equals(st.getUid())){
						fach[st.getFach()-1].karteHinzufuegen(k);
						System.out.println("Kartenstatus gefunden");
						found = true;
						break;
					}
				}
				
				if (found == false) {
					System.out.println("Kartenstatus nicht gefunden");
					alleStatus.add(new KartenStatus(k.getId(), 1));
					fach[0].karteHinzufuegen(k);
				}
				
			}
			
								
				}			
	
public Karte gibNaechsteKarte(int fachnr, String sprache) {
	
	//Nächste Karte aus diesem Fach in der entsprechenden Sprache ausgeben
	for (Karte k : fach[fachnr-1].gibKarten()) {
		if (k.getSprache().equals(sprache)) {
			return k;
		}
	}
	
	return null;
}

public void karteVerschieben(Karte k, int altesFach, int neuesFach) {
	
	while (k != null) {
	
	//Zuerst Benutzerstatus anpassen

	boolean found = false;
	
	for (KartenStatus ks : benutzer.getLernfortschritte()) {
		if (k.getId().equals(ks.getUid())) {
			ks.setFach(neuesFach);
			found = true;		
			break;
		}
	}

	if (found == false) {
		benutzer.getLernfortschritte().add(new KartenStatus(k.getId(), neuesFach));
	}
	
	//.. dann Karte in Fach verschieben
	
	fach[altesFach-1].karteEnfernen(k);
	
	fach[neuesFach-1].karteHinzufuegen(k);
	
	
	}
	
	
}


}
