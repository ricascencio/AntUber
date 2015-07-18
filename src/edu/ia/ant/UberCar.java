package edu.ia.ant;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class UberCar extends JComponent{

	private BufferedImage image;
	public enum Direction {LEFT, RIGTH};
	private Direction turn;
	
	UberCar(){
		try {
			image = ImageIO.read(this.getClass().getResource("/images/blackCar8.png"));
			turn = Direction.RIGTH;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDirection(){
		this.turn = Direction.RIGTH;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(this.getX() + 11, this.getY() + 10);
		g2d.rotate(1.57); //1.57
		g2d.drawImage(image, 140, 0, null);
		//g.drawImage(image, 95, 20, null);
	}
	
	public void paintComponent(Graphics g){
		switch(turn){
			case LEFT:
				break;
			case RIGTH:
				Graphics2D g2d = (Graphics2D)g;
				g2d.translate(this.getX() + 11, this.getY() + 10);
				g2d.rotate(1.57); //1.57
				g2d.drawImage(image, 0, 0, null);
				break;
		}
		
		
		
	}
	
}
