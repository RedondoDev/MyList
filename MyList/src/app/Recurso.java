package app;

import java.text.DecimalFormat;

public class Recurso {

	protected int codigo;
	protected String tipo;
	protected String nombre;
	protected String genero;
	protected int fecha;
	protected int puntuacion;
	protected boolean enLista;
	protected double progreso;
	protected String estado;

	public Recurso(int codigo, String tipo, String nombre, String genero, int fecha, int puntuacion, boolean enLista,
			double progreso, String estado) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.genero = genero;
		this.fecha = fecha;
		this.puntuacion = puntuacion;
		this.enLista = enLista;
		this.progreso = progreso;
		this.estado = estado;
	}

	public Recurso() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean isEnLista() {
		return enLista;
	}

	public void setEnLista(boolean enLista) {
		this.enLista = enLista;
	}

	public double getProgreso() {
		return progreso;
	}

	public void setProgreso(double progreso) {
		this.progreso = progreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void mostrarInfoLista() {
		DecimalFormat df = new DecimalFormat("#.##");

		if (enLista == true) {
			System.out.println("Tipo: " + this.tipo + "\nNombre: " + this.nombre + "\nGénero: " + this.genero
					+ "\nFecha: " + this.fecha);

			if (this.puntuacion >= 0 && this.puntuacion <= 10) {
				System.out.println("Puntuación : " + this.puntuacion + "/10");
			} else {
				System.out.println("Puntuación: Sin puntuar");
			}
			if (tipo != "Videojuego") {
				System.out.println("Progreso : " + df.format(this.progreso) + "%");
			}
			System.out.println("Estado : " + this.estado);
		}
	}

	public void mostrarRecurso() {
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("-----------");
		System.out.println("Tipo: " + this.tipo + "\nNombre: " + this.nombre + "\nGénero: " + this.genero + "\nFecha: "
				+ this.fecha);

		if (this.puntuacion >= 0 && this.puntuacion <= 10) {
			System.out.println("Puntuación : " + this.puntuacion);
		} else {
			System.out.println("Puntuación: Sin puntuar");
		}
		if (tipo != "Videojuego") {
			System.out.println("Progreso : " + df.format(this.progreso) + "%");
		}
		System.out.println("Estado: " + this.estado);
	}

}
