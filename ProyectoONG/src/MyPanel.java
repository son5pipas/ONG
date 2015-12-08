import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
        BufferedImage image = null;
        int x = 0;
        int y = 0;
        int weight = 0;
        int heigh = 0;
        String path = null;

        public void pintar (String paths, int xs, int ys, int weights, int heighs){
                x=xs;
                y=ys;
                weight=weights;
                heigh=heighs;
                path=paths;

                try {
                        image = ImageIO.read(new File(path));
                } catch (IOException e) {
                  
                        e.printStackTrace();
                }
        }

        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
        g.drawImage(image, x, y,weight, heigh, null); // see javadoc for more info on the parameters
    }

}
