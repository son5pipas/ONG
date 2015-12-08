import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {

	final static String REGISTRO = "L";
	final static String LOGIN = "T";

	JFrame ventanaControlada;

	public Controlador(JFrame win) {
		ventanaControlada = win;
	}

	public void actionPerformed(ActionEvent e) {

		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = (String) (e.getActionCommand());
		// Abro la ventana sea Lista , ComboBox o Table.
		abrirVentana(current);

		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	public void abrirVentana(String current) {
		int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "¿Seguro?", "¿Seguro?",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			// Crear Nuevo objeto del Modelo
			// *****************************

			if (current.equals(Controlador.REGISTRO)) {
				// Creo la ventana para representarlo
				VentanaRegistro intern = new VentanaRegistro();
				intern.crearVista();
				// Creo el controlador pasando la ventana
				UserController ic = new UserController(intern);
				intern.addController(ic);// Le Asociamos el controlador a la
											// ventana

				// La añado a la ventana principal
				// ventanaControlada.getContentPane().add(intern);
				try {
					// intern.setSelected(true);
				} catch (Exception e) {
					System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
					return;
				}
				intern.toFront();
			} else if (current.equals(Controlador.LOGIN)) {
				// Creo la ventana para representarlo
				VentanaUsuario intern = new VentanaUsuario();
				intern.crearVista();
				// Creo el controlador pasando la ventana
				UserProfileController ic = new UserProfileController(intern);
				intern.addController(ic);// Le Asociamos el controlador a la
											// ventana
				ventanaControlada.setVisible(false);
				// La añado a la ventana principal
				// ventanaControlada.getContentPane().add(intern);
				try {
					// intern.setSelected(true);
				} catch (Exception e) {
					System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
					return;
				}
				intern.toFront();
			}
		}
	}

	public void cerrarFichero() {
		ventanaControlada.dispose();
		System.exit(0);
	}

	public static void main(String args[]) {

		System.out.println("Starting VentanaPrincipal...");

		// Leyendo el Properties

		Properties appProps = new Properties();

		String usersPath = "";
		int logLevel = 0;

		try {
			FileInputStream in = new FileInputStream("ejemplo.properties");
			appProps.load(in);
			in.close();

			usersPath = appProps.getProperty("LogPath");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Creo la ventana
		VentanaPrincipal mainFrame = new VentanaPrincipal();

		// Creo el controlador pasando la ventana
		Controlador mc = new Controlador(mainFrame);

		// Le Asociamos el controlador a la ventana
		mainFrame.addController(mc);
		mainFrame.crearVista();

	}
}