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

public class MainController implements ActionListener, KeyListener {

	public final static String LOGIN = "L";
	public final static String REGISTER = "R";
	public final static String REGISTER_ACEPTAR = "RA";
	public final static String REGISTER_CANCELAR = "RC";
	
	private UserController userController;
	
	private MainWindow ventanaControlada;

	public MainController(MainWindow win) {
		ventanaControlada = win;
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = (String) (e.getActionCommand());
		abrirVentana(current);
//		System.out.println(current);///////////////////////////////////////

		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	public void abrirVentana(String current) {
		if (current.equals(MainController.LOGIN)) {
			int login = -1;
			login = userController.login(ventanaControlada.user.getText(), ventanaControlada.pass.getText());
			login = 0; ///////////////////////////////////////BORRAR!!!!!
			if (login != -1) {
				// Creo la ventana para representarlo
				UserWindow window = new UserWindow();
				// Le Asociamos el controlador a la ventana
				window.addController(userController);
				//Se crea la vista de la ventana
				window.crearVista(login);   ///////////////////////¿Por qué se manda current?
				//Se le asocia al controlador la ventana
				userController.addWindow(window);
				
				ventanaControlada.setVisible(false);
				window.toFront();
			}else{
				ventanaControlada.failLogin();
			}
			

			
		} else if (current.equals(MainController.REGISTER)) {
			
			// LLama para crear la ventana de registro
			this.ventanaControlada.abrirRegistro();

			// Si el usuario se ha introducido se copia en la ventana de registro
			if (!ventanaControlada.user.getText().isEmpty()) {
				this.ventanaControlada.getVentanaRegistro().setUsuario(ventanaControlada.user.getText(),
						ventanaControlada.pass.getText());
			}
			
		} else if (current.equals(MainController.REGISTER_ACEPTAR)) {
			// Creo la ventana para representarlo
			UserWindow intern = new UserWindow();
		//	intern.crearVista(current);
			// Creo el controlador pasando la ventana
			UserController ic = new UserController(intern);
			intern.addController(ic);
			// Le Asociamos el controlador a la ventana
			ventanaControlada.setVisible(false);

			intern.toFront();
		} else if (current.equals(MainController.REGISTER_CANCELAR)) {
		
			ventanaControlada.getVentanaRegistro().doDefaultCloseAction();
			ventanaControlada.user.setText("");
			ventanaControlada.pass.setText("");

		}
	}

	public static void main(String args[]) {

		System.out.println("Starting VentanaPrincipal...");
/*
		// Leyendo el Properties

		Properties appProps = new Properties();

		String usersPath = "";
		String bankPath = "";
		int logLevel = 0;
		ArrayList<User> users = new ArrayList<User>();

		try {
			FileInputStream in = new FileInputStream("/Users/josel/Documents/workspace/ONGV2.3/ejemplo.properties");
			appProps.load(in);
			in.close();

			usersPath = appProps.getProperty("userPath");
			bankPath = appProps.getProperty("bankPath");

			System.out.println(usersPath);

		} catch (Exception e) {
			System.out.println("poh va aser k no");
			System.out.println(e.getMessage());
		}
		// usersPath = "users.txt";

		// bankPath = "dataBank.txt";
 */
		
		// Crea el array de usuarios y los lee de archivo. Crea el controlador de usuarios y le asinga el array
		ArrayList<User> users = new ArrayList<User>();
		String usersPath = "/Users/josel/Documents/workspace/ONGV2.3/users.txt";
		String bankPath = "/Users/josel/Documents/workspace/ONGV2.3/dataBank.txt";

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

		
		
		
		mc.abrirVentana(MainController.LOGIN);///////////////////////////////BORRAR
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		ventanaControlada.user.setPlaceHolder("");

		ventanaControlada.pass.setPlaceHolder("");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (ventanaControlada.user.getText().isEmpty()) {
			ventanaControlada.user.setPlaceHolder("Usuario");
		}
	}

	public void keyReleased1(KeyEvent e) {
		if (ventanaControlada.pass.getPassword() != null) {
			ventanaControlada.pass.setPlaceHolder("Contraseña");
		}
	}
	
	private void addUserController (UserController uC){
		userController = uC;
	}
	
	public MainWindow getMainWindow (){
		return ventanaControlada;
	}
}