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
				System.out.println("4-Crear Préstamo");
				System.out.println("5-Devolver Préstamo");
				System.out.println("6-Borrar socio");

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
				case 4: {
					CrearPrestamo();
					break;
				}
				case 5: {
					DevolverPrestamo();
					break;
				}
				case 6: {
					borrarSocio();
					break;
				}
				}

			} while (opcion != 0);
			ad.cerrar();
		} else {
			System.out.println("Error, no se ha podido conectar con la BD");
		}
	}

	private static void borrarSocio() {
		// TODO Auto-generated method stub
		MostrarSocios();
		System.out.println("Dni socio");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if(s!=null) {
			if(!ad.borrarSocio(s)) {
				System.out.println("Error al borrar el socio");
			}
		}
		else {
			System.out.println("Error socio no existe");
		}
	}

	private static void DevolverPrestamo() {
		// TODO Auto-generated method stub
		MostrarSocios();
		System.out.println("Dni socio");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if(s!=null) {
		
			mostrarPrestamosSocio(s);
			System.out.println("Id libro");
			int id = t.nextInt();t.nextLine();
			Libro l  = ad.obtenerLibro(id);
			if(l!=null) {
				//Obtener todos los préstamos del socio y del libro
				ArrayList<Prestamo> prestamos = ad.obtenerPrestamos(s,l);
				for(Prestamo p: prestamos) {
					if(!p.isDevuelto()) {
						if(!ad.devolverPrestamo(p)) {
							System.out.println("Error al devolver el préstamo");
						}
					}
				}
			}
			else {
				System.out.println("Error no existe libro");
			}
		}
		else {
			System.out.println("Socio no existe");
		}
	}

	private static void CrearPrestamo() {
		// TODO Auto-generated method stub
		MostrarSocios();
		System.out.println("Dni socio");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if(s!=null) {
			mostrarLibros();
			System.out.println("Id:");
			int idLibro = t.nextInt();t.nextLine();
			Libro l = ad.obtenerLibro(idLibro);
			if(l!=null) {
				if(l.getNumEjemplares()>=1) {
					if(!ad.crearPrestamo(s,l)) {
						System.out.println("Error al crear el préstamo");
					}
					else {
						mostrarPrestamosSocio(s);
					}
				}
				else {
					System.out.println("Error, no hay ejemplares");
				}
			}
			else {
				System.out.println("error libro no existe");
			}
		}
		else {
			System.out.println("Error, socio no existe");
		}
	}

	private static void mostrarPrestamosSocio(Socio s) {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> prestamos = ad.obtenerPrestamos(s);
		for(Prestamo p: prestamos) {
			p.mostrar();
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> libros = ad.obtenerLibros();
		for(Libro l:libros) {
			l.mostrar(false);
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
