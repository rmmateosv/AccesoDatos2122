package alumnos;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static String nombreFichero;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opc�on:");
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero aleatorio con notas de un alumno");
			System.out.println("2-Modificar nota");
			System.out.println("3-Opci�n 3");
			System.out.println("4-Opci�n 4");
			System.out.println("5-Opci�n 5");
			System.out.println("6-Opci�n 6");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				crearFicheroNotasAlumno();
				break;
			}
			case 2: {
				modificarNota();
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

	private static void modificarNota() {
		// TODO Auto-generated method stub
		mostrarAleatorio(nombreFichero);
		System.out.println("Introduce expediente");
		int exp = t.nextInt();
		t.nextLine();
		System.out.println("Introudce asig");
		String asig = t.nextLine();
		NotasAle n = ad.obtenerNotaAle(nombreFichero, exp, asig);
		if (n != null) {
			System.out.println("Introduce nueva nota");
			n.setNota(t.nextInt());
			t.nextLine();
			if (!ad.modificarNota(nombreFichero,n)) {
				System.out.println("error al modificar la nota");
			} else {
				mostrarAleatorio(nombreFichero);
			}
		} else {
			System.out.println("Error, nota no existe");
		}

	}

	private static void crearFicheroNotasAlumno() {
		// TODO Auto-generated method stub
		nombreFichero = ad.crearFAleatorio();
		if (nombreFichero == null) {
			System.out.println("Error, no se ha generado ficheor");
		} else {
			mostrarAleatorio(nombreFichero);
		}
	}

	private static void mostrarAleatorio(String nombreFichero) {
		// TODO Auto-generated method stub
		ArrayList<NotasAle> notas = ad.obtenerNotas(nombreFichero);
		for (NotasAle n : notas) {
			n.mostrar();
		}
	}

}
