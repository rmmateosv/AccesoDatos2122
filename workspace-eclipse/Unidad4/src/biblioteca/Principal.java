package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opc?on:");
				System.out.println("0-Salir");				
				System.out.println("1-Crear Libro");
				System.out.println("2-Mostrar Libros");
				System.out.println("3-Crear Socio");
				System.out.println("4-Mostrar Socios");
				System.out.println("5-Prestar Libro");
				System.out.println("6-Mostrar Pr?stamos");
				System.out.println("7-Mostrar Pr?stamos pendientes de un socio");
				System.out.println("8-Devolver Pr?stamos");
				System.out.println("9- Borrar Socio");
				System.out.println("10- Estad?stica Socio");
				System.out.println("11- Estad?stica Socio2");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				
				case 1: {
					crearLibro();
					break;
				}
				case 2: {
					mostrarLibros();
					break;
				}
				case 3: {
					crearSocio();
					break;
				}
				case 4: {
					mostrarSocios();
					break;

				}
				case 5:
					crearPrestamo();
					break;
				case 6:
					mostrarPrestamos();
					break;
				case 7:
					mostrarPrestamosSocio();
					break;
				case 8:
					devolverPrestamo();
					break;
				case 9:
					borrarSocio();
					break;
				case 10:
					estadistica();
					break;
				case 11:
					estadistica2();
					break;					

				}

			} while (opcion != 0);
			ad.cerrar();
		} else {
			System.out.println("Error, no se ha podido conectar con la BD");
		}
	}

private static void estadistica2() {
		// TODO Auto-generated method stub
	List<Socio> socios = ad.obtenerSocios();
	for(Socio s:socios) {
		int noDevueltos=0;
		Date maxFecha=null, minFecha=null;
		for(Prestamo p: s.getPrestamos()) {
			if(!p.isDevuelto()) {
				noDevueltos++;
				
			}
			if(maxFecha==null  ||
			   p.getClave().getFechaP().getTime()>maxFecha.getTime()) {
				maxFecha = p.getClave().getFechaP();
			}
			if(minFecha==null  ||
			   p.getClave().getFechaP().getTime()<minFecha.getTime()) {
				minFecha = p.getClave().getFechaP();
			}
		}
		System.out.println("DNI:" + s.getDni()
				+"\tSocio:"+ s.getNombre()
				+"\tN?Pr?stamos:"+ s.getPrestamos().size()
				+"\tNoDevueltos:"+  noDevueltos
				+"\t?ltimo Pr?stamo:"+ maxFecha
				+"\tPrimer Pr?stamo:"+ minFecha);
	}
}

private static void estadistica() {
		
		// TODO Auto-generated method stub
		//Estructura de obtener datos
		//DNI,Nombre socio, N?  Pr?stamos, N? Prestamos sin devolver,
		//Fecha ?litmo Pr?stamo, Fecha del Primer Pr?stamo
		List<Object[]> datos = ad.obtenerDatos();
		for(Object[] o : datos) {
			System.out.println("DNI:" +o[0] 
			+"\tSocio:"+o[1]
			+"\tN?Pr?stamos:"+o[2]
			+"\tNoDevueltos:"+o[3]
			+"\t?ltimo Pr?stamo:"+formato.format(o[4])
			+"\tPrimer Pr?stamo:"+formato.format(o[5]));
		}
	}

	private static void borrarSocio() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Dni:");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if(s!=null && s.getPrestamos().isEmpty()) {
			if(!ad.borrarSocio(s)) {
				System.out.println("Error al borrar el socio");
			}
			
		}
		else {
			System.out.println("El socio no existe o tine pr?stamos");
		}
	}

	

	private static void devolverPrestamo() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Dni");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if (s != null) {
			
			for (Prestamo p : s.getPrestamos()) {
				if(!p.isDevuelto())
				{
					p.mostrar();
				}
			}
			System.out.println("Isbn");
			String isbn = t.nextLine();
			Prestamo pBuscado = null;
			for (Prestamo p : s.getPrestamos()) {
				//comprobamos si el isbn del libro es el buscado
				if(isbn.equals(p.getClave().getLibro().getIsbn())
						&& !p.isDevuelto()) {
					pBuscado = p;
					break;
				}
			}
			if (pBuscado != null) {				
				if (!ad.devolverPrestamo(pBuscado)) {
					System.out.println("Error, al devolver el pr?stamo");
				}
			} else {
				System.out.println("El pr?stamo no existe o est? devuelto");
			}

		}
	}

	private static void mostrarPrestamosSocio() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("DNI:");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if (s != null) {
			
			//No pendientes los pr?stamos
			for(Prestamo p:s.getPrestamos()) {
				if(p.isDevuelto()) {
					p.mostrar();
				}
			}
			System.out.println("Pr?stamos pendientes:");
			//Solamente pr?stamos pendientes
			for(Prestamo p:s.getPrestamos()) {
				if(!p.isDevuelto()) {
					p.mostrar();
				}
			}

		} else {
			System.out.println("Error, socio no existe");
		}
	}

	private static void mostrarPrestamos() {
		// TODO Auto-generated method stub
		List<Prestamo> prestamos = ad.obtenerPrestamos();
		for (Prestamo p : prestamos) {
			p.mostrar();
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Dni:");
		String dni = t.nextLine();
		Socio s = ad.obtenerSocio(dni);
		if (s != null) {
			mostrarLibros();
			System.out.println("ISBN:");
			String isbn = t.nextLine();
			Libro l = ad.obtenerLibro(isbn);
			if (l != null) {
				String mensaje = ad.registrarPrestamo(s, l);
				System.out.println(mensaje);
				//Recargar el socio para que muestre los pr?stamos
				s=ad.obtenerSocio(s.getDni());
				System.out.println("Datos del socio:");
				s.mostrar(true);
			} else {
				System.out.println("Error, libro no existe");
			}
		} else {
			System.out.println("Error, el socio no existe");
		}
	}

	private static void mostrarSocios() {
		// TODO Auto-generated method stub
		List<Socio> socios = ad.obtenerSocios();
		for (Socio s : socios) {
			s.mostrar(false);
		}
	}

	private static void crearSocio() {
		// TODO Auto-generated method stub
		try {
			System.out.println("DNI");
			String dni = t.nextLine();
			Socio s = ad.obtenerSocio(dni);
			if (s == null) {
				s = new Socio();
				s.setDni(dni);
				System.out.println("Nombre");
				s.setNombre(t.nextLine());
				System.out.println("Fecha Nacimiento(dd/MM/yy)");
				s.setFechaN(formato.parse(t.nextLine()));
				s.setActivo(true);
				if (!ad.crearSocio(s)) {
					System.out.println("Error al crear el socio");
				}
			} else {
				System.out.println("Error, socio existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		List<Libro> libros = ad.obtenerLibros();
		for (Libro l : libros) {
			l.mostrar(false);
		}
	}

	private static void crearLibro() {
		// TODO Auto-generated method stub

		try {

			System.out.println("Isbn");
			String isbn = t.nextLine();
			Libro l = ad.obtenerLibro(isbn);
			// Chequear que el libro no exista a?n
			if (l == null) {
				l = new Libro();
				l.setIsbn(isbn);
				System.out.println("T?tulo");
				l.setTitulo(t.nextLine());
				System.out.println("Autor");
				l.setAutor(t.nextLine());
				System.out.println("Fecha Lanzamiento (dd/mm/yy)");
				l.setFechaLanzamiento(formato.parse(t.nextLine()));
				System.out.println("N? de ejemplares");
				l.setNumEjemplares(t.nextInt());
				t.nextLine();
				if (!ad.crearLibro(l)) {
					System.out.println("Error al crear el libro");
				}
				else {
					System.out.println("Libro creado:");
					l.mostrar(false);
				}
			} else {
				System.out.println("Error, el libro ya existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}

	}

	

	



}
