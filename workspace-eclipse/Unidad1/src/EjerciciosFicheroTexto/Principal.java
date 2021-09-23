package EjerciciosFicheroTexto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
					crearLibro();
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

	private static void crearLibro() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Libro l  = new Libro();
			System.out.println("Introduce ISBN");
			l.setIsbn(t.nextLine());
			System.out.println("Introduce Título");
			l.setTitulo(t.nextLine());
			System.out.println("Introduce Autor");
			l.setAutor(t.nextLine());
			System.out.println("Introduce Fecha Lanazamiento");
			l.setFechaLanzamiento(formato.parse(t.nextLine()));
			System.out.println("Introduce Nº Ejemplares");
			l.setNumEjemplares(Integer.parseInt(t.nextLine()));
			//Guardar el libro en el fichero
			if(datos.crearLibro(l)) {
				System.out.println("Libro creado");
			}
			else {
				System.out.println("Error, al crear el libro");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
		
		
		
		
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
