package Modelo;

public class BankData {
	// Inicializacion de Variables
	private int ID;
	private String name;
	private String surname;
	private String birthday;
	private String account;

	// Metodos Setters y Getters de las variables anteriores:
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
	 * @return name;
	 */
	public String getName() {
		return name;
	}

	/*
	 * @param String name
	 * 
	 * @return this.name = name;
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @param
	 * 
	 * @return surname;
	 */
	public String getSurname() {
		return surname;
	}

	/*
	 * @param String surname
	 * 
	 * @return this.surname = surname;
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/*
	 * @param
	 * 
	 * @return birthday;
	 */
	public String getBirthday() {
		return birthday;
	}

	/*
	 * @param String birthday
	 * 
	 * @return this.birthday = birthday;
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/*
	 * @param
	 * 
	 * @return account;
	 */
	public String getAccount() {
		return account;
	}

	/*
	 * @param String account
	 * 
	 * @return this.account = account;
	 */
	public void setAccount(String account) {
		this.account = account;
	}

}
