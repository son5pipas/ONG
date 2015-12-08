public class Usuario {
	private String nombre, apellidos, dni;

	public Usuario (){

	}
	
	public Usuario (String n, String a, String d){
		nombre = n;
		apellidos = a;
		dni = d;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}