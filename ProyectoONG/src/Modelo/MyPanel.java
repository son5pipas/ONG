package Modelo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

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
		}
	}
}