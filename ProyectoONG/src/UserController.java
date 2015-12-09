import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class UserController implements ActionListener, KeyListener {

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

	final static String REGISTRO = "REG";
	final static String LOGIN = "LOG";
	final static String CANCEL = "CAN";
	final static String AGREGAR = "AG";
	final static String BORRAR = "BO";
	final static String ELIMINAR = "EL";

	VentanaRegistro ventanaControlada;
	VentanaPrincipal ventanaPrincipal;
	VentanaLogin miVentana;
	VentanaAdministrador miVentanaAdmin;

	public UserController(VentanaRegistro win) {
		ventanaControlada = win;
	}

	public UserController(VentanaAdministrador wun) {
		miVentanaAdmin = wun;
	}

	public UserController(VentanaLogin won) {
		miVentana = won;
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		/*
		 * Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		 * ventanaControlada.setCursor(cur);
		 */

		String d = (String) (e.getActionCommand());
		if (d == UserController.REGISTRO) {
			//String fichero = (String) ventanaControlada.tf.getText();
			//mostrar("Proceso completado " + fichero);
			mostrar("¿Desea iniciar sesion?");
			int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "¿Seguro?", "¿Seguro?",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				abrirVentana2(d);/*,fichero*/
			}
		}

		String a = (String) (e.getActionCommand());
		if (a == UserController.LOGIN) {
			/*String fichero = (String) miVentana.tf.getText();
			mostrar2("Proceso completado " + fichero);
			if (fichero.equals("admin")) {
				abrirVentana(a, fichero);
			}else{*/
				abrirVentana3(a);
			//}
		}
		String b = (String) (e.getActionCommand());
		if (b == UserController.CANCEL) {
             ventanaControlada.dispose();
		}
		String g = (String) (e.getActionCommand());
		if (g == UserController.AGREGAR) {
			String fichero = (String) miVentanaAdmin.campo.getText();
			mostrar("Usuario agregado: " + fichero);
			agregarNombre(fichero);
		}
		String f = (String) (e.getActionCommand());
		if (f == UserController.ELIMINAR) {
			int puntero = (int) miVentanaAdmin.listaUsuarios.getSelectedIndex();
			eliminarNombre(puntero);
		}
		String l = (String) (e.getActionCommand());
		if (l == UserController.BORRAR) {
			borrarLista();
		}
		// Dejo el cursor como estaba
		/*
		 * cur = new Cursor(Cursor.DEFAULT_CURSOR);
		 * ventanaControlada.setCursor(cur);
		 */

	}

	public void abrirVentana(String cmd, String fichero) {
		int respuesta = JOptionPane.showConfirmDialog(miVentana, "Seguro?", "Seguro?", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			// Crear Nuevo objeto del Modelo
			// *****************************

			if (cmd.equals(UserController.LOGIN)) {
				// Creo la ventana para representarlo
				VentanaAdministrador interna = new VentanaAdministrador();
				interna.crearVista(cmd, fichero);
				// Creo el controlador pasando la ventana
				UserController ic = new UserController(interna);
				interna.addController(ic);// Le Asociamos el controlador a la
											// ventana
				miVentana.setVisible(false);
				interna.modelo.addElement(fichero);

				// La a�ado a la ventana principal
				// miVentana.getContentPane().add(interna);
				try {
					// interna.setSelected(true);
				} catch (Exception e) {
					System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
					return;
				}
				interna.toFront();
			}
		}
	}

	public void abrirVentana2(String cmd) {/*,fichero*/
		int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "Seguro?", "Seguro?",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			// Crear Nuevo objeto del Modelo
			// ***************************** b
			if (cmd.equals(UserController.REGISTRO)) {
				// Creo la ventana para representarlo
				VentanaLogin interna = new VentanaLogin();
				interna.crearVista(cmd);/*fichero*/
				// Creo el controlador pasando la ventana
				UserController ic = new UserController(interna);
				interna.addController(ic);// Le Asociamos el controlador a la
											// ventana
				ventanaControlada.setVisible(false);
				//interna.tf.setText(fichero);
				// La a�ado a la ventana principal
				//ventanaControlada.getContentPane().add(interna);
				try {
					// interna.setSelected(true);
				} catch (Exception e) {
					System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
					return;
				}
				interna.toFront();
			} else {
			}

		}
	}
	public void abrirVentana3(String cmd) {/*,fichero*/
		int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "Seguro?", "Seguro?",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			// Crear Nuevo objeto del Modelo
			// ***************************** b
			if (cmd.equals(UserController.LOGIN)) {
				// Creo la ventana para representarlo
				VentanaUsuario interna = new VentanaUsuario();
				interna.crearVista(cmd);/*fichero*/
				// Creo el controlador pasando la ventana
				UserProfileController ic = new UserProfileController(interna);
				interna.addController(ic);// Le Asociamos el controlador a la
											// ventana
				miVentana.setVisible(false);
				//interna.tf.setText(fichero);
				// La a�ado a la ventana principal
				//ventanaControlada.getContentPane().add(interna);
				try {
					// interna.setSelected(true);
				} catch (Exception e) {
					System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
					return;
				}
				interna.toFront();
			} else {
			}

		}
	}

	public void mostrar(String mensaje) {
		JOptionPane.showMessageDialog(ventanaControlada, mensaje);
	}

	public void mostrar2(String mensaje) {
		JOptionPane.showMessageDialog(miVentana, mensaje);
	}

	private void agregarNombre(String n) {
		miVentanaAdmin.modelo.addElement(n);
		miVentanaAdmin.listaUsuarios.setModel(miVentanaAdmin.modelo);
		miVentanaAdmin.campo.setText("");
	}

	private void eliminarNombre(int indice) {
		if (indice >= 0) {
			miVentanaAdmin.modelo.removeElementAt(indice);
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un indice", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void borrarLista() {
		miVentanaAdmin.modelo.clear();
	}

}