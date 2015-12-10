package Modelo;

import java.util.ArrayList;

public class User {
	private int ID;
	private String user;
	private String password;
	private String address;
	private int karma;
	private BankData data;
	private ArrayList<Proposal> proposals;
	private boolean erase;
	private int role;
	private String photo;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setID(String iD) {
		ID = Integer.parseInt(iD);
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getKarma() {
		return karma;
	}
	public void setKarma(int karma) {
		this.karma = karma;
	}
	public void setKarma(String karma) {
		this.karma = Integer.parseInt(karma);
	}
	public BankData getData() {
		return data;
	}
	public void setData(BankData data) {
		this.data = data;
	}
	public ArrayList<Proposal> getProposals() {
		return proposals;
	}
	public void setProposals(ArrayList<Proposal> proposals) {
		this.proposals = proposals;
	}
	public boolean isErase() {
		return erase;
	}
	public void setErase(boolean erase) {
		this.erase = erase;
	}
	public void setErase(String erase) {
		boolean set = false;
		if (erase == "true"){
			set = true;
		}
		this.erase = set;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}	
	public void setRole(String role) {
		this.role = Integer.parseInt(role);
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
		
}
