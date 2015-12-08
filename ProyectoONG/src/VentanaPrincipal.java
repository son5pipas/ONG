import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	Controlador controlador;
	MyPanel norte, centro;

	public VentanaPrincipal() {
		super();
		setSize(1200, 850);
	}

	public void addController(Controlador mc) {
		controlador = mc;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void crearVista() {
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

		MyJtextField user = new MyJtextField();
		user.setBounds(640, 25, 150, 25);
		user.setPlaceHolder("Usuario");

		MyJPasswordField pass = new MyJPasswordField();
		pass.setBounds(800, 25, 150, 25);
		pass.setPlaceHolder("Contraseña");

		JButton registro = new JButton();
		registro.setText("Registro");
		registro.setBounds(1000, 60, 150, 25);
		registro.addActionListener(controlador);
		registro.setActionCommand(Controlador.REGISTRO);

		JButton login = new JButton();
		login.setText("Login");
		login.setBounds(1000, 25, 150, 25);
		login.addActionListener(controlador);
		login.setActionCommand(Controlador.LOGIN);

		norte.add(user);
		norte.add(pass);
		norte.add(registro);
		norte.add(login);
	}

	private void panelCentro() {
		centro = new MyPanel();
		centro.setLayout(null);

		centro.pintar("Campanas.jpg", 410, 10, 380, 433);
	}

}