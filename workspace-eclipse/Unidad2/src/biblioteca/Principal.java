package biblioteca;

import java.util.Scanner;

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
			System.out.println("2-Opci�n 2");
			System.out.println("3-Opci�n 3");
			System.out.println("4-Opci�n 4");
			System.out.println("5-Opci�n 5");
			System.out.println("6-Opci�n 6");
			
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
		ad.ejecutarScript();
	}

}

