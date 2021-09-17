package EjerciciosClaseFile;

import java.io.File;
import java.util.Scanner;

public class Ejercicio0 {
	static Scanner t = new java.util.Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Opción 1");
			System.out.println("2-Opción 2");
			System.out.println("3-Opción 3");
			System.out.println("4-Opción 4");
			System.out.println("5-Opción 5");
			System.out.println("6-Opción 6");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					//1.-Mostrar la ruta absoluta de la carpeta 
					//en la que está el programa java
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
				// a la carpeta en la que está el programa
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
