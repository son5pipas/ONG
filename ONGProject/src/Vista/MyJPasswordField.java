package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPasswordField;

public class MyJPasswordField extends JPasswordField implements KeyListener, FocusListener {

	private String placeholder = "", placeholder2 = "";

	public MyJPasswordField (){
		this.addKeyListener(this);
		this.addFocusListener(this);
	}
	
	public void setPlaceHolder(String ph) {
		placeholder = ph;
		if (placeholder2=="")
			placeholder2 = ph;
	}

	public void paintComponent(Graphics g) {
		Color phColor = new Color(0, 0, 0);
		boolean band = true;
		super.paintComponent(g);
		// color de placeholder
		g.setColor(new Color(phColor.getRed(), phColor.getGreen(), phColor.getBlue(), 90));
		// dibuja texto
		g.drawString((band) ? placeholder : "", getMargin().left + 10,
				(getSize().height) / 2 + getFont().getSize() / 2);
	}
	
	public void clear(){
		this.setText("");
		repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!this.getText().isEmpty()){
			setPlaceHolder("");
		}else{
			setPlaceHolder(placeholder2);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!this.getText().isEmpty()){
			setPlaceHolder("");
		}else{
			setPlaceHolder(placeholder2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!this.getText().isEmpty()){
			setPlaceHolder("");
		}else{
			setPlaceHolder(placeholder2);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		setPlaceHolder("");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (!this.getText().isEmpty()){
			setPlaceHolder("");
		}else{
			setPlaceHolder(placeholder2);
			this.updateUI();
			
		}
	}	
}