package Modelo;

public class BankData {
	private int ID;

	private String name;
	private String surname;
	private int birthday;
	private String account;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setID(String iD) {
		ID = Integer.parseInt(iD);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = Integer.parseInt(birthday);
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	
}
