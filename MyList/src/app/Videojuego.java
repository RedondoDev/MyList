package app;

public class Videojuego extends Recurso {

	private String plataforma;

	public Videojuego(int codigo, String tipo, String nombre, String genero, int fecha, int puntuacion, String estado,
			double progreso, boolean enLista, String plataforma) {
		super(codigo, tipo, nombre, genero, fecha, puntuacion, enLista, progreso, estado);
		this.plataforma = plataforma;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public void mostrarInfoLista() {
		if (enLista == true) {
			super.mostrarInfoLista();
			System.out.println("Plataforma: " + this.plataforma);
			System.out.println();
		}
	}

}
