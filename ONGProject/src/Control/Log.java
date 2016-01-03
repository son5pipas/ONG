package Control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Modelo.Comment;

public class Log {
	// Variables staticas que las llamaremos en las clases en las que
	// queramoshacer un
	// registro de la fecha en que se ha hecho cualquier accion:
	private static String logFile;
	private static String errorFile;

	/*
	 * @param String file
	 * 
	 * @return logFile=file;
	 */
	public static void setLogFile(String file) {
		logFile = file;
	}

	/*
	 * @param String String user, String module, String action, String aux
	 * 
	 * @return
	 */
	public static void addLog(String user, String module, String action, String aux) {

		File f = new File(logFile);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			bw.write(dateFormat.format(date) + ";" + user + ";" + module + ";" + action + ";" + aux);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @param String file
	 * 
	 * @return
	 */
	public static void setErrorFile(String file) {
		errorFile = file;
	}

	/*
	 * @param String user, String module, String action, String aux
	 * 
	 * @return
	 */
	public static void addError(String user, String module, String action, String aux) {

		File f = new File(errorFile);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(f, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			bw.write(dateFormat.format(date) + ";" + user + ";" + module + ";" + action + ";" + aux);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
