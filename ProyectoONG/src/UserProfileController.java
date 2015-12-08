import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class UserProfileController implements ActionListener, KeyListener {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {

		System.out.println("Pulsada");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {

		System.out.println("Liberada");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	final static String PROPUESTA = "REG";
	final static String CAMPAÑA = "LOG";
	final static String PERFIL = "CAN";
	final static String CERRARSESION = "CS";

	VentanaUsuario ventanaControlada;
	VentanaPropuestas ventanaPropuestas;
	VentanaCampaña miVentanaCampaña;
	VentanaDatosUsuario miVentanaDatosUsuario;
	Controlador controlador;

	public UserProfileController(VentanaUsuario win) {
		ventanaControlada = win;
	}

	public UserProfileController(VentanaPropuestas win) {
		ventanaPropuestas = win;
	}

	public UserProfileController(VentanaCampaña wun) {
		miVentanaCampaña = wun;
	}

	public UserProfileController(VentanaDatosUsuario won) {
		miVentanaDatosUsuario = won;
	}

	public void actionPerformed(ActionEvent e) {
		String d = (String) (e.getActionCommand());
		if (d == UserProfileController.CERRARSESION) {
			mostrar("¿Desea cerrar sesion?");
			int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "¿Seguro?", "¿Seguro?",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
			    
			}
		}

	}

	public void mostrar(String mensaje) {
		JOptionPane.showMessageDialog(ventanaControlada, mensaje);
	}
}
