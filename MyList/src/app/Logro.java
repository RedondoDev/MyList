package app;

public class Logro {

	private String nombre;
	private String descripcion;
	private double progreso;
	private String tipo;

	public Logro(String nombre, String descripcion, double progreso, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.progreso = progreso;
		this.tipo = tipo;
	}

	public Logro() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getProgreso() {
		return progreso;
	}

	public void setProgreso(double progreso) {
		this.progreso = progreso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void mostrarInfoLogro() {
		System.out.println("\tProgreso: " + this.progreso + "%" + "\t" + this.nombre + "\tDescripción: " + this.descripcion);
	}
	
}
