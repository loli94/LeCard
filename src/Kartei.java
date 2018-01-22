import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Kartei")
public class Kartei {
	
	@XmlElement(name = "Karte")
	private ArrayList<Karte> lernkartei;

	public Kartei() {
		this.lernkartei = new ArrayList<Karte>();
	}

	public ArrayList<Karte> kartenAusgeben(){
		return lernkartei;
	}
	
	public void kartenLaden(ArrayList<Karte> karten) {
		this.lernkartei = karten;
	}
	
	
	public void karteHinzufuegen(Karte k) {
		lernkartei.add(k);
	}
	
	public void karteLoeschen(Karte k) {
		lernkartei.remove(k);
	}
	
	
	
	
	public ArrayList<Karte> getLernkartei() {
		return lernkartei;
	}

	public void setLernkartei(ArrayList<Karte> lernkartei) {
		this.lernkartei = lernkartei;
	}

	public void lernkarteiSpeichern(String pfad) {
	    
		try {
    		
		JAXBContext jaxbContext = JAXBContext.newInstance(Kartei.class);
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
	
	
	public void karteiEinlesen(String pfad) {
		/*
		 * XML File einlesen und die enthaltenen Elemente zu Objekten (Karte) umwandeln
		 */
		
	try {
    		
			JAXBContext context = JAXBContext.newInstance(Kartei.class);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			Kartei geleseneKarten =  (Kartei) unmarshaller.unmarshal(new File(pfad));
			
				System.out.println(geleseneKarten);
			
			
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
		
	}

	public void kartenAnzeigen() {
		for (Karte k : lernkartei) {
			System.out.println(k.toString());
	}
	
	
		
		
		
	}
}
	
	
	



	
	

