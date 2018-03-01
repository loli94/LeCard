import java.awt.FileDialog;
import java.io.IOException;

import javax.swing.JFrame;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6
 * Datum:24.02.2018
 */
public class PanelImport {

	public PanelImport() {
		this.importFile();
	}
	
	public void importFile() {
		FileDialog fd = new FileDialog(new JFrame(), "File auswählen", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.csv"); 
		fd.setVisible(true);
		String filename = fd.getDirectory() + fd.getFile();
		if (filename != null) {
			try {
				LoadCsv loader = new LoadCsv(filename, ";");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
