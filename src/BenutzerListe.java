import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name = "BenutzerListe")
@XmlAccessorType(XmlAccessType.FIELD)

public class BenutzerListe {
	
	@XmlElement(name = "Benutzer")
	private ArrayList<Benutzer> benutzerListe;
	
	public BenutzerListe() {
		benutzerListe = new ArrayList<Benutzer>();
	}

	public boolean benutzerExistiert(String benutzername) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				return true;
			}
		}
		return false;
	}
	
	public Benutzer benutzerLaden(String benutzername) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				return b;
			}
		}
		return null;
	}
	
	public void benutzerHinzufuegen(Benutzer bneu) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(bneu.getBenutzername())) {
				System.out.println("Benutzer existiert bereits");
				return;				
			}
		}
		benutzerListe.add(bneu);
	}
	
	public void benutzerLoeschen(Benutzer b) {
		benutzerListe.remove(b);
	}
		
	public ArrayList<Benutzer> getBenutzerListe() {
		return benutzerListe;
	}

	public void benutzerListeLaden(String pfad) {
		if (new File(pfad).isFile()) {
			
			System.out.println("Starte Benutzerimport");
			
			try {

				JAXBContext context = JAXBContext.newInstance(BenutzerListe.class);

				Unmarshaller unmarshaller = context.createUnmarshaller();

				BenutzerListe imp = (BenutzerListe) unmarshaller.unmarshal(new File(pfad));

				for (Benutzer b : imp.getBenutzerListe()) {
					//System.out.println("Importiere Karte:" + k.toString());
					benutzerListe.add(b);
				}


			} catch (JAXBException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void benutzerlisteSpeichern(String pfad) {
	    
		try {
    		
		JAXBContext jaxbContext = JAXBContext.newInstance(BenutzerListe.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the card list in console
	    //jaxbMarshaller.marshal(this, System.out);
	     
	    //Marshal the card list in file
	    jaxbMarshaller.marshal(this, new File(pfad));
		 } catch (JAXBException e) {
				e.printStackTrace();
			      }
    }

	@Override
	public String toString() {
		return "BenutzerVerwaltung [benutzerStatus=" + benutzerListe + "]";
	}
	
	
	
	

}
