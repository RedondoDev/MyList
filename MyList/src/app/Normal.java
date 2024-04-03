package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Normal extends Usuario {

	private ArrayList<Recurso> recursos = new ArrayList<>();
	private ArrayList<Logro> logros = new ArrayList<>();
	private LocalDate fechaSuscripcion;

	public Normal(String nombre, int id, String contrasena, ArrayList<Recurso> recursos, ArrayList<Logro> logros,
			LocalDate fechaSuscripcion) {
		super(nombre, id, contrasena);
		this.recursos = recursos;
		this.logros = logros;
		this.fechaSuscripcion = fechaSuscripcion;
	}

	public Normal(ArrayList<Recurso> recursos, ArrayList<Logro> logros) {
		this.recursos = crearRecursos();
		this.logros = crearLogros();
	}

	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}

	public ArrayList<Logro> getLogros() {
		return logros;
	}

	public void setLogros(ArrayList<Logro> logros) {
		this.logros = logros;
	}

	public LocalDate getFechaSuscripcion() {
		return fechaSuscripcion;
	}

	public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}

	public char menuNormal(char opcion, Usuario u) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n---" + u.getNombre());
			System.out.println(
					"1) Perfil personal.\n2) MyList.\n3) Explorar recursos.\n4) Ver tus logros.\n5) Cerrar sesión.");
			opcion = sc.next().charAt(0);
			switch (opcion) {
			case '1':
				mostrarInformacion();
				break;
			case '2':
				mostrarListaPers(u);
				break;
			case '3':
				char opcionRecursos;
				do {
					System.out.println("\nEXPLORADOR DE RECURSOS:");
					System.out.println(
							"1) General.\n2) Libros.\n3) Animes.\n4) Videojuegos.\n5) Volver al menú de usuario.");
					opcionRecursos = sc.next().charAt(0);
					switch (opcionRecursos) {
					case '1', '2', '3', '4':
						System.out.println();
						buscarRecursos(opcionRecursos, recursos);
						anadirRecurso();
						break;
					case '5':
						System.out.println("\nRegresando al menú de usuario.");
						break;
					default:
						System.out.println("\nOpción no válida.");
					}
				} while (opcionRecursos != '5');
				break;
			case '4':
				char opcionLogros;
				do {
					System.out.println("\nMENÚ DE LOGROS de " + u.getNombre() + ":");
					System.out.println(
							"1) Logros de libros.\n2) Logros de anime.\n3) Logros de videojuegos.\n4) Todos los logros.\n5) Volver al menú de usuario.");
					opcionLogros = sc.next().charAt(0);
					switch (opcionLogros) {
					case '1':
						mostrarLogrosLibros();
						break;
					case '2':
						mostrarLogrosAnime();
						break;
					case '3':
						mostrarLogrosVideojuegos();
						break;
					case '4':
						mostrarTodosLogros();
						break;
					case '5':
						System.out.println("\nRegresando al menú de usuario.");
						break;
					default:
						System.out.println("\nOpción no válida.");
					}
				} while (opcionLogros != '5');
				break;
			case '5':
				System.out.println("\nVolviendo al menú de MyList.");
				break;
			default:
				System.out.println("\nOpción no válida. ");
			}
		} while (opcion != '5');
		return opcion;
	}

	public void buscarRecursos(char opcionRecursos, ArrayList<Recurso> recursos) {
		if (opcionRecursos == '1') {
			for (Recurso r : recursos) {
				r.mostrarRecurso();
				System.out.println();
			}
		} else if (opcionRecursos == '2') {
			for (Recurso r : recursos) {
				if (r.getTipo().equals("Libro")) {
					r.mostrarRecurso();
					System.out.println();
				}
			}
		} else if (opcionRecursos == '3') {
			for (Recurso r : recursos) {
				if (r.getTipo().equals("Anime")) {
					r.mostrarRecurso();
					System.out.println();
				}
			}
		} else if (opcionRecursos == '4') {
			for (Recurso r : recursos) {
				if (r.getTipo().equals("Videojuego")) {
					r.mostrarRecurso();
					System.out.println();
				}
			}
		}
	}

	public void anadirRecurso() {
		Scanner sc = new Scanner(System.in);
		char seleccion;
		do {
			System.out.println("1) Añadir recurso a mi lista.\n2) Volver al explorador de recursos.");
			seleccion = sc.next().charAt(0);
			switch (seleccion) {
			case '1':
				System.out.println("\nEscribe el nombre del recurso que deseas añadir a tu lista: ");
				sc.nextLine();
				String nombreR = sc.nextLine();
				boolean esta = false;
				for (Recurso r : recursos) {
					if (nombreR.equalsIgnoreCase(r.getNombre())) {
						r.setEnLista(true);
						r.setEstado("Pendiente");
						r.setPuntuacion(-1);
						r.setProgreso(0);
						esta = true;
						System.out.println("\n" + r.getNombre() + " se ha añadido a tu MyList.\n");
					}
				}
				if (!esta) {
					System.out.println("\nNo hay ningún recurso con ese nombre.\n");
				}
				break;
			case '2':
				break;
			default:
				System.out.println("Opción inválida. Inténtelo de nuevo.\n");
			}
		} while (seleccion != '2');
	}

	public void mostrarInformacion() {
		super.mostrarInformacion();
		System.out.println("Fecha suscripción: " + this.fechaSuscripcion);
	}

	public void mostrarListaPers(Usuario u) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nLista personal: ");
		boolean hay = false;
		for (Recurso r : recursos) {
			if (r.isEnLista() == true) {
				hay = true;
				r.mostrarRecurso();
			}
		}
		if (hay == true) {
			char elige;
			do {
				System.out.println(
						"\n1) Cambiar progreso del recurso.\n2) Borrar recurso de MyList.\n3) Puntuar recurso.\n4) Volver.");
				elige = sc.next().charAt(0);
				String nombreR;
				switch (elige) {
				case '1':
					System.out.print("\nNombre del recurso: ");
					sc.nextLine();
					nombreR = sc.nextLine();
					cambiarEstado(nombreR);
					break;
				case '2':
					System.out.println("\n¿Qué recurso quieres quitar de tu MyList?");
					sc.nextLine();
					nombreR = sc.nextLine();
					boolean esta = false;
					for (Recurso r : recursos) {
						if (nombreR.equalsIgnoreCase(r.getNombre())) {
							esta = true;
							r.setEnLista(false);
						}
					}
					if (esta == false) {
						System.out.println(nombreR + " no está en tu lista.");
					}
					break;
				case '3':
					boolean paraPuntuar = false;
					for (Recurso r : recursos) {
						if (r.isEnLista() == true && r.getEstado().equals("Terminado")) {
							paraPuntuar = true;
							System.out.println();
							r.mostrarRecurso();
						}
					}
					if (paraPuntuar == false) {
						System.out.println("\nNo has terminado ningún recurso. Termina un recurso para poder puntuarlo.");
					} else {
						System.out.print("\n¿Qué recurso deseas puntuar? ");
						sc.nextLine();
						nombreR = sc.nextLine();
						int puntuacion;
						for (Recurso r : recursos) {
							if (nombreR.equalsIgnoreCase(r.getNombre()) && r.estado.equals("Terminado")) {
								System.out.print("\nPuntuación(0-10): ");
								puntuacion = sc.nextInt();
								if (puntuacion >= 0 && puntuacion <= 10) {
									r.setPuntuacion(puntuacion);
									System.out.println("Puntuación de " + r.getNombre() + " guardada.");
								} else {
									System.out.println("Puntuación no válida.");
								}
							}
						}
					}
					break;
				default:
					System.out.println("Opción inválida. Vuelve a intentarlo.");
				}
			} while (elige != '4');

		} else {
			System.out.println("No hay recursos en tu lista.");
		}
	}

	public void cambiarEstado(String nombreR) {
		Scanner sc = new Scanner(System.in);

		double progreso;
		char pasado;
		boolean esta = false;
		for (Recurso r : recursos) {
			if (nombreR.equalsIgnoreCase(r.getNombre()) && r.isEnLista() == true) {
				esta = true;
				if (r.getTipo().equals("Libro")) {
					System.out.print("¿Por qué página vas? ");
					progreso = sc.nextInt();
					sc.nextLine();
					if (progreso >= 0 && progreso <= ((Libro) r).getPaginas()) {
						r.setProgreso(progreso / ((Libro) r).getPaginas() * 100);
						if (r.getProgreso() == 0.0) {
							r.setEstado("Pendiente");
						} else if (r.getProgreso() > 0 && r.getProgreso() < 100) {
							r.setEstado("En curso");
						} else {
							r.setEstado("Terminado");
						}
						System.out.println("\nProgreso y estado de " + r.getNombre() + " actualizados.");
					} else {
						System.out.println("\nNúmero de páginas incorrecto.");
					}
				} else if (r.getTipo().equals("Anime")) {
					System.out.print("¿Por qué capítulo vas? ");
					progreso = sc.nextInt();
					sc.nextLine();
					if (progreso >= 0 && progreso <= ((Anime) r).getCapitulos()) {
						r.setProgreso(progreso / ((Anime) r).getCapitulos() * 100);
						if (r.getProgreso() == 0.0) {
							r.setEstado("Pendiente");
						} else if (r.getProgreso() > 0 && r.getProgreso() < 100) {
							r.setEstado("En curso");
						} else {
							r.setEstado("Terminado");
						}
						System.out.println("\nProgreso y estado de " + r.getNombre() + " actualizados.");
					} else {
						System.out.println("\nNúmero de capítulo incorrecto.");
					}
				} else if (r.getTipo().equals("Videojuego")) {
					System.out.print("¿Cómo llevas el videojuego? 1) Pendiente\t2) En curso\t3) Terminado ");
					pasado = sc.next().charAt(0);
					switch (pasado) {
					case '1':
						r.setEstado("Pendiente");
						System.out.println("\nEstado de " + r.getNombre() + " actualizado.");
						break;
					case '2':
						r.setEstado("En curso");
						System.out.println("\nEstado de " + r.getNombre() + " actualizado.");
						break;
					case '3':
						r.setEstado("Terminado");
						System.out.println("\nEstado de " + r.getNombre() + " actualizado.");
						break;
					default:
						System.out.println("Opción inválida.");
					}
				} else {
					System.out.println("\nNúmero de capítulo incorrecto.");
				}
			}
		}
		if (esta == false) {
			System.out.println("\n" + nombreR + " no se encuentra en tu MyList.");
		}
	}

	public ArrayList<Logro> crearLogros() {
		// Logros Libro
		logros.add(new Logro("No sé leer.", "Crea tu cuenta.", 100.0, "Libro"));
		logros.add(new Logro("Lector primerizo.", "Comienza a leer tu primer libro.", 0.0, "Libro"));
		logros.add(new Logro("¡Lo terminé!", "Termina de leer tu primer libro.", 0.0, "Libro"));
		logros.add(new Logro("Amante de la lectura.", "Termina de leer 5 libros.", 0.0, "Libro"));
		logros.add(new Logro("El puto Espronceda.", "Termina de leer 10 libros.", 0.0, "Libro"));

		// Logros Anime
		logros.add(new Logro("No veo anime.", "Crea tu cuenta.", 100.0, "Anime"));
		logros.add(new Logro("Fanático del anime.", "Empieza a ver tu primer anime.", 0.0, "Anime"));
		logros.add(new Logro("¡Fin de la temporada!", "Termina de ver tu primer anime.", 0.0, "Anime"));
		logros.add(new Logro("Maratón.", "Termina de ver 5 animes.", 0.0, "Anime"));
		logros.add(new Logro("Te huele el sobaco.", "Termina de ver 10 animes.", 0.0, "Anime"));

		// Logros Videojuego
		logros.add(new Logro("No juego a nada.", "Crea tu cuenta.", 100.0, "Videojuego"));
		logros.add(new Logro("Noob.", "Empieza a jugar tu primer videojuego.", 0.0, "Videojuego"));
		logros.add(new Logro("¡Lo gané!", "Termina tu primer videojuego.", 0.0, "Videojuego"));
		logros.add(new Logro("Viciado.", "Termina 5 videojuegos.", 0.0, "Videojuego"));
		logros.add(new Logro("Busca un trabajo.", "Termina 10 videojuegos.", 0.0, "Videojuego"));

		return logros;
	}

	public ArrayList<Recurso> crearRecursos() {
		// Recursos Libro
		int codigo = 0;
		recursos.add(new Libro(codigo++, "Libro", "Don Quijote de la Mancha", "Novela, Sátira", 1605, -1, false,
				"No está en tu lista", 0.0, 1560, "Miguel de Cervantes"));
		recursos.add(new Libro(codigo++, "Libro", "Gerónimo Stilton en el reino de la fantasia", "Infantil, Ficción",
				2003, -1, false, "No está en tu lista", 0.0, 384, "Elisabetta Dami"));
		recursos.add(new Libro(codigo++, "Libro", "La hija del loto", "Novela, Aventura", 2022, -1, false,
				"No está en tu lista", 0.0, 409, "Paloma Orozco"));
		recursos.add(new Libro(codigo++, "Libro", "Rey de la ira", "Novela, Romántica", 2024, -1, false,
				"No está en tu lista", 0.0, 432, "Ana Huang"));
		recursos.add(new Libro(codigo++, "Libro", "El barco de Teseo", "Novela, Fantasía", 2023, -1, false,
				"No está en tu lista", 0.0, 472, "J. J. Abrams, Doug Dorst"));

		// Recursos Anime
		recursos.add(new Anime(codigo++, "Anime", "Kimetsu no Yaiba", "Shounen", 2016, -1, "No está en tu lista", 0.0,
				false, 3, 55));
		recursos.add(new Anime(codigo++, "Anime", "Full Metal Alchemist Brotherhood", "Shounen", 2009, -1,
				"No está en tu lista", 0.0, false, 1, 64));
		recursos.add(new Anime(codigo++, "Anime", "Boku no Hero", "Shounen", 2015, -1, "No está en tu lista", 0.0, false,
				7, 108));
		recursos.add(
				new Anime(codigo++, "Anime", "Haikyuu", "Deporte", 2020, -1, "No está en tu lista", 0.0, false, 4, 110));
		recursos.add(
				new Anime(codigo++, "Anime", "Boku no Pico", "Yaoi", 2006, -1, "No está en tu lista", 0.0, false, 3, 3));

		// Recursos Videojuego
		recursos.add(new Videojuego(codigo++, "Videojuego", "League of Legends", "Moba", 2009, -1, "No está en tu lista",
				0.0, false, "PC"));
		recursos.add(new Videojuego(codigo++, "Videojuego", "Stardew Valley", "Gestión, Simulación", 2016, -1,
				"No está en tu lista", 0.0, false, "PS4, PSVita, XboxOne, Switch, PC"));
		recursos.add(new Videojuego(codigo++, "Videojuego", "Kingdom Hearts", "Acción, Aventuras", 2002, -1,
				"No está en tu lista", 0.0, false, "PlayStation 2, PSP, GBA, DS, 3DS, PS3, PS4, XboxOne, Switch, PC"));
		recursos.add(new Videojuego(codigo++, "Videojuego", "Clash Royale", "Estrategia", 2016, -1,
				"No está en tu lista", 0.0, false, "Móvil"));
		recursos.add(new Videojuego(codigo++, "Videojuego", "Golden Sun", "RPG", 2001, -1, "No está en tu lista", 0.0,
				false, "Switch, GBA, WiiU"));

		return recursos;
	}

	public void mostrarLogrosLibros() {
		int cont = 0;
		System.out.println("\n--------LOGROS LIBROS--------");
		System.out.println("-COMPLETADOS");
		for (Logro l : logros) {
			if (l.getProgreso() == 100 && l.getTipo().equals("Libro")) {
				l.mostrarInfoLogro();
			}
		}
		System.out.println("-EN CURSO");
		for (Logro l : logros) {
			if (l.getProgreso() > 0 && l.getProgreso() < 100 && l.getTipo().equals("Libro")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
		System.out.println("-OTROS LOGROS");
		cont = 0;
		for (Logro l : logros) {
			if (l.getProgreso() == 0 && l.getTipo().equals("Libro")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
	}

	public void mostrarLogrosAnime() {
		int cont = 0;
		System.out.println("\n--------LOGROS ANIME--------");
		System.out.println("-COMPLETADOS");
		for (Logro l : logros) {
			if (l.getProgreso() == 100 && l.getTipo().equals("Anime")) {
				l.mostrarInfoLogro();
			}
		}
		System.out.println("-EN CURSO");
		for (Logro l : logros) {
			if (l.getProgreso() > 0 && l.getProgreso() < 100 && l.getTipo().equals("Anime")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
		System.out.println("-OTROS LOGROS");
		cont = 0;
		for (Logro l : logros) {
			if (l.getProgreso() == 0 && l.getTipo().equals("Anime")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
	}

	public void mostrarLogrosVideojuegos() {
		int cont = 0;
		System.out.println("\n--------LOGROS VIDEOJUEGOS--------");
		System.out.println("-COMPLETADOS");
		for (Logro l : logros) {
			if (l.getProgreso() == 100 && l.getTipo().equals("Videojuego")) {
				l.mostrarInfoLogro();
			}
		}
		System.out.println("-EN CURSO");
		for (Logro l : logros) {
			if (l.getProgreso() > 0 && l.getProgreso() < 100 && l.getTipo().equals("Videojuego")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
		System.out.println("-OTROS LOGROS");
		cont = 0;
		for (Logro l : logros) {
			if (l.getProgreso() == 0 && l.getTipo().equals("Videojuego")) {
				l.mostrarInfoLogro();
				cont++;
			}
		}
		if (cont == 0) {
			System.out.println("\tNo hay ningún logro.");
		}
	}

	public void mostrarTodosLogros() {
		mostrarLogrosLibros();
		mostrarLogrosAnime();
		mostrarLogrosVideojuegos();
	}

}
