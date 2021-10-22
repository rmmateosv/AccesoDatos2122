package EjerciciosDOM;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import EjerciciosFicherosObjetos.AccesoDatosPrestamos;
import EjerciciosFicherosObjetos.Prestamo;

public class Principal {
	
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosBiblioteca datosB = new AccesoDatosBiblioteca("biblioteca.xml");
	static AccesoDatosPrestamos datosP = new AccesoDatosPrestamos("prestamos.obj");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Importar Prestamos");
			System.out.println("2-Mostrar Biblioteca");
			System.out.println("3-Crear Préstamo");
			System.out.println("4-Modificar Socio de un préstamo");
			System.out.println("5-Borrar Prestamo");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				importarPrestamos();
				break;
			}
			case 2: {
				mostrarBiblioteca();
				break;
			}
			case 3: {
				crearPrestamo();
				break;
			}
			case 4: {
				modificarSocio();
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
		//¡¡¡PASAR EL ÁRBOL DE MEMORIA A FICHERO XML!!!
		datosB.guardarArbol();
	}

	private static void modificarSocio() {
		// TODO Auto-generated method stub
		mostrarBiblioteca();
		System.out.println("Introduce el id del socio a modificar");		
		int id = t.nextInt();t.nextLine();
		
		System.out.println("Introduce el nombre del socio");
		String nombre = t.nextLine();
		
		if(!datosB.modificarSocio(id,nombre)) {
			System.out.println("Error al modificar el socio");
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		PrestamoXML p = new PrestamoXML();
		
		int id = datosB.obtenerIdPrestamo();
		if(id!=0) {
			p.setId(id);
			p.setFecha(new Date());
			System.out.println("Nombre del socio");		
			p.setSocio(t.nextLine());
			System.out.println("Título del libro");
			p.setTitulo(t.nextLine());
			if(!datosB.crearPrestamo(p)) {
				System.out.println("Error al crear el préstamo");
			}
		}
		else {
			System.out.println("Error, no se pueden crear préstamos");
		}
	}

	private static void mostrarBiblioteca() {
		// TODO Auto-generated method stub
		Biblioteca b = datosB.obtenerBiblioteca();
		if(b!=null) {
			b.mostrar();
		}
		else {
			System.out.println("Error, no existen datos");
		}
	}

	private static void importarPrestamos() {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> prestamos = datosP.obtenerPrestamos();
		if(!prestamos.isEmpty())
			if(!datosB.importar(prestamos)) {
				System.out.println("Error al importar los préstamos");
			}
	}

}
