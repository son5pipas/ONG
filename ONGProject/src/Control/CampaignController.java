package Control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import Modelo.Campaign;
import Modelo.Donation;
import Vista.CampaignWindow;

public class CampaignController implements ActionListener, KeyListener {
	// Contendra dos variables provenientes de la vista de campaña y otra de la
	// clase del mainController.
	private CampaignWindow ventanaControlada;
	private MainController mainController;
	// Campañas tendra dos arrays tanto las suyas de camapañas como el listado
	// de donaciones
	private ArrayList<Campaign> campanas;
	private ArrayList<Donation> donaciones;

	private int campaignActual;
	// Variables estaticas para dar funcionalidad a un boton de la vista
	// "CampaignWindow"
	public final static String DONAR = "BOTONDONAR";
	public final static String ATRAS = "Atras";

	/*
	 * @param
	 * 
	 * @return mainController;
	 */
	public MainController getMainController() {
		return mainController;
	}

	/*
	 * @param MainController
	 * 
	 * @return mainController = mc;
	 */
	public void addMainController(MainController mc) {
		mainController = mc;
	}

	/*
	 * @param CampaignWindow w
	 * 
	 * @return ventanaControlada = w;
	 */
	public void addCampaignWindow(CampaignWindow w) {
		ventanaControlada = w;
	}

	public CampaignController() {
	}

	/*
	 * @param ArrayList<Campaign> camp, ArrayList<Donation> donac
	 * 
	 * @return campanas = camp; donaciones = donac;
	 */
	public CampaignController(ArrayList<Campaign> camp, ArrayList<Donation> donac) {
		campanas = camp;
		donaciones = donac;
	}

	/*
	 * @param String current
	 * 
	 * @return
	 */
	public void abrirVentana(String current) {
		if (current.equals(CampaignController.DONAR)) {
			Donation nuevo = new Donation();
			int id = donaciones.size() + 1;

			nuevo.setId(id);
			nuevo.setIdBankdate(mainController.getUserController()
					.getUser(mainController.getUserController().getLogin()).getData().getID());
			nuevo.setMoney(Integer.parseInt(ventanaControlada.cantidadDonacion().getText()));
			donaciones.add(nuevo);
			GestorFichero.escribirFicheroDonacion(mainController.donationPath, donaciones);

			campanas.get(campaignActual).getIdDonations().add(id - 1);
			GestorFichero.escribirFicheroCampaigns(mainController.campaignPath, campanas);

			// ventanaControlada.donar.disable();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		System.out.println("ale");
		String current = new String(e.getActionCommand());
		this.abrirVentana(current);

		// Dejo el cursor por defecto
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	/*
	 * @return campanas;
	 */
	public ArrayList<Campaign> getCampaigns() {
		return campanas;
	}

	/*
	 * @param ArrayList<Campaign> campaigns
	 * 
	 * @return this.campanas = campaigns;
	 */
	public void setCampaigns(ArrayList<Campaign> campaigns) {
		this.campanas = campaigns;
	}

	/*
	 * @param int id
	 * 
	 * @return index;
	 */
	public int searchCampaign(int id) {
		int index = -1;
		for (int i = 0; i <= campanas.size(); i++) {
			if (campanas.get(i).getID() == id) {
				return index = i;
			}
		}
		return index;
	}

	/*
	 * @param int index
	 * 
	 * @return campaignActual = index;
	 */
	public void setCampaignActual(int index) {
		campaignActual = index;
	}

	/*
	 * @param int id
	 * 
	 * @return index;
	 */
	public void abrirCampaignX(int index) {
		ventanaControlada.crearVista(index);
	}

	/*
	 * @return ventanaControlada;
	 */
	public CampaignWindow getCampaignWindow() {
		return ventanaControlada;
	}

	/*
	 * @param int index
	 * 
	 * @return dinero;
	 */
	public int recaudacionCampaign(int index) {
		int dinero = 0;
		for (int i = 1; i <= campanas.get(index).getIdDonations().size() - 1; i++) {

			int idDonation = campanas.get(index).getIdDonations().get(i);
			dinero = dinero + donaciones.get(idDonation).getMoney();

		}
		return dinero;
	}

	/*
	 * @return campanas;
	 */
	public ArrayList<Campaign> getCampanas() {
		return campanas;
	}

	/*
	 * @return donaciones;
	 */
	public ArrayList<Donation> getDonaciones() {
		return donaciones;
	}

	/*
	 * @return this.donaciones = donaciones;
	 */
	public void setDonaciones(ArrayList<Donation> donaciones) {
		this.donaciones = donaciones;
	}

}
