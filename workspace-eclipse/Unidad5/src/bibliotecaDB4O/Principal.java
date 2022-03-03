package bibliotecaDB4O;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Importar LibroS");
				System.out.println("2-Crear Socio");
				System.out.println("3-Mostrar Socios");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1: {
					importarLibros();
					break;
				}
				case 2: {
					crearSocio();
					break;
				}
				case 3: {
					MostrarSocios();
					break;
				}

				}

			} while (opcion != 0);
			ad.cerrar();
		} else {
			System.out.println("Error, no se ha podido conectar con la BD");
		}
	}

	private static void MostrarSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> socios = ad.obtenerSocios();
		for(Socio s: socios) {
			s.mostrar(false);
		}
	}

	private static void crearSocio() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce DNI");
			String dni = t.nextLine();
			Socio s = ad.obtenerSocio(dni);
			if (s == null) {
				s = new Socio();
				s.setDni(dni);
				s.setActivo(true);
				System.out.println("Nombre");
				s.setNombre(t.nextLine());
				System.out.println("Introduce Fecha Nacimiento(dd/mm/yy)");

				s.setFechaN(formato.parse(t.nextLine()));

				if (!ad.crearSocio(s)) {
					System.out.println("Error al crear el socio");
				}
			} else {
				System.out.println("Error ya existe el socio");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		biblioteca.AccesoDatos adP = new biblioteca.AccesoDatos();
		ArrayList<biblioteca.Libro> libros = adP.obtenerLibros();
		for (biblioteca.Libro l : libros) {
			Libro lNuevo = new Libro(l.getIsbn(), l.getTitulo(), l.getNumEjem());
			// Rellenamos el id simulando un autonumérico
			lNuevo.setId(ad.obtenerId());
			lNuevo.setIsbn(l.getIsbn());
			lNuevo.setTitulo(l.getTitulo());
			lNuevo.setNumEjemplares(l.getNumEjem());
			if (!ad.crearLibro(lNuevo)) {
				System.out.println("Error al importar el libro " + lNuevo.getIsbn());
			}
		}
	}

}
