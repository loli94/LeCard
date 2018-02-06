package Import;


import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//zeigt einen Öffnen Dialog
public class opendialog
{
    private ArrayList<String> _extensiontext = new ArrayList<>();
    private ArrayList<String> _extension = new ArrayList<>();
 
    
     // initialisiert den Pfad mit null
    // 	return= ausgewähltes File
   
    public File showDialog(String initialpath)
    {
        if ((initialpath == null) || (initialpath.length() == 0))
        {
            initialpath = System.getProperty("user.home");
        }
 
        final JFileChooser c = new JFileChooser(initialpath);
         
        c.setDialogTitle("Select file");
        c.setDialogType(JFileChooser.OPEN_DIALOG); 
        c.setFileSelectionMode(JFileChooser.FILES_ONLY);
         
        // Filter hinzufügen
        for (int i = 0; i < _extensiontext.size(); i++)
        {
            FileNameExtensionFilter ff = new FileNameExtensionFilter(_extensiontext.get(i), _extension.get(i));
            c.setFileFilter(ff);
        }
 
        // zeigt den Dialog
        c.setVisible(true);
         
        File f = null;
         
        //Ok oder Ja gedrückt
        if (c.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            // gibt das selektierte File 
            f = c.getSelectedFile();
        }
         
        // schliesst den Dialog
        c.setVisible(false);
 
        return f;
    }
 
    	//Fügt der Datei eine Formatierung zu
    	//text= Dateiname
    	//ext= Dateiendung
    
    public void addExtension(String text, String ext)
    {
        _extensiontext.add(text);
        _extension.addAll(_extension);
    }
    
}