package Modelo;

import java.util.ArrayList;
import java.util.Vector;

import Vista.Imagen;

public class Proposal implements Comparable<Proposal> {
	// Inicializacion de variables:
	private int ID;
	private String title;
	private String description;
	private Imagen image;
	private String photo;
	private int idUser;
	// Contendra dos arrays para comentaros relacionados con una propuesta y un
	// listado de votos de una propuesta:
	private ArrayList<Integer> idComments;
	private ArrayList<Integer> idVotes;
	Boolean erase;

	// Contructor de la clase:
	public Proposal() {
		idVotes = new ArrayList<Integer>();
		idComments = new ArrayList<Integer>();
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
	 * @param String id
	 * 
	 * @return ID = Integer.parseInt(id);
	 */
	public void setID(String id) {
		ID = Integer.parseInt(id);
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
	 * @return idUser;
	 */
	public int getIdUser() {
		return idUser;
	}

	/*
	 * @param int id
	 * 
	 * @return this.idUser = id;
	 */
	public void setIdUser(int id) {
		this.idUser = id;
	}

	/*
	 * @param String id
	 * 
	 * @return this.idUser = Integer.parseInt(id);
	 */
	public void setIdUser(String id) {
		this.idUser = Integer.parseInt(id);
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
	 * @return this.photo;
	 */
	public String getPhoto() {
		return this.photo;
	}

	/*
	 * @param int c
	 * 
	 * @return idComments.add(c);
	 */
	public void setIdComment(int c) {
		idComments.add(c);
	}

	/*
	 * @param int v
	 * 
	 * @return idVotes.add(v);
	 */
	public void setIdVote(int v) {
		idVotes.add(v);
	}

	/*
	 * @param
	 * 
	 * @return idComments;
	 */
	public ArrayList getIdComments() {
		return idComments;
	}

	/*
	 * @param ArrayList<Integer> idComments
	 * 
	 * @this.idComments = idComments;
	 */
	public void setIdComments(ArrayList<Integer> idComments) {
		this.idComments = idComments;
	}

	/*
	 * @param
	 * 
	 * @return idVotes;
	 */
	public ArrayList getIdVotes() {
		return idVotes;
	}

	/*
	 * @param ArrayList<Integer> idVotes
	 * 
	 * @return this.idVotes = idVotes;
	 */
	public void setIdVotes(ArrayList<Integer> idVotes) {
		this.idVotes = idVotes;
	}

	/*
	 * @param int id
	 * 
	 * @return result;
	 */
	public boolean hasVote(int id) {
		boolean result = false;
		for (int i = 0; i < idVotes.size(); i++) {
			if (idVotes.get(i) == id)
				result = true;
		}
		return result;
	}

	/*
	 * @param
	 * 
	 * @return array;
	 */
	public String getStringIdComments() {

		String array = "";
		int contador = 0;
		for (int i = 0; i < this.idComments.size(); i++) {
			if (contador == this.idComments.size()) {
				array = array + this.idComments.get(i);
			} else {
				array = array + this.idComments.get(i) + ",";
			}
			contador++;
		}
		return array;
	}

	/*
	 * @param
	 * 
	 * @return array;
	 */
	public String getStringIdVotes() {

		String array = "";
		int contador = 0;
		for (int i = 0; i < this.idVotes.size(); i++) {
			if (contador == this.idVotes.size()) {
				array = array + this.idVotes.get(i);
			} else {
				array = array + this.idVotes.get(i) + ",";
			}
			contador++;
		}
		return array;
	}

	@Override
	/*
	 * @param Proposal other
	 * 
	 * @return this.idVotes.size() - other.idVotes.size();
	 */
	public int compareTo(Proposal other) {
		return this.idVotes.size() - other.idVotes.size();
	}

	/*
	 * @param
	 * 
	 * @return erase;
	 */
	public Boolean getErase() {
		return erase;
	}

	/*
	 * @param
	 * 
	 * @return erase;
	 */
	public Boolean isErase() {
		return erase;
	}

	/*
	 * @param Boolean erase
	 * 
	 * @return this.erase = erase;
	 */
	public void setErase(Boolean erase) {
		this.erase = erase;
	}

	/*
	 * @param String erase
	 * 
	 * @return this.erase = set;
	 */
	public void setErase(String erase) {
		boolean set = false;
		if (erase.equals("true")) {
			set = true;
		}
		this.erase = set;
	}

}
