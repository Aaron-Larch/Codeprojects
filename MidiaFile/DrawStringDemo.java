package MidiaFile;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DrawStringDemo extends JComponent {
	private static final long serialVersionUID = -7199469682507443122L;
	int textX = 10, textY = 20;
	@Override
	public void paintComponent(Graphics g) {
		g.drawString("Hello Java", textX, textY);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}
