package edu.ia.ant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ZombieHorde extends JComponent {
	private int x,y;
	protected BufferedImage image;
	
	ZombieHorde(int x, int y){
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(this.getClass().getResource("/images/zombies.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
