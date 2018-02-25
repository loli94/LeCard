import java.awt.FileDialog;
import java.io.IOException;

import javax.swing.JFrame;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6
 * Datum:24.02.2018
 */
public class panelImport {

	public panelImport() {
		this.paint();
	}
	
	public void paint() {
		FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.csv");
		fd.setVisible(true);
		String filename = fd.getDirectory() + fd.getFile();
		if (filename == null)
		  System.out.println("Abbruch");
		else {
			this.importFile(filename);
		}
	}
	
	public void importFile(String filename) {
		try {
			LoadCsv loader = new LoadCsv(filename, ";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
