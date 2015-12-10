package Control;

import Modelo.User;
import Modelo.BankData;
import Modelo.Imagen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestorFichero {
	private static BufferedReader lector;

	public static ArrayList<User> leerFicheroUsuarios(String path, String pathAccount) {
		ArrayList<BankData> cuenta = new ArrayList<BankData>();
		cuenta = leerFicheroBanco(pathAccount);
		
		int index = 0;
		
		ArrayList<User> users = new ArrayList<User>();
		
		String line = null;
		String separador = new String(";\n");
		try {
			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			while (line != null) {
				StringTokenizer token = new StringTokenizer(line, separador);
				{
					User usuario = new User();
					
					usuario.setID(token.nextToken());
					usuario.setUser(token.nextToken());
					usuario.setPassword(token.nextToken());
					usuario.setAddress(token.nextToken());
					usuario.setKarma(token.nextToken());
					usuario.setErase(token.nextToken());
					usuario.setRole(token.nextToken());			
					usuario.setPhoto(token.nextToken());	
					
					usuario.setData(cuenta.get(index));
					
					users.add(usuario);
					index++;	
				}
				line = lector.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error al leer fichero");
		}

		return (ArrayList<User>) users;
	}
	
	public static ArrayList<BankData> leerFicheroBanco(String path) {
		ArrayList<BankData> datosBancarios = new ArrayList<BankData>();
		String line = null;
		String separador = new String(";\n");
		try {
			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			while (line != null) {
				StringTokenizer token = new StringTokenizer(line, separador);
				{
					BankData cuenta = new BankData();
					
					cuenta.setID(token.nextToken());
					cuenta.setName(token.nextToken());
					cuenta.setSurname(token.nextToken());
					cuenta.setBirthday(token.nextToken());
					cuenta.setAccount(token.nextToken());
					datosBancarios.add(cuenta);
				}
				line = lector.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error al leer fichero");
		}
		return (ArrayList<BankData>) datosBancarios;
	}

}

