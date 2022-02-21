package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	private static AccesoDatos ad = new AccesoDatos();
	private static Scanner t = new java.util.Scanner(System.in);
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Mostrar Socios");
				System.out.println("3-Crear Revista");
				System.out.println("4-Mostrar Libros");
				System.out.println("5-Prestar Libro");
				System.out.println("6-Devolver Préstamos");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1:
					crearsocio();
					break;

				case 2:
					mostrarsocio();
					break;

				case 3:
					crearRevsita();
					break;

				case 4:
					mostrarLibros();
					break;
				case 5:
					prestarLibro();
					break;
				case 6:
					devolverLibro();
					break;

				}
			} while (opcion != 0);
			ad.cerrar();

		}
	}

	private static void devolverLibro() {
		// TODO Auto-generated method stub
		mostrarLibros();
		System.out.println("Isbn:");
		String isbn = t.nextLine();
		if(ad.existePublicacion(isbn)) {
			System.out.println("Id socio:");
			int id = t.nextInt(); t.nextLine();
			if(!ad.borrarPrestamo(isbn,id)) {
				System.out.println("Error al borrar el préstamo");
			}
		}
	}

	private static void prestarLibro() {
		// TODO Auto-generated method stub
		mostrarsocio();
		System.out.println("Id Socio:");
		int id = t.nextInt(); t.nextLine();
		if(ad.existeSocio(id)) {
			mostrarLibros();
			System.out.println("ISBN:");
			String isbn = t.nextLine();
			if(ad.existePublicacion(isbn)) {
				if(!ad.registrarPrestamo(isbn, id)) {
					System.out.println("Error al crear el préstamo");
				}
			}
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> libros = ad.obtenerLibros();
		for (Libro l : libros) {
			l.mostrar(true);
		}
	}

	private static void crearRevsita() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce ISBN:");
			String isbn = t.nextLine();

			if (!ad.existePublicacion(isbn)) {
				Revista r = new Revista();
				r.setIsbn(isbn);
				System.out.println("Introduce Título:");
				r.setTitulo(t.nextLine());
				System.out.println("Introduce Nº Ejemplares:");
				r.setNumEjem(t.nextInt());
				t.nextLine();
				System.out.println("Introduce Género:");
				r.setGenero(t.nextLine());
				System.out.println("Introduce Fecha Publicación:");

				r.setFechaP(formato.parse(t.nextLine()));
				if (!ad.crearRevista(r)) {
					System.out.println("Error al crear la revista");
				}
			} else {
				System.out.println("Error, ya existe una publicación con ese isbn");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Fecha incorrecta");
		}
	}

	private static void mostrarsocio() {
		// TODO Auto-generated method stub
		ArrayList<Socio> socios = ad.obtenerSocios();
		for (Socio s : socios) {
			s.mostrar();
		}
	}

	private static void crearsocio() {
		// TODO Auto-generated method stub
		try {
			Socio s = new Socio();

			System.out.println("Nombre:");
			s.setNombre(t.nextLine());
			System.out.println("Calle:");
			s.getDireccion().setCalle(t.nextLine());
			System.out.println("Nº:");
			s.getDireccion().setNumero(t.nextInt());
			t.nextLine();
			System.out.println("CP:");
			s.getDireccion().setCp(t.nextInt());
			t.nextLine();
			System.out.println("Fecha Nacicimiento (dd/MM/yy:");

			s.setFechaNac(formato.parse(t.nextLine()));

			if (!ad.crearSocio(s)) {
				System.out.println("Error al crear el socio");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Fecha incorrecta");
		}
	}

}
