package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Control.MainController;
import Modelo.Imagen;
import Modelo.MyDesktopPane;
import Modelo.MyJPasswordField;
import Modelo.MyJtextField;
import Modelo.MyPanel;

public class MainWindow extends JFrame {

	MyPanel norte;
	MyDesktopPane centro;
	RegisterWindow registro;
	public MyJtextField user;
	public MyJPasswordField pass;
	JLabel fail;

	MainController controlador;

	/*
	 * Constructor Establece tama�o de la ventana
	 */
	public MainWindow() {
		super();

		setSize(1200, 850);
	}

	/*
	 * Crear vista Crea los LayOut y los establece Hace Visible la ventana
	 */
	public void crearVista() {
		setLayout(new BorderLayout());

		panelNorte();
		panelCentro();

		getContentPane().add(norte, BorderLayout.NORTH);
		norte.setBackground(Color.RED);
		norte.setPreferredSize(new Dimension(1200, 100));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 650));
		// abrirRegistro();///////////////////////////////////////////////////////////////
		setVisible(true);
	}

	/*
	 * Crea el panel norte
	 */
	private void panelNorte() {

		norte = new MyPanel();
		norte.setLayout(null);

		norte.pintar("image/logo2.jpg", 5, 5, 330, 90);

		user = new MyJtextField();
		user.setBounds(840, 25, 150, 25);
		user.setPlaceHolder("Usuario");
		user.addKeyListener(controlador);

		pass = new MyJPasswordField();
		pass.setBounds(1000, 25, 150, 25);
		pass.setPlaceHolder("Contraseña");
		pass.addKeyListener(controlador);

		JButton login = new JButton();
		login.setText("Login");
		login.setBounds(1000, 60, 150, 25);

		login.addActionListener(controlador);
		login.setActionCommand(MainController.LOGIN);

		JButton registro = new JButton();
		registro.setText("Registro");
		registro.setBounds(840, 60, 150, 25);

		registro.addActionListener(controlador);
		registro.setActionCommand(MainController.REGISTER);

		norte.add(user);
		norte.add(pass);
		norte.add(login);
		norte.add(registro);
	}

	/*
	 * Crea el panel central
	 */
	private void panelCentro() {
		centro = new MyDesktopPane();
		centro.setLayout(null);

		Imagen primera = new Imagen("image/Propuestas.jpg", 10, 10, 380, 600);
		Imagen segunda = new Imagen("image/Campanas.jpg", 410, 10, 380, 600);
		Imagen tercera = new Imagen("image/Ganadoras.jpg", 810, 10, 380, 600);

		ArrayList<Imagen> fotos = new ArrayList<Imagen>();
		fotos.add(primera);
		fotos.add(segunda);
		fotos.add(tercera);

		centro.pintar(fotos);
	}

	/*
	 * Crea la ventana interna de registro, la hace visible y la trae al frente
	 */
	public void abrirRegistro() {
		registro = new RegisterWindow();
		registro.addController(controlador);
		registro.crearVista();

		centro.add(registro);

		registro.setVisible(true);
		registro.toFront();
	}

	/*
	 * Añade el controlador
	 */
	public void addController(MainController s) {
		controlador = s;
	}

	public MainController getController() {
		return controlador;
	}

	/*
	 * Devuelve la ventana de registro
	 */
	public RegisterWindow getVentanaRegistro() {
		return registro;
	}
	
	
	public void failLogin (){
		crearVista();
		fail = new JLabel();
		fail.setText("* ¡Usuario o contraseña incorrectos! *");
		fail.setBounds(850, 2, 300, 25);
		fail.setFont(new Font(fail.getText(), Font.BOLD, 15));
		norte.add(fail);
	}
}
