package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Control.CampaignController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Control.MainController;
import Control.ProposalController;
import Control.UserController;
import Modelo.Comment;
import Vista.MyPanel;

public class ProposalWindow extends JFrame {

	private ProposalController controlador;
	private MyPanel cabecera, centro, sur;
	private JLabel titulo, descripcion, nVoto, comment, descripcionProp, nVotaciones, comentarioProp;
	private JTextField descripcionT, tituloT, newComment;
	
	private JButton votar, volver;
	
	public JTextField getTituloText (){
		return tituloT;
	}
	public JTextField getDescripcionText (){
		return descripcionT;
	}
	
	public JTextField getCommentText (){
		return newComment;
	}
	
	public ProposalWindow() {
		super();
		setSize(1200, 850);
	}

	//Creo la vista de la propuesta GENERAL
	public void crearVistaProp(int index) {
		setLayout(new BorderLayout());

		panelCabecera();
		panelCentroProp(index);

		getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 650));

		setVisible(true);
	}
	
	public void updateVotos(){
		nVotaciones.setText(Integer.toString(controlador.getProposal(controlador.getPropuestaActual()).getIdVotes().size()-1));
		votar.hide();
	}
	
	//Creo la vista de crear propuesta
	public void crearVistaCrear() {
		setLayout(new BorderLayout());

		panelCabecera();
		panelCentroCrear();

		getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 650));

		setVisible(true);
	}

	private void panelCabecera() {

		cabecera = new MyPanel();
		cabecera.setLayout(null);
		
		cabecera.pintar(MainController.LOGO, 5, 5, 330, 90);
		int index = controlador.getMainController().getUserController().getLogin();
		JLabel usuario = new JLabel(controlador.getMainController().getUserController().getUser(index).getData().getName() + " "
				+ controlador.getMainController().getUserController().getUser(index).getData().getSurname());
		usuario.setBounds(510, 40, 500, 23);
		usuario.setFont(new Font(usuario.getText(), Font.BOLD, 17));
		usuario.setHorizontalAlignment(JLabel.RIGHT);
		

		cabecera.add(usuario);

		JButton cerrarSesion = new JButton("Cerrar Sesion", new ImageIcon ("image/logout2.png"));
		cerrarSesion.setBounds(1020, 25, 150, 50);
		cerrarSesion.setFont(new Font(cerrarSesion.getText(), Font.BOLD, 12));
		cerrarSesion.addActionListener(controlador);
		cerrarSesion.setActionCommand(ProposalController.CERRARSESION);

		cabecera.add(cerrarSesion);
	}
	
	//Creo el panel central de la propuesta general
	private void panelCentroProp(int index){
		
		controlador.setPropuestaActual(index);
		
		centro = new MyPanel();
		centro.setLayout(null);
		
		titulo = new JLabel(controlador.getProposal().get(index).getTitle());
		titulo.setBounds(100, 5, 1000, 80);
		titulo.setFont(new Font(titulo.getText(), Font.LAYOUT_NO_START_CONTEXT, 30));
		titulo.setHorizontalAlignment(JLabel.CENTER);

		descripcion = new JLabel("Descripcion");
		descripcion.setBounds(1, 325, 150, 30);
		descripcion.setFont(new Font("Descripcion", Font.LAYOUT_NO_START_CONTEXT, 20));
		descripcion.setHorizontalAlignment(JLabel.RIGHT);

		nVoto = new JLabel("Voto");
		nVoto.setBounds(-50, 600, 150, 30);
		nVoto.setFont(new Font("Voto", Font.LAYOUT_NO_START_CONTEXT, 20));
		nVoto.setHorizontalAlignment(JLabel.RIGHT);

		if (!controlador.getProposal(index).hasVote(controlador.getMainController().getUserController().getUser(controlador.getMainController().getUserController().getLogin()).getID())){
			votar = new JButton("Votar");
			votar.setBounds(200, 600, 150, 50);
			votar.setFont(new Font(votar.getText(), Font.BOLD, 12));
			votar.addActionListener(controlador);
			votar.setActionCommand(ProposalController.VOTAR);
			centro.add(votar);
		}
		
		comment = new JLabel("Comentarios");
		comment.setBounds(450, 470, 200, 300);
		comment.setFont(new Font("Comentarios", Font.LAYOUT_NO_START_CONTEXT, 20));
		comment.setHorizontalAlignment(JLabel.RIGHT);

		descripcionProp = new JLabel();
		descripcionProp.setBounds(200, 270, 490, 300);
		descripcionProp.setText(controlador.getProposal(index).getDescription());

		nVotaciones = new JLabel();
		nVotaciones.setBounds(120, 590, 50, 50);
		nVotaciones.setText(Integer.toString(controlador.getProposal(index).getIdVotes().size()-1));

		comentarioProp = new JLabel();
		comentarioProp.setBounds(700, 570, 400, 100);
		
		newComment = new JTextField();
		newComment.setBounds(700, 420, 400, 50);
		
		volver = new JButton();
		volver.setText("Volver");
		volver.addActionListener(controlador);
		volver.setActionCommand(ProposalController.VOLVER);
		volver.setBounds(1100, 40, 80, 30);
		
		
		JButton addComment = new JButton("Agregar");
		addComment.setBounds(700, 470, 100, 50);
		addComment.setActionCommand(ProposalController.ADDCOMMENT);
		addComment.addActionListener(controlador);
		
		JScrollPane comentariosPanel = new JScrollPane();
		comentariosPanel.setBounds(700, 570, 400, 100);
		JPanel comments = new JPanel();
		comments.setLayout(new GridLayout(0, 1, 0, 0));
		
		comentariosPanel.getViewport().add(comments);
		
		for (int i=0 ; i < controlador.getProposal(index).getIdComments().size();i++){
			Integer idPropuesta = (Integer)controlador.getProposal(index).getIdComments().get(i);
			Comment comentarioEncontrado = controlador.searchComment(idPropuesta);
			
			if(comentarioEncontrado != null){
				JLabel commentLabel = new JLabel(comentarioEncontrado.getComment());
				commentLabel.setSize(400, 100);
				comments.add(commentLabel);
				
				
				if (!comentarioEncontrado.hasVote(controlador.getMainController().getUserController().getUser(controlador.getMainController().getUserController().getLogin()).getID())){
					JPanel buttons = new JPanel();
					buttons.setLayout(new FlowLayout());
					
					JButton meGusta = new JButton("OK");
					meGusta.setFont(new Font(meGusta.getText(), Font.BOLD, 12));
					meGusta.addActionListener(controlador);
					meGusta.setActionCommand(ProposalController.OKCOMMENT+";"+comentarioEncontrado.getID());
					buttons.add(meGusta);
					
					JButton noMeGusta = new JButton("KO");
					noMeGusta.setFont(new Font(noMeGusta.getText(), Font.BOLD, 12));
					noMeGusta.addActionListener(controlador);
					noMeGusta.setActionCommand(ProposalController.KOCOMMENT+";"+comentarioEncontrado.getID());
					buttons.add(noMeGusta);
					comments.add(buttons);
				}
			}
		}
	
		centro.pintar("image/crow.jpg", 30, 80, 1000, 180);

		centro.add(comentarioProp);
		centro.add(descripcion);
		centro.add(nVoto);
		centro.add(comment);
		centro.add(titulo);
		centro.add(descripcionProp);
		centro.add(comentariosPanel);
		centro.add(nVotaciones);
		centro.add(newComment);
		centro.add(addComment);
		centro.add(volver);
		}
	
	//Creo el panel de la ventana de crear propuesta
	private void panelCentroCrear() {  
		
		centro = new MyPanel();
		centro.setLayout(null);
		
		descripcion = new JLabel("DESCRIPCION");
		descripcion.setBounds(10, 250, 300, 30);
		descripcion.setFont(new Font("DESCRIPCION", Font.LAYOUT_NO_START_CONTEXT, 30));
		descripcion.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel tituloJL = new JLabel("TITULO");
		tituloJL.setBounds(20,20,150,30);
		tituloJL.setFont(new Font("TITULO:", Font.LAYOUT_NO_START_CONTEXT, 30));
		tituloJL.setHorizontalAlignment(JLabel.RIGHT);
		
		tituloT = new JTextField();
		tituloT.setBounds(300,20,300,40);
		
		descripcionT = new JTextField();
		descripcionT.setBounds(450,200,350,150);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Aceptar",Font.LAYOUT_NO_START_CONTEXT, 15));
		aceptar.setBounds(35, 600, 200, 50);
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand("Aceptar");
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Cancelar",Font.LAYOUT_NO_START_CONTEXT, 15));
		cancelar.setBounds(950, 600, 200, 50);
		cancelar.addActionListener(controlador);
		cancelar.setActionCommand("Cancelar");
		
		centro.add(descripcion);
		centro.add(tituloJL);
		centro.add(descripcionT);
		centro.add(tituloT);
		centro.add(aceptar);
		centro.add(cancelar);
		}
		
	
	
	public void addProposalController (ProposalController pC){
		controlador = pC;
	}

}
