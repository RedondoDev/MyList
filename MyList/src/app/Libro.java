package app;

public class Libro extends Recurso {

	private int paginas;
	private String autor;

	public Libro(int codigo, String tipo, String nombre, String genero, int fecha, int puntuacion, boolean enLista,
			String estado, double progreso, int paginas, String autor) {
		super(codigo, tipo, nombre, genero, fecha, puntuacion, enLista, progreso, estado);
		this.paginas = paginas;
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public void mostrarInfoLista() {
		if (enLista == true) {
			super.mostrarInfoLista();
			System.out.println("Páginas: " + this.paginas + "\nAutor: " + this.autor);
			System.out.println();
		}
	}

}
