import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.3
 */

@XmlRootElement(name = "Benutzer")
@XmlType(propOrder = { "benutzername", "passwort", "lernfortschritte" })
@XmlAccessorType(XmlAccessType.FIELD)

public class Benutzer {
		
		private String benutzername;
		private String passwort;
		
		@XmlElement(name = "KartenStatus")
		private ArrayList<KartenStatus> lernfortschritte;
		
	public Benutzer() {
		
	}
	
	public Benutzer(String name, String passwort) {
		this.benutzername = name;
		this.passwort = passwort;
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

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPasswort() {
		return passwort;
	}
	
 

	
}
