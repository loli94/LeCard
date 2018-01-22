import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Benutzerliste")
public class BenutzerListe {
	
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
	
	public void benutzerHinzufuegen(Benutzer b) {
		benutzerListe.add(b);
	}
	
	public void benutzerLoeschen(Benutzer b) {
		benutzerListe.remove(b);
	}
	
	public void benutzerListeLaden(String pfad) {
		if (new File(pfad).isFile()) {
			try {

				JAXBContext context = JAXBContext.newInstance(BenutzerListe.class);

				Unmarshaller unmarshaller = context.createUnmarshaller();

				BenutzerListe benutzerStatus = (BenutzerListe) unmarshaller.unmarshal(new File(pfad));

				System.out.println(benutzerStatus);

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
	    jaxbMarshaller.marshal(this, System.out);
	     
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
