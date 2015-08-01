package edu.ia.ant;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


public class UberFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPopupMenu popUpMenu;
	private JMenu addMenu;
	private int mouseX, mouseY;
	private UberCar uber;
	private AntColony antCol;

	class City extends JComponent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		

		public void paint(Graphics g) {
			g.drawRect(10, 10, 70, 75);
			g.drawRect(120, 10, 70, 75);
			g.drawRect(230, 10, 70, 75);
			g.drawRect(340, 10, 70, 75);
			g.drawRect(450, 10, 70, 75);
			g.drawRect(560, 10, 70, 75);
			g.drawRect(670, 10, 70, 75);
			g.drawRect(780, 10, 70, 75);
			g.drawRect(890, 10, 70, 75);
			
			g.drawRect(10, 120, 70, 75);
			g.drawRect(120, 120, 70, 75);
			g.drawRect(230, 120, 70, 75);
			g.drawRect(340, 120, 70, 75);
			g.drawRect(450, 120, 70, 75);
			g.drawRect(560, 120, 70, 75);
			g.drawRect(670, 120, 70, 75);
			g.drawRect(780, 120, 70, 75);
			g.drawRect(890, 120, 70, 75);
			
			g.drawRect(10, 230, 70, 75);
			g.drawRect(120, 230, 180, 75);
			g.drawRect(340, 230, 70, 75);
			g.drawRect(450, 230, 70, 75);
			g.drawRect(560, 230, 180, 185);
			g.drawRect(780, 230, 70, 75);
			g.drawRect(890, 230, 70, 75);
			
			g.drawRect(10, 340, 70, 75);
			g.drawRect(120, 340, 180, 75);
			g.drawRect(340, 340, 70, 75);
			g.drawRect(450, 340, 70, 75);
			g.drawRect(780, 340, 70, 75);
			g.drawRect(890, 340, 70, 75);
			
			g.drawRect(10, 450, 70, 75);
			g.drawRect(120, 450, 180, 75);
			g.drawRect(340, 450, 70, 75);
			g.drawRect(450, 450, 70, 185);
			g.drawRect(560, 450, 70, 75);
			g.drawRect(670, 450, 70, 75);
			g.drawRect(780, 450, 70, 75);
			g.drawRect(890, 450, 70, 75);
			
			g.drawRect(10, 560, 70, 75);
			g.drawRect(120, 560, 70, 75);
			g.drawRect(230, 560, 70, 75);
			g.drawRect(340, 560, 70, 75);
			g.drawRect(560, 560, 70, 75);
			g.drawRect(670, 560, 70, 75);
			g.drawRect(780, 560, 70, 75);
			g.drawRect(890, 560, 70, 75);
			
		}
	}

	UberFrame() {
		setTitle("Uber environment");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		final JPanel panel = (JPanel) getContentPane();
		popUpMenu = new JPopupMenu();
		popUpMenu.setSize(100, 250);
		addMenu = new JMenu("Add");
		JMenuItem taxiMenu = new JMenuItem("Taxi", new ImageIcon(this.getClass().getResource("/images/taxiCar8.png")));
		taxiMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TaxiCar taxi = new TaxiCar(mouseX, mouseY);
				int nodeX = mouseX < 40 ? 0 : 1 + (mouseX - 40) / 110;
				int nodeY = mouseY < 42 ? 0 : 1 + (mouseY - 42) / 110;
				antCol.updateNears(nodeX, nodeY, AntColony.Factor.TAXI);
				panel.add(taxi);
				panel.validate();
			}
			
		});
		JMenuItem carMenu = new JMenuItem("Car", new ImageIcon(this.getClass().getResource("/images/redCar8.png")));
		carMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Car blueCar = new Car(mouseX, mouseY);
				panel.add(blueCar);
				panel.validate();
			}
			
		});
		JMenuItem zombiesMenu = new JMenuItem("Zombies");
		zombiesMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ZombieHorde zombieHorde = new ZombieHorde(mouseX, mouseY);
				panel.add(zombieHorde);
				panel.validate();
			}
			
		});
		addMenu.add(taxiMenu);
		addMenu.add(carMenu);
		addMenu.add(zombiesMenu);
		popUpMenu.add(addMenu);
		JMenuItem fromItem = new JMenuItem(".. from here");
		fromItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uber != null)
					panel.remove(uber);
				uber = new UberCar(mouseX, mouseY);
				panel.add(uber);
				panel.validate();
				TSP tsp = new TSP();
				initAnts(30, 1, tsp, new Random(1));
			}
		});
		JMenuItem toItem = new JMenuItem(".. to here");
		toItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				runAnts();
			}
		});
		popUpMenu.add(fromItem);
		popUpMenu.add(toItem);
		panel.add(new City());
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				showPopUp(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			private void showPopUp(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY() - 25;
				popUpMenu.show(e.getComponent(), mouseX, mouseY);
				popUpMenu.setVisible(true);
			}
		});
	}
	
	/*------------------------------------------------------------------*/

	public void initAnts (int antcnt, double phero, TSP tsp, Random rand)
	{                             /* --- initialize an ant colony */
		this.antCol = new AntColony(tsp, antcnt, rand);
		this.antCol.init(phero);      /* create and init. an ant colony */
		this.repaint();             /* and redraw the TSP */
	}  /* initAnts() */

  /*------------------------------------------------------------------*/

	public void setParams (double exploit, double alpha, double beta,
                         double trail, double elite, double evap)
	{                             /* --- set parameters */
	    if (this.antCol == null) return;
	    this.antCol.setExploit(exploit);
	    this.antCol.setAlpha(alpha);
	    this.antCol.setBeta(beta);
	    this.antCol.setTrail(trail);
	    this.antCol.setElite(elite);
	    this.antCol.setEvap(evap);
	}  /* setParams() */
  
	public void runAnts ()
	{                             /* --- run the ants */
	    if (this.antCol == null) return;
	    int i=0;
	    while(i<=100){
	    	i+=1;
	    	this.antCol.runAllAnts();     /* run all ants once */
	    }
	    this.repaint(); 
	    /* and redraw the TSP */
	    int[]  best = this.antCol.getBestTour();
	    for(int j=0; j<best.length;j++){
	    	System.out.print(best[j]+",");
	    }
	    
	}  /* runAnts() */

}
