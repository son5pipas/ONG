package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Control.MainController;
import Control.UserController;
import Modelo.Campaign;
import Modelo.Proposal;
import Modelo.User;

public class UserWindow extends JFrame {

	UserController controlador;

	MyPanel cabecera, principalUsuario, principalCentro, principalSur, panelPerfil, panelPropuesta; 
	JPanel panelGeneralPerfil, panelGeneralPropuesta, auxPropuesta, panelGeneralCampaign, panelAdministrador, auxAdmin, panelAdministradorSur, panelAdminUser, panelAdminProposal, panelAdminCampaign;
	JScrollPane panelPrincipal, panelAdministradorPrincipal;
	JComboBox<String> sex;
	JCheckBox eraseP, eraseU;
	
	public MyJtextField nombre, usuario, fechaNacimiento, buscarPropias, buscarTodas, buscarUser, buscarProposal, address, cuentaBancaria, photo, role, apellido;
	public MyJPasswordField contrasena, confirmar;
	
	JLabel fail, refill;
	
	private JTable tablaPropiasPropuestas, tablaTodasPropuestas, tableUser, tableProposal;
	
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
	
	private void clear(){
		this.getContentPane().removeAll();
	}
	
	public void crearVistaPrincipal(){
		clear();
		this.setLayout(new BorderLayout ());
		
		panelCabecera();
		this.getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));
		
		panelPrincipal();
		this.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void crearVistaPerfil(){
		clear();
		this.setLayout(new BorderLayout ());

		panelCabecera();	
		this.getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));
		
		crearPanelGeneralPerfil();
		this.getContentPane().add(panelGeneralPerfil, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void crearVistaPropuesta (){
		clear();
		this.setLayout(new BorderLayout ());
		
		panelCabecera();
		this.getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));
		
		panelPropuesta();
		this.getContentPane().add(panelGeneralPropuesta, BorderLayout.CENTER);
		panelGeneralPropuesta.setBackground(new Color(0, 255, 209));
		panelGeneralPropuesta.setPreferredSize(new Dimension(1200, 500));
		
		this.setVisible(true);
	}
	
	public void crearVistaCampaign (){
		clear();
		this.setLayout(new BorderLayout ());
		panelCabecera();
		this.getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));
		
		panelCampaign();
		this.getContentPane().add(panelGeneralCampaign, BorderLayout.CENTER);
		panelGeneralCampaign.setBackground(new Color(0, 255, 209));
		panelGeneralCampaign.setPreferredSize(new Dimension(1200, 500));
		this.setVisible(true);
	}
	
	public void crearVistaAdmin() {
		clear();
		this.setLayout(new BorderLayout());

		panelCabecera();
		this.getContentPane().add(cabecera, BorderLayout.NORTH);
		cabecera.setBackground(new Color(0, 206, 209));
		cabecera.setPreferredSize(new Dimension(1200, 100));

		panelAdmin();
		this.getContentPane().add(panelAdministradorPrincipal, BorderLayout.CENTER);
		panelAdministradorPrincipal.setBackground(new Color(0, 255, 209));
		panelAdministradorPrincipal.setPreferredSize(new Dimension(1200, 500));

		this.setVisible(true);
	}
	
	private void panelCabecera() {
		
		cabecera = new MyPanel();
		cabecera.setLayout(null);
		cabecera.pintar(MainController.LOGO, 5, 5, 330, 90);

		JLabel usuario = new JLabel();
		usuario.setText(controlador.getUser(controlador.getLogin()).getData().getName() + " "
				+ controlador.getUser(controlador.getLogin()).getData().getSurname());
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
		panelAux.setPreferredSize(new Dimension(1150,1200));
		panelAux.setLayout(new BorderLayout());
		
		crearPanelUsuario();
		
		panelAux.add(principalUsuario, BorderLayout.NORTH);
		
		crearPrincipalCentro();
		
		panelAux.add(principalCentro, BorderLayout.CENTER);
				
		crearPrincipalSur();
		
		panelAux.add(principalSur, BorderLayout.SOUTH);
		
		panelPrincipal.getViewport().add(panelAux);		
	}
	
	private void crearPanelUsuario(){
		principalUsuario = new MyPanel();
		principalUsuario.setLayout(null);
		principalUsuario.setBackground(Color.WHITE);
		principalUsuario.setPreferredSize(new Dimension(1100, 218));
		
		boolean border = true;
		int karma = controlador.getUser(controlador.getLogin()).getKarma();
		principalUsuario.pintar(controlador.getUser(controlador.getLogin()).getPhoto(), 50, 10, 200, 200, border, karma);
		
		JLabel karmaNum = new JLabel();
		karmaNum.setText("Karma: " + karma);
		karmaNum.setBounds(275, 60, 300, 100);
		karmaNum.setFont(new Font(karmaNum.getText(), Font.BOLD, 50));

		JButton perfil = new JButton(" Perfil", new ImageIcon("image/perfil2.png"));
		perfil.setFont(new Font(perfil.getText(), Font.BOLD, 30));
		perfil.setBounds(310, 15, 270, 70);
		perfil.addActionListener(controlador);
		perfil.setActionCommand(UserController.PERFIL);

		JButton propuesta = new JButton(" Propuestas", new ImageIcon ("image/proposal.png"));
		propuesta.setFont(new Font(perfil.getText(), Font.BOLD, 30));
		propuesta.setBounds(600, 15, 270, 70);
		propuesta.addActionListener(controlador);
		propuesta.setActionCommand(UserController.PROPOSAL);

		JButton campana = new JButton(" Campañas", new ImageIcon ("image/campaign.png"));
		campana.setFont(new Font(perfil.getText(), Font.BOLD, 30));
		campana.setBounds(890, 15, 270, 70);
		campana.addActionListener(controlador);
		campana.setActionCommand(UserController.CAMPANA);
		
		principalUsuario.add(karmaNum);
		principalUsuario.add(perfil);
		principalUsuario.add(propuesta);
		principalUsuario.add(campana);
		
		if (controlador.getUser(controlador.getLogin()).getRole() == 99){
			JButton admin = new JButton(" Administración", new ImageIcon ("image/admin.png"));
			admin.setFont(new Font(admin.getText(), Font.BOLD, 30));
			admin.setBounds(860, 122, 300, 70);
			admin.addActionListener(controlador);
			admin.setActionCommand(UserController.ADMIN);
			
			principalUsuario.add(admin);
		}
		
	}
	
	private void crearPrincipalCentro(){
		
		principalCentro = new MyPanel();
		principalCentro.setLayout(null);
		int x=45, y=15, width=250, height = 450;
		
		principalCentro.setPreferredSize(new Dimension(1150, 500));
		
		for (int i = 0; i < 4 ; i++){
			Proposal propuesta = new Proposal();
			propuesta = controlador.getMainController().getProposalController().getPropasals().get(i);
			User usuario = new User();
			usuario = controlador.getUser(controlador.searchUser(controlador.getMainController().getProposalController().getPropasals().get(i).getIdUser()));
			String nombre = new String ();
			nombre = usuario.getData().getName() + " " + usuario.getData().getSurname();
			
			MyPanelProposal propuestaMostrar = new MyPanelProposal(propuesta, nombre, controlador);
			propuestaMostrar.setBounds(x, y, width, height);
			propuestaMostrar.setPreferredSize(new Dimension(width, 450));
			
			x = x + width + 30;
			
			principalCentro.add(propuestaMostrar);
		}
	}
	
	private void crearPrincipalSur(){
		
		principalSur = new MyPanel();
		principalSur.setLayout(null);
		int x=113, y=15, width=250, height = 450;
		
		principalSur.setPreferredSize(new Dimension(1150, 500));
		
		for (int i = 0; i < 3 ; i++){
			Proposal propuesta = new Proposal();
			propuesta = controlador.getMainController().getProposalController().getPropasals().get(i);
			User usuario = new User();
			usuario = controlador.getUser(i);
			String nombre = new String ();
			nombre = usuario.getData().getName() + " " + usuario.getData().getSurname();
			
			MyPanelProposal propuestaMostrar = new MyPanelProposal(propuesta, nombre, controlador);
			propuestaMostrar.setBounds(x, y, width, height);
			propuestaMostrar.setPreferredSize(new Dimension(width, 450));
			
			x = x + width + 111;
			
			principalSur.add(propuestaMostrar);
		}
	}
	
	private void crearPanelGeneralPerfil(){
		panelGeneralPerfil = new JPanel();
		panelGeneralPerfil.setLayout(new BorderLayout());
		
		crearPanelUsuario();
		panelGeneralPerfil.add(principalUsuario, BorderLayout.NORTH);
		
		panelPerfilSur();
		panelGeneralPerfil.add(panelPerfil, BorderLayout.CENTER);
	}
	
	private void panelPerfilSur() {

		panelPerfil = new MyPanel();
		panelPerfil.setLayout(null);
		panelPerfil.setPreferredSize(new Dimension(1200, 300));

		JButton cerrar = new JButton();
		cerrar.setText("Cerrar");
		cerrar.setBounds(1000, 40, 100, 30);
		cerrar.addActionListener(controlador);
		cerrar.setActionCommand(UserController.CERRARPANEL);

		nombre = new MyJtextField();
		nombre.setText(controlador.getUser(controlador.getLogin()).getData().getName());
		nombre.setPlaceHolder("Nombre");
		nombre.setBounds(20, 40, 200, 30);
		nombre.addKeyListener(controlador);

		usuario = new MyJtextField();
		usuario.setText(controlador.getUser(controlador.getLogin()).getUser());
		usuario.setPlaceHolder("Usuario");
		usuario.setBounds(250, 40, 200, 30);
		usuario.addKeyListener(controlador);

		contrasena = new MyJPasswordField();
		contrasena.setPlaceHolder("Contrasenia");
		contrasena.setBounds(480, 40, 200, 30);
		contrasena.addKeyListener(controlador);

		confirmar = new MyJPasswordField();
		confirmar.setPlaceHolder("Confirmar contrasenia");
		confirmar.setBounds(720, 40, 200, 30);
		confirmar.addKeyListener(controlador);

		fechaNacimiento = new MyJtextField();
		fechaNacimiento.setText(controlador.getUser(controlador.getLogin()).getData().getBirthday());
		fechaNacimiento.setPlaceHolder("Fecha de Nacimiento");
		fechaNacimiento.setBounds(20, 100, 200, 30);
		fechaNacimiento.addKeyListener(controlador);
		
		address = new MyJtextField();
		address.setText(controlador.getUser(controlador.getLogin()).getAddress());
		address.setPlaceHolder("Direccion");
		address.setBounds(250, 100, 200, 30);
		address.addKeyListener(controlador);

		cuentaBancaria = new MyJtextField();
		cuentaBancaria.setText(controlador.getUser(controlador.getLogin()).getData().getAccount());
		cuentaBancaria.setPlaceHolder("Numero de Cuenta Bancaria");
		cuentaBancaria.setBounds(480, 100, 200, 30);
		cuentaBancaria.addKeyListener(controlador);
		
		photo = new MyJtextField();
		photo.setText(controlador.getUser(controlador.getLogin()).getPhoto());
		photo.setPlaceHolder("Fotografía");
		photo.setBounds(720, 100, 200, 30);
		photo.addKeyListener(controlador);
		
		panelPerfil.add(nombre);
		panelPerfil.add(usuario);
		panelPerfil.add(contrasena);
		panelPerfil.add(confirmar);
		panelPerfil.add(fechaNacimiento);
		panelPerfil.add(address);
		panelPerfil.add(cuentaBancaria);
		panelPerfil.add(photo);		
		panelPerfil.add(cerrar);
	
	}

	private void panelPropuesta(){
		panelGeneralPropuesta = new JPanel();
		panelGeneralPropuesta.setLayout(new BorderLayout());
		
		crearPanelUsuario();
		panelGeneralPropuesta.add(principalUsuario, BorderLayout.NORTH);
		panelGeneralPropuesta.setPreferredSize(new Dimension(1100, 518));
		
		auxPropuesta = new JPanel();
		auxPropuesta.setLayout(null);
	
		JLabel propias = new JLabel("Tus propuestas");
		propias.setFont(new Font(propias.getText(), Font.BOLD, 20));
		propias.setBounds(90, 20, 250, 50);
		
		auxPropuesta.add(propias);
		
		JLabel todas = new JLabel("Todas las propuestas");
		todas.setFont(new Font(propias.getText(), Font.BOLD, 20));
		todas.setBounds(590, 20, 250, 50);
		
		auxPropuesta.add(todas);
		
		buscarPropias = new MyJtextField();
		buscarPropias.setPlaceHolder("Buscar");
		buscarPropias.setBounds(333, 40, 200, 40);
		
		auxPropuesta.add(buscarPropias);
		
		buscarTodas = new MyJtextField();
		buscarTodas.setPlaceHolder("Buscar");
		buscarTodas.setBounds(933, 40, 200, 40);
		
		auxPropuesta.add(buscarTodas);
		
		JButton botonBuscarPropias = new JButton(" Buscar", new ImageIcon("image/search.png"));
		botonBuscarPropias.setBounds(433, 10, 100, 30);
		botonBuscarPropias.addActionListener(controlador);
		botonBuscarPropias.setActionCommand(UserController.BUSCARPROPIAS);///////////////////////////////CAMBIAR
	
		auxPropuesta.add(botonBuscarPropias);

		JButton botonBuscarTodas = new JButton(" Buscar", new ImageIcon("image/search.png"));
		botonBuscarTodas.setBounds(1033, 10, 100, 30);
		botonBuscarTodas.addActionListener(controlador);
		botonBuscarTodas.setActionCommand(UserController.BUSCARTODAS);///////////////////////////////CAMBIAR
	
		auxPropuesta.add(botonBuscarTodas);
				
		JButton abrirPropias = new JButton("Abrir");
		abrirPropias.setFont(new Font(abrirPropias.getText(), Font.BOLD, 20));
		abrirPropias.setBounds(433, 380, 100, 50);
		abrirPropias.addActionListener(controlador);
		abrirPropias.setActionCommand(UserController.ABRIRPROPIAS);///////////////////////////////CAMBIAR
	
		auxPropuesta.add(abrirPropias);

		JButton abrirTodas = new JButton("Abrir");
		abrirTodas.setFont(new Font(abrirTodas.getText(), Font.BOLD, 20));
		abrirTodas.setBounds(1033, 380, 100, 50);
		abrirTodas.addActionListener(controlador);
		abrirTodas.setActionCommand(UserController.ABRIRTODAS);///////////////////////////////CAMBIAR
	
		auxPropuesta.add(abrirTodas);
		
		JButton crearPropuesta = new JButton(" Crear propuesta", new ImageIcon("image/add.png"));
		crearPropuesta.setFont(new Font(crearPropuesta.getText(), Font.BOLD, 20));
		crearPropuesta.setBounds(80, 380, 230, 50);
		crearPropuesta.addActionListener(controlador);
		crearPropuesta.setActionCommand(UserController.CREARP);///////////////////////////////CAMBIAR
	
		auxPropuesta.add(crearPropuesta);
		
		crearTablaPropuestasPropias();
		crearTablaPropuestasTodas(-1);
		
		panelGeneralPropuesta.add(auxPropuesta, BorderLayout.CENTER);
		
		JButton cerrar = new JButton();
		cerrar.setText("Cerrar");
		cerrar.setBounds(1040, 450, 100, 30);
		cerrar.addActionListener(controlador);
		cerrar.setActionCommand(UserController.CERRARPANELPROPOSAL);
		
		auxPropuesta.add(cerrar);
	}

	private void crearTablaPropuestasPropias(){
	tablaPropiasPropuestas = new JTable();
		
		DefaultTableModel modeltablaPropiasPropuestas = new DefaultTableModel();
		modeltablaPropiasPropuestas.addColumn("Propuesta");
		modeltablaPropiasPropuestas.addColumn("Votos");
		modeltablaPropiasPropuestas.addColumn("Index");
		for (int i = 0; i < controlador.getMainController().getProposalController().getPropasals().size(); i++) {
			if (controlador.getMainController().getProposalController().getProposal(i).getIdUser() == controlador
					.getUser(controlador.getLogin()).getID() && !controlador.getMainController().getProposalController().getProposal(i).isErase()) {
				modeltablaPropiasPropuestas.addRow(new Object[] {
						controlador.getMainController().getProposalController().getProposal(i).getTitle(),
						controlador.getMainController().getProposalController().getProposal(i).getIdVotes().size()-1,
						i });
			}
		}
	
		tablaPropiasPropuestas.setModel(modeltablaPropiasPropuestas);
		tablaPropiasPropuestas.getColumnModel().getColumn(0).setPreferredWidth(400);
		tablaPropiasPropuestas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPropiasPropuestas.getColumnModel().getColumn(2).setPreferredWidth(0);	
		tablaPropiasPropuestas.removeColumn(tablaPropiasPropuestas.getColumnModel().getColumn(2));
		
		tablaPropiasPropuestas.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(tablaPropiasPropuestas);
		scrollPane.setPreferredSize(new Dimension (450,300));
		scrollPane.setBounds(80,80,450,300);
		
		auxPropuesta.add(scrollPane);
	}
	
	private void crearTablaPropuestasTodas(int index){
		tablaTodasPropuestas = new JTable();
		
		DefaultTableModel modeltablaTodasPropuestas = new DefaultTableModel();
		modeltablaTodasPropuestas.addColumn("Propuesta");
		modeltablaTodasPropuestas.addColumn("Usuario");
		modeltablaTodasPropuestas.addColumn("Votos");
		modeltablaTodasPropuestas.addColumn("Index");
		for (int i = 0; i < controlador.getMainController().getProposalController().getPropasals().size(); i++) {
			if (index == -1 && !controlador.getMainController().getProposalController().getProposal(i).isErase()) {
						modeltablaTodasPropuestas.addRow(new Object[] {
								controlador.getMainController().getProposalController().getProposal(i).getTitle(),
								controlador.getUser(controlador.searchUser(controlador.getMainController().getProposalController().getPropasals().get(i).getIdUser())).getUser(),
								controlador.getMainController().getProposalController().getProposal(i).getIdVotes().size()-1, i});
			}
		}
	
		tablaTodasPropuestas.setModel(modeltablaTodasPropuestas);
		tablaTodasPropuestas.getColumnModel().getColumn(0).setPreferredWidth(380);
		tablaTodasPropuestas.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaTodasPropuestas.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaTodasPropuestas.getColumnModel().getColumn(3).setPreferredWidth(0);
		tablaTodasPropuestas.removeColumn(tablaTodasPropuestas.getColumnModel().getColumn(3));
		tablaTodasPropuestas.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JScrollPane scrollPane2 = new JScrollPane(tablaTodasPropuestas);
		scrollPane2.setPreferredSize(new Dimension (550,300));
		scrollPane2.setBounds(580,80,550,300);
		
		auxPropuesta.add(scrollPane2);
	}

	private void panelCampaign (){
		panelGeneralCampaign = new JPanel();
		panelGeneralCampaign.setPreferredSize(new Dimension(1150,1200));
		panelGeneralCampaign.setLayout(new BorderLayout());

		if (controlador.getLogin()!=0){
			crearPanelUsuario();
			panelGeneralCampaign.add(principalUsuario, BorderLayout.NORTH);
		}
		
		principalSur = new MyPanel();
		principalSur.setLayout(null);
		int x=113, y=15, width=250, height = 450;
		
		JPanel aux = new JPanel();
		aux.setPreferredSize(new Dimension(1150, 500));

		
		
		for (int i = 0; i <= 2 ; i++){
			Campaign campana = new Campaign();
		

			campana = controlador.getMainController().getCampaignController().getCampaigns().get(i);
			
			
			User usuario = new User();
			usuario = controlador.getUser(i);
			String nombre = new String ();
			nombre = usuario.getData().getName() + " " + usuario.getData().getSurname();
			
			MyPanelCompaign propuestaMostrar = new MyPanelCompaign(campana, nombre, controlador.getMainController().getCampaignController());
			propuestaMostrar.setBounds(x, y, width, height);
			propuestaMostrar.setPreferredSize(new Dimension(width, 450));
			
			x = x + width + 111;
			
			aux.add(propuestaMostrar);

		}		
		panelGeneralCampaign.add(aux, BorderLayout.CENTER);


	}
	
	private void panelAdmin() {

		panelAdministradorPrincipal = new JScrollPane();
		
		panelAdministrador = new JPanel();
		panelAdministrador.setLayout(new BorderLayout());
		panelAdministrador.setPreferredSize(new Dimension(1150,1200));

		crearPanelUsuario();
		panelAdministrador.add(principalUsuario, BorderLayout.NORTH);
		
		auxAdmin = new JPanel();
		auxAdmin.setPreferredSize(new Dimension(1200, 500));
		auxAdmin.setLayout(null);

		JLabel usuarios = new JLabel("Usuarios");
		usuarios.setFont(new Font(usuarios.getText(), Font.BOLD, 20));
		usuarios.setBounds(90, 20, 250, 50);
		auxAdmin.add(usuarios);

		JLabel propuesta = new JLabel("Propuesta");
		propuesta.setFont(new Font(propuesta.getText(), Font.BOLD, 20));
		propuesta.setBounds(450, 20, 250, 50);
		auxAdmin.add(propuesta);
		
		buscarUser = new MyJtextField();
		buscarUser.setPlaceHolder("Buscar");
		buscarUser.setBounds(200, 40, 200, 40);

		auxAdmin.add(buscarUser);

		buscarProposal = new MyJtextField();
		buscarProposal.setPlaceHolder("Buscar");
		buscarProposal.setBounds(600, 40, 200, 40);

		auxAdmin.add(buscarProposal);

		JButton botonBuscarUser = new JButton(" Buscar", new ImageIcon("image/search.png"));
		botonBuscarUser.setBounds(200, 10, 100, 30);
		botonBuscarUser.addActionListener(controlador);
		botonBuscarUser.setActionCommand(UserController.BUSCARUSER);/////////////////////////////// CAMBIAR

		auxAdmin.add(botonBuscarUser);

		JButton botonBuscarProposal = new JButton(" Buscar", new ImageIcon("image/search.png"));
		botonBuscarProposal.setBounds(600, 10, 100, 30);
		botonBuscarProposal.addActionListener(controlador);
		botonBuscarProposal.setActionCommand(UserController.BUSCARPROPOSAL);/////////////////////////////// CAMBIAR

		auxAdmin.add(botonBuscarProposal);
		
		JButton abrirUser = new JButton("Modificar");
		abrirUser.setFont(new Font(abrirUser.getText(), Font.BOLD, 20));
		abrirUser.setBounds(280, 440, 120, 50);
		abrirUser.addActionListener(controlador);
		abrirUser.setActionCommand(UserController.MOSMODUS);///////////////////////////////CAMBIAR
	
		auxAdmin.add(abrirUser);

		JButton abrirProp = new JButton("Modificar");
		abrirProp.setFont(new Font(abrirProp.getText(), Font.BOLD, 20));
		abrirProp.setBounds(650, 440, 120, 50);
		abrirProp.addActionListener(controlador);
		abrirProp.setActionCommand(UserController.MOSMODPROP);///////////////////////////////CAMBIAR
	
		auxAdmin.add(abrirProp);
		
		JButton aCampaign = new JButton();
		aCampaign.setText("Activar Campañas");
		aCampaign.setBounds(900, 40, 200, 440);
		aCampaign.addActionListener(controlador);
		aCampaign.setActionCommand(UserController.ACAMPAIGN);

		auxAdmin.add(aCampaign);
		
		crearTablaUser();
		crearTablaProposal();
		crearTablaCampaign();

		panelAdministrador.add(auxAdmin, BorderLayout.CENTER);
				
		panelAdministradorPrincipal.getViewport().add(panelAdministrador);

	}	
	
	private void crearTablaUser(){
		tableUser = new JTable();

		DefaultTableModel modelTable = new DefaultTableModel();
		modelTable.addColumn("Usuarios");
		modelTable.addColumn("index");
		for (int i = 0; i < controlador.getUsers().size(); i++) {
			modelTable.addRow(new Object[] {controlador.getUser(i).getUser(), i,});
		}
		tableUser.setModel(modelTable);
		tableUser.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableUser.removeColumn(tableUser.getColumnModel().getColumn(1));
		
		tableUser.setFont(new Font("Arial", Font.PLAIN, 18));
		tableUser.addKeyListener(controlador);

		JScrollPane scrollPane = new JScrollPane(tableUser);
		scrollPane.setPreferredSize(new Dimension(315, 350));
		scrollPane.setBounds(80, 80, 315, 350);

		auxAdmin.add(scrollPane);
	}
	
	private void crearTablaProposal (){
		tableProposal = new JTable();

		DefaultTableModel modelTable2 = new DefaultTableModel();
		modelTable2.addColumn("Propuesta");
		modelTable2.addColumn("Index");
		modelTable2.addColumn("User");
		modelTable2.addColumn("Votos");
		for (int i = 0; i < controlador.getMainController().getProposalController().getPropasals().size(); i++) {
			modelTable2.addRow(
					new Object[] { controlador.getMainController().getProposalController().getProposal(i).getTitle(), i,
							controlador.getUser(controlador.searchUser(
							controlador.getMainController().getProposalController().getProposal(i).getIdUser())).getUser(),
							controlador.getMainController().getProposalController().getProposal(i).getIdVotes().size()-1});
		}
		tableProposal.setModel(modelTable2);
		tableProposal.getColumnModel().getColumn(0).setPreferredWidth(400);
		tableProposal.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableProposal.removeColumn(tableProposal.getColumnModel().getColumn(1));
		tableProposal.removeColumn(tableProposal.getColumnModel().getColumn(1));
		tableProposal.setFont(new Font("Arial", Font.PLAIN, 18));

		JScrollPane scrollPane2 = new JScrollPane(tableProposal);
		scrollPane2.setPreferredSize(new Dimension(315, 350));
		scrollPane2.setBounds(450, 80, 315, 350);

		auxAdmin.add(scrollPane2);
	}
	
	private void crearTablaCampaign(){
		
	}
	
	private void crearAdminSur(int opcion){
		panelAdministradorSur = new JPanel();
		if (opcion != -1){
			switch (opcion) {
			case 1: 
				adminUser();
				break;
			case 2: 
				adminProposal();
				break;
			}
		}
	}
	
	public void mostrarUserMod(){
		crearAdminSur(1);
		panelAdministrador.add(panelAdminUser, BorderLayout.SOUTH);
	}
	
	public void mostrarProposalMod (){
		crearAdminSur(2);
		panelAdministrador.add(panelAdminUser, BorderLayout.SOUTH);
	}
	
	private void adminUser(){
		panelAdminUser = new JPanel();
		panelAdminUser.setLayout(null);
		panelAdminUser.setPreferredSize(new Dimension(1200, 300));
		
		nombre = new MyJtextField();
		nombre.setText(controlador.getUser(this.getItemSelectUser()).getData().getName());
		nombre.setPlaceHolder("Nombre");
		nombre.setBounds(20, 40, 200, 30);

		usuario = new MyJtextField();
		usuario.setText(controlador.getUser(this.getItemSelectUser()).getUser());
		usuario.setPlaceHolder("Usuario");
		usuario.setBounds(250, 40, 200, 30);

		fechaNacimiento = new MyJtextField();
		fechaNacimiento.setText(controlador.getUser(this.getItemSelectUser()).getData().getBirthday());
		fechaNacimiento.setPlaceHolder("Fecha de Nacimiento");
		fechaNacimiento.setBounds(20, 100, 200, 30);
		
		address = new MyJtextField();
		address.setText(controlador.getUser(this.getItemSelectUser()).getAddress());
		address.setPlaceHolder("Direccion");
		address.setBounds(250, 100, 200, 30);

		cuentaBancaria = new MyJtextField();
		cuentaBancaria.setText(controlador.getUser(this.getItemSelectUser()).getData().getAccount());
		cuentaBancaria.setPlaceHolder("Numero de Cuenta Bancaria");
		cuentaBancaria.setBounds(480, 100, 200, 30);
		
		photo = new MyJtextField();
		photo.setText(controlador.getUser(this.getItemSelectUser()).getPhoto());
		photo.setPlaceHolder("Photo");
		photo.setBounds(480, 160, 200, 30);
		
		
		role = new MyJtextField();
		role.setText(Integer.toString(controlador.getUser(this.getItemSelectUser()).getRole()));
		role.setPlaceHolder("Role");
		role.setBounds(250, 160, 200, 30);
		
		apellido = new MyJtextField();
		apellido.setText(controlador.getUser(this.getItemSelectUser()).getData().getSurname());
		apellido.setPlaceHolder("Apellidos");
		apellido.setBounds(480, 40, 200, 30);
		
		String[] sexoString = {"Hombre", "Mujer"};
		sex = new JComboBox<String>(sexoString);
		int index = -1;
		if (controlador.getUser(this.getItemSelectUser()).getSex().equals("Hombre")){
			index = 0;
		}else {
			index = 1;
		}
		sex.setSelectedIndex(index);
		sex.setBounds(40, 160, 200, 30);
		
		eraseU = new JCheckBox();
		eraseU.setText("Borrado");
		eraseU.setBounds(700, 160, 150, 30);
		if(controlador.getUser(this.getItemSelectUser()).isErase()){
			eraseU.setSelected(true);
		}
		
		JButton aceptar = new JButton ();
		aceptar.setText("Aceptar");
		aceptar.setBounds(1000, 200, 150, 30);
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand(UserController.MODUSER);
		
		panelAdminUser.add(nombre);
		panelAdminUser.add(usuario);
		panelAdminUser.add(fechaNacimiento);
		panelAdminUser.add(address);
		panelAdminUser.add(cuentaBancaria);
		panelAdminUser.add(photo);
		panelAdminUser.add(role);
		panelAdminUser.add(sex);
		panelAdminUser.add(eraseU);
		panelAdminUser.add(apellido);
		panelAdminUser.add(aceptar);
	}
	
	private void adminProposal(){
		panelAdminUser = new JPanel();
		panelAdminUser.setLayout(null);
		panelAdminUser.setPreferredSize(new Dimension(1200, 300));

		eraseP = new JCheckBox();
		eraseP.setText("Borrado");
		eraseP.setBounds(700, 160, 150, 30);
		if(controlador.getMainController().getProposalController().getPropasals().get(this.getItemSelectProp()).isErase()){
			eraseP.setSelected(true);
		}
		
		JButton aceptar = new JButton ();
		aceptar.setText("Aceptar");
		aceptar.setBounds(1000, 200, 150, 30);
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand(UserController.MODPROP);
	
		panelAdminUser.add(eraseP);
		panelAdminUser.add(aceptar);
	}
	
	public int setNewKarmaVotes() {
		int karma = 0;
		for (int i = 0; i < controlador.getMainController().getProposalController().getPropasals().size(); i++) {
			if (controlador.getMainController().getProposalController().getProposal(i).getIdUser() == controlador
					.getUser(controlador.getLogin()).getID()) {
				if ((controlador.getMainController().getProposalController().getProposal(i).getIdVotes().size()-1)
						% 5 == 0) {
					karma = controlador.getUser(controlador.getLogin()).getKarma();
					controlador.getUser(controlador.getLogin()).setKarma(karma + 1);
				}
			}
		}
		return karma;
	}

	public int setNewKarmaComment() {
		int karma = 0;
		for (int i = 0; i < controlador.getMainController().getProposalController().getPropasals().size(); i++) {
			if (controlador.getMainController().getProposalController().getProposal(i).getIdUser() == controlador
					.getUser(controlador.getLogin()).getID()) {
				if (controlador.getMainController().getProposalController().getProposal(i).getIdComments().size()
						% 2 == 2) {
					karma = controlador.getUser(controlador.getLogin()).getKarma();
					controlador.getUser(controlador.getLogin()).setKarma(karma + 1);
				}
			}
		}
		return karma;
	}
	
	public UserWindow getVentanaUsuario() {
		return this;
	}

	public MyJtextField getNombre() {
		return nombre;
	}

	public void setNombre(MyJtextField nombre) {
		this.nombre = nombre;
	}

	public MyJtextField getUsuario() {
		return usuario;
	}

	public void setUsuario(MyJtextField usuario) {
		this.usuario = usuario;
	}

	public MyJtextField getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(MyJtextField FechaNacimiento) {
		this.fechaNacimiento = FechaNacimiento;
	}

	public MyJPasswordField getContrasena() {
		return contrasena;
	}

	public void setContrasena(MyJPasswordField contrasena) {
		this.contrasena = contrasena;
	}

	public MyJPasswordField getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(MyJPasswordField confirmar) {
		this.confirmar = confirmar;
	}	
	
	public int getItemSelectPropias (){
		int index = -1;
		if (tablaPropiasPropuestas.getSelectedRow()!=-1){
			Object indexSelect = tablaPropiasPropuestas.getModel().getValueAt(tablaPropiasPropuestas.getSelectedRow(), 2);
			index = Integer.parseInt(indexSelect.toString());
		}
		return index;
	}
	
	public int getItemSelectTodas (){
		int index = -1;
		if (tablaTodasPropuestas.getSelectedRow()!=-1){
			Object indexSelect = tablaTodasPropuestas.getModel().getValueAt(tablaTodasPropuestas.getSelectedRow(), 3);
			index = Integer.parseInt(indexSelect.toString());
		}
		return index;
	}
	
	public int getItemSelectUser (){
		int index = -1;
		if (tableUser.getSelectedRow()!=-1){
			Object indexSelect = tableUser.getModel().getValueAt(tableUser.getSelectedRow(), 1);
			index = Integer.parseInt(indexSelect.toString());
		}
		return index;
	}
	
	public int getItemSelectProp (){
		int index = -1;
		if (tableProposal.getSelectedRow()!=-1){
			Object indexSelect = tableProposal.getModel().getValueAt(tableProposal.getSelectedRow(), 1);
			index = Integer.parseInt(indexSelect.toString());
		}
		return index;
	}
	
	public void mostrarUser(){
		String text = buscarUser.getText();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableUser.getModel());
		tableUser.setRowSorter(sorter);
		if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	    }
		TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(tableProposal.getModel());
		tableProposal.setRowSorter(sorter2);
		if (text.length() == 0) {
	          sorter2.setRowFilter(null);
	        } else {
	          sorter2.setRowFilter(RowFilter.regexFilter(text));
	    }
	}
	
	public void mostrarPropuestas(){
		String text = buscarProposal.getText();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableProposal.getModel());
		tableProposal.setRowSorter(sorter);
		if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	    }
		String user ="";
		if (tableProposal.getRowCount()!=0){
			Object indexSelect = tableProposal.getModel().getValueAt(0, 2);
			user = indexSelect.toString();
		}
		TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(tableUser.getModel());
		tableUser.setRowSorter(sorter2);
		if (user.length() == 0) {
	          sorter2.setRowFilter(null);
	        } else {
	          sorter2.setRowFilter(RowFilter.regexFilter(user));
	    }
	}
	
	public void mostrarPropuestasPropias(){
		String text = buscarPropias.getText();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaPropiasPropuestas.getModel());
		tablaPropiasPropuestas.setRowSorter(sorter);
		if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	    }
	}
	
	public void mostrarPropuestasTodas(){
		String text = buscarTodas.getText();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaTodasPropuestas.getModel());
		tablaTodasPropuestas.setRowSorter(sorter);
		if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	    }
	}	
	
	public void refillPassword() {
		refill.setText("°Debe introducir una contrasenia!");
		refill.setBounds(100, 200, 350, 25);
		refill.setFont(new Font(refill.getText(), Font.BOLD, 15));
		panelPerfil.add(refill);
		panelPerfil.remove(fail);
		repaint();
	}
	
	public MyJtextField getAccount() {
		return cuentaBancaria;
	}

	public void setAccount(MyJtextField account) {
		this.cuentaBancaria = account;
	}

	public MyJtextField getAddress() {
		return address;
	}

	public void setAddress(MyJtextField address) {
		this.address = address;
	}

	public JComboBox<String> getSex() {
		return sex;
	}

	public MyJtextField getCuentaBancaria() {
		return cuentaBancaria;
	}

	public MyJtextField getPhoto() {
		return photo;
	}

	public MyJtextField getRole() {
		return role;
	}

	public MyJtextField getApellido() {
		return apellido;
	}

	public JCheckBox getEraseP() {
		return eraseP;
	}

	public void setEraseP(JCheckBox eraseP) {
		this.eraseP = eraseP;
	}

	public JCheckBox getEraseU() {
		return eraseU;
	}

	public void setEraseU(JCheckBox eraseU) {
		this.eraseU = eraseU;
	}
}
