package app;

public class Anime extends Recurso {

	private int temporadas;
	private int capitulos;

	public Anime(int codigo, String tipo, String nombre, String genero, int fecha, int puntuacion, String estado,
			double progreso, boolean enLista, int temporadas, int capitulos) {
		super(codigo, tipo, nombre, genero, fecha, puntuacion, enLista, progreso, estado);
		this.temporadas = temporadas;
		this.capitulos = capitulos;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public int getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}

	@Override
	public void mostrarInfoLista() {
		if (enLista == true) {
			super.mostrarInfoLista();
			System.out.println("Temporadas: " + this.temporadas + "\nCapítulos: " + this.capitulos);
			System.out.println();
		}
	}

}
