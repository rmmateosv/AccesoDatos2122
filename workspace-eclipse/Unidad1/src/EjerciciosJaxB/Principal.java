package EjerciciosJaxB;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import EjerciciosFicheroBinarios.AccesoDatosSocios;
import EjerciciosFicheroBinarios.Socio;
import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;
import EjerciciosFicherosObjetos.AccesoDatosPrestamos;
import EjerciciosFicherosObjetos.Prestamo;

public class Principal {
	
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosBiblioteca datosB = new AccesoDatosBiblioteca("biblioJAXB.xml");
	static AccesoDatosPrestamos datosP = new AccesoDatosPrestamos("prestamos.obj");
	static AccesoDatosSocios datosS =new AccesoDatosSocios("socios.bin");
	static AccesoDatosLibro datosL =new AccesoDatosLibro("libros.txt");
	
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
				borrarPrestamo();
				break;
			}
			case 6: {
				break;
			}

			}

		} while (opcion != 0);
		//¡¡¡PASAR EL objeto biblioteca DE MEMORIA A FICHERO XML!!!
		datosB.marshal();
	}

	private static void borrarPrestamo() {
		// TODO Auto-generated method stub
		mostrarBiblioteca();
		System.out.println("Introduce el id del prestamo a borrar");		
		int id = t.nextInt();t.nextLine();
		if(!datosB.borrarPrestamo(id)) {
			System.out.println("Error,no se ha borrado el préstamo");
		}
	}

	private static void modificarSocio() {
		// TODO Auto-generated method stub
		mostrarBiblioteca();
		System.out.println("Introduce el id del préstamo a modificar");		
		int id = t.nextInt();t.nextLine();
		
		for(Socio s:datosS.obtenerSocios()) {
			s.mostrar();
		}
		System.out.println("Introduce el nif del nuevo socio");
		String nif = t.nextLine();
		Socio s = datosS.obtenerSocio(nif);
		if(s!=null) {
			if(!datosB.modificarSocio(id,s.getNombre())) {
				System.out.println("Error al modificar el socio");
			}
		}
		else {
			System.out.println("Error, no existe el socio");
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		PrestamoXML p = new PrestamoXML();
		
		int id = datosB.obtenerIdPrestamo();
		if(id!=0) {
			p.setId(id);
			p.setFecha(new Date());

			ArrayList<Libro> libros = datosL.obtenerLibros();
			for(Libro l: libros) {
				l.mostrar();
			}
			System.out.println("Isbn:");
			String isbn = t.nextLine();
			Libro l = datosL.obtenerLibro(isbn);
			if(l!=null) {
				ArrayList<Socio> socios = datosS.obtenerSocios();
				for(Socio s:socios) {
					s.mostrar();
				}
				System.out.println("Dni socio:");
				String dni = t.nextLine();
				Socio s = datosS.obtenerSocio(dni);
				if(s!=null) {
					p.setLibro(l.getTitulo());
					p.setSocio(s.getNombre());
					if(!datosB.crearPrestamo(p)) {
						System.out.println("Error al crear el préstamo");
					}
				}
				else {
					System.out.println("Error, socio no existe");
				}
				
			}
			else {
				System.out.println("Error, libro no existe");
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
		if(!prestamos.isEmpty()) {
			System.out.println("Nombre biblioteca");
			String nombre = t.nextLine();
			
			if(!datosB.importar(prestamos, nombre, new Date())) {
				System.out.println("Error al importar los préstamos");
			}
		}
	}

}
