import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class VentanaAdministrador extends JDialog {

	public VentanaAdministrador() {
		// super("Administracion ONG", true, true, true, true);
	}

	static int xPos = 100;
	static int yPos = 100;

	JPanel panel;
	JTextField campo;/* Declaramos el textField */
	JButton agregar, eliminar, borrar;/* Declaramos el Boton */
	JList listaUsuarios;/* Declaramos La Lista */
	JList listaPropuestas;/* Declaramos La Lista */
	JList listaCampañas;/* Declaramos La Lista */
	DefaultListModel modelo;/* Declaramos el Modelo */
	JScrollPane scrollListaUsuarios, scrollListaPropuestas,
			scrollListaCampañas;/* Declaramos el scroll */
	UserController controlador;

	public void crearVista(String cmd, String fichero) {

		panel = new JPanel();
		panel.setLayout(null);

		JLabel label1 = new JLabel("Usuarios");
		panel.add(label1);

		JLabel label2 = new JLabel("Propuestas");
		panel.add(label2);

		JLabel label3 = new JLabel("Campañas");
		panel.add(label3);

		label1.setBounds(10, 50, 280, 23);
		label2.setBounds(50, 50, 280, 23);
		label3.setBounds(80, 50, 280, 23);

		campo = new JTextField("");
		panel.add(campo);

		agregar = new JButton();
		agregar.setText("Agregar");
		panel.add(agregar);

		eliminar = new JButton();
		eliminar.setText("Eliminar");
		panel.add(eliminar);

		borrar = new JButton();
		borrar.setText("Borrar Lista");
		panel.add(borrar);
		listaUsuarios = new JList();
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(listaUsuarios);
		listaPropuestas = new JList();
		listaPropuestas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(listaPropuestas);
		listaCampañas = new JList();
		listaCampañas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(listaCampañas);

		// instanciamos el modelo
		modelo = new DefaultListModel();

		// instanciamos el Scroll que tendra la lista
		scrollListaUsuarios = new JScrollPane();
		scrollListaUsuarios.setViewportView(listaUsuarios);
		panel.add(scrollListaUsuarios);
		scrollListaPropuestas = new JScrollPane();
		scrollListaPropuestas.setViewportView(listaPropuestas);
		panel.add(scrollListaPropuestas);
		scrollListaCampañas = new JScrollPane();
		scrollListaCampañas.setViewportView(listaCampañas);
		panel.add(scrollListaCampañas);

		// Coloco los elementos en las coordenadas deseadas
		Insets insets = panel.getInsets();
		label1.setBounds(20, 90, 280, 23);
		label2.setBounds(300, 90, 280, 23);
		label3.setBounds(600, 90, 280, 23);
		listaUsuarios.setBounds(100 + insets.left, 60 + insets.top, 75, 20);
		listaPropuestas.setBounds(100 + insets.left, 60 + insets.top, 75, 20);
		listaCampañas.setBounds(100 + insets.left, 60 + insets.top, 75, 20);
		agregar.setBounds(160, 50, 80, 23);
		eliminar.setBounds(20, 210, 80, 23);
		borrar.setBounds(120, 210, 120, 23);
		scrollListaUsuarios.setBounds(20, 120, 220, 80);
		scrollListaPropuestas.setBounds(300, 120, 220, 80);
		scrollListaCampañas.setBounds(600, 120, 220, 80);
		campo.setBounds(20, 50, 135, 23);

		setSize(1000, 1000);
		setLocation(xPos, yPos);
		xPos = xPos + 10;
		yPos = yPos + 10;
		getContentPane().add(panel);
		setVisible(true);

	}

	public void addController(UserController uc) {
		controlador = uc;
		agregar.addActionListener(controlador);
		agregar.setActionCommand(UserController.AGREGAR);
		eliminar.addActionListener(controlador);
		eliminar.setActionCommand(UserController.ELIMINAR);
		borrar.addActionListener(controlador);
		borrar.setActionCommand(UserController.BORRAR);
		listaUsuarios.addKeyListener(controlador);
		listaPropuestas.addKeyListener(controlador);
		listaCampañas.addKeyListener(controlador);
	}
}
