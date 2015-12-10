package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Control.UserController;
import Modelo.MyPanel;

public class UserWindow extends JFrame {

	UserController controlador;
	MainWindow ventanaPrincipal;
	MyPanel norte, centro;

	public UserWindow() {
		super();
		setSize(1200, 850);
	}

	public void addController(UserController mc) {
		controlador = mc;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void crearVista(int index) {
		setLayout(new BorderLayout());

		panelNorte(index);
		panelCentro(index);

		getContentPane().add(norte, BorderLayout.NORTH);
		norte.setBackground(new Color(0, 206, 209));
		norte.setPreferredSize(new Dimension(1200, 100));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 650));
		setVisible(true);
	}

	private void panelNorte(int index) {

		norte = new MyPanel();
		norte.setLayout(null);

		norte.pintar("image/logo2.jpg", 5, 5, 330, 90);

		JLabel usuario = new JLabel(controlador.getUser(index).getData().getName() + " " + controlador.getUser(index).getData().getSurname());//"David Tupetaso");
		usuario.setBounds(490, 27, 500, 23);
		usuario.setFont(new Font(usuario.getText(), Font.BOLD, 17));
		usuario.setHorizontalAlignment(JLabel.RIGHT);

		norte.add(usuario);

		JButton cerrarSesion = new JButton();
		cerrarSesion.setText("Cerrar Sesion");
		cerrarSesion.setBounds(1000, 25, 150, 30);
		cerrarSesion.addActionListener(controlador);
		cerrarSesion.setActionCommand(UserController.CERRARSESION);

		norte.add(cerrarSesion);
	}

	private void panelCentro(int index) {

		centro = new MyPanel();
		centro.setLayout(null);

		JButton perfil = new JButton();
		perfil.setText("Perfil");
		perfil.setBounds(200, 25, 150, 30);
		perfil.addActionListener(controlador);
		// perfil.setActionCommand(UserController.PERFIL);

		JButton propuesta = new JButton();
		propuesta.setText("Propuestas");
		propuesta.setBounds(400, 25, 150, 30);
		propuesta.addActionListener(controlador);
		// propuesta.setActionCommand(UserController.PROPUESTA);

		JButton campana = new JButton();
		campana.setText("Campañas");
		campana.setBounds(600, 25, 150, 30);
		campana.addActionListener(controlador);
		// campana.setActionCommand(UserController.CAMPANA);

		centro.pintar(controlador.getUser(index).getPhoto(), 50, 10, 150, 200);

		centro.add(perfil);
		centro.add(propuesta);
		centro.add(campana);
	}
}
