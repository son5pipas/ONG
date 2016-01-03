package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Control.CampaignController;
import Control.MainController;
import Control.ProposalController;
import Control.UserController;
import Modelo.Comment;
import Vista.MyJtextField;
import Vista.MyPanel;
import Modelo.Proposal;
import Modelo.User;

public class CampaignWindow extends JFrame {

	private CampaignController controlador;

	MyPanel cabecera, principalUsuario, principalCentro, principalSur, panelPerfil, panelPropuesta;
	JPanel panelGeneralPerfil, panelGeneralPropuesta;
	JScrollPane panelPrincipal;

	private MyPanel centro, sur;
	private JLabel titulo, descripcion, descripcionProp, nDonaciones;
	private JTextField descripcionT, tituloT;
	private MyJtextField cantidadDonacion;
	public JButton donar;

	public CampaignWindow() {
		super();
		setSize(1200, 850);
	}

	public JButton botonDonar() {
		return donar;
	}

	private void clear() {
		this.getContentPane().removeAll();
	}

	public MyJtextField cantidadDonacion() {
		return cantidadDonacion;
	}

	public void addCampaignController(CampaignController mc) {
		controlador = mc;
	}

	public void crearVista(int index) {

		clear();
		setLayout(new BorderLayout());

		panelCabecera();
		panelCentroCamp(index);

		getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));

		getContentPane().add(centro, BorderLayout.CENTER);
		centro.setBackground(Color.WHITE);
		centro.setPreferredSize(new Dimension(1200, 650));

		setVisible(true);
	}

	private void panelCentroCamp(int index) {

		controlador.setCampaignActual(index);

		centro = new MyPanel();
		centro.setLayout(null);

		titulo = new JLabel(controlador.getCampanas().get(index).getTitle());
		titulo.setBounds(100, 5, 1000, 80);
		titulo.setFont(new Font(titulo.getText(), Font.LAYOUT_NO_START_CONTEXT, 30));
		titulo.setHorizontalAlignment(JLabel.CENTER);

		descripcion = new JLabel("Descripcion");
		descripcion.setBounds(1, 325, 150, 30);
		descripcion.setFont(new Font("Descripcion", Font.LAYOUT_NO_START_CONTEXT, 20));
		descripcion.setHorizontalAlignment(JLabel.RIGHT);

		descripcionProp = new JLabel();
		descripcionProp.setBounds(200, 280, 700, 200);
		descripcionProp.setText(controlador.getCampaigns().get(index).getDescription());

		nDonaciones = new JLabel("Recaudacion");
		nDonaciones.setBounds(300, 300, 150, 100);
		nDonaciones.setText("Recaudacion: " + Integer.toString(controlador.recaudacionCampaign(index)) + " €");
		nDonaciones.setBounds(550, 500, 220, 100);

		JButton donar = new JButton("DONAR");
		donar.setBounds(200, 600, 100, 30);
		donar.setFont(new Font("DONAR", Font.LAYOUT_NO_START_CONTEXT, 15));
		donar.addActionListener(controlador);
		donar.setActionCommand(CampaignController.DONAR);

		cantidadDonacion = new MyJtextField();
		cantidadDonacion.setPlaceHolder("Cantidad");
		cantidadDonacion.setBounds(400, 600, 80, 30);

		centro.pintar("image/crow.jpg", 30, 80, 1000, 180);

		centro.add(descripcion);
		centro.add(titulo);
		centro.add(descripcionProp);
		centro.add(donar);
		centro.add(cantidadDonacion);
		centro.add(nDonaciones);

	}

	private void panelCabecera() {

		cabecera = new MyPanel();
		cabecera.setLayout(null);

		cabecera.pintar(MainController.LOGO, 5, 5, 330, 90);

		JLabel usuario = new JLabel();
		usuario.setText(controlador.getMainController().getUserController()
				.getUser(controlador.getMainController().getUserController().getLogin()).getData().getName()
				+ " "
				+ controlador.getMainController().getUserController()
						.getUser(controlador.getMainController().getUserController().getLogin()).getData()
						.getSurname());
		usuario.setBounds(510, 40, 500, 23);
		usuario.setFont(new Font(usuario.getText(), Font.BOLD, 17));
		usuario.setHorizontalAlignment(JLabel.RIGHT);

		cabecera.add(usuario);

		JButton cerrarSesion = new JButton("Cerrar Sesion", new ImageIcon("image/logout2.png"));
		cerrarSesion.setBounds(1020, 25, 150, 50);
		cerrarSesion.setFont(new Font(cerrarSesion.getText(), Font.BOLD, 12));
		cerrarSesion.addActionListener(controlador);
		cerrarSesion.setActionCommand(UserController.CERRARSESION);

		cabecera.add(cerrarSesion);
	}

	private void panelPrincipal() {

		panelPrincipal = new JScrollPane();

		JPanel panelAux = new JPanel();
		panelAux.setPreferredSize(new Dimension(1150, 1200));
		panelAux.setLayout(new BorderLayout());

		crearPrincipalCentro();

		panelAux.add(principalCentro, BorderLayout.CENTER);

		panelPrincipal.getViewport().add(panelAux);
	}

	private void crearPrincipalCentro() {

		principalCentro = new MyPanel();
		principalCentro.setLayout(null);
		int x = 80, y = 15, width = 250, height = 450;

		principalCentro.setPreferredSize(new Dimension(1150, 500));
		int contador = 0;
		for (int i = 0; i <= 2; i++) {

			Proposal propuesta = new Proposal();
			propuesta = controlador.getMainController().getProposalController().getProposalsVotes().get(i);

			User usuario = new User();
			// ID del usuario que ha hecho la propuesta
			int idUser = propuesta.getIdUser();
			usuario = controlador.getMainController().getUserController().getUser(idUser);
			String nombre = new String();
			nombre = usuario.getData().getName() + " " + usuario.getData().getSurname();

			MyPanelProposal propuestaMostrar = new MyPanelProposal(propuesta, nombre,
					controlador.getMainController().getUserController());
			propuestaMostrar.setBounds(x + 20, y + 20, width, height);
			propuestaMostrar.setPreferredSize(new Dimension(width, 450));

			x = (x + 60) + width + 30;
			contador++;

			principalCentro.add(propuestaMostrar);
		}

	}

}