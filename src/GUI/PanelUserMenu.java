package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Logik.Kartei;
import Logik.Sprache;

public class PanelUserMenu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String>  lernSprachenMenu;
	private JLabel lBenutzer, lLernSprache;

	public PanelUserMenu() {
		initComponents();
		bindListener();
		paint();
	}
	
	private void initComponents() {
		
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Benutzer") + ": "+ Kartei.getInstance().getBenutzer().getBenutzername());
		lLernSprache = new JLabel(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Lernsprache") + ": ");
		lLernSprache.setHorizontalAlignment(JTextField.RIGHT);
		
		lernSprachenMenu = new JComboBox<String>();
		for(Sprache s : Kartei.getInstance().getSprachen()) {
			lernSprachenMenu.addItem(s.getSprachPaar().toUpperCase());
		}
		lernSprachenMenu.setSelectedItem(Kartei.getInstance().getAktuellesSprachpaar().toUpperCase());
	}

	public void bindListener() {
		lernSprachenMenu.addActionListener(new DropDownListenerLernSprache());
	}

	public void paint() {
		
		Border border = this.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		
		
		this.setLayout(new GridLayout(1, 2));
		this.setBorder(new CompoundBorder(border, margin));
				
		this.add(lBenutzer);		
		this.add(lLernSprache);
		this.add(lernSprachenMenu);

	}
	
	public void spracheWechseln() {
		lBenutzer.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Benutzer") + ": "+ Kartei.getInstance().getBenutzer().getBenutzername());
		lLernSprache.setText(ResourceBundle.getBundle("Bundle", Kartei.getInstance().getLocale()).getString("Lernsprache") + ": ");
	}
	
		
	public JComboBox<String> getLernSprachenMenu() {
		return lernSprachenMenu;
	}





	class DropDownListenerLernSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String selection = (String) lernSprachenMenu.getSelectedItem();
			Kartei.getInstance().spracheWaehlen(selection);
			Kartei.getInstance().faecherBefuellen();
			
			if (Kartei.getInstance().getAktuellesFach() > 0) {
				Hauptfenster.getInstance().paintPanelLernen();
				Hauptfenster.getInstance().getPanelLernen().spracheWechseln();
			}
			if (Kartei.getInstance().getAktuellesFach() == 0) {
				Hauptfenster.getInstance().paintPanelStat();
			}
		}

	}

}
