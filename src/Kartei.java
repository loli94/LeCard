import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Kartei")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kartei {

	private static Kartei instance;

	@XmlElement(name = "Karte")
	private ArrayList<Karte> kartei;

	@XmlElement(name = "Sprache")
	private ArrayList<Sprache> sprachen;

	@XmlElement(name = "Benutzer")
	private ArrayList<Benutzer> benutzerListe;

	@XmlTransient
	private Fach[] fach;

	@XmlTransient
	private Benutzer benutzer;
	
	@XmlTransient
	private String aktuelleSprache; 
	
	@XmlTransient
	private int aktuellesFach;
	
	@XmlTransient
	private Karte aktuelleKarte;

	private Kartei() {

	}

	public static Kartei getInstance() {
		if (instance == null) {
			instance = new Kartei();
		}
		return instance;
	}

	public Kartei(String pfad) throws Exception {
		this.kartei = new ArrayList<Karte>();
		this.sprachen = new ArrayList<Sprache>();
		this.benutzerListe = new ArrayList<Benutzer>();
		this.fach = new Fach[5];
		karteiEinlesen(pfad);
		this.aktuelleSprache = "de-en";
		this.aktuellesFach=1;
	}

	public String getAktuelleSprache() {
		return aktuelleSprache;
	}

	public void setAktuelleSprache(String aktuelleSprache) {
		this.aktuelleSprache = aktuelleSprache;
	}

	public void karteHinzufuegen(Karte k1) {
		for (Karte k2 : kartei) {
			if (k1.getWortA().equalsIgnoreCase(k2.getWortA()) && k2.getWortB().equalsIgnoreCase(k1.getWortB())) {
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

	public ArrayList<Benutzer> getBenutzeriste() {
		return benutzerListe;
	}

	public void setLernkartei(ArrayList<Karte> lernkartei) {
		this.kartei = lernkartei;
	}

	public void lernkarteiSpeichern(String pfad) {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Kartei.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the card list in console
			// jaxbMarshaller.marshal(this, System.out);

			// Marshal the card list in file
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

			Kartei imp = (Kartei) unmarshaller.unmarshal(new File(pfad));

			for (Karte k : imp.getLernkartei()) {
				// System.out.println("Importiere Karte:" + k.toString());
				kartei.add(k);
			}

			for (Benutzer b : imp.getBenutzerListe()) {
				benutzerListe.add(b);

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

	public boolean benutzerExistiert(String benutzername) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				return true;
			}
		}
		return false;
	}
	

	public boolean benutzerLaden(String benutzername, String passwort) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername) || b.getBenutzername().toLowerCase().equals(benutzername)) {
				if (b.getPasswort().equals(getMD5Hash(passwort))) {
					this.benutzer = b;
					this.faecherBefuellen();
					return true;

				} else {
					// falsches passwort
					System.out.println("Passwort falsch");
					return false;
				}
			}
		}
		System.out.println("Benutzer nicht gefunden");
		return false;
	}

	public void benutzerHinzufuegen(String benutzername, String passwort) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				System.out.println("Benutzer existiert bereits");
				return;
			}
		}
		benutzerListe.add(new Benutzer(benutzername, getMD5Hash(passwort)));
	}

	public void benutzerLoeschen(Benutzer b) {
		benutzerListe.remove(b);
	}

	public ArrayList<Benutzer> getBenutzerListe() {
		return benutzerListe;
	}

	public void faecherBefuellen() {

		if (benutzer.getLernfortschritte() == (null)) {
			benutzer.setLernfortschritte(new ArrayList<KartenStatus>());
		}

		ArrayList<KartenStatus> alleStatus = benutzer.getLernfortschritte();

		// Faecher 1-5 erstellen und gegebenenfalls bereits vorhandene Faecher löschen
		for (int i = 1; i < 6; i++) {
			fach[i - 1] = new Fach(i - 1);

		}

		// Karten aus der Kartei in Fach laden, dazugehörigen Benutzerstatus prüfen und
		// ins jeweilige Fach ablegen.

		for (Karte k : kartei) {

			boolean found = false;

			// System.out.println(k);
			// System.out.println(alleStatus);

			for (KartenStatus st : alleStatus) {
				if (k.getId().equals(st.getUid())) {
					fach[st.getFach() - 1].karteHinzufuegen(k);
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

	public Fach getFach(int x) {
		return fach[x-1]; 
	}

	public boolean gibNaechsteKarte() {

		// Nächste Karte aus diesem Fach in der entsprechenden Sprache ausgeben
		for (Karte k : fach[aktuellesFach - 1].gibKarten()) {
			if (k.getSprache().equals(aktuelleSprache)) {
				this.aktuelleKarte= k;
				
				return true;
			}
		}

		return false;
	}

	public void karteVerschieben(Karte k, int neuesFach) {

		if (k != null) {

			// Zuerst Benutzerstatus anpassen

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

			// .. dann Karte in Fach verschieben

			// System.out.println("entfernen");
			fach[aktuellesFach - 1].karteEnfernen(k);

			// System.out.println("hinzufuegen");
			fach[neuesFach - 1].karteHinzufuegen(k);

		}

		else {
			System.out.println("Verschieben nicht möglich. Keine Karte.");
		}

	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public static String getMD5Hash(String str) {
		StringBuilder sb = new StringBuilder(32);
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			Formatter f = new Formatter(sb);
			for (byte b : md5.digest()) {
				f.format("%02x", b);
			}
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public boolean spracheHinzugfuegen(String ab, String a, String b) {
		for (Sprache s : sprachen) {
			if (s.getSpracheA().equalsIgnoreCase(a) && s.getSpracheB().equalsIgnoreCase(b)) {
				System.out.println("Sprachpaar existiert bereits");
				return false;
			}
			if (s.getSpracheA().equalsIgnoreCase(b) && s.getSpracheB().equalsIgnoreCase(a)) {
				System.out.println("Sprachpaar existiert bereits");
				return false;
			}
		}

		sprachen.add(new Sprache(ab, a, b));

		return true;
	}

	public int getAktuellesFach() {
		return aktuellesFach;
	}

	public void setAktuellesFach(int aktuellesFach) {
		this.aktuellesFach = aktuellesFach;
	}

	public Karte getAktuelleKarte() {
		return aktuelleKarte;
	}

	public void setAktuelleKarte(Karte aktuelleKarte) {
		this.aktuelleKarte = aktuelleKarte;
	}
	
	
	
	

}
