package biblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;

public class Pricipal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Crear BD Biblioteca");
			System.out.println("2-Importar Libros");
			System.out.println("3-Mostrar Libros");
			System.out.println("4-Mostrar Libro por isbn");
			System.out.println("5-Crear Socio");
			System.out.println("7-Mostrar Socios");
			System.out.println("8-Prestar Libro");
			System.out.println("9-Mostrar Préstamos");
			System.out.println("10-Mostrar Préstamos pendientes de un socio");
			System.out.println("11-Devolver Préstamos");
			System.out.println("12- Borrar Socio");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				crearBiblioteca();
				break;
			}
			case 2: {
				importarLibros();
				break;
			}
			case 3: {
				mostrarLibros();
				break;
			}
			case 4: {
				mostrarLibro();
				break;
			}
			case 5: {
				crearSocio();
				break;
			}
			case 6: {

				break;
			}

			}

		} while (opcion != 0);
		ad.cerrar();
	}

	private static void crearSocio() {
		
		// TODO Auto-generated method stub
		if(ad.getColeccion()!=null) {
			System.out.println("Nif:");
			String nif = t.nextLine();
			System.out.println("Nombre:");
			String nombre = t.nextLine();			
			
			if(!ad.crearSocio(nif, nombre)){
				System.out.println("Error al crear el socio");
			}
		
		}
		else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void mostrarLibro() {
		// TODO Auto-generated method stub
		mostrarLibros();
		if(ad.getColeccion()!=null) {
			System.out.println("Introduce ISBN:");
			String isbn = t.nextLine();
			ad.mostrarLibro(isbn);
		}
		else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		if(ad.getColeccion()!=null) {
			ad.mostrarLibros();
		}
		else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			ArrayList<Libro> libros = new AccesoDatosLibro("libros.txt").obtenerLibros();
			for (Libro l : libros) {
				if (!ad.crearLibro(l))					
				{
					System.out.println("Error al crear el libro" + l.getIsbn());
				}
			}
		} else {
			System.out.println("Error, no existe la colección biblioteca");
		}
	}

	private static void crearBiblioteca() {
		// TODO Auto-generated method stub
		// Chequear que no existe antes de crear
		if (ad.getColeccion() == null) {
			if (!ad.crearBiblioteca()) {
				System.out.println("Error, no se puede crear la colección");
			}
		} else {
			System.out.println("Error, ya existe la colección");
		}
	}

}
