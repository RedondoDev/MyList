package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuario {

	public Administrador(String nombre, int id, String contrasena) {
		super(nombre, id, contrasena);
	}

	public void mostrarInformacion() {
		super.mostrarInformacion();
	}

	public char menuAdministrador(char opcion, Usuario u, ArrayList<Usuario> usuarios) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n---" + u.getNombre() + " (Administrador)");
			System.out.println(
					"1) Perfil personal.\n2) Mostrar lista de usuarios\n3) Borrar usuario.\n4) Cerrar sesión.");
			opcion = sc.next().charAt(0);
			switch (opcion) {
			case '1':
				u.mostrarInformacion();
				break;
			case '2':
				mostrarListaUsuarios(usuarios);
				break;
			case '3':
				((Administrador) u).borrarUsuario(usuarios);
				break;
			case '4':
				System.out.println("\nVolviendo al menú de MyList.");
				break;
			default:
				System.out.println("Opción no válida.\n");
			}
		} while (opcion != '4');
		return opcion;
	}

	public boolean mostrarListaUsuarios(ArrayList<Usuario> usuarios) {
		boolean existe = false;

		for (Usuario u : usuarios) {
			if (u.getId() != 0) {
				u.mostrarInformacion();
				existe = true;
			}
		}

		if (!existe) {
			System.out.println("\nNo hay usuarios registrados.");
		}

		return existe;
	}

	public void borrarUsuario(ArrayList<Usuario> usuarios) {
		Scanner sc = new Scanner(System.in);

		boolean hayUsuarios = mostrarListaUsuarios(usuarios);
		if (!hayUsuarios) {
			return;
		}

		System.out.println("\nEscribe el ID del usuario que quieres eliminar ('0' para salir): ");
		int id = sc.nextInt();
		if (id == 0) {
			return;
		}

		boolean encontrado = false;
		for (Usuario u : usuarios) {
			if (u.getId() == id) {
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			System.out.println("El usuario con ID " + id + " no existe.");
			return;
		}

		System.out.println("\n¿Estás seguro de que deseas borrar al usuario con ID " + id + "? ('s'/'n')");
		char confirmacion = sc.next().toLowerCase().charAt(0);
		if (confirmacion == 's') {
			usuarios.removeIf(u -> u.getId() == id);
			System.out.println("Usuario eliminado correctamente.");
		} else {
			System.out.println("\nOperación cancelada.");
		}
	}

}
