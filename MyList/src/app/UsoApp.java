package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UsoApp {

	public static void main(String[] args) {

		// CREACIÓN DEL ADMIN Y AGREGACIÓN A LA LISTA DE USUARIOS
		//Todo hecho por Tymur. De nada :)
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Administrador("Javi", 0, "Admin"));

		int contadorId = 1;
		char opcion;

		// MENÚ
		System.out.println("Bienvenido a MyList");
		do {
			opcion = inicio();
			switch (opcion) {
			case '1':
				Usuario u = iniciarSesion(usuarios);
				if (u != null) {
					if (u instanceof Normal) {
						((Normal) u).menuNormal(opcion, u);
					} else if (u instanceof Administrador) {
						((Administrador) u).menuAdministrador(opcion, u, usuarios);
					}
				}
				break;
			case '2':
				Normal n = registrarse(contadorId, usuarios);
				usuarios.add(n);
				contadorId++;
				break;
			case '3':
				System.out.println("\nGracias por utilizar MyList. Vuelve pronto.");
				break;
			}
		} while (opcion != '3');
	}

	public static char inicio() {
		Scanner sc = new Scanner(System.in);
		char opcion;
		do {
			System.out.println("\n1. Iniciar sesión\n2. Registrarse\n3. Salir");
			opcion = sc.next().charAt(0);
			if (opcion != '1' && opcion != '2' && opcion != '3') {
				System.out.println("\nOpción incorrecta. Vuelve a intentarlo.");
			}
		} while (opcion != '1' && opcion != '2' && opcion != '3');
		return opcion;
	}

	public static Usuario iniciarSesion(ArrayList<Usuario> usuarios) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nNombre: ");
		String nombre = sc.nextLine();
		System.out.print("Contraseña: ");
		String contrasena = sc.nextLine();
		for (Usuario u : usuarios) {
			if (u.getNombre().equals(nombre) && u.getContrasena().equals(contrasena)) {
				return u;
			}
		}
		System.out.println("El nombre de usuario o la contraseña no son correctos.");

		return null;
	}

	public static Normal registrarse(int contadorId, ArrayList<Usuario> usuarios) {
		Scanner sc = new Scanner(System.in);

		boolean longitud, may1, min1, num, caract = false;

		// LOGROS VACÍOS
		ArrayList<Logro> logros = new ArrayList<>();
		ArrayList<Recurso> recursos = new ArrayList<>();
		Normal n = new Normal(recursos, logros);

		// ID
		n.setId(contadorId);

		// NOMBRE
		do {
			longitud = false;
			may1 = false;
			min1 = false;
			System.out.print("\nNombre de usuario: ");
			n.setNombre(sc.nextLine());
			boolean existe = false;
			for (Usuario u : usuarios) {
				if (u.getNombre().equals(n.getNombre())) {
					existe = true;
				}
			}

			// MANEJO USUARIO REPETIDO
			if (existe) {
				System.out.println("El nombre de usuario ya existe. Inténtelo de nuevo.");
				continue;
			}

			// NOMBRE CORRECTO
			if (n.getNombre().length() >= 2 && n.getNombre().length() <= 16) {
				longitud = true;
			} else {
				System.out.println("El nombre debe tener entre 2 y 16 caracteres.");
			}
			if (!n.getNombre().isEmpty() && n.getNombre().charAt(0) >= 'A' && n.getNombre().charAt(0) <= 'Z') {
				may1 = true;
			} else {
				System.out.println("El nombre debe comenzar por letra mayúscula.");
			}
			for (int i = 1; i < n.getNombre().length(); i++) {
				if (n.getNombre().charAt(i) >= 'a' && n.getNombre().charAt(i) <= 'z') {
					caract = true;
				} else {
					System.out.println(
							"El nombre sólo puede tener la primera letra mayúscula y no puede contener caracteres especiales.");
					caract = false;
					break;
				}
			}

		} while (longitud == false || may1 == false || caract == false);

		// CONTRASEÑA
		do {
			longitud = false;
			may1 = false;
			min1 = false;
			num = false;

			System.out.print("Contraseña: ");
			n.setContrasena(sc.nextLine());

			if (n.getContrasena().length() >= 8 && n.getContrasena().length() <= 16) {
				longitud = true;
			}

			for (int i = 0; i < n.getContrasena().length(); i++) {
				if (n.getContrasena().charAt(i) >= 'A' && n.getContrasena().charAt(i) <= 'Z') {
					may1 = true;
				} else if (n.getContrasena().charAt(i) >= 'a' && n.getContrasena().charAt(i) <= 'z') {
					min1 = true;
				} else if (n.getContrasena().charAt(i) >= '0' && n.getContrasena().charAt(i) <= '9') {
					num = true;
				}
			}

			if (!longitud) {
				System.out.println("La contraseña debe tener entre 8 y 16 caracteres.");
			}
			if (!may1) {
				System.out.println("La contraseña debe contener al menos una letra mayúscula.");
			}
			if (!min1) {
				System.out.println("La contraseña debe contener al menos una letra minúscula.");
			}
			if (!num) {
				System.out.println("La contraseña debe contener al menos un número.");
			}

		} while (longitud == false || may1 == false || min1 == false || num == false);

		// VERIFICAR CONTRASEÑA
		String contrasena2;
		do {
			System.out.print("Vuelve a escribir la contraseña: ");
			contrasena2 = sc.nextLine();
			if (n.getContrasena().equals(contrasena2)) {
				System.out.println("Contraseña correcta. \n\nUsuario creado correctamente.");
			} else {
				System.out.println("Las contraseñas no coinciden.");
			}
		} while (!n.getContrasena().equals(contrasena2));

		// FECHA SUSCRIPCIÓN
		n.setFechaSuscripcion(LocalDate.now());

		return n;
	}

}
