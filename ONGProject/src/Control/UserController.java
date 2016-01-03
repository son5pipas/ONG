package Control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import Modelo.Campaign;
import Modelo.Proposal;
import Modelo.User;
import Vista.MainWindow;
import Vista.ProposalWindow;
import Vista.UserWindow;

public class UserController implements ActionListener, KeyListener {
	// Inicialiacion de las varaibles estaticas para relacionar una accion con
	// un boton de la vista asi se generara la comuncacion entre la vista, el
	// controlador y el modelo:
	public final static String CERRARSESION = "CS";
	public final static String PERFIL = "PF";
	public final static String CERRARPANEL = "CP";
	public final static String PROPOSAL = "P";
	public final static String CAMPANA = "CMP";
	public final static String ADMIN = "AD";
	public final static String BUSCARPROPIAS = "BP";
	public final static String BUSCARTODAS = "BT";
	public final static String ABRIRPROPIAS = "AP";
	public final static String ABRIRTODAS = "AT";
	public final static String CREARP = "CREARP";
	public final static String CERRARPANELPROPOSAL = "CRP";
	public final static String BUSCARUSER = "BUS";
	public final static String BUSCARPROPOSAL = "BUP";
	public final static String CERRARADMINUSER = "CAU";
	public final static String MODUSER = "MODU";
	public final static String MODPROP = "MODPR";
	public final static String MOSMODUS = "MOSMODUS";
	public final static String MOSMODPROP = "MOSMODPROP";
	public final static String ACAMPAIGN = "ACP";

	MainController mainController;

	ArrayList<User> users;

	UserWindow ventanaControlada;

	private int login = -1;

	/*
	 * @param ArrayList<User> usr
	 * 
	 * @return users = usr;
	 */
	public UserController(ArrayList<User> usr) {
		users = usr;
	}

	/*
	 * @param UserWindow win
	 * 
	 * @return ventanaControlada = win;
	 */
	public UserController(UserWindow win) {
		ventanaControlada = win;
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = (String) (e.getActionCommand());
		abrirVentana(current);
		System.out.println(current);

		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	/*
	 * Dependiendo de la accion que hagamos en la ventana del perfil del Usuario
	 * funcionara de una manera u otra ya que hay varias acciones como
	 * CERRARSESION,PERFIL,PROPOSAL,ADMIN,CERRRAPANEL,BUSCARPROPIAS,BUSCARTODAS,
	 * ABRIRTODAS,CREARP,CERRARPANELPROPOSAL,CAMPANA,BUSCARUSER,BUSCARPROPOSAL,
	 * MOSMODUS,MOSMODROP,MODUSER. Cada accion de las anteriores comentadas
	 * estan relacionadas con un boton en la vista de UserWindow.
	 * 
	 * @param String current
	 * 
	 * @return
	 */
	public void abrirVentana(String current) {
		if (current.equals(UserController.CERRARSESION)) {
			mainController.abrirVentanaPrincipal(mainController);
			ventanaControlada.dispose();
			Log.addLog(users.get(login).getUser(), "User", "CerrarSesion", "");
		} else if (current.equals(UserController.PERFIL)) {
			ventanaControlada.crearVistaPerfil();
			Log.addLog(users.get(login).getUser(), "User", "Perfil", "");
		} else if (current.equals(UserController.PROPOSAL)) {
			ventanaControlada.crearVistaPropuesta();
			Log.addLog(users.get(login).getUser(), "User", "Proposal", "");
		} else if (current.equals(UserController.ADMIN)) {
			ventanaControlada.crearVistaAdmin();
			Log.addLog(users.get(login).getUser(), "User", "Admin", "");
		} else if (current.equals(UserController.CERRARPANEL)) {
			if (PerfilRelleno()) {
				// Carga los datos del nuevo usuario de la ventana de registro.
				User modificado = new User();
				modificado = users.get(login);
				modificado.getData().setName(ventanaControlada.getVentanaUsuario().getNombre().getText());
				modificado.setUser(ventanaControlada.getVentanaUsuario().getUsuario().getText());
				if (ventanaControlada.contrasena.getText().isEmpty()
						|| ventanaControlada.confirmar.getText().isEmpty()) {
				} else if (!ventanaControlada.contrasena.getText().isEmpty() && contrasenaCoincide()) {
					modificado.setPassword(ventanaControlada.getVentanaUsuario().getContrasena().getText());
				}
				String nacimiento = new String(ventanaControlada.getVentanaUsuario().getFechaNacimiento().getText());
				modificado.getData().setBirthday(nacimiento);
				modificado.setAddress(ventanaControlada.getAddress().getText());
				modificado.getData().setAccount(ventanaControlada.getAccount().getText());
				modificado.setPhoto(ventanaControlada.getPhoto().getText());
				users.set(login, modificado);
				GestorFichero.escribirFicheroUsuarios(users, mainController.usersPath, mainController.bankPath);
			}
			ventanaControlada.crearVistaPrincipal();
			Log.addLog(users.get(login).getUser(), "User", "Modifica su usuario", "");
		} else if (current.equals(UserController.BUSCARPROPIAS)) {
			ventanaControlada.mostrarPropuestasPropias();
			Log.addLog(users.get(login).getUser(), "User", "BuscarPropias", "");
		} else if (current.equals(UserController.BUSCARTODAS)) {
			ventanaControlada.mostrarPropuestasTodas();
			Log.addLog(users.get(login).getUser(), "User", "BuscarTodas", "");
		} else if (current.equals(UserController.ABRIRPROPIAS)) {
			int index = ventanaControlada.getItemSelectPropias();
			ProposalWindow ventanaProp = new ProposalWindow();
			mainController.getProposalController().addProposalWindow(ventanaProp);
			ventanaProp.addProposalController(mainController.getProposalController());
			getMainController().getProposalController().abrirPropuestaX(index);
			ventanaControlada.dispose();
			Log.addLog(users.get(login).getUser(), "User", "AbrirPropias", "");
		} else if (current.equals(UserController.ABRIRTODAS)) {
			int index = ventanaControlada.getItemSelectTodas();
			ProposalWindow ventanaProp = new ProposalWindow();
			mainController.getProposalController().addProposalWindow(ventanaProp);
			ventanaProp.addProposalController(mainController.getProposalController());
			getMainController().getProposalController().abrirPropuestaX(index);
			ventanaControlada.dispose();
			Log.addLog(users.get(login).getUser(), "User", "AbrirTodas", "");
		} else if (current.equals(UserController.CREARP)) {
			System.out.println("JO");
			ProposalWindow ventanaProp = new ProposalWindow();
			mainController.getProposalController().addProposalWindow(ventanaProp);
			ventanaProp.addProposalController(mainController.getProposalController());
			ventanaProp.crearVistaCrear();
			ventanaControlada.dispose();
			Log.addLog(users.get(login).getUser(), "User", "CrearPropuesta", "");
		} else if (current.equals(UserController.CERRARPANELPROPOSAL)) {
			ventanaControlada.crearVistaPrincipal();
			Log.addLog(users.get(login).getUser(), "User", "CerrarPanelPropuesta", "");
		} else if (current.equals(UserController.CAMPANA)) {
			ventanaControlada.crearVistaCampaign();
			Log.addLog(users.get(login).getUser(), "User", "Campana", "");
		} else if (current.equals(UserController.BUSCARUSER)) {
			ventanaControlada.mostrarUser();
			Log.addLog(users.get(login).getUser(), "User", "BuscarUser", "");
		} else if (current.equals(UserController.BUSCARPROPOSAL)) {
			ventanaControlada.mostrarPropuestas();
			Log.addLog(users.get(login).getUser(), "User", "BuscarPropuesta", "");
		} else if (current.equals(UserController.MOSMODUS)) {
			ventanaControlada.mostrarUserMod();
			Log.addLog(users.get(login).getUser(), "User", "MostrarModificarUsuario", "");
		} else if (current.equals(UserController.MOSMODPROP)) {
			ventanaControlada.mostrarProposalMod();
			Log.addLog(users.get(login).getUser(), "User", "MostrarModificarPropuesta", "");
		} else if (current.equals(UserController.MODUSER)) {
			// Carga los datos del nuevo usuario de la ventana de registro.
			User modificado = new User();
			modificado = users.get(ventanaControlada.getItemSelectUser());
			modificado.getData().setName(ventanaControlada.getVentanaUsuario().getNombre().getText());
			modificado.setUser(ventanaControlada.getVentanaUsuario().getUsuario().getText());
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			String nacimiento = new String(ventanaControlada.getVentanaUsuario().getFechaNacimiento().getText());
			modificado.getData().setBirthday(nacimiento);
			modificado.getData().setSurname(ventanaControlada.getApellido().getText());
			modificado.setPhoto(ventanaControlada.getPhoto().getText());
			modificado.setAddress(ventanaControlada.getAddress().getText());
			modificado.setSex(ventanaControlada.getSex().getSelectedItem().toString());
			modificado.getData().setAccount(ventanaControlada.getAccount().getText());
			modificado.setRole(Integer.parseInt(ventanaControlada.getRole().getText()));
			if (ventanaControlada.getEraseU().isSelected()) {
				modificado.setErase(true);
			} else {
				modificado.setErase(false);
			}

			users.set(ventanaControlada.getItemSelectUser(), modificado);
			GestorFichero.escribirFicheroUsuarios(users, mainController.usersPath, mainController.bankPath);
			Log.addLog(users.get(login).getUser(), "User", "ModificarUsuario", "");
		} else if (current.equals(UserController.MODPROP)) {
			// Carga los datos del nuevo usuario de la ventana de registro.
			Proposal modificado = new Proposal();
			modificado = mainController.getProposalController().getPropasals()
					.get(ventanaControlada.getItemSelectProp());
			if (ventanaControlada.getEraseP().isSelected()) {
				modificado.setErase(true);
			} else {
				modificado.setErase(false);
			}

			mainController.getProposalController().getPropasals().set(ventanaControlada.getItemSelectProp(),
					modificado);
			GestorFichero.escribirPropuesta(mainController.proposalPath,
					mainController.getProposalController().getPropasals());
		} else if (current.equals(UserController.ACAMPAIGN)) {
			pasarCampaigns();
		}
	}

	private void pasarCampaigns() {
		ArrayList<Proposal> ordenados = new ArrayList<Proposal>(mainController.getProposalController().getPropasals());
		Collections.sort(ordenados);
		ArrayList<Campaign> campanas = new ArrayList<Campaign>();
		for (int i = 0; i < 3; i++) {
			Campaign nueva = new Campaign();
			nueva.setID(mainController.getCampaignController().getCampaigns().size() + 1);
			nueva.setDescription(ordenados.get(i).getDescription());
			nueva.setIdDonations(-1);
			nueva.setIdUser(mainController.getProposalController().getPropasals().get(i).getIdUser());
			nueva.setPhoto(mainController.getProposalController().getPropasals().get(i).getPhoto());
			nueva.setTitle(mainController.getProposalController().getPropasals().get(i).getTitle());
			mainController.getCampaignController().getCampaigns().add(nueva);
		}
		for (int i = 0; i < mainController.getProposalController().getPropasals().size(); i++) {
			mainController.getProposalController().getPropasals().get(i).setErase(true);
		}
		GestorFichero.escribirFicheroCampaigns(MainController.campaignPath,
				mainController.getCampaignController().getCampaigns());
		GestorFichero.escribirPropuesta(MainController.proposalPath,
				mainController.getProposalController().getPropasals());

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
	 * @param
	 * 
	 * @return mainController;
	 */
	public MainController getMainController() {
		return mainController;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * @param ArrayList<User> usr
	 * 
	 * @return users = usr;
	 */
	public void setUsers(ArrayList<User> usr) {
		users = usr;
	}

	/*
	 * @param UserWindow win
	 * 
	 * @login = index;
	 */
	public void login(String user, String pass) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			if (user.equals(users.get(i).getUser()) && pass.equals(users.get(i).getPassword())
					&& !users.get(i).isErase()) {
				index = i;
				Log.addLog(users.get(index).getUser(), "User", "Login", "");
			}
		}
		login = index;
	}

	/*
	 * @param UserWindow win
	 * 
	 * @return ventanaControlada = win;
	 */
	public void addWindow(UserWindow win) {
		ventanaControlada = win;
	}

	/*
	 * @param
	 * 
	 * @return users;
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/*
	 * @param int index
	 * 
	 * @return users.get(index);
	 */
	public User getUser(int index) {
		return users.get(index);
	}

	/*
	 * @param User nuevo
	 * 
	 * @return users.add(nuevo);
	 */
	public void addUser(User nuevo) {
		int index = users.size();
		nuevo.setID(index + 1);
		nuevo.getData().setID(index + 1);
		nuevo.setKarma(50);
		nuevo.setRole(10);
		nuevo.setErase(false);
		users.add(nuevo);
	}

	public void abrirUserWindow() {
		if (login == -2) {
			login = 0;
			// login=100;
			// Creo la ventana para representarlo
			UserWindow window = new UserWindow();
			// Le Asociamos el controlador a la ventana
			window.addController(mainController.getUserController());
			// Se crea la vista de la ventana
			window.crearVistaPrincipal();
			// Se le asocia al controlador la ventana
			mainController.getUserController().addWindow(window);
			window.crearVistaCampaign();
			window.toFront();
		} else {
			// Creo la ventana para representarlo
			UserWindow window = new UserWindow();
			// Le Asociamos el controlador a la ventana
			window.addController(mainController.getUserController());
			// Se crea la vista de la ventana
			window.crearVistaPrincipal();
			// Se le asocia al controlador la ventana
			mainController.getUserController().addWindow(window);
			window.toFront();
		}

	}

	/*
	 * @param
	 * 
	 * @return login;
	 */
	public int getLogin() {
		return login;
	}

	/*
	 * @param
	 * 
	 * @return relleno;
	 */
	private boolean PerfilRelleno() {
		// VERIFICO SI TODAS LAS CASILLAS DEL PERFIL ESTAN RELLENAS PARA PODER
		// MODIFICAR
		boolean relleno = false;
		if (!ventanaControlada.getNombre().getText().isEmpty() && !ventanaControlada.getUsuario().getText().isEmpty()
				&& !ventanaControlada.getFechaNacimiento().getText().isEmpty()
				&& !ventanaControlada.getAccount().getText().isEmpty()
				&& !ventanaControlada.getAddress().getText().isEmpty()) {
			relleno = true;
		}
		return relleno;
	}

	/*
	 * @param
	 * 
	 * @return coincide;
	 */
	private boolean contrasenaCoincide() {
		boolean coincide = false;
		if (ventanaControlada.contrasena.getText().compareTo(ventanaControlada.confirmar.getText()) == 0) {
			coincide = true;
		}
		return coincide;
	}

	/*
	 * @param int id
	 * 
	 * @return index;
	 */
	public int searchUser(int id) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getID() == id) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @param int noRegistrado
	 * 
	 * @return login = noRegistrado;
	 */
	public void setLogin(int noRegistrado) {
		login = noRegistrado;
	}

	private void abrirCampanaNoRegistrados() {

	}

	/*
	 * @param
	 * 
	 * @return ventanaControlada;
	 */
	public UserWindow getVentanaUser() {
		return ventanaControlada;
	}
}
