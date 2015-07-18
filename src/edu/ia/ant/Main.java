package edu.ia.ant;

import javax.swing.JPanel;

import edu.ia.ant.UberFrame.City;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UberFrame myFrame = new UberFrame();
		myFrame.setVisible(true);
		JPanel panel = (JPanel) myFrame.getContentPane();
		UberCar uber = new UberCar();
		TaxiCar taxi = new TaxiCar();
		panel.add(taxi);
		panel.revalidate();
		panel.repaint();
		panel.add(uber);
		
		//uber.setDirection();
		uber.repaint();
		panel.revalidate();
		panel.repaint();
	}

}
