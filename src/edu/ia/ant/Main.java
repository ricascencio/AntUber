package edu.ia.ant;

import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UberFrame myFrame = new UberFrame();
		myFrame.setVisible(true);
		JPanel panel = (JPanel) myFrame.getContentPane();
		UberCar uber = new UberCar(95, 20);
		TaxiCar taxi = new TaxiCar(200, 100);
		panel.add(taxi);
		panel.validate();
		panel.add(uber);
		panel.validate();
		uber.turn(Car.Direction.BACK);
		uber.move();
		uber.move();
		uber.move();
		uber.move();
		uber.move();
		uber.move();
		uber.move();
		uber.turn(Car.Direction.LEFT);
		uber.move();
		uber.move();
		uber.move();
		panel.validate();
	}

}
