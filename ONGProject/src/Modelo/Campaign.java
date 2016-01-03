package Modelo;

import java.util.ArrayList;

import Vista.Imagen;

public class Campaign {
	// Incializacion de variables:
	private int ID;
	private String title;
	private String description;
	private Imagen image;
	private String photo;
	private int idUser;
	// Lista de identificadores de donaciones
	private ArrayList<Integer> idDonations;

	// Contructor de la clase:
	public Campaign() {
		idDonations = new ArrayList<Integer>();
	}

	// Metodos Setters y Getters:
	/*
	 * @param
	 * 
	 * @return ID;
	 */
	public int getID() {
		return ID;
	}

	/*
	 * @param int id
	 * 
	 * @return ID = id;
	 */
	public void setID(int id) {
		ID = id;
	}

	/*
	 * @param
	 * 
	 * @return title;
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * @param String title
	 * 
	 * @return this.title = title;
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * @param
	 * 
	 * @return description;
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * @param String description
	 * 
	 * @return this.description = description;
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * @param
	 * 
	 * @return image;
	 */
	public Imagen getImage() {
		return image;
	}

	/*
	 * @param Imagen image
	 * 
	 * @return this.image = image;
	 */
	public void setImage(Imagen image) {
		this.image = image;
	}

	/*
	 * @param
	 * 
	 * @return photo;
	 */
	public String getPhoto() {
		return photo;
	}

	/*
	 * @param String photo
	 * 
	 * @return this.photo = photo;
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/*
	 * @param
	 * 
	 * @return idUser;
	 */
	public int getIdUser() {
		return idUser;
	}

	/*
	 * @param int idUser
	 * 
	 * @return this.idUser = idUser;
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/*
	 * @param
	 * 
	 * @return idDonations;
	 */
	public ArrayList<Integer> getIdDonations() {
		return idDonations;
	}

	/*
	 * @param ArrayList<Integer> idDonations
	 * 
	 * @return this.idDonations = idDonations;
	 */
	public void setIdDonations(ArrayList<Integer> idDonations) {
		this.idDonations = idDonations;
	}

	/*
	 * @param int i
	 * 
	 * @return this.idDonations.add(i);
	 */
	public void setIdDonations(int i) {
		this.idDonations.add(i);
	}

	/*
	 * @param String a
	 * 
	 * @return this.idDonations.add(Integer.parseInt(a));
	 */
	public void setIdDonation(String a) {
		this.idDonations.add(Integer.parseInt(a));
	}
	/*
	 * @param
	 * 
	 * @return array;
	 */

	public String getStringIdDonations() {
		String array = "";
		for (int i = 0; i < this.idDonations.size(); i++) {
			array = array + this.idDonations.get(i) + ",";
		}
		return array;
	}

}
