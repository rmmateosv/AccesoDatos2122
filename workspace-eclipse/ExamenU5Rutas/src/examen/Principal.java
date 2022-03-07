package examen;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {

	private static Scanner t = new java.util.Scanner(System.in);
	private static AccesoDatosPostgress adP = new AccesoDatosPostgress();
	private static AccesoDatosDB4O adD = new AccesoDatosDB4O();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (adP.getConexion() != null && adB.getConexion()) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Mostrar Socios");
				System.out.println("3-Crear Revista");
				System.out.println("4-Mostrar Libros");
				System.out.println("5-Prestar Libro");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1:

					break;

				case 2:

					break;

				case 3:

					break;

				case 4:

					break;
				case 5:

					break;

				}
			} while (opcion != 0);

		}
		else {
			System.out.println("Error al conectar con Postgress o DB4O");
		}
	}

}
