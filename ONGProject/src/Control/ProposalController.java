package Control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import Modelo.Comment;
import Modelo.Proposal;
import Vista.MainWindow;
import Vista.ProposalWindow;

public class ProposalController implements ActionListener, KeyListener {
	// Inicialiacion de las varaibles estaticas para relacionar una accion con
	// un boton de la vista asi se generara la comuncacion entre la vista, el
	// controlador y el modelo:
	public final static String VOLVER = "VLV";
	public final static String CERRARSESION = "CS";
	public final static String ACEPTAR = "Aceptar";
	public final static String CANCELAR = "Cancelar";
	public final static String VOTAR = "Votar";
	public final static String OKCOMMENT = "OKcomment";
	public final static String KOCOMMENT = "KOcomment";
	public static final String ADDCOMMENT = "AddComment";

	private MainController mainController;
	// Dos listas: Propuestas y comentarios
	private ArrayList<Proposal> propuestas;
	private ArrayList<Comment> comentarios;
	// Variable de la vista de ProposalWindow
	private ProposalWindow ventanaControlada;
	private int PropuestaActual;

	/*
	 * @param ArrayList<Proposal> prop, ArrayList<Comment> comment
	 * 
	 * @return propuestas = prop; comentarios = comment;
	 */
	public ProposalController(ArrayList<Proposal> prop, ArrayList<Comment> comment) {
		propuestas = prop;
		comentarios = comment;
	}

	/*
	 * Dependiendo de la accion que hagamos en la ventana de propuestas
	 * funcionara de una manera u otra ya que hay varias acciones como
	 * VOLVER,CERRARSESION,ACEPTAR,CANCELAR,VOTAR,OKCOMMENT,KOCOMMENT,
	 * ADDCOMMENT. Cada accion de las anteriores comentadas estan relacionadas
	 * con un boton en la vista de ProposalWindow.
	 * 
	 * @param String current
	 * 
	 * @return
	 */
	public void abrirVentana(String comando) {
		String separador = new String(";");
		StringTokenizer token = new StringTokenizer(comando, separador);
		String current = token.nextToken();
		String aux = "";
		if (token.hasMoreTokens()) {
			aux = token.nextToken();
		}
		if (current.equals(ProposalController.VOLVER)) {
			mainController.getUserController().abrirUserWindow();
			ventanaControlada.dispose();
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "Volver", "");

		} else if (current.equals(ProposalController.CERRARSESION)) {
			mainController.abrirVentanaPrincipal(mainController);
			ventanaControlada.dispose();
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "CerrarSesion", "");

		} else if (current.equals(ProposalController.ACEPTAR)) {
			Proposal nuevo = new Proposal();
			nuevo.setTitle(ventanaControlada.getTituloText().getText());
			nuevo.setDescription(ventanaControlada.getDescripcionText().getText());
			int index = (this.getPropasals().size());
			index++;
			nuevo.setID(index);
			nuevo.setPhoto("/Users/josel/Documents/workspace/ONGV2.6.1/image/crow2.jpg");
			nuevo.setIdUser(this.getMainController().getUserController()
					.getUser(mainController.getUserController().getLogin()).getID());
			nuevo.setIdComment(-1);
			nuevo.setIdVote(-1);
			this.setProposal(nuevo);
			System.out.println(mainController.proposalPath);
			GestorFichero.escribirPropuesta(mainController.proposalPath, this.getPropasals());
			mainController.getUserController().abrirUserWindow();
			ventanaControlada.dispose();
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "Crear Aceptar", "");

		} else if (current.equals(ProposalController.CANCELAR)) {
			mainController.getUserController().abrirUserWindow();
			ventanaControlada.dispose();
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "Crear Cancelar", "");

		} else if (current.equals(ProposalController.VOTAR)) {
			this.getProposal(PropuestaActual).setIdVote(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getID());
			mainController.getUserController().ventanaControlada.setNewKarmaVotes();
			GestorFichero.escribirPropuesta(mainController.proposalPath, this.getPropasals());
			ventanaControlada.updateVotos();
			GestorFichero.escribirFicheroUsuarios(mainController.getUserController().users, mainController.usersPath,
					mainController.bankPath);
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "Votar", "");

		} else if (current.equals(ProposalController.OKCOMMENT)) {
			searchComment(Integer.parseInt(aux)).setIdVoteOK(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getID());
			GestorFichero.escribirComentario(mainController.commentPath, this.getComentarios());
			ventanaControlada.getContentPane().removeAll();
			abrirPropuestaX(PropuestaActual);
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "OK Comentario", "");

		} else if (current.equals(ProposalController.KOCOMMENT)) {
			searchComment(Integer.parseInt(aux)).setIdVoteKO(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getID());
			GestorFichero.escribirComentario(mainController.commentPath, this.getComentarios());
			ventanaControlada.getContentPane().removeAll();
			abrirPropuestaX(PropuestaActual);
			Log.addLog(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getUser(),
					"Propuestas", "KO Comentario", "");

		} else if (current.equals(ProposalController.ADDCOMMENT)) {
			Comment nuevo = new Comment();
			nuevo.setComment(ventanaControlada.getCommentText().getText());
			int index = (this.getComentarios().size());
			index++;
			nuevo.setID(index);
			nuevo.setIdUser(
					mainController.getUserController().getUser(mainController.getUserController().getLogin()).getID());
			nuevo.setIdVoteKO(-1);
			nuevo.setIdVoteOK(-1);
			this.getComentarios().add(nuevo);
			GestorFichero.escribirComentario(mainController.commentPath, this.getComentarios());
			this.getProposal(PropuestaActual).setIdComment(index);
			GestorFichero.escribirPropuesta(mainController.proposalPath, this.getPropasals());
			ventanaControlada.getContentPane().removeAll();
			abrirPropuestaX(PropuestaActual);
		}
	}

	/*
	 * @param int c
	 * 
	 * @return null
	 */
	public Comment searchComment(int c) {
		for (int i = 0; i < comentarios.size(); i++) {
			if (comentarios.get(i).getID() == c) {
				return comentarios.get(i);
			}

		}
		return null;
	}

	/*
	 * @param ProposalWindow win)
	 * 
	 * @return ventanaControlada = win;
	 */
	public ProposalController(ProposalWindow win) {
		ventanaControlada = win;
	}

	/*
	 * @param MainController mc
	 * 
	 * @return mainController = mc;
	 */
	public void addMainController(MainController mc) {
		mainController = mc;
	}

	/*
	 * public MainWindow getMainWindow() { return ventanaPrincipal; }
	 */
	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = new String(e.getActionCommand());
		this.abrirVentana(current);

		// Dejo el cursor por defecto
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);

	}

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	/*
	 * @param
	 * 
	 * @return this.propuestas;
	 */
	public ArrayList<Proposal> getPropasals() {
		return this.propuestas;
	}

	/*
	 * @param ArrayList<Proposal> p
	 * 
	 * @propuestas = p;
	 */
	public void setProposals(ArrayList<Proposal> p) {
		propuestas = p;
	}

	/*
	 * @param int index
	 * 
	 * @return propuestas.get(index);
	 */
	public Proposal getProposal(int index) {
		return propuestas.get(index);
	}

	/*
	 * @param
	 * 
	 * @return propuestas;
	 */
	public ArrayList<Proposal> getProposal() {
		return propuestas;
	}

	/*
	 * @param Proposal a
	 * 
	 * @return propuestas.add(a);
	 */
	public void setProposal(Proposal a) {
		propuestas.add(a);
	}

	/*
	 * @param
	 * 
	 * @return mainController;
	 */
	public MainController getMainController() {
		return mainController;
	}

	/*
	 * @param ProposalWindow win
	 * 
	 * @return ventanaControlada = win;
	 */
	public void addProposalWindow(ProposalWindow win) {
		ventanaControlada = win;
	}

	/*
	 * @param int index
	 * 
	 * @return ventanaControlada.crearVistaProp(index);
	 */
	public void abrirPropuestaX(int index) {
		ventanaControlada.crearVistaProp(index);
	}

	/*
	 * @param
	 * 
	 * @return ordenadas;
	 */
	public ArrayList<Proposal> getProposalsVotes() {
		ArrayList<Proposal> ordenadas = new ArrayList<Proposal>(propuestas);
		Collections.sort(ordenadas);
		return ordenadas;
	}

	/*
	 * @param int id
	 * 
	 * @return index;
	 */
	public int searchProposal(int id) {
		int index = -1;
		for (int i = 0; i < propuestas.size(); i++) {
			if (propuestas.get(i).getID() == id) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @param
	 * 
	 * @return PropuestaActual;
	 */
	public int getPropuestaActual() {
		return PropuestaActual;
	}

	/*
	 * @param int propuestaActual
	 *
	 * @return PropuestaActual = propuestaActual;
	 */
	public void setPropuestaActual(int propuestaActual) {
		PropuestaActual = propuestaActual;
	}

	/*
	 * @param
	 * 
	 * @return comentarios;
	 */
	public ArrayList<Comment> getComentarios() {
		return comentarios;
	}

	/*
	 * @param ArrayList<Comment> comentarios
	 * 
	 * @return this.comentarios = comentarios;
	 */
	public void setComentarios(ArrayList<Comment> comentarios) {
		this.comentarios = comentarios;
	}
}
