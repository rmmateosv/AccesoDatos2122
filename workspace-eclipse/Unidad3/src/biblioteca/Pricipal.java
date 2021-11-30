package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Scanner;

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
			System.out.println("3-Importar Libros");
			System.out.println("5-Mostrar Libros");
			System.out.println("6-Crear Socio");
			System.out.println("7-Mostrar Socios");
			System.out.println("8-Prestar Libro");
			System.out.println("9-Mostrar Préstamos");
			System.out.println("10-Mostrar Préstamos pendientes de un socio");
			System.out.println("11-Devolver Préstamos");
			System.out.println("12- Borrar Socio");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					crearBiblioteca();
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
				case 6: {
					
					break;
				}
				
			}
			
		} while (opcion != 0);
		ad.cerrar();
	}
	private static void crearBiblioteca() {
		// TODO Auto-generated method stub
		//Chequear que no existe antes de crear
		if(ad.getColeccion()==null) {
			if(!ad.crearBiblioteca()) {
				System.out.println("Error, no se puede crear la colección");
			}
		}
		else {
			System.out.println("Error, ya existe la colección");
		}
	}

}
