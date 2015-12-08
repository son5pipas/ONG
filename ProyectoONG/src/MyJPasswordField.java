import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPasswordField;

public class MyJPasswordField extends JPasswordField {
	private String placeholder = "";

	public void setPlaceHolder(String ph) {
		placeholder = ph;
	}

	public void paintComponent(Graphics g) {
		Color phColor = new Color(0, 0, 0);
		boolean band = true;
		super.paintComponent(g);
		// color de placeholder
		g.setColor(new Color(phColor.getRed(), phColor.getGreen(), phColor.getBlue(), 90));
		// dibuja texto
		g.drawString((band) ? placeholder : "", getMargin().left + 10, (getSize().height) / 2 + getFont().getSize()/ 2);
	}
}