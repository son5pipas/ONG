package Modelo;

import java.util.ArrayList;

public class User {
	// Inicializacion de variables:
	private int ID;
	private String user;
	private String password;
	private String address;
	private int karma;
	private BankData data;
	// Contendra una lista de propuestas cada usuario:
	private ArrayList<Proposal> proposals;
	private boolean erase;
	private String sex;
	private int role;
	private String photo;

	// Contructor de la clase:
	public User() {
		data = new BankData();
		proposals = new ArrayList<Proposal>();
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
	 * @param int iD
	 * 
	 * @return ID = iD;
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/*
	 * @param String iD
	 * 
	 * @return ID = Integer.parseInt(iD);
	 */
	public void setID(String iD) {
		ID = Integer.parseInt(iD);
	}

	/*
	 * @param
	 * 
	 * @return user;
	 */
	public String getUser() {
		return user;
	}

	/*
	 * @param String user
	 * 
	 * @return this.user = user;
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * @param
	 * 
	 * @return password;
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * @param String password
	 * 
	 * @return this.password = password;
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * @param
	 * 
	 * @return address;
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * @param String address
	 * 
	 * @return this.address = address;
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * @param
	 * 
	 * @return karma;
	 */
	public int getKarma() {
		return karma;
	}

	/*
	 * @param int karma
	 * 
	 * @return this.karma = karma;
	 */
	public void setKarma(int karma) {
		this.karma = karma;
	}

	/*
	 * @param String karma
	 * 
	 * @return this.karma = Integer.parseInt(karma);
	 */
	public void setKarma(String karma) {
		this.karma = Integer.parseInt(karma);
	}

	/*
	 * @param
	 * 
	 * @return data;
	 */
	public BankData getData() {
		return data;
	}

	/*
	 * @param BankData data
	 * 
	 * @return this.data = data;
	 */
	public void setData(BankData data) {
		this.data = data;
	}

	/*
	 * @param
	 * 
	 * @return proposals;
	 */
	public ArrayList<Proposal> getProposals() {
		return proposals;
	}

	/*
	 * @param ArrayList<Proposal> proposals
	 * 
	 * @return this.proposals = proposals;
	 */
	public void setProposals(ArrayList<Proposal> proposals) {
		this.proposals = proposals;
	}

	/*
	 * @param
	 * 
	 * @return erase;
	 */
	public boolean isErase() {
		return erase;
	}

	/*
	 * @param boolean erase
	 * 
	 * @return this.erase = erase;
	 */
	public void setErase(boolean erase) {
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

	/*
	 * @param
	 * 
	 * @return role;
	 */
	public int getRole() {
		return role;
	}

	/*
	 * @param int role
	 * 
	 * @return this.role = role;
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/*
	 * @param String role
	 * 
	 * @return this.role = Integer.parseInt(role);
	 */
	public void setRole(String role) {
		this.role = Integer.parseInt(role);
	}

	/*
	 * @param
	 * 
	 * @return sex;
	 */
	public String getSex() {
		return sex;
	}

	/*
	 * @param String sex
	 * 
	 * @this.sex = sex;
	 */
	public void setSex(String sex) {
		this.sex = sex;
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

}
