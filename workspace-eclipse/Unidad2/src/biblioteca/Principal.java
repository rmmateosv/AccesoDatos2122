package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			System.out.println("Introduce una opc�on:");
			System.out.println("0-Salir");
			System.out.println("1-Crear BD Biblioteca (ejecutar script)");
			System.out.println("2-Mostrar Metadatos");
			System.out.println("3-Importar Libros");
			System.out.println("4-Crear Libro");
			System.out.println("5-Opci�n 5");
			System.out.println("6-Opci�n 6");
			
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
					crearLibro();
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

	private static void crearLibro() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");		
		try {
			
			System.out.println("Isbn");
			String isbn = t.nextLine();			
			Libro l = ad.obtenerLibro(isbn);
			//Chequear que el libro no exista a�n
			if(l==null) {
				l = new Libro();
				l.setIsbn(isbn);
				System.out.println("T�tulo");
				l.setTitulo(t.nextLine());
				System.out.println("Autor");
				l.setAutor(t.nextLine());
				System.out.println("Fecha Lanzamiento (dd/mm/yy)");
				l.setFechaLanzamiento(formato.parse(t.nextLine()));
				System.out.println("N� de ejemplares");
				l.setNumEjemplares(t.nextInt());t.nextLine();
				if(!ad.crearLibro(l)) {
					System.out.println("Error al crear el libro");
				}
			}
			else {
				System.out.println("Error, el libro ya existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
		
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

