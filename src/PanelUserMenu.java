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
		
		lBenutzer = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Benutzer") + ": "+ Main.daten1.getBenutzer().getBenutzername());
		lLernSprache = new JLabel(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Lernsprache") + ": ");
		lLernSprache.setHorizontalAlignment(JTextField.RIGHT);
		
		lernSprachenMenu = new JComboBox<String>();
		for(Sprache s : Main.daten1.getSprachen()) {
			System.out.println(s);
			lernSprachenMenu.addItem(s.getSprachPaar());
		}
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
		lBenutzer.setText(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Benutzer"));
		lLernSprache.setText(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Lernsprache"));
	}

	
	class DropDownListenerLernSprache implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			String selection = (String) cb.getSelectedItem();
			Main.daten1.spracheWaehlen(selection);
		}

	}

}
