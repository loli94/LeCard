package Logik;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/**
 * Klasse Kartei beinhaltet alle zentralen Verwaltungselemente des Programs, also User-, Karten- und Sprachhandling.
 * Kartei ist nach dem Singleton Template aufgebaut, damit die aktuelle Kartei aus anderen Klassen und Methoden direkt aufgerufen werden kann.
 * 
 * @author Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * 
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

	/**
	 * Standardkonstruktor für Kartei. Protected, damit die Klasse nur über GetInstance aufgerufen werden kann.
	 */
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
		if (instance == null) {
			instance = new Kartei();
		}
		return instance;
	}


	/**
	 * Alle Karten, Sprachen und Benutzer werden in das XML File geschrieben
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

	/**
	 * 
	 * Einlesen der Objekte aus dem XML File und Übernahme der Daten in die aktuelle Instanz.
	 * @param p Pfad von dem das XML File geöffnet wird.
	 * @throws Exception Fehler ausgeben wenn Import nicht erfolgreich
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
	
	

	/**
	 * Benutzerliste durchsuchen ob ein Benutzer mit diesem Benutzernamen bereits existiert.
	 * @param benutzername Zu suchender Benutzername.
	 * @return True wenn Benutzer gefunden wurde. False wenn kein ensprechender Benutzer gefunden wurde.
	 */
	public boolean benutzerExistiert(String benutzername) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Benutzer in der Benutzerliste suchen und das Benutzerobjekt laden wenn die Passworteingabe korrekt ist
	 * @param benutzername Benutzername
	 * @param passwort Passwort
	 * @return True wenn Benutzer geladen werden konnte, sonst False.
	 */
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

		return false;
	}

	/**
	 * Neues Benutzerobjekt erstellen und zur benutzerListe hinzufügen
	 * Passwort wird in MD5 Hash umgewandelt.
	 * 
	 * @param benutzername Benutzername des neuen Benutzers
	 * @param passwort Passwort des neuen Benutezrs.
	 */
	public void benutzerHinzufuegen(String benutzername, String passwort) {
		for (Benutzer b : benutzerListe) {
			if (b.getBenutzername().equals(benutzername)) {
				return;
			}
		}
		benutzerListe.add(new Benutzer(benutzername, getMD5Hash(passwort)));
	}

	/**
	 * Benutzer löschen
	 * @param b Zu löschender Benutzer
	 */
	public void benutzerLoeschen(Benutzer b) {
		benutzerListe.remove(b);
	}

	/**
	 * Alle Karten aus der Kartei werden mit der aktuellen Sprache abgeglichen und die Karten derr aktuellen Sprache werden geladen.
	 * Falls der Benutzer die Karte schonmal gelernt hat, dann wird sie in das entsprechende Fach abgelegt, sonst in Fach 1. 
	 */
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

	
	/**
	 * Zufälliges Laden einer neuen Karte aus dem aktuelle Fach. Die Karte wird dann als aktuelleKarte gesetzt.
	 * @return Falls keine weitere Karte in dem Fach vorhanden ist (null) wird false zurückgegeben.
	 */
	public boolean gibNaechsteKarte() {

		// Nächste Karte aus diesem Fach in der entsprechenden Sprache ausgeben

		if (aktuellesFach > 0 && fach[aktuellesFach - 1].gibKarten().size() > 0) {
			Random rnd = new Random();
			int index = rnd.nextInt(fach[aktuellesFach - 1].gibKarten().size());
			this.aktuelleKarte = fach[aktuellesFach - 1].gibKarten().get(index);
			return true;
		} else {
			this.aktuelleKarte = null;
			return false;
		}
	}
	

	/**
	 * Die Karte k wird aus dem aktuellen Fach entfernt und in das neue Zielfach verschoben.
	 * 
	 * 
	 * @param k Karten welche verschobenw erden soll
	 * @param neuesFach Zielfach, in welchem die Karte abgelegt werden soll
	 * @return Rückgabe ob das verschieben erfolgreich war.
	 */
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



	/**
	 * Umwandlung des eingegebenen Passworts in einen Hash, welcher im XML File anstatt des Passwords abgelegt wird. 
	 * Deckt nur minimale Sicherheitsanforderungen ab, also nur um zu verhindern, dass ein Benutezr versehentlich mit einem falschen Account lernt. Passwort könnte im XML jederzeit überschrieben werden
	 * @param str Übergabe des Strings, welcher verschlüsselt werden soll
	 * @return Rückgabe des Passwordhash
	 */
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
	

	
	/**
	 * Erstellen eines neuen Objekts Sprache und Hinzufügen zur Sprachenliste.
	 * 
	 * @param ab Sprachpaarung in Kurzform z.B. de-en 
	 * @param a Erste Sprache in Langform z.B. Deutsch
	 * @param b Zweite Sprache in Lanform z.B. Englisch
	 * @return True wenn Sprache hinzugefügt werden konnte. Sonst False.
	 */
	public boolean spracheHinzugfuegen(String ab, String a, String b) {

		for (Sprache s : sprachen) {
			if (s.getSpracheA().equalsIgnoreCase(a) && s.getSpracheB().equalsIgnoreCase(b)
					|| s.getSpracheA().equalsIgnoreCase(b) && s.getSpracheB().equalsIgnoreCase(a)
					|| s.getSprachPaar().equalsIgnoreCase(ab.substring(0, 2) + "-" + ab.substring(3, 5))
					|| s.getSprachPaar().equalsIgnoreCase(ab.substring(3, 5) + "-" + ab.substring(0, 2))) {
				return false;
			}

		}

		sprachen.add(new Sprache(ab, a, b));

		return true;
	}



	/**
	 * Durchsuchen der Sprachenliste nach dem entsprechenden Objekt der gewählten Sprachpaarung. Das Sprachobjekt wird dann als aktuelle Sprache in der Kartei gesetzt.
	 * 
	 * @param sprachpaar Sprachpaarung welche durchsucht werden soll.
	 * @return True wenn die Sprache gefunden wurde. False wenn die Sprachpaarung nicht existiert.
	 */
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
	
	
	
	/**
	 * Karte k1 wird zur Kartei hinzugefügt. Falls die Sprachpaarung schon exisistiert wird nichts gemacht.
	 * @param k1 Karte welche hinzugefügt werden soll.
	 */
	public void karteHinzufuegen(Karte k1) {
		for (Karte k2 : kartei) {
			if (k1.getWortA().equalsIgnoreCase(k2.getWortA()) && k2.getWortB().equalsIgnoreCase(k1.getWortB())) {
				return;
			}
		}

		kartei.add(k1);

	}

	
	/**
	 * Karte k wird aus der Kartei und aus dem aktuellen Fach entfernt.
	 * @param k Zu löschende Karte
	 */
	public void karteLoeschen(Karte k) {
		if (aktuellesFach > 0) {
			fach[aktuellesFach - 1].karteEnfernen(k);
			kartei.remove(k);
		}
	}

	public ArrayList<Sprache> getSprachen() {
		return sprachen;
	}

	public int getFachGroesse(int fachnummer) {
		int fg = fach[fachnummer].gibAnzahlKarten();
		return fg;
	}

	
	/**
	 * Cleanupfunktion.
	 * Vergleichen aller Benutzerstatus mit den bestehenden Karten in der Kartei.
	 * Falls die Karte nicht mehr existiert, können die Benutzerstatus ebenfalls gelöscht werden.
	 */
	public void statusBereinigen() {
		ArrayList<UUID> idListe = new ArrayList<UUID>();
		for (Karte k : kartei) {
			idListe.add(k.getId());

		}

		
		
		for (Benutzer b : benutzerListe) {
			
			ArrayList<KartenStatus> ksdelete = new ArrayList<KartenStatus>();
			
			if (b.getLernfortschritte() != null) {
				for (KartenStatus ks : b.getLernfortschritte()) {
					if (idListe.contains(ks.getUid())) {
						break;
					} else {
						ksdelete.add(ks);
					}

				}
			}
			
			for (KartenStatus ks : ksdelete) {
				b.getLernfortschritte().remove(ks);			
			}

		}
		

	}

	
	/**
	 * Löschen des Sprachpaars sp und aller zugehöriger Karten.
	 * @param sp Sprachpaar welches gelöscht werden soll in der Kurzform z.B. de-en
	 */
	public void sprachpaarLoeschen(String sp) {
		
		ArrayList<Karte> kartenL = new ArrayList<Karte>();
		
		for (Karte k : kartei) {
			if (k.getSprache().equalsIgnoreCase(sp)) {
				kartenL.add(k);
			}

		}
		
		for (Karte k : kartenL) {
			kartei.remove(k);
		}
		
		Sprache spracheL = null;
		
		for (Sprache s : sprachen) {
			if (s.getSprachPaar().equalsIgnoreCase(sp)) {
				spracheL = s;
			}
		}
		
		sprachen.remove(spracheL);
		
	}

	/**
	 * Gibt jeweils das ausgewählte Fach zurück. Korrektur mit -1 da Array mit 0 anfängt.
	 * @param x Nummer des gewählten Fachs
	 * @return Rückgabe des Fach x
	 */
	public Fach getFach(int x) {
		return fach[x - 1];
	}
	
	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
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
	
	public Locale getLocale() {
		return lokal;
	}

	public void setLocale(Locale locale) {
		this.lokal = locale;
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
	
	public ArrayList<Benutzer> getBenutzerListe() {
		return benutzerListe;
	}
	
}
