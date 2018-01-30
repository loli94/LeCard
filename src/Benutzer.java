import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlRootElement(name = "Benutzer")
@XmlType(propOrder = { "benutzername", "lernfortschritte" })
@XmlAccessorType(XmlAccessType.FIELD)

public class Benutzer {
		
		private String benutzername;
		
		@XmlElement(name = "KartenStatus")
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
	
	public void setLernfortschritte(ArrayList<KartenStatus> lf) {
		this.lernfortschritte = lf;
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
