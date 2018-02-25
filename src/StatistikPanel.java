import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatistikPanel extends JPanel {
	private int Kart1_X = 100;
	private int Kart1_Y = 20;
	private int Kart1_WIDTH = 100;
	private int Kart1_HEIGHT = 50;
	private int Kart2_X = 100;
	private int kart2_Y = 80;
	private int kart2_WIDTH = 70;
	private int kart2_Height = 50;
	private int kart3_X = 100;
	private int kart3_Y = 140;
	private int kart3_WIDTH = 150;
	private int kart3_Height = 50;
	private int kart4_X = 100;
	private int kart4_Y = 200;
	private int kart4_WIDTH = 30;
	private int kart4_Height = 50;
	private int kart5_X = 100;
	private int kart5_Y = 260;
	private int kart5_WIDTH = 250;
	private int kart5_Height = 50;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillRect(Kart1_X, Kart1_Y, Kart1_WIDTH, Kart1_HEIGHT);
		g.drawString("Kartei 1", 10, 50);

		g.setColor(Color.RED);
		g.fillRect(Kart2_X, kart2_Y, kart2_WIDTH, kart2_Height);
		g.drawString("Kartei 2", 10, 110);

		g.setColor(Color.CYAN);
		g.fillRect(kart3_X, kart3_Y, kart3_WIDTH, kart3_Height);
		g.drawString("Kartei 3", 10, 175);

		g.setColor(Color.GREEN);
		g.fillRect(kart4_X, kart4_Y, kart4_WIDTH, kart4_Height);
		g.drawString("Kartei 4", 10, 225);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(kart5_X, kart5_Y, kart5_WIDTH, kart5_Height);
		g.drawString("Kartei 5", 10, 285);
	}
	
	//Getter und setter

}
