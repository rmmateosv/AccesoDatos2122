package EjerciciosClaseFile;

import java.io.File;
import java.util.Scanner;

public class Ejercicio0 {
	static Scanner t = new java.util.Scanner(System.in);
	
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
			System.out.println("5-Opci�n 5");
			System.out.println("6-Opci�n 6");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					//1.-Mostrar la ruta absoluta de la carpeta 
					//en la que est� el programa java
					mostrarRuta();					
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

	private static void mostrarRuta() {
		// TODO Auto-generated method stub
		
		//Declaramos un objeto File sobre la carpeta actual
		// Podemos hacerlo de tres formas f, f1 y f2:
		
		//Con ruta relativa
		File f  = new File("Ejercicio0.java");
		
		//Con la carpeta actual
		//El . hace referencia a la carpeta actual,
				// a la carpeta en la que est� el programa
		File f1  = new File(".\\Ejercicio0.java");
		
		//Con separador portable
		File f2  = new File("."+File.pathSeparator+"Ejercicio0.java");
	
		
		System.out.println("La ruta absoluta de la carpeta acutal es:"+
							f.getAbsolutePath());
		System.out.println("La ruta absoluta de la carpeta acutal es:"+
				f1.getAbsolutePath());

		System.out.println("La ruta absoluta de la carpeta acutal es:"+
				f2.getAbsolutePath());

		
	}

}
