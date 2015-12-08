import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaRegistro extends JDialog {

	public VentanaRegistro() {
		// super("Registro", true, true, true, true);
	}

	static int xPos = 100;
	static int yPos = 100;

	JTextField tf, t, f;
	JPasswordField passwordField;
	JPanel panel;
	JButton botonAccion;
	JButton botonAccion2;
	JComboBox combo;
	UserController controlador;
	VentanaRegistro ventanaInternal;

	public void crearVista() {

		panel = new JPanel();
		panel.setLayout(null);

		JLabel label1 = new JLabel("Registro");
		Insets insets = panel.getInsets();
		label1.setBounds(25 + insets.left, 5 + insets.top, 75, 20);
		panel.add(label1);

		/*
		 * JLabel label2 = new JLabel("Nombre"); label2.setBounds(10, 50, 280,
		 * 23); panel.add(label2);
		 * 
		 * JLabel label3 = new JLabel("Apellidos"); label3.setBounds(10, 90,
		 * 280, 23); panel.add(label3);
		 * 
		 * JLabel label4 = new JLabel("Contraseña"); label4.setBounds(10, 130,
		 * 280, 23); panel.add(label4);
		 */

		MyJtextField tf = new MyJtextField();
		tf.setBounds(20, 50, 280, 23);
		tf.setPlaceHolder("Username");
		panel.add(tf);

		MyJPasswordField passwordField = new MyJPasswordField();
		passwordField.setBounds(20, 110, 280, 23);
		passwordField.setPlaceHolder("Password");
		panel.add(passwordField);
		
		MyJPasswordField confirmPasswordField = new MyJPasswordField();
		confirmPasswordField.setBounds(20, 140, 280, 23);
		confirmPasswordField.setPlaceHolder("Confirm Password");
		panel.add(confirmPasswordField);
		
		MyJPasswordField firstName= new MyJPasswordField();
		firstName.setBounds(20, 170, 280, 23);
		firstName.setPlaceHolder("First Name");
		panel.add(firstName);
		
		MyJPasswordField lasName = new MyJPasswordField();
		lasName.setBounds(20, 200, 280, 23);
		lasName.setPlaceHolder("Last Name");
		panel.add(lasName);
		
		MyJPasswordField address = new MyJPasswordField();
		address.setBounds(20, 230, 280, 23);
		address.setPlaceHolder("Address");
		panel.add(address);
		
		MyJPasswordField city = new MyJPasswordField();
		city.setBounds(20, 260, 280, 23);
		city.setPlaceHolder("City");
		panel.add(city);
		
		combo = new JComboBox();
		combo.addItem("Madrid");
		combo.addItem("Barcelona");
		combo.addItem("Bilbao");
		combo.setBounds(20,290,280,23);
		panel.add(combo);

		botonAccion = new JButton("OK");
		botonAccion.setBounds(20, 320, 80, 23);
		panel.add(botonAccion);
		botonAccion2 = new JButton("CANCEL");
		botonAccion2.setBounds(120, 320, 80, 23);
		panel.add(botonAccion2);

		setSize(400, 400);
		setLocation(xPos, yPos);
		xPos = xPos + 20;
		yPos = yPos + 20;
		getContentPane().add(panel);
		setVisible(true);
	}

	public void addController(UserController uc) {
		controlador = uc;
		botonAccion.addActionListener(controlador);
		botonAccion.setActionCommand(UserController.REGISTRO);
		botonAccion2.addActionListener(controlador);
		// passwordField.addActionListener(controlador);
	}
}
