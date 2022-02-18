package biblioteca;

import java.util.Scanner;

public class Principal {

	private static AccesoDatos ad = new AccesoDatos();
	static Scanner t = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Mostrar Socios");
				System.out.println("3-Crear Libro");
				System.out.println("4-Mostrar Libros");				
				System.out.println("5-Prestar Libro");
				System.out.println("6-Mostrar Préstamos");
				System.out.println("8-Devolver Préstamos");


				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1: {

					break;
				}
				}
			} while (opcion != 0);
			ad.cerrar();
		}
	}

}
