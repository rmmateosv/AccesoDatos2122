package EjerciciosDOM;

import java.util.Scanner;

import EjerciciosFicherosObjetos.AccesoDatosPrestamos;

public class Principal {
	
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosBiblioteca datosB = new AccesoDatosBiblioteca("biblioteca.xml");
	static AccesoDatosPrestamos datosP = new AccesoDatosPrestamos("prestamos.obj");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opc�on:");
			System.out.println("0-Salir");
			System.out.println("1-Importar Prestamos");
			System.out.println("2-Mostrar Prestamos");
			System.out.println("3-Crear Pr�stamo");
			System.out.println("4-Modificar fecha biblioteca");
			System.out.println("5-Modificar Socio de un pr�stamo");
			System.out.println("6-Borrar Prestamo");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				importarPrestamos();
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
	}

}
