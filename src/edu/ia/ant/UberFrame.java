package edu.ia.ant;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
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
	private int srcX, srcY, desX, desY;
	private Map<String, Integer> nodes;
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
		nodes = new HashMap<String, Integer>();
		nodes.put("0,0", 0);
		nodes.put("1,0", 1);
		nodes.put("2,0", 2);
		nodes.put("3,0", 3);
		nodes.put("4,0", 4);
		nodes.put("5,0", 5);
		nodes.put("6,0", 6);
		nodes.put("7,0", 7);
		nodes.put("8,0", 8);
		nodes.put("9,0", 9);
		nodes.put("0,1", 10);
		nodes.put("1,1", 11);
		nodes.put("2,1", 12);
		nodes.put("3,1", 13);
		nodes.put("4,1", 14);
		nodes.put("5,1", 15);
		nodes.put("6,1", 16);
		nodes.put("7,1", 17);
		nodes.put("8,1", 18);
		nodes.put("9,1", 19);
		nodes.put("0,2", 20);
		nodes.put("1,2", 21);
		nodes.put("2,2", 22);
		nodes.put("3,2", 23);
		nodes.put("4,2", 24);
		nodes.put("5,2", 25);
		nodes.put("6,2", 26);
		nodes.put("7,2", 27);
		nodes.put("8,2", 28);
		nodes.put("9,2", 29);
		nodes.put("0,3", 30);
		nodes.put("1,3", 31);
		nodes.put("2,3", 31);
		nodes.put("3,3", 32);
		nodes.put("4,3", 33);
		nodes.put("5,3", 34);
		nodes.put("6,3", 34);
		nodes.put("7,3", 35);
		nodes.put("8,3", 36);
		nodes.put("9,3", 37);
		nodes.put("0,4", 38);
		nodes.put("1,4", 39);
		nodes.put("2,4", 39);
		nodes.put("3,4", 40);
		nodes.put("4,4", 41);
		nodes.put("5,4", 42);
		nodes.put("6,4", 43);
		nodes.put("7,4", 44);
		nodes.put("8,4", 45);
		nodes.put("9,4", 46);
		nodes.put("0,5", 47);
		nodes.put("1,5", 48);
		nodes.put("2,5", 49);
		nodes.put("3,5", 50);
		nodes.put("4,5", 51);
		nodes.put("5,5", 52);
		nodes.put("6,5", 53);
		nodes.put("7,5", 54);
		nodes.put("8,5", 55);
		nodes.put("9,5", 56);
		nodes.put("0,6", 57);
		nodes.put("1,6", 58);
		nodes.put("2,6", 59);
		nodes.put("3,6", 60);
		nodes.put("4,6", 61);
		nodes.put("5,6", 62);
		nodes.put("6,6", 63);
		nodes.put("7,6", 64);
		nodes.put("8,6", 65);
		nodes.put("9,6", 66);
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
				setElementOnNode(mouseX, mouseY, AntColony.Factor.TAXI);
				panel.add(taxi);
				panel.validate();
			}
			
		});
		JMenuItem carMenu = new JMenuItem("Car", new ImageIcon(this.getClass().getResource("/images/redCar8.png")));
		carMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Car blueCar = new Car(mouseX, mouseY);
				setElementOnNode(mouseX, mouseY, AntColony.Factor.CAR);
				panel.add(blueCar);
				panel.validate();
			}
			
		});
		JMenuItem zombiesMenu = new JMenuItem("Zombies");
		zombiesMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ZombieHorde zombieHorde = new ZombieHorde(mouseX, mouseY);
				setElementOnNode(mouseX, mouseY, AntColony.Factor.ZOMBIES);
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
				int node [] = findNode(mouseX, mouseY);
				srcX = node[0];
				srcY = node[1];
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
				int node [] = findNode(mouseX, mouseY);
				desX = node[0];
				desY = node[1];
				System.out.println("SOURCE " + srcX + " - " + srcY);
				System.out.println("DESTINATION " + desX + " - " + desY);
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
	
	private int[] findNode(int mouseX, int mouseY){
		int [] res = new int[2]; 
		res[0] = mouseX < 40 ? 0 : 1 + (mouseX - 40) / 110;
		res[1] = mouseY < 42 ? 0 : 1 + (mouseY - 42) / 110;
		System.out.println("X " + res[0]);
		System.out.println("Y " + res[1]);
		return res;
	}
	
	private void setElementOnNode(int mouseX, int mouseY, AntColony.Factor factor){
		int node [] = findNode(mouseX, mouseY);
		antCol.updateNears(node[0], node[1], factor);
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
	    while(i<=1000){
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
