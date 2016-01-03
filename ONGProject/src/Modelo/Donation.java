package Modelo;

public class Donation {
	// Inicializcion de variables:
	private int id;
	private int idBankdate;
	private int money;

	// Metodos Setters y Getters:
	/*
	 * @param
	 * 
	 * @return id;
	 */
	public int getId() {
		return id;
	}

	/*
	 * @param int id
	 * 
	 * @return this.id = id;
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * @param
	 * 
	 * @return idBankdate;
	 */
	public int getIdBankdate() {
		return idBankdate;
	}

	/*
	 * @param int idBankdate
	 * 
	 * @return this.idBankdate = idBankdate;
	 */
	public void setIdBankdate(int idBankdate) {
		this.idBankdate = idBankdate;
	}

	/*
	 * @param
	 * 
	 * @return money;
	 */
	public int getMoney() {
		return money;
	}

	/*
	 * @param int money
	 * 
	 * @return this.money = money;
	 */
	public void setMoney(int money) {
		this.money = money;
	}

}
