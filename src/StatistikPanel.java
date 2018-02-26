import java.awt.Color;
import java.awt.Graphics;
import java.util.ResourceBundle;

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
		g.drawString(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Kartei1"), 10, 50);

		g.setColor(Color.RED);
		g.fillRect(Kart2_X, kart2_Y, kart2_WIDTH, kart2_Height);
		g.drawString(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Kartei2"), 10, 110);

		g.setColor(Color.CYAN);
		g.fillRect(kart3_X, kart3_Y, kart3_WIDTH, kart3_Height);
		g.drawString(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Kartei3"), 10, 175);

		g.setColor(Color.GREEN);
		g.fillRect(kart4_X, kart4_Y, kart4_WIDTH, kart4_Height);
		g.drawString(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Kartei4"), 10, 225);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(kart5_X, kart5_Y, kart5_WIDTH, kart5_Height);
		g.drawString(ResourceBundle.getBundle("Bundle", Hauptfenster.locale).getString("Kartei5"), 10, 285);
	}

	public int getKart1_WIDTH() {
		return Kart1_WIDTH;
	}

	public void setKart1_WIDTH(int kart1_WIDTH) {
		Kart1_WIDTH = kart1_WIDTH * 10;
	}

	public int getKart2_WIDTH() {
		return kart2_WIDTH;
	}

	public void setKart2_WIDTH(int kart2_WIDTH) {
		this.kart2_WIDTH = kart2_WIDTH * 10;
	}

	public int getKart3_WIDTH() {
		return kart3_WIDTH;
	}

	public void setKart3_WIDTH(int kart3_WIDTH) {
		this.kart3_WIDTH = kart3_WIDTH * 10;
	}

	public int getKart4_WIDTH() {
		return kart4_WIDTH;
	}

	public void setKart4_WIDTH(int kart4_WIDTH) {
		this.kart4_WIDTH = kart4_WIDTH * 10;
	}

	public int getKart5_WIDTH() {
		return kart5_WIDTH;
	}

	public void setKart5_WIDTH(int kart5_WIDTH) {
		this.kart5_WIDTH = kart5_WIDTH * 10;
	}

}
