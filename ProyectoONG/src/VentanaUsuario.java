import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaUsuario extends JFrame {

	UserProfileController controlador;
	MyPanel norte, centro;

	public VentanaUsuario() {
		super();
		setSize(1200, 850);
	}

	public void addController(UserProfileController mc) {
		controlador = mc;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void crearVista(String cmd) {
		setLayout(new BorderLayout());

		panelNorte();
		panelCentro();

		getContentPane().add(norte, BorderLayout.NORTH);
		norte.setBackground(new Color(0, 206, 209));
		norte.setPreferredSize(new Dimension(1200, 150));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 500));

		setVisible(true);
	}

	private void panelNorte() {

		norte = new MyPanel();
		norte.setLayout(null);

		norte.pintar("unnamed.jpg", 5, 5, 512, 140);

		JLabel label1 = new JLabel("Alexander");
		label1.setBounds(800, 25, 150, 23);
		norte.add(label1);

		JButton cerrarSesion = new JButton();
		cerrarSesion.setText("Cerrar Sesion");
		cerrarSesion.setBounds(1000, 25, 150, 30);
		cerrarSesion.addActionListener(controlador);
		cerrarSesion.setActionCommand(UserProfileController.CERRARSESION);

		norte.add(cerrarSesion);
	}

	private void panelCentro() {
		centro = new MyPanel();
		centro.setLayout(null);

		JButton perfil = new JButton();
		perfil.setText("Perfil");
		perfil.setBounds(200, 25, 150, 30);
		perfil.addActionListener(controlador);
		perfil.setActionCommand(UserProfileController.PERFIL);

		JButton propuesta = new JButton();
		propuesta.setText("Propuestas");
		propuesta.setBounds(400, 25, 150, 30);
		propuesta.addActionListener(controlador);
		propuesta.setActionCommand(UserProfileController.PROPUESTA);

		JButton campaña = new JButton();
		campaña.setText("Campañas");
		campaña.setBounds(600, 25, 150, 30);
		campaña.addActionListener(controlador);
		campaña.setActionCommand(UserProfileController.CAMPAÑA);

		centro.pintar("foto1.jpg", 50, 10, 100, 100);
		centro.add(perfil);
		centro.add(propuesta);
		centro.add(campaña);
	}
}
