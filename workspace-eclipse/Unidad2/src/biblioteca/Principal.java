package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import EjerciciosFicheroBinarios.Socio;
import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Crear BD Biblioteca (ejecutar script)");
			System.out.println("2-Mostrar Metadatos");
			System.out.println("3-Importar Libros");
			System.out.println("4-Crear Libro");
			System.out.println("5-Mostrar Libros");
			System.out.println("6-Crear Socio");
			System.out.println("7-Mostrar Socios");
			
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					crearBiblioteca();
					break;
				}
				case 2: {
					metadatos();
					break;
				}
				case 3: {
					importarLibros();
					break;
				}
				case 4: {
					crearLibro();
					break;
				}
				case 5: {
					mostrarLibros();
					break;
				}
				case 6: {
					crearSocio();
					break;
				}
				case 7: {
					mostrarSocios();
					break;
				
				}
				case 8:
					crearPrestamo();
					break;
				
			}
			
		} while (opcion != 0);
		ad.cerrar();
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Dni:");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if(s!=null) {
			mostrarLibros();
			System.out.println("ISBN:");
			String isbn = t.nextLine();
			Libro l = ad.obtenerLibro(isbn);
			if(l!=null) {
				String mensaje = ad.registrarPrestamo(s,l);
				System.out.println(mensaje);
			}
			else {
				System.out.println("Error, libro no existe");
			}
		}
		else {
			System.out.println("Error, el socio no existe");
		}
	}

	private static void mostrarSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> socios = ad.obtenerSocios();
		for(Socio s:socios) {
			s.mostrar();
		}
	}

	private static void crearSocio() {
		// TODO Auto-generated method stub
		try {
			System.out.println("DNI");
			String dni = t.nextLine();
			Socio s = ad.obtenerSocio(dni);
			if(s==null) {
				s= new Socio();
				s.setDni(dni);
				System.out.println("Nombre");
				s.setNombre(t.nextLine());
				System.out.println("Fecha Nacimiento(dd/MM/yy)");
				s.setFechaN(formato.parse(t.nextLine()));
				s.setActivo(true);
				if(!ad.crearSocio(s)) {
					System.out.println("Error al crear el socio");
				}
			}
			else {
				System.out.println("Error, socio existe");
			}
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> libros = ad.obtenerLibros();
		for(Libro l:libros) {
			l.mostrar();
		}
	}

	private static void crearLibro() {
		// TODO Auto-generated method stub
				
		try {
			
			System.out.println("Isbn");
			String isbn = t.nextLine();			
			Libro l = ad.obtenerLibro(isbn);
			//Chequear que el libro no exista aún
			if(l==null) {
				l = new Libro();
				l.setIsbn(isbn);
				System.out.println("Título");
				l.setTitulo(t.nextLine());
				System.out.println("Autor");
				l.setAutor(t.nextLine());
				System.out.println("Fecha Lanzamiento (dd/mm/yy)");
				l.setFechaLanzamiento(formato.parse(t.nextLine()));
				System.out.println("Nº de ejemplares");
				l.setNumEjemplares(t.nextInt());t.nextLine();
				if(!ad.crearLibro(l)) {
					System.out.println("Error al crear el libro");
				}
			}
			else {
				System.out.println("Error, el libro ya existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
		
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		AccesoDatosLibro datosL = new AccesoDatosLibro("libros.txt");
		ArrayList<Libro> libros = datosL.obtenerLibros();
		for(Libro l:libros) {
			if(!ad.crearLibro(l)) {
				System.out.println("Error al crear el libro " + l.getIsbn());
			}
		}
	}

	private static void metadatos() {
		// TODO Auto-generated method stub
		ad.mostrarMetadatos();
	}

	private static void crearBiblioteca() {
		// TODO Auto-generated method stub
		ad.ejecutarScript();
	}

}

