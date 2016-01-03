package Modelo;

import java.util.ArrayList;

public class Comment {
	// Inicializacion de variables:
	private int ID;
	private String comment;
	private int idUser;
	// Contendra dos arrays para votos a favor o en contra:
	private ArrayList<Integer> idVotesOK;
	private ArrayList<Integer> idVotesKO;

	// Contructor de la clase:
	public Comment() {
		idVotesOK = new ArrayList<Integer>();
		idVotesKO = new ArrayList<Integer>();

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
	 * @return comment;
	 */
	public String getComment() {
		return comment;
	}

	/*
	 * @param String comment
	 * 
	 * @return this.comment = comment;
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @returnthis.idUser = idUser;
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/*
	 * @param
	 * 
	 * @return idVotesOK;
	 */
	public ArrayList<Integer> getIdVotesOK() {
		return idVotesOK;
	}

	/*
	 * @param ArrayList<Integer> idVotesOK
	 * 
	 * @return this.idVotesOK = idVotesOK;
	 */
	public void setIdVotesOK(ArrayList<Integer> idVotesOK) {
		this.idVotesOK = idVotesOK;
	}

	/*
	 * @param
	 * 
	 * @return idVotesKO;
	 */
	public ArrayList<Integer> getIdVotesKO() {
		return idVotesKO;
	}

	/*
	 * @param ArrayList<Integer> idVotesKO
	 * 
	 * @return this.idVotesKO = idVotesKO;
	 */
	public void setIdVotesKO(ArrayList<Integer> idVotesKO) {
		this.idVotesKO = idVotesKO;
	}

	/*
	 * @param int v
	 * 
	 * @return idVotesOK.add(v);
	 */
	public void setIdVoteOK(int v) {
		idVotesOK.add(v);
	}

	/*
	 * @param int v
	 * 
	 * @return idVotesKO.add(v);
	 */
	public void setIdVoteKO(int v) {
		idVotesKO.add(v);
	}

	/*
	 * @param
	 * 
	 * @return array;
	 */
	public String getStringIdVotesOK() {
		String array = "";
		for (int i = 0; i < this.idVotesOK.size(); i++) {
			array = array + this.idVotesOK.get(i) + ",";
		}
		return array;
	}

	/*
	 * @param
	 * 
	 * @return array;
	 */
	public String getStringIdVotesKO() {
		String array = "";
		for (int i = 0; i < this.idVotesKO.size(); i++) {
			array = array + this.idVotesKO.get(i) + ",";
		}
		return array;
	}

	/*
	 * @param int user
	 * 
	 * @return result;
	 */
	public boolean hasVote(int user) {
		boolean result = false;
		for (int i = 0; i < idVotesOK.size(); i++) {
			if (idVotesOK.get(i) == user)
				result = true;
		}
		for (int i = 0; i < idVotesKO.size(); i++) {
			if (idVotesKO.get(i) == user)
				result = true;
		}
		return result;
	}
}
