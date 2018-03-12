package GUI;
import java.awt.FileDialog;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import Logik.Kartei;
import Logik.LoadCsv;

/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.6
 * Datum:24.02.2018
 */
public class PanelImport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelImport() {
		this.importFile();
	}

	/*
	 * Das Fenster für den Import wird definiert. Es kann nur ein csv ausgewählt werden. Dies importiert danach das CSV und wird der jeweiligen Kartei hinzugefügt
	 * */
	public void importFile() {
		FileDialog fd = new FileDialog(this, ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("dateiauswählen"), FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.csv");
		fd.setVisible(true);
		String filename = fd.getDirectory() + fd.getFile();
		if (filename != null) {
			try {
				LoadCsv loader = new LoadCsv(filename, ";");
				loader.uploadData();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
