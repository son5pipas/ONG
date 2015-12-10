package Control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import Modelo.User;
import Vista.MainWindow;
import Vista.UserWindow;

public class UserController implements ActionListener, KeyListener {

	public final static String CERRARSESION = "CS";
	MainController mainController;

	ArrayList<User> users;
	
	UserWindow ventanaControlada;
	MainWindow ventanaPrincipal;

	public UserController(ArrayList<User> usr) {
		users = usr;
	}
	
	public UserController(UserWindow win) {
		ventanaControlada = win;
	}

	public void actionPerformed(ActionEvent e) {
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		ventanaControlada.setCursor(cur);

		String current = (String) (e.getActionCommand());
		abrirVentana(current);
		System.out.println(current);

		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
		ventanaControlada.setCursor(cur);
	}

	public void abrirVentana(String current) {
		if (current.equals(UserController.CERRARSESION)) {
			mainController.getMainWindow().crearVista();
			mainController.getMainWindow().setVisible(true);
			ventanaControlada.setVisible(false);
			
			try {
			} catch (Exception e) {
				System.out.println("Error tratando de seleccionar la ventana:" + e.getMessage());
				return;
			}
		}
	}

	public void addMainController(MainController mc) {
		mainController = mc;
	}

	public MainWindow getMainWindow() {
		return ventanaPrincipal;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void setUsers (ArrayList<User> usr){
		users = usr;
	}
	
	public int login (String user, String pass){
		int index = -1;
		for (int i=0; i < users.size(); i++){
			if (user.equals(users.get(i).getUser()) && pass.equals(users.get(i).getPassword())){
				index = i;
			}
		}
		return index;
	}
	
	public void addWindow (UserWindow win){
		ventanaControlada = win;
	}
	
	public ArrayList<User> getUsers (){
		return users;
	}
	
	public User getUser (int index){
		return users.get(index);
	}
}
