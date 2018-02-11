
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadCsv {
	private File f;
	private String separator;
	private ArrayList<String> wortA, wortB, sprache;
	private Kartei kartei;

	public LoadCsv(String csvfile, String separator) throws FileNotFoundException, IOException {
		System.out.println(csvfile);
		System.out.println(separator);
		
		f = new File(csvfile);
		this.separator = separator;
		wortA = new ArrayList<>();
		wortB = new ArrayList<>();
		sprache= new ArrayList<>();
		kartei = Kartei.getInstance();

		readCsv();
		uploadData();
	}

	private void readCsv() throws IOException {
		// prüfen, ob Datei existiert
		if (f.exists() && f.isFile()) {
			FileInputStream in = null;
			in = new FileInputStream(f);
			Scanner s = new Scanner(in);

			try {
				

				// solange Zeilen in der Datei vorhanden sind
				while (s.hasNext()) {
					// Zeilen anhand des Separators aufsplitten
					String line = s.nextLine();
					if (line.endsWith(";")) {
				//	substring
					}
					String[] col = line.split(separator);
					saveInList(col);
				}
				// Falls kein File vorhanden
			} finally {
				if (s != null) {
					s.close();
				}

				if (in != null) {
					in.close();
				}
			}

		}

	}

	// speichert die Wörter ins Array
	private void saveInList(String[] col) {
		wortA.add(col[0]);
		wortB.add(col[1]);
		sprache.add(col[2]);
	}

	// übergibt die Wörter an die Logik
	
	public void uploadData() {
		for (int i = 0; i < wortA.size(); i++) {
			System.out.println("Karte hinzufuegen: " + sprache.get(i) + "; " + wortA.get(i) + "; " + wortB.get(i) );
			kartei.karteHinzufuegen(new Karte(sprache.get(i),wortA.get(i),wortB.get(i)));
		}
	
	}

	private void Kartei(ArrayList<String> wortA2, ArrayList<String> wortB2, ArrayList<String> sprache2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
