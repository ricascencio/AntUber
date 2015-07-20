package edu.ia.ant;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UberFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		panel.add(new City());
	}

}
