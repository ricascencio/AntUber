package edu.ia.ant;

import javax.imageio.ImageIO;

public class TaxiCar extends Car{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TaxiCar(int x, int y){
		super(x, y);
		try {
			image = ImageIO.read(this.getClass().getResource("/images/taxiCar8.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
