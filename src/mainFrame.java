import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainFrame {

	private JFrame mainFrame;
	
	private JButton home; 
	private JButton kartei1;
	private JButton kartei2;
	private JButton kartei3;
	private JButton kartei4;
	private JButton kartei5;
	
	
	private JLabel benutzer; 
	
	private JPanel karteiPanel; 
	private JPanel menuPanel;
	
	
	
	public mainFrame() {
		mainFrame = new JFrame("LeCard");
		kartei1 = new JButton("1"); 
		kartei2 = new JButton("2"); 
		kartei3 = new JButton("3"); 
		kartei4 = new JButton("4"); 
		kartei5 = new JButton("5"); 
		home = new JButton("Kartei"); 
		
		benutzer = new JLabel("Benutzer: "); 
		
		karteiPanel = new JPanel(); 
		menuPanel = new JPanel(); 
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent jc = (JComponent) mainFrame.getContentPane();
	}
	
	public void paint() {
		mainFrame.setSize(500, 500);
		// mainFrame.setLayout(new GridLayout(1,2));
		karteiPanel.setLayout(new GridLayout(6,1));
		menuPanel.setLayout(new GridLayout(1,3));
		//menuPanel.setLayout(new GridLayout(2,1));
		
		karteiPanel.add(home); 
		karteiPanel.add(kartei1); 
		karteiPanel.add(kartei2); 
		karteiPanel.add(kartei3); 
		karteiPanel.add(kartei4); 
		karteiPanel.add(kartei5); 
			
		mainFrame.add(karteiPanel, BorderLayout.WEST);
		
		
		
		
		menuPanel.add(benutzer);
		menuPanel.add(benutzer, BorderLayout.NORTH); 
		mainFrame.add(menuPanel);
		mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		mainFrame gui1 = new mainFrame();
		gui1.paint();
	}
}