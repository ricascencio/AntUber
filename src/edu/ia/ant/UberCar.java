package edu.ia.ant;

import javax.imageio.ImageIO;

public class UberCar extends Car{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UberCar(int x, int y){
		super(x,y);
		try {
			image = ImageIO.read(this.getClass().getResource("/images/blackCar8.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
