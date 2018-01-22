import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Benutzer")
@XmlAccessorType(XmlAccessType.FIELD)

public class Benutzer {
		private String benutzername;
		private ArrayList<KartenStatus> lernfortschritte;
		
	public Benutzer() {
		
	}
	
	public Benutzer(String name) {
		this.benutzername = name;
		lernfortschritte = new ArrayList<KartenStatus>();
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public ArrayList<KartenStatus> getLernfortschritte() {
		return lernfortschritte;
	}

	@Override
	public String toString() {
		return "Benutzer [benutzername=" + benutzername + ", Lernfortschritte=" + lernfortschritte + "]";
		
	}
	
	public void kartenStatusAnpassen(UUID id, int fach ) {
		for (KartenStatus k : lernfortschritte){
			if (k.getUid().equals(id)){
				k.setFach(fach);
			}
			}
	}
	
	public void benutzerStatusAnzeigen() {
		System.out.println(benutzername);
		for (KartenStatus k : lernfortschritte) {
			System.out.println(k.toString());
		}
	}
	
	
	
	


	
	

	
}
