package app;

public class Usuario {

	protected String nombre;
	protected int id;
	protected String contrasena;

	public Usuario(String nombre, int id, String contrasena) {
		this.nombre = nombre;
		this.id = id;
		this.contrasena = contrasena;
	}

	public Usuario() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void mostrarInformacion() {
		System.out.println("\n-----Usuario-----");
		System.out.println("Nombre: " + this.nombre + "\nId: " + this.id);
	}

}
