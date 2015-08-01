package edu.ia.ant;

import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("70 - 1 = " + Math.pow(70, -1.0));
		System.out.println("71 - 1 = " + Math.pow(71, -1.0));
		System.out.println("72 - 1 = " + Math.pow(72, -1.0));
		System.out.println("73 - 1 = " + Math.pow(73, -1.0));
		System.out.println("70 - .9 = " + Math.pow(70, 0.9));
		UberFrame myFrame = new UberFrame();
		myFrame.setVisible(true);
		JPanel panel = (JPanel) myFrame.getContentPane();
		//UberCar uber = new UberCar(95, 20);
		TaxiCar taxi = new TaxiCar(200, 100);
		panel.add(taxi);
		panel.validate();
		//panel.add(uber);
		panel.validate();
		//uber.turn(Car.Direction.BACK);
		int i =0;
		while(i<=10){
			//uber.move();
			i+=1;
			if(i== 7)
				//uber.turn(Car.Direction.LEFT);
			try{
                Thread.sleep(100);
            } catch (Exception exc){}
            myFrame.repaint();
		}
	}

}
