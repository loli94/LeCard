package Logik;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.3
 * Datum:24.02.2018
 */
@XmlRootElement(name = "Kartei")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kartei {

	private static Kartei instance = null;

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
	private String aktuellesSprachpaar;
	@XmlTransient
	private Sprache aktuelleSprache;
	@XmlTransient
	private int aktuellesFach;
	@XmlTransient
	private Karte aktuelleKarte;
	@XmlTransient
	private int richtigeAntwort;
	@XmlTransient
	private int falscheAntwort;
	@XmlTransient
	private Locale lokal;
	@XmlTransient
	private String pfad;
	

	protected Kartei() {
		this.kartei = new ArrayList<Karte>();
		this.sprachen = new ArrayList<Sprache>();
		this.benutzerListe = new ArrayList<Benutzer>();
		this.fach = new Fach[5];
		this.aktuellesSprachpaar = "DE-EN";
		this.aktuellesFach = 0;
		this.richtigeAntwort = 0;
		this.falscheAntwort = 0;
		this.lokal = new Locale("de", "DE");
	}

	public static Kartei getInstance() {
		if(instance == null) {
			instance = new Kartei();
		}
		return instance;
	}

	public Sprache getAktuelleSprache() {
		return aktuelleSprache;
	}

	public String getAktuellesSprachpaar() {
		return aktuellesSprachpaar;
	}

	public void setAktuelleSprache(String aktuelleSprache) {
		this.aktuellesSprachpaar = aktuelleSprache;
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
		if (aktuellesFach > 0) {
			fach[aktuellesFach - 1].karteEnfernen(k);
			kartei.remove(k);
		}
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

	public int getRichtigeAntwort() {
		return richtigeAntwort;
	}

	public void setRichtigeAntwort() {
		this.richtigeAntwort++;
	}

	public int getFalscheAntwort() {
		return falscheAntwort;
	}

	public void setFalscheAntwort() {
		this.falscheAntwort++;
		System.out.println("" + falscheAntwort);
	}

	/*
	 * Arraylists mit Sprachen, Benutzern und Karten in XML File exportieren.
	 */
	public void lernkarteiSpeichern() {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Kartei.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this, new File(pfad));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/*
	 * XML File einlesen und die enthaltenen Elemente zu Objekten umwandeln
	 */
	public void karteiOeffnen(String p) throws Exception {
		
		this.pfad = p;

		try {

			JAXBContext context = JAXBContext.newInstance(Kartei.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Kartei imp = (Kartei) unmarshaller.unmarshal(new File(pfad));

			this.kartei = imp.getLernkartei();
			this.benutzerListe = imp.getBenutzerListe();
			this.sprachen = imp.getSprachen();

		} catch (JAXBException e) {
			e.printStackTrace();
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
			this.fach[i - 1] = new Fach(i - 1);

		}

		// Karten aus der Kartei in Fach laden, dazugehörigen Benutzerstatus prüfen und
		// ins jeweilige Fach ablegen.

		for (Karte k : kartei) {

			boolean found = false;

			if (k.getSprache().equalsIgnoreCase(aktuellesSprachpaar)) {
				for (KartenStatus st : alleStatus) {
					if (k.getId().equals(st.getUid())) {
						fach[st.getFach() - 1].karteHinzufuegen(k);
						found = true;
						break;
					}
				}

				if (found == false) {
					alleStatus.add(new KartenStatus(k.getId(), 1));
					fach[0].karteHinzufuegen(k);
				}
			}
		}

	}

	public Fach getFach(int x) {
		return fach[x - 1];
	}

	public boolean gibNaechsteKarte() {

		// Nächste Karte aus diesem Fach in der entsprechenden Sprache ausgeben

		if (aktuellesFach > 0 && fach[aktuellesFach - 1].gibKarten().size() > 0 ) {
			Random rnd = new Random();
			int index = rnd.nextInt(fach[aktuellesFach - 1].gibKarten().size());
			this.aktuelleKarte = fach[aktuellesFach - 1].gibKarten().get(index);
			return true;
		}
		else {
			this.aktuelleKarte = null;
			return false;	
		}
	}

	public boolean karteVerschieben(Karte k, int neuesFach) {

		if (k != null && neuesFach < 6 && neuesFach > 0) {

			// Falls Kartenstatus für Benutzer in XML bereits vorhanden, dann verschieben in
			// neues Fach
			for (KartenStatus ks : benutzer.getLernfortschritte()) {
				if (k.getId().equals(ks.getUid())) {
					ks.setFach(neuesFach);
					fach[aktuellesFach - 1].karteEnfernen(k);
					fach[neuesFach - 1].karteHinzufuegen(k);
					return true;
				}
			}

			// Wenn Kartenstatus in XML für diesen Benutzer noch nicht vorhanden, dann
			// Status erstellen und Karte verschieben
			benutzer.getLernfortschritte().add(new KartenStatus(k.getId(), neuesFach));
			fach[aktuellesFach - 1].karteEnfernen(k);
			fach[neuesFach - 1].karteHinzufuegen(k);

			return true;

		}

		else {
			return false;
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
			f.close();
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

	public boolean spracheWaehlen(String sprachpaar) {

		for (Sprache s : sprachen) {
			if (s.getSprachPaar().equalsIgnoreCase(sprachpaar)) {
				aktuelleSprache = s;
				aktuellesSprachpaar = s.getSprachPaar();
				aktuelleSprache = s;
				return true;
			}
		}

		return false;
	}

	public ArrayList<Sprache> getSprachen() {
		return sprachen;
	}

	public int getFachGroesse(int fachnummer) {
		int fg = fach[fachnummer].gibAnzahlKarten();
		return fg;
	}
	
	public Locale getLocale() {
		return lokal;
	}

	public void setLocale(Locale locale) {
		this.lokal = locale;
	}
	
	



}
