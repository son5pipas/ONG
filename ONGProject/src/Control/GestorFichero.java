package Control;

import Modelo.Comment;
import Modelo.Donation;
import Modelo.User;
import Vista.Imagen;
import Modelo.BankData;
import Modelo.Campaign;
import Modelo.Proposal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestorFichero {

	private static BufferedReader lector;

	/*
	 * Funcion para realizar la lectura de Fichero de usuarios
	 * 
	 * @param String path, String pathAccount
	 * 
	 * @return (ArrayList<User>) users;
	 */
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
					usuario.setSex(token.nextToken());
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
			Log.addError("GestorFichero", "User", "Error al leer fichero usuarios", "");
		}

		return (ArrayList<User>) users;
	}

	/*
	 * Funcion para realizar la lectura de Fichero de datos bancarios
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<BankData>) datosBancarios;
	 */
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
			Log.addError("GestorFichero", "DataBank", "Error al leer fichero datos bancarios", "");
		}
		return (ArrayList<BankData>) datosBancarios;
	}

	/*
	 * Funcion para realizar la escritura de Fichero de usuarios
	 * 
	 * @param ArrayList<User> users, String path, String pathAccount
	 * 
	 * @return
	 */
	public static void escribirFicheroUsuarios(ArrayList<User> users, String path, String pathAccount) {
		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < users.size(); i++) {
				String linea = new String(users.get(i).getID() + ";" + users.get(i).getUser() + ";"
						+ users.get(i).getPassword() + ";" + users.get(i).getAddress() + ";" + users.get(i).getKarma()
						+ ";" + users.get(i).isErase() + ";" + users.get(i).getSex() + ";" + users.get(i).getRole()
						+ ";" + users.get(i).getPhoto());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "User", "Error al escribir fichero usuarios", "");
		}
		escribirFicheroBanco(users, pathAccount);
	}

	/*
	 * Funcion para realizar la escritura de Fichero de datos bancarios
	 * 
	 * @param ArrayList<User> users, String path
	 * 
	 * @return
	 */
	public static void escribirFicheroBanco(ArrayList<User> users, String path) {
		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < users.size(); i++) {
				String linea = new String(users.get(i).getData().getID() + ";" + users.get(i).getData().getName() + ";"
						+ users.get(i).getData().getSurname() + ";" + users.get(i).getData().getBirthday() + ";"
						+ users.get(i).getData().getAccount());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "DataBank", "Error al escribir fichero datos bancarios", "");
		}
	}

	/*
	 * Funcion para realizar la lectura de Fichero de propuestas
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<Proposal>) datosPropuestas;
	 */
	public static ArrayList<Proposal> leerFicheroProposal(String path) {

		ArrayList<Proposal> datosPropuestas = new ArrayList<Proposal>();
		String line = null;
		String separador = new String(";");
		String separador2 = new String(",");

		try {
			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			while (line != null) {

				StringTokenizer token = new StringTokenizer(line, separador);

				Proposal propuesta = new Proposal();
				propuesta.setID(token.nextToken());
				propuesta.setTitle(token.nextToken());
				propuesta.setDescription(token.nextToken());
				propuesta.setPhoto(token.nextToken());
				propuesta.setIdUser(Integer.parseInt(token.nextToken()));

				StringTokenizer token2 = new StringTokenizer(token.nextToken(), separador2);
				while (token2.hasMoreTokens()) {
					propuesta.setIdComment(Integer.parseInt(token2.nextToken()));
				}

				StringTokenizer token3 = new StringTokenizer(token.nextToken(), separador2);
				while (token3.hasMoreTokens()) {
					propuesta.setIdVote(Integer.parseInt(token3.nextToken()));
				}

				propuesta.setErase(token.nextToken());

				datosPropuestas.add(propuesta);
				line = lector.readLine();

			}
		} catch (Exception e) {
			System.out.println("Error al leer fichero PROPUESTA ");
			Log.addError("GestorFichero", "Propuesta", "Error al leer fichero propuesta", "");
		}
		return (ArrayList<Proposal>) datosPropuestas;
	}

	/*
	 * Funcion para realizar la lectura de Fichero de comentarios
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<Comment>) comentarios;
	 */
	public static ArrayList<Comment> leerFicheroComments(String path) {

		ArrayList<Comment> comentarios = new ArrayList<Comment>();
		String line = null;
		String separador = ";";
		String separador2 = ",";

		try {

			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();

			while (line != null) {

				StringTokenizer token = new StringTokenizer(line, separador);

				Comment comentario = new Comment();
				comentario.setID(Integer.parseInt(token.nextToken()));
				comentario.setComment(token.nextToken());
				comentario.setIdUser(Integer.parseInt(token.nextToken()));

				StringTokenizer token2 = new StringTokenizer(token.nextToken(), separador2);
				while (token2.hasMoreTokens()) {
					comentario.setIdVoteOK(Integer.parseInt(token2.nextToken()));
				}

				StringTokenizer token3 = new StringTokenizer(token.nextToken(), separador2);
				while (token3.hasMoreTokens()) {
					comentario.setIdVoteKO(Integer.parseInt(token3.nextToken()));
				}

				comentarios.add(comentario);
				line = lector.readLine();

			}
		}

		catch (Exception e) {
			System.out.println("Error al leer fichero Comentario");
			Log.addError("GestorFichero", "Comment", "Error al leer fichero comentarios", "");
		}

		return (ArrayList<Comment>) comentarios;

	}

	/*
	 * Funcion para realizar la escritura de Fichero de propuestas
	 * 
	 * @param String path, ArrayList<Proposal> propuestas
	 * 
	 * @return
	 */
	public static void escribirPropuesta(String path, ArrayList<Proposal> propuestas) {

		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < propuestas.size(); i++) {
				String linea = new String(propuestas.get(i).getID() + ";" + propuestas.get(i).getTitle() + ";"
						+ propuestas.get(i).getDescription() + ";" + propuestas.get(i).getPhoto() + ";"
						+ propuestas.get(i).getIdUser() + ";" + propuestas.get(i).getStringIdComments() + ";"
						+ propuestas.get(i).getStringIdVotes() + ";" + propuestas.get(i).isErase());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "Propuesta", "Error al escribir fichero propuestas", "");
		}

	}

	/*
	 * Funcion para realizar la escritura de Fichero de comentarios
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<Proposal>) datosPropuestas;
	 */
	public static void escribirComentario(String path, ArrayList<Comment> comentarios) {

		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < comentarios.size(); i++) {
				String linea = new String(comentarios.get(i).getID() + ";" + comentarios.get(i).getComment() + ";"
						+ comentarios.get(i).getIdUser() + ";" + comentarios.get(i).getStringIdVotesOK() + ";"
						+ comentarios.get(i).getStringIdVotesKO());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "Comment", "Error al escribir fichero comentarios", "");
		}

	}

	/*
	 * Funcion para realizar la lectura de Fichero de campañas
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<Campaign>) campanas;
	 */
	public static ArrayList<Campaign> leerFicheroCampaigns(String path) {

		ArrayList<Campaign> campanas = new ArrayList<Campaign>();

		String line = null;
		String separador = new String(";\n");
		String separador2 = new String(",\n");

		try {
			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			while (line != null) {
				StringTokenizer token = new StringTokenizer(line, separador);

				Campaign campana = new Campaign();

				campana.setID(Integer.parseInt(token.nextToken()));
				campana.setTitle(token.nextToken());
				campana.setDescription(token.nextToken());
				campana.setPhoto(token.nextToken());
				campana.setIdUser(Integer.parseInt(token.nextToken()));

				StringTokenizer token2 = new StringTokenizer(token.nextToken(), separador2);

				while (token2.hasMoreTokens()) {
					campana.setIdDonation(token2.nextToken());
				}

				campanas.add(campana);
				line = lector.readLine();
			}

		} catch (Exception e) {
			System.out.println("Error al leer fichero campana");
			Log.addError("GestorFichero", "Campaign", "Error al leer fichero campana", "");
		}

		return (ArrayList<Campaign>) campanas;
	}

	/*
	 * Funcion para realizar la escritura de Fichero de campañas
	 * 
	 * @param String path, ArrayList<Campaign> campanas
	 * 
	 * @return
	 */
	public static void escribirFicheroCampaigns(String path, ArrayList<Campaign> campanas) {

		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < campanas.size(); i++) {
				String linea = new String(campanas.get(i).getID() + ";" + campanas.get(i).getTitle() + ";"
						+ campanas.get(i).getDescription() + ";" + campanas.get(i).getPhoto() + ";"
						+ campanas.get(i).getIdUser() + ";" + campanas.get(i).getStringIdDonations());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "Campaign", "Error al escribir fichero campana", "");
		}
	}

	/*
	 * Funcion para realizar la lectura de Fichero de donaciones
	 * 
	 * @param String path
	 * 
	 * @return (ArrayList<Donation>) donaciones;
	 */
	public static ArrayList<Donation> leerFicheroDonacion(String path) {
		ArrayList<Donation> donaciones = new ArrayList<Donation>();

		String line = null;
		String separador = new String(",");

		try {
			File f = new File(path);
			lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			while (line != null) {
				StringTokenizer token = new StringTokenizer(line, separador);

				Donation donacion = new Donation();

				donacion.setId(Integer.parseInt(token.nextToken()));
				donacion.setIdBankdate(Integer.parseInt(token.nextToken()));
				donacion.setMoney(Integer.parseInt(token.nextToken()));

				donaciones.add(donacion);
				line = lector.readLine();
			}

		} catch (Exception e) {
			System.out.println("Error al leer fichero donaciones");
			Log.addError("GestorFichero", "Donacion", "Error al leer fichero donaciones", "");
		}

		return (ArrayList<Donation>) donaciones;
	}

	/*
	 * Funcion para realizar la escritura de Fichero de donaciones
	 * 
	 * @param String path, ArrayList<Donation> donaciones
	 * 
	 * @return
	 */
	public static void escribirFicheroDonacion(String path, ArrayList<Donation> donaciones) {
		File f = new File(path);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			for (int i = 0; i < donaciones.size(); i++) {
				String linea = new String(donaciones.get(i).getId() + "," + donaciones.get(i).getIdBankdate() + ","
						+ donaciones.get(i).getMoney());
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.addError("GestorFichero", "Donacion", "Error al escribir fichero donaciones", "");
		}
	}
}
