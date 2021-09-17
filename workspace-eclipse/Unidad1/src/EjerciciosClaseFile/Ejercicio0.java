package EjerciciosClaseFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
					//2.- Pedir por teclado una ruta de fichero o carpeta 
					//y mostrar si lo que se ha introducido existe,
					// si es un fichero o una carpeta, 
					//la fecha de modificación y el tamaño.
					datosElemento();
					break;
				}
				case 3: {
					//Mostrar contenido carpeta
					mostrarCarpeta();
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

	private static void mostrarCarpeta() {
		// TODO Auto-generated method stub
		//Pedimos la ruta de la carpeta
		System.out.println("Introduce la ruta de una carpeta");
		String carpeta = t.nextLine();
		
		//Creamos objeto File al elemento
		File f = new File(carpeta);
		
		//Comprobamos que existe y es carpeta
		if(f.exists() && f.isDirectory()) {
			//Obtenemos el contenido de la carpeta
			String[] contenido = f.list();
			//Mostramos el contenido
			for(int i=0;i<contenido.length;i++) {
				System.out.println(contenido[i]);
				
			}
			
		}
		else {
			System.out.println("El elemento introducido no existe o no es "
					+ "una carpeta");
		}

	}

	private static void datosElemento() {
		// TODO Auto-generated method stub
		//Pedimos la ruta del elemento
		System.out.println("Introduce la ruta de un fichero o carpeta");
		String elto = t.nextLine();
		
		//Creamos objeto File al elemento
		File f = new File(elto);
		
		//Comprobar si existe
		if(f.exists()) {
			//Comprobar si es una carpeta o un fichero
			if(f.isDirectory()) {
				System.out.println("La ruta introducida es de una carpeta");
			}
			else {
				System.out.println("La ruta introducida es de un fichero");
				//Mostrar la fecha de modificación
				//Creamos un objeto con el formato de la fecha
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				//Crear un objeto fecha con la fecha de modificación del fichero
				Date fechaM = new Date(f.lastModified());
				//Mostra la fecha con el formato correcto
				System.out.println("Fecha:" + formato.format(fechaM));
				//Mostrar el tamaño
				System.out.println("Tamaño:" + f.length());
				
			}
			
		}
		else {
			System.out.println("La ruta introducida no existe");
		}
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
