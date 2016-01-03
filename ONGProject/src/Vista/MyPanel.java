package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	int avance = -1;
	
	BufferedImage imagen = null;
	ArrayList<BufferedImage> image = new ArrayList<BufferedImage>();;
	ArrayList<Imagen> images = new ArrayList<Imagen>();;

	public void pintar(String paths, int xs, int ys, int weights, int heighs) {
		Imagen foto = new Imagen(paths, xs, ys, weights, heighs);
		images.add(foto);

		try {
			BufferedImage imagen = ImageIO.read(new File(images.get(0).getPath()));
			image = new ArrayList<BufferedImage>();
			image.add(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pintar(String paths, int xs, int ys, int weights, int heighs, boolean border) {
		Imagen foto = new Imagen(paths, xs, ys, weights, heighs);
		foto.setBorder(border);
		images.add(foto);

		try {
			BufferedImage imagen = ImageIO.read(new File(images.get(0).getPath()));
			image = new ArrayList<BufferedImage>();
			image.add(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pintar(String paths, int xs, int ys, int weights, int heighs, boolean border, int avanceUser) {
		avance = avanceUser;
		Imagen foto = new Imagen(paths, xs, ys, weights, heighs);
		foto.setBorder(border);
		images.add(foto);

		try {
			BufferedImage imagen = ImageIO.read(new File(images.get(0).getPath()));
			image = new ArrayList<BufferedImage>();
			image.add(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pintar(Imagen foto) {
		images.add(foto);

		try {
			BufferedImage imagen = ImageIO.read(new File(images.get(0).getPath()));
			image = new ArrayList<BufferedImage>();
			image.add(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pintar(ArrayList<Imagen> fotos) {
		images = fotos;

		for (int i = 0; i < fotos.size(); i++) {
			try {
				BufferedImage imagen = ImageIO.read(new File(images.get(i).getPath()));
				image.add(imagen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < images.size(); i++) {
			BufferedImage img = image.get(i);
			g.drawImage(img, images.get(i).getX(), images.get(i).getY(), images.get(i).getWeight(),
					images.get(i).getHeigh(), null); // see javadoc for more
														// info on the
														// parameters
			if (images.get(i).isBorder() == true){
				paintLine (g);
			}
			if (avance != -1){
				pintarRectangulo(g);
			}
		}
	}
		
	private void paintLine (Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < images.size(); i++) {
			g2.draw(new Line2D.Float(images.get(i).getX(), images.get(i).getY(), images.get(i).getX() + images.get(i).getWeight(), images.get(i).getY()));
			g2.draw(new Line2D.Float(images.get(i).getX(), images.get(i).getY(), images.get(i).getX() , images.get(i).getY() + images.get(i).getHeigh()));
			g2.draw(new Line2D.Float(images.get(i).getX() + images.get(i).getWeight(), images.get(i).getY(), images.get(i).getX() + images.get(i).getWeight(), images.get(i).getY() + images.get(i).getHeigh()));
			g2.draw(new Line2D.Float(images.get(i).getX(), images.get(i).getY() + images.get(i).getHeigh(), images.get(i).getX() + images.get(i).getWeight(), images.get(i).getY() + images.get(i).getHeigh()));
		}
	}
	
	public void pintarRectangulo (Graphics g){
		if(avance <20 || avance >80){
			g.setColor(new Color(255-((255/100)*avance), ((255/100)*avance), 0));
		}else{
			g.setColor(new Color(255, ((255/100)*avance), 0));
		}
		g.fillRoundRect(270, 141, 3*avance, 48, 15, 15);
		g.setColor(Color.BLACK);
		g.drawRoundRect(270,140,300,50,25,25);		
	}

}