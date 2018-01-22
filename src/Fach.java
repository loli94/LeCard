import java.util.ArrayList;

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
	
}
