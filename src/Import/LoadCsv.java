package Import;

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

	public LoadCsv(String csvfile, String separator) throws FileNotFoundException, IOException {
		f = new File(csvfile);
		this.separator = separator;
		wortA = new ArrayList<>();
		wortB = new ArrayList<>();
		sprache= new ArrayList<>();

		readCsv();
		uploadData();
	}

	private void readCsv() throws IOException {
		// prüfen, ob Datei existiert
		if (f.exists() && f.isFile()) {
			FileInputStream in = null;
			Scanner s = new Scanner(in);

			try {
				in = new FileInputStream(f);

				// solange Zeilen in der Datei vorhanden sind
				while (s.hasNext()) {
					// Zeilen anhand des Separators aufsplitten
					String line = s.nextLine();
					String[] col = line.split(separator);
					saveInList(col);
				}
				// Falls kein File vorhanden
			} catch (FileNotFoundException e) {
				System.out.println("File nicht gefunden");
				e.printStackTrace();
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
	
	private void uploadData() {
		Kartei.(wortA, wortB, sprache);
	}
}