import java.util.ArrayList;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 1.6
 * Datum:24.02.2018
 */
public class Fach {
	
	private int fachNummer;
	private ArrayList<Karte> karten;

	public Fach(int nr) {
		this.fachNummer = nr;
		this.karten = new ArrayList<Karte>();
	}

	public void karteHinzufuegen(Karte k) {
		karten.add(k);
	}
	
	public void karteEnfernen(Karte k) {
		karten.remove(k);
	}
	
	public ArrayList<Karte> gibKarten(){
		return karten;
	}
	
	@Override
	public String toString() {
		return "Fach [fachNummer=" + fachNummer + ", faecher=" + karten + "]";
	}
	
	public int gibAnzahlKarten() {
		return karten.size();
	}
	
}
