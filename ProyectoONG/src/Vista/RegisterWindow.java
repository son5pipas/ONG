package Vista;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import Control.MainController;
import Modelo.MyJPasswordField;
import Modelo.MyJtextField;

public class RegisterWindow extends JInternalFrame {
	// JButton aceptar, cancelar;
	MyJtextField nombre, usuario, fechaDia, fechaMes, fechaAno;
	MyJPasswordField contrasena, confirmar;
	JLabel titular, labelNombre, labelUsuario, labelContrasena, labelConfirmar, fecha;

	MainController controlador;

	/*
	 * Contructor Establece que se puede cerrar Establece que no se puede
	 * redimensionar Establece el tama�o
	 */
	public RegisterWindow() {
		super();
		this.setClosable(true);
		this.setResizable(false);
		this.setBounds(370, 30, 460, 360);
	}

	/*
	 * Crea la vista
	 */
	public void crearVista() {
		this.getContentPane().setLayout(null);

		titular = new JLabel();
		titular.setText("REGISTRO");
		titular.setBounds(100, -10, 300, 100);
		titular.setFont(new Font(titular.getText(), Font.BOLD, 50));

		labelNombre = new JLabel();
		labelNombre.setText("Nombre");
		labelNombre.setBounds(73, 80, 120, 30);
		labelNombre.setFont(new Font(labelNombre.getText(), Font.BOLD, 25));

		labelUsuario = new JLabel();
		labelUsuario.setText("Usuario");
		labelUsuario.setBounds(74, 120, 120, 30);
		labelUsuario.setFont(new Font(labelUsuario.getText(), Font.BOLD, 25));

		labelContrasena = new JLabel();
		labelContrasena.setText("Contraseña");
		labelContrasena.setBounds(30, 160, 200, 30);
		labelContrasena.setFont(new Font(labelContrasena.getText(), Font.BOLD, 25));

		labelConfirmar = new JLabel();
		labelConfirmar.setText("Confirmar");
		labelConfirmar.setBounds(45, 200, 200, 30);
		labelConfirmar.setFont(new Font(labelContrasena.getText(), Font.BOLD, 25));

		fecha = new JLabel();
		fecha.setText("Nacimiento");
		fecha.setBounds(31, 240, 200, 30);
		fecha.setFont(new Font(labelContrasena.getText(), Font.BOLD, 25));

		nombre = new MyJtextField();
		nombre.setPlaceHolder("Nombre");
		nombre.setBounds(180, 80, 200, 30);
		nombre.addKeyListener(controlador);

		usuario = new MyJtextField();
		usuario.setPlaceHolder("Usuario");
		usuario.setBounds(180, 120, 200, 30);
		usuario.addKeyListener(controlador);

		contrasena = new MyJPasswordField();
		contrasena.setPlaceHolder("Contrasña");
		contrasena.setBounds(180, 160, 200, 30);
		contrasena.addKeyListener(controlador);

		confirmar = new MyJPasswordField();
		confirmar.setPlaceHolder("Confirmar contraseña");
		confirmar.setBounds(180, 200, 200, 30);
		confirmar.addKeyListener(controlador);

		fechaDia = new MyJtextField();
		fechaDia.setPlaceHolder("Día");
		fechaDia.setBounds(180, 240, 50, 30);
		fechaDia.addKeyListener(controlador);

		fechaMes = new MyJtextField();
		fechaMes.setPlaceHolder("Mes");
		fechaMes.setBounds(230, 240, 90, 30);
		fechaMes.addKeyListener(controlador);

		fechaAno = new MyJtextField();
		fechaAno.setPlaceHolder("Año");
		fechaAno.setBounds(320, 240, 60, 30);
		fechaAno.addKeyListener(controlador);

		JButton aceptar = new JButton();
		aceptar.setText("Aceptar");
		aceptar.setBounds(40, 280, 180, 30);
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand(MainController.REGISTER_ACEPTAR);

		JButton cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.setBounds(230, 280, 180, 30);
		cancelar.addActionListener(controlador);
		cancelar.setActionCommand(MainController.REGISTER_CANCELAR);

		this.getContentPane().add(titular);
		this.getContentPane().add(labelNombre);
		this.getContentPane().add(labelUsuario);
		this.getContentPane().add(labelContrasena);
		this.getContentPane().add(labelConfirmar);
		this.getContentPane().add(fecha);

		this.getContentPane().add(nombre);
		this.getContentPane().add(usuario);
		this.getContentPane().add(contrasena);
		this.getContentPane().add(confirmar);
		this.getContentPane().add(fechaDia);
		this.getContentPane().add(fechaMes);
		this.getContentPane().add(fechaAno);

		this.getContentPane().add(aceptar);
		this.getContentPane().add(cancelar);
	}

	public void setUsuario(String user, String pass) {
		usuario.setText(user);
		contrasena.setText(pass);
	}

	public void addController(MainController c) {
		controlador = c;
	}

}
