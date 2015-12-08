import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class ManejarFichero {
	
        private static BufferedReader lector;

        public static ArrayList<Usuario> leerFichero (String path){
        	
                ArrayList<Usuario> users = new ArrayList<Usuario>();
                String line = null;
                String separador = new String (";\n");
                try{
                        File f = new File (path);
                        lector = new BufferedReader (new FileReader (f));
                        line = lector.readLine();
                        while (line != null){
                                StringTokenizer token = new StringTokenizer (line, separador);
                                while (token.hasMoreTokens()){
                                //      System.out.println(token.nextToken());
                                        Usuario nuevo = new Usuario();
                                        nuevo.setNombre(token.nextToken());
                                        nuevo.setApellidos(token.nextToken());
                                        nuevo.setDni(token.nextToken());
                                        users.add(nuevo);
                                //      System.out.println(nuevo.getDni() + " " + nuevo.getApellidos() + " " + nuevo.getNombre());
                                }
                                line = lector.readLine();
                        }
                }catch(Exception e){
                        System.out.println("Error al leer fichero");
                }
                return (ArrayList<Usuario>) users;
        }

        public static void escribirFichero (ArrayList<Usuario> users, String path) throws IOException{
                File f = new File(path);
                FileOutputStream writer = new FileOutputStream(f);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));

                for (int i = 0; i < users.size(); i++) {
                        String linea = new String (users.get(i).getNombre() + ";" + users.get(i).getApellidos() + ";" + users.get(i).getDni());
                        bw.write(linea);
                        bw.newLine();
                }

                bw.close();

        }
}