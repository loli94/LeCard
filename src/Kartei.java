import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Kartei")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kartei {
	
	@XmlElement(name = "Karte")
	private ArrayList<Karte> kartei;

	public Kartei() {
		this.kartei = new ArrayList<Karte>();
	}
	
	public Kartei(String pfad) throws Exception {
		this.kartei = new ArrayList<Karte>();
		karteiEinlesen(pfad);
	}

	public ArrayList<Karte> kartenAusgeben(){
		return kartei;
	}
	
	public void kartenLaden(ArrayList<Karte> karten) {
		this.kartei = karten;
	}
	
	
	public void karteHinzufuegen(Karte k1) {		
		for (Karte k2 : kartei) {
			if (k1.getWortA().equalsIgnoreCase(k2.getWortA()) && k2.getWortB().equalsIgnoreCase(k1.getWortB())){
				System.out.println("Wortkombination bereits vorhanden");
				return;
			}
		}
		
		kartei.add(k1);
		
	}
	
	public void karteLoeschen(Karte k) {
		kartei.remove(k);
	}
			
	public ArrayList<Karte> getLernkartei() {
		return kartei;
	}

	public void setLernkartei(ArrayList<Karte> lernkartei) {
		this.kartei = lernkartei;
	}

	public void lernkarteiSpeichern(String pfad) {
	    
		try {
    		
		JAXBContext jaxbContext = JAXBContext.newInstance(Kartei.class);
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
	
	
	public void karteiEinlesen(String pfad) throws Exception {
		/*
		 * XML File einlesen und die enthaltenen Elemente zu Objekten (Karte) umwandeln
		 */

		
	try {
    		
			JAXBContext context = JAXBContext.newInstance(Kartei.class);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			Kartei imp =  (Kartei) unmarshaller.unmarshal(new File(pfad));
			
				for (Karte k : imp.getLernkartei()) {
					//System.out.println("Importiere Karte:" + k.toString());
					kartei.add(k);
				}
			
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
		
	}

	public void kartenAnzeigen() {
		for (Karte k : kartei) {
			System.out.println(k.toString());
	}
	
	
		
		
		
	}
}
	
	
	



	
	

