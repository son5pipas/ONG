package Control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.ArrayList;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.User;
import Vista.MainWindow;
import Vista.RegisterWindow;
import Vista.UserWindow;
import Modelo.Campaign;
import Modelo.Comment;
import Modelo.Donation;
import Modelo.Proposal;
import Vista.ProposalWindow;

public class MainController implements ActionListener, KeyListener {
	// Inicialiacion de las varaibles estaticas para relacionar una accion con
	// un boton de la vista asi se generara la comuncacion entre la vista, el
	// controlador y el modelo:
	public final static String LOGIN = "L";
	public final static String REGISTER = "R";
	public final static String REGISTER_ACEPTAR = "RA";
	public final static String REGISTER_CANCELAR = "RC";
	public final static String NOREGISTER = "NR";
	public final static String LOGO = "image/logo2.png";
	public static Properties appProps = null;
	public static String usersPath = null, bankPath = null, proposalPath = null, commentPath = null,
			campaignPath = null, donationPath = null;
	// Variables privates de los diferentes controladores ya que nuestro
	// controlador principal sera necesario para el paso de informacion de un
	// controlador a otro, es decir, siempre tendran que pasar por el
	// mainController
	private UserController userController;
	private ProposalController proposalController;
	private CampaignController campaignController;
	// Variable de la vista de MainWindow:
	private MainWindow ventanaControlada;

	/*
	 * @param MainWindow win
	 * 
	 * @return ventanaControlada = win;
	 */
	public MainController(MainWindow win) {
		ventanaControlada = win;
	}

	// Inicio de Main del Programa ONG:
	public static void main(String args[]) {

		System.out.println("Starting VentanaPrincipal...");
		// Leyendo el Properties

		appProps = new Properties();

		int logLevel = 0;
		ArrayList<User> users = new ArrayList<User>();

		try {
			FileInputStream in = new FileInputStream("ejemplo.properties");
			appProps.load(in);
			in.close();

			usersPath = appProps.getProperty("usersPath");
			bankPath = appProps.getProperty("bankPath");
			proposalPath = appProps.getProperty("proposalPath");
			commentPath = appProps.getProperty("commentPath");
			campaignPath = appProps.getProperty("campaignPath");
			donationPath = appProps.getProperty("donationPath");

			Log.setLogFile(appProps.getProperty("logPath"));
			Log.setErrorFile(appProps.getProperty("errorPath"));

			Log.addLog("-1", "Main", "Load", "");

			System.out.println(usersPath);
			System.out.println(System.getProperty("user.dir"));

		} catch (Exception e) {
			System.out.println("poh va aser k no");
			System.out.println(e.getMessage());
		}

		users = GestorFichero.leerFicheroUsuarios(usersPath, bankPath);

		UserController uC = new UserController(users);

		// Creo la ventana
		MainWindow mainFrame = new MainWindow();

		// Creo el controlador pasando la ventana
		MainController mc = new MainController(mainFrame);
		mc.addUserController(uC);
		uC.addMainController(mc);

		// Le Asociamos el controlador a la ventana
		mainFrame.addController(mc);
		mainFrame.crearVista();

		// Creo el array de propuestas y los leo de archivo. Creo el controlador
		// de propuestas y le asigno el array
		ArrayList<Proposal> propuestas = new ArrayList<Proposal>();
		propuestas = GestorFichero.leerFicheroProposal(proposalPath);

		// Creo el array de comentarios y los leo de archivo
		ArrayList<Comment> comentarios = new ArrayList<Comment>();
		comentarios = GestorFichero.leerFicheroComments(commentPath);

		// Creo el controlador de propuestasy le paso el array de propuestas
		ProposalController pC = new ProposalController(propuestas, comentarios);

		// Creo el controlador de campaña
		CampaignController campaignC = new CampaignController();
		mc.addCompaignController(campaignC);
		ArrayList<Campaign> lC = new ArrayList<Campaign>();
		lC = GestorFichero.leerFicheroCampaigns(campaignPath);
		campaignC.setCampaigns(lC);
		campaignC.addMainController(mc);

		ArrayList<Donation> lD = new ArrayList<Donation>();
		lD = GestorFichero.leerFicheroDonacion(donationPath);
		campaignC.setDonaciones(lD);

		mc.addProposalController(pC);
		pC.addMainController(mc);
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = (String) (e.getActionCommand());
		abrirVentana(current);
		// System.out.println(current);///////////////////////////////////////

		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// ventanaControlada.user.setPlaceHolder("");

		// ventanaControlada.pass.setPlaceHolder("");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// if (ventanaControlada.user.getText().isEmpty()) {
		// ventanaControlada.user.setPlaceHolder("Usuario");
		// }
	}

	public void keyReleased1(KeyEvent e) {
		// if (ventanaControlada.pass.getPassword() != null) {
		// ventanaControlada.pass.setPlaceHolder("ContraseÃ±a");
		// }
	}

	/*
	 * Dependiendo de la accion que hagamos en la ventana principal funcionara
	 * de una manera u otra ya que hay varias acciones como
	 * LOGIN,REGISTER,REGISTER_ACEPTAR,REGISTER_CANCELAR,NOREGISTER este ultimo
	 * es el usuario sin registro que solo puede visualizar las campañas una vez
	 * esten publicadas. Cada accion de las anteriores comentadas estan
	 * relacionadas con un boton en la vista de MainWindow.
	 * 
	 * @param String current
	 * 
	 * @return
	 */
	public void abrirVentana(String current) {
		if (current.equals(MainController.LOGIN)) {
			if (ventanaControlada.user.getText().isEmpty() || ventanaControlada.pass.getText().isEmpty()) {
				ventanaControlada.refillLogin();
			} else {
				userController.login(ventanaControlada.user.getText(), ventanaControlada.pass.getText());
				int login = userController.getLogin();
				if (login != -1) {
					userController.abrirUserWindow();
					ventanaControlada.dispose();
				} else {
					ventanaControlada.failLogin();
				}
			}

		} else if (current.equals(MainController.REGISTER)) {

			// LLama para crear la ventana de registro
			this.ventanaControlada.abrirRegistro();

			// Si el usuario se ha introducido se copia en la ventana de
			// registro
			if (!ventanaControlada.user.getText().isEmpty()) {
				this.ventanaControlada.getVentanaRegistro().setUsuario(ventanaControlada.user.getText(),
						ventanaControlada.pass.getText());
			}

		} else if (current.equals(MainController.REGISTER_ACEPTAR)) {
			if (registroRelleno() && contrasenaCoincide()) {
				// Carga los datos del nuevo usuario de la ventana de registro.
				User nuevo = new User();
				nuevo.getData().setName(ventanaControlada.getVentanaRegistro().getNombre().getText());
				nuevo.getData().setSurname(ventanaControlada.getVentanaRegistro().getApellidos().getText());
				nuevo.setUser(ventanaControlada.getVentanaRegistro().getUsuario().getText());
				nuevo.setPassword(ventanaControlada.getVentanaRegistro().getContrasena().getText());
				String nacimiento = new String(ventanaControlada.getVentanaRegistro().getFechaDia().getText() + "/"
						+ ventanaControlada.getVentanaRegistro().getFechaMes().getText() + "/"
						+ ventanaControlada.getVentanaRegistro().getFechaAno().getText());
				nuevo.setSex(ventanaControlada.getVentanaRegistro().getSexo().getSelectedItem().toString());
				if (nuevo.getSex().equals("Hombre")) {
					nuevo.setPhoto("image/hombre.jpg");
				} else {
					nuevo.setPhoto("image/mujer.jpg");
				}
				nuevo.getData().setBirthday(nacimiento);
				userController.addUser(nuevo);

				userController.login(nuevo.getUser(), nuevo.getPassword());
				userController.abrirUserWindow();

				ventanaControlada.dispose();

				GestorFichero.escribirFicheroUsuarios(userController.getUsers(), usersPath, bankPath);
			}

		} else if (current.equals(MainController.REGISTER_CANCELAR)) {

			ventanaControlada.getVentanaRegistro().dispose();
			ventanaControlada.user.clear();
			ventanaControlada.pass.clear();

		} else if (current.equals(MainController.NOREGISTER)) {
			userController.setLogin(-2);
			userController.abrirUserWindow();
			ventanaControlada.dispose();
		}
	}

	/*
	 * @param MainController mc
	 * 
	 * @return ventanaControlada = mainFrame;
	 */
	public void abrirVentanaPrincipal(MainController mc) {
		MainWindow mainFrame = new MainWindow();

		// Le Asociamos el controlador a la ventana
		mainFrame.addController(mc);
		mainFrame.crearVista();
		ventanaControlada = mainFrame;

	}

	/*
	 * @param UserController uC
	 * 
	 * @return userController = uC;
	 */
	private void addUserController(UserController uC) {
		userController = uC;
	}

	/*
	 * @param
	 * 
	 * @return ventanaControlada;
	 */
	public MainWindow getMainWindow() {
		return ventanaControlada;
	}

	/*
	 * Se comprueba que todas las casillas de la internalFrame de registro esten
	 * rellenadas:
	 * 
	 * @param
	 * 
	 * @return relleno;
	 */
	private boolean registroRelleno() {
		boolean relleno = false;
		if (!ventanaControlada.getVentanaRegistro().getNombre().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getApellidos().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getUsuario().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getContrasena().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getConfirmar().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getFechaDia().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getFechaMes().getText().isEmpty()
				&& !ventanaControlada.getVentanaRegistro().getFechaAno().getText().isEmpty()
				&& ventanaControlada.getVentanaRegistro().getSexo().getSelectedIndex() != 0) {
			relleno = true;
			// System.out.println("Relleno");////////////////////
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
		if (ventanaControlada.getVentanaRegistro().getConfirmar().getText()
				.compareTo(ventanaControlada.getVentanaRegistro().getContrasena().getText()) == 0) {
			coincide = true;
			// System.out.println("Coinciden");/////////////////////////
		}
		return coincide;
	}

	/*
	 * @param ProposalController pC
	 * 
	 * @return proposalController = pC;
	 */
	private void addProposalController(ProposalController pC) {
		proposalController = pC;
	}

	/*
	 * @param
	 * 
	 * @return proposalController;
	 */
	public ProposalController getProposalController() {
		return proposalController;
	}

	/*
	 * @param
	 * 
	 * @return userController;
	 */
	public UserController getUserController() {
		return userController;
	}

	/*
	 * @param
	 * 
	 * @return campaignController;
	 */
	public CampaignController getCampaignController() {
		return campaignController;
	}

	/*
	 * @param CampaignController mc
	 * 
	 * @return campaignController = mc;
	 */
	public void addCompaignController(CampaignController mc) {
		campaignController = mc;
	}
}