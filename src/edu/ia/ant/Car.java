package edu.ia.ant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import edu.ia.ant.utils.ImageUtils;

public class Car extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static class Compass{
		private Orientation current;
		private Orientation left;
		private Orientation rigth;
		private Orientation back;
		
		Compass(Orientation current, Orientation left, Orientation rigth, Orientation back){
			this.current = current;
			this.left = left;
			this.rigth = rigth;
			this.back = back;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((current == null) ? 0 : current.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Compass other = (Compass) obj;
			if (current != other.current)
				return false;
			return true;
		}
	}
	protected BufferedImage image;
	private int x, y;
	public enum Direction {LEFT, RIGTH, BACK};
	protected enum Orientation {NORTH, EAST, SOUTH, WEST};
	protected Orientation orientation;
	private static Map<Orientation, Compass> orientations;
	
	static{
		Compass north = new Compass(Orientation.NORTH, Orientation.WEST, Orientation.EAST, Orientation.SOUTH);
		Compass east = new Compass(Orientation.EAST, Orientation.NORTH, Orientation.SOUTH, Orientation.WEST);
		Compass south = new Compass(Orientation.SOUTH, Orientation.EAST, Orientation.WEST, Orientation.NORTH);
		Compass west = new Compass(Orientation.WEST, Orientation.SOUTH, Orientation.NORTH, Orientation.EAST);
		orientations = new HashMap<Orientation, Compass>();
		orientations.put(Orientation.NORTH, north);
		orientations.put(Orientation.EAST, east);
		orientations.put(Orientation.SOUTH, south);
		orientations.put(Orientation.WEST, west);
	}
	
	protected Car(int x, int y){
		orientation = Orientation.NORTH;
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(this.getClass().getResource("/images/redCar8.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public void turn(Direction dir){
		switch(dir){
			case LEFT:
				image = (BufferedImage) ImageUtils.rotateImage(image, 270);
				orientation = orientations.get(orientation).left;
				break;
			case RIGTH:
				image = (BufferedImage) ImageUtils.rotateImage(image, 90);
				orientation = orientations.get(orientation).rigth;
				break;
			case BACK:
				image = (BufferedImage) ImageUtils.rotateImage(image, 180);
				orientation = orientations.get(orientation).back;
				break;
		}
	}
	
	public void move(){
		switch(orientation){
		case NORTH:
			y = y - 10;
			break;
		case EAST:
			x = x + 10;
			break;
		case SOUTH:
			y = y + 10;
			break;
		case WEST:
			x = x - 10;
			break;
		}
	}
}
