package EjerciciosFicheroTexto;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosLibro datos = new AccesoDatosLibro("libros.txt");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Libros");
			System.out.println("2-Crear libro");
			System.out.println("3-Modificar Número de Ejemplares");
			System.out.println("4-Borrar Libro");
			System.out.println("5-Buscar Libro por ISBN");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					mostrarLibros();
					break;
				}
				case 2: {
					
					break;
				}
				case 3: {
					
					break;
				}
				case 4: {
					
					break;
				}
				case 5: {
					
					break;
				}
				
				
			}
			
		} while (opcion != 0);
		
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		
		//Rellenamos libros con los libros del fichero
		 ArrayList<Libro> libros = datos.obtenerLibros();
		
		//Mostrar libros
		for(Libro l:libros) {
			l.mostrar();
		}
	}

}
