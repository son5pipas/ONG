package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.UserController;
import Modelo.Proposal;

public class MyPanelProposal extends JPanel implements ActionListener {
		
	BufferedImage imagen = null;
	Imagen foto = null;
	JButton abrir;
	int id = -1;
	UserController uC;
	public final static String ABRIR = "OPEN";


	public MyPanelProposal (Proposal propuesta, String user, UserController Uc){
		super();
		
		id = propuesta.getID();
		uC = Uc;
		
		this.setLayout(null);
		
		JLabel titulo = new JLabel(propuesta.getTitle());
		titulo.setFont(new Font(titulo.getText(), Font.BOLD, 30));
		titulo.setBounds(10, 5, 230, 90);
		titulo.setHorizontalAlignment(JLabel.CENTER);
				
		foto = new Imagen(propuesta.getPhoto(), -100, 100, 450, 200);
		
		JLabel votos = new JLabel();
		votos.setText("Votos: " + (propuesta.getIdVotes().size()-1));
		votos.setBounds(10, 320, 230, 30);

		JLabel usuario = new JLabel();
		usuario.setText("Usuario: " + user);
		usuario.setBounds(10, 350, 230, 30);
		
		abrir = new JButton();
		abrir.setText("Abrir");
		abrir.setBounds(20,	380, 100, 30);
		abrir.addActionListener(this);
		abrir.setActionCommand(MyPanelProposal.ABRIR);
		this.add(abrir);
		
		this.add(titulo);
		this.add(votos);
		this.add(usuario);
		pintar (foto.getPath());
	}
	
	public void pintar(String paths) {
		try {
			imagen = ImageIO.read(new File(paths));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = imagen;
		g.drawImage(img, foto.getX(), foto.getY(), foto.getWeight(),
				foto.getHeigh(), null);
		
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
		g2.draw(new Line2D.Float(0, 0, 250, 0));
		g2.draw(new Line2D.Float(0, 0, 0, 450));
		g2.draw(new Line2D.Float(250, 0, 250, 450));
		g2.draw(new Line2D.Float(0, 450, 250, 450));	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String current = (String) (e.getActionCommand());
		if (current.equals(MyPanelProposal.ABRIR)){
			System.out.println(this.id);
			ProposalWindow ventanaProp = new ProposalWindow();
			uC.getMainController().getProposalController().addProposalWindow(ventanaProp);
			ventanaProp.addProposalController(uC.getMainController().getProposalController());
			uC.getMainController().getProposalController().abrirPropuestaX(uC.getMainController().getProposalController().searchProposal(this.id));
			uC.getVentanaUser().dispose();
		}
	}
}
