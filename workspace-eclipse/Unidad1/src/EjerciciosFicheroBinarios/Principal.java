package EjerciciosFicheroBinarios;


import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosSocios datos = new AccesoDatosSocios("socios.bin");
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Socios");
			System.out.println("2-Crear Socio");
			System.out.println("3-Baja Socio");
			System.out.println("4-Borrar Socio");
			

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				
				break;
			}
			case 2: {
				crearSocio();
				
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

	private static void crearSocio() {
		// TODO Auto-generated method stub
		
	}

	}
