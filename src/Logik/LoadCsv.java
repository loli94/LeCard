package Logik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.3
 * Datum:24.02.2018
 */
public class LoadCsv {
	private File f;
	private String separator;
	private ArrayList<String> wortA, wortB, sprache;

	public LoadCsv(String csvfile, String separator) throws FileNotFoundException, IOException {
		System.out.println(csvfile);
		System.out.println(separator);
		
		f = new File(csvfile);
		this.separator = separator;
		wortA = new ArrayList<>();
		wortB = new ArrayList<>(); 
		sprache= new ArrayList<>();

		readCsv();
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
		System.out.println(wortA);
		wortB.add(col[1]);
		System.out.println(wortB);
		sprache.add(col[2]);
		System.out.println(sprache);
	}

	// übergibt die Wörter an die Kartei
	
	public void uploadData() {
		for (int i = 0; i < wortA.size(); i++) {
			System.out.println("Karte hinzufuegen: " + sprache.get(i) + "; " + wortA.get(i) + "; " + wortB.get(i) );
			Kartei.getInstance().karteHinzufuegen(new Karte(sprache.get(i),wortA.get(i),wortB.get(i)));
		}
	
	}

	
	
}
