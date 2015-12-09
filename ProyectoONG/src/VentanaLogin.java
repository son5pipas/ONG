import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaLogin extends JDialog {

	public VentanaLogin() {
		// super("Usuarios", true, true, true, true);
	}

	JTextField tf;
	JPanel panel;
	JButton botonAccion;
	JButton botonAccion2;
	JPasswordField passwordField;
	UserController controlador;
	VentanaRegistro ventanaInternal;

	static int xPos = 100;
	static int yPos = 100;

	public void crearVista() {

		panel = new JPanel();
		panel.setLayout(null);

		MyJtextField tf = new MyJtextField();
		tf.setBounds(20, 50, 280, 23);
		tf.setPlaceHolder("Nombre");
		panel.add(tf);

		MyJPasswordField passwordField = new MyJPasswordField();
		passwordField.setBounds(20, 90, 280, 23);
		passwordField.setPlaceHolder("Contraseña");
		panel.add(passwordField);

		botonAccion = new JButton("OK");
		botonAccion.setBounds(20, 140, 80, 23);
		panel.add(botonAccion);

		botonAccion2 = new JButton("CANCEL");
		botonAccion2.setBounds(120, 140, 80, 23);
		panel.add(botonAccion2);

		setSize(400, 300);
		setLocation(xPos, yPos);
		xPos = xPos + 20;
		yPos = yPos + 20;
		getContentPane().add(panel);
		setVisible(true);
	}

	public void crearVista(String cmd) /*, String fichero*/{

		panel = new JPanel();
		panel.setLayout(null);

		MyJtextField tf = new MyJtextField();
		tf.setBounds(20, 50, 280, 23);
		tf.setPlaceHolder("Nombre");
		panel.add(tf);

		MyJPasswordField passwordField = new MyJPasswordField();
		passwordField.setBounds(20, 90, 280, 23);
		passwordField.setPlaceHolder("Contraseña");
		panel.add(passwordField);

		botonAccion = new JButton("OK");
		botonAccion.setBounds(20, 190, 80, 23);
		panel.add(botonAccion);
		botonAccion2 = new JButton("CANCEL");
		botonAccion2.setBounds(120, 190, 80, 23);
		panel.add(botonAccion2);

		setSize(400, 300);
		setLocation(xPos, yPos);
		xPos = xPos + 20;
		yPos = yPos + 20;
		getContentPane().add(panel);
		setVisible(true);
	}

	public void addController(UserController v) {
		controlador = v;
		botonAccion.addActionListener(controlador);
		botonAccion.setActionCommand(UserController.LOGIN);
		botonAccion2.addActionListener(controlador);
		botonAccion2.setActionCommand(UserController.CANCEL);
		//passwordField.addActionListener(controlador);
	}
}
