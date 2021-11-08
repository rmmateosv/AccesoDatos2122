package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Crear BD Biblioteca (ejecutar script)");
			System.out.println("2-Mostrar Metadatos");
			System.out.println("3-Importar Libros");
			System.out.println("4-Opción 4");
			System.out.println("5-Opción 5");
			System.out.println("6-Opción 6");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					crearBiblioteca();
					break;
				}
				case 2: {
					metadatos();
					break;
				}
				case 3: {
					importarLibros();
					break;
				}
				case 4: {
					
					break;
				}
				case 5: {
					
					break;
				}
				case 6: {
					
					break;
				}
				
			}
			
		} while (opcion != 0);
		ad.cerrar();
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		AccesoDatosLibro datosL = new AccesoDatosLibro("libros.txt");
		ArrayList<Libro> libros = datosL.obtenerLibros();
		for(Libro l:libros) {
			if(!ad.crearLibro(l)) {
				System.out.println("Error al crear el libro " + l.getIsbn());
			}
		}
	}

	private static void metadatos() {
		// TODO Auto-generated method stub
		ad.mostrarMetadatos();
	}

	private static void crearBiblioteca() {
		// TODO Auto-generated method stub
		ad.ejecutarScript();
	}

}

