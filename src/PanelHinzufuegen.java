import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelHinzufuegen {

	private JFrame mainFrame_1;
	private JPanel hinzufuegenPanelText;
	private JPanel hinzufuegenPanelButton; 
	private JLabel lSprache1;
	private JLabel lSprache2;
	private JTextField tSprache1;
	private JTextField tSprache2;
	private JButton hinzufuegenButton;
	
	

	public PanelHinzufuegen() {
		initComponents();
		bindListener();
		
	}

	private void initComponents() {
		mainFrame_1 = new JFrame(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen"));
		lSprache1 = new JLabel("Sprache 1");
		lSprache2 = new JLabel("Sprache 2");
		tSprache1 = new JTextField();
		tSprache2 = new JTextField();
		hinzufuegenPanelText = new JPanel();
		hinzufuegenPanelButton = new JPanel(); 
		hinzufuegenButton = new JButton(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("hinzufuegen")); 
	}

	private void bindListener() {
		hinzufuegenButton.addActionListener(new ButtonListenerHinzufuegen());
	}
	
	public void paint() {
		mainFrame_1.setSize(800, 450);
		hinzufuegenPanelText.setLayout(new GridLayout(2, 2));
		hinzufuegenPanelText.add(lSprache1);
		hinzufuegenPanelText.add(tSprache1);
		hinzufuegenPanelText.add(lSprache2);
		hinzufuegenPanelText.add(tSprache2);
		hinzufuegenPanelButton.add(hinzufuegenButton); 
		
		mainFrame_1.add(hinzufuegenPanelText, BorderLayout.CENTER);
		mainFrame_1.add(hinzufuegenPanelButton, BorderLayout.SOUTH); 
		
		mainFrame_1.setVisible(true);
	}
	
	class ButtonListenerHinzufuegen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Karte wurde in die Kartei 1 hinzugefügt");
	}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanelHinzufuegen h1 = new PanelHinzufuegen();
		h1.paint();

	}

}
