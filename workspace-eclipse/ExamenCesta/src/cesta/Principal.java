package cesta;

import java.util.Scanner;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos fichero = new AccesoDatos("cesta.bin",);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opc�on:");
			System.out.println("0-Salir");
			System.out.println("1-Opci�n 1");
			System.out.println("2-Opci�n 2");
			System.out.println("3-Opci�n 3");
			System.out.println("4-Opci�n 4");
		
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					
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

