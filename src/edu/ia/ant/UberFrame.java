package edu.ia.ant;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		JPanel panel = (JPanel) getContentPane();
		popUpMenu = new JPopupMenu();
		popUpMenu.setSize(100, 250);
		addMenu = new JMenu("Add");
		JMenuItem taxiMenu = new JMenuItem("Taxi", new ImageIcon(this.getClass().getResource("/images/taxiCar8.png")));
		taxiMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				
			}
			
		});
		JMenuItem carMenu = new JMenuItem("Car", new ImageIcon(this.getClass().getResource("/images/blueCar8.png")));
		carMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				
			}
			
		});
		addMenu.add(taxiMenu);
		addMenu.add(carMenu);
		addMenu.add(new JMenuItem("Zombies", new ImageIcon(this.getClass().getResource("/images/zombies.jpg"))));
		popUpMenu.add(addMenu);
		popUpMenu.add(new JMenuItem(".. to here"));
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
				popUpMenu.show(e.getComponent(), e.getX(), e.getY());
				popUpMenu.setVisible(true);
			}
		});
	}
	
	

}
